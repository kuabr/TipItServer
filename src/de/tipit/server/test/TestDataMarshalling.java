package de.tipit.server.test;

import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import de.tipit.server.transfer.data.*;

public class TestDataMarshalling {

    public static void main(String[] args) throws JAXBException {
        Marshaller marshaller = JAXBContext.newInstance(UserDataTO.class).createMarshaller();
        UserDataTO x = new UserDataTO();
        x.setBirthday(new Date());
        x.setFullPreName("Bernd");
        x.setFullSurName("Roffmann");
        UserAccountTO y = new UserAccountTO();
        y.setPassword("jau");
        y.setUserName("bernd");
        x.setAccount(y);

        marshaller.marshal(x, System.out);
    }
}
