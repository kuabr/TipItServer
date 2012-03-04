package de.tipit.server.model.data;

import java.util.Date;

import javax.persistence.EntityManager;

import de.tipit.server.model.entity.UserEO;
import de.tipit.server.model.work.DatabaseCharger;
import de.tipit.server.transfer.data.UserAccountTO;
import de.tipit.server.transfer.data.UserDataTO;

public class TestUser implements DatabaseCharger {

    @Override
    public void add(EntityManager entityManager) {
        UserEO newUser = new UserEO();

        // set user data
        newUser.setUserName("test");
        newUser.setEncryptedPasswd(UserAccountTO.encryptPassword("1234"));
        newUser.setMailAddress("test@tipit.de");
        newUser.setFullPreName("Fred");
        newUser.setFullSurName("Hans");
        newUser.setGender(UserDataTO.Gender.MALE);
        newUser.setBirthday(new Date());
        newUser.setAdmin(false);
        newUser.setGuest(false);
        newUser.setCreation(new Date());

        // add new user
        entityManager.persist(newUser);
        entityManager.flush();
        entityManager.refresh(newUser);
    }
}
