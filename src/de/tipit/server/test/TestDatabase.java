package de.tipit.server.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.tipit.server.model.entity.UserEO;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserDataTO;

public class TestDatabase {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAManager");
        EntityManager em = emf.createEntityManager();

        try {
            Query q;

            //
            q = em.createQuery("select o from java.lang.Object o");
            for (Object o : q.getResultList()) {
                System.out.println(">>> " + o.toString());
            }

            //
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            System.out.println();

            //
            try {
                //
                UserEO user = new UserEO();
                user.setUserName("bernd");
                user.setAdmin(true);
                user.setBirthday(new Date());
                user.setEncryptedPasswd(UserAccountTO.encryptPassword("bla"));
                user.setFullPreName("Bernd");
                user.setFullSurName("Roffmann");
                user.setGender(UserDataTO.Gender.MALE);
                user.setMailAddress("b.roffmann@t-online.de");
                user.setUserName("shit");
                user.setCreation(new Date());
                em.persist(user);
                em.refresh(user);
                System.out.println("User: " + user.toString());
                System.out.println("User sessions object: " + user.getSessions().getClass());

                //
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
            }

            //
            q = em.createQuery("select o from java.lang.Object o");
            for (Object o : q.getResultList()) {
                System.out.println(">>> " + o.toString());
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}
