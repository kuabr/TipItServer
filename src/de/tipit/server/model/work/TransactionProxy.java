package de.tipit.server.model.work;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import de.tipit.server.model.i18n.error.IllegalAccess;
import de.tipit.server.model.i18n.error.IllegalArgument;
import de.tipit.server.model.i18n.error.UnknownError;
import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class TransactionProxy implements InvocationHandler {

    private final Object databaseObject;

    private final EntityManager entityManager;

    public static Object newInstance(final Object databaseObject, final EntityManager entityManager) {
        return Proxy.newProxyInstance(databaseObject.getClass().getClassLoader(), databaseObject.getClass().getInterfaces(), new TransactionProxy(
                databaseObject, entityManager));
    }

    private TransactionProxy(final Object databaseObject, final EntityManager entityManager) {
        this.databaseObject = databaseObject;
        this.entityManager = entityManager;
    }

    private static ContextTO findContext(Object[] arguments) {
        ContextTO context = null;
        for (int i = 0; i < arguments.length; i++) {
            Object argument = arguments[i];
            if (argument != null && argument instanceof ContextTO) {
                context = (ContextTO) argument;
                break;
            }
        }
        return context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arguments) throws GeneralError {
        // start transaction
        EntityTransaction trans = entityManager.getTransaction();
        trans.begin();

        // invoke method
        Object result = null;
        try {
            synchronized (proxy) {
                result = method.invoke(databaseObject, arguments);
            }

            trans.commit(); // end transaction with success
        } catch (IllegalArgumentException exc) {
            trans.rollback(); // end transaction with error

            throw new IllegalArgument(findContext(arguments));
        } catch (IllegalAccessException exc) {
            trans.rollback(); // end transaction with error

            throw new IllegalAccess(findContext(arguments));
        } catch (InvocationTargetException exc) {
            trans.rollback(); // end transaction with error

            Throwable targetExc = exc.getTargetException();
            if (targetExc instanceof RuntimeException) {
                throw (RuntimeException) targetExc;
            } else if (targetExc instanceof GeneralError) {
                throw (GeneralError) targetExc;
            } else {
                throw new UnknownError(findContext(arguments));
            }
        }

        // return result
        return result;
    }
}
