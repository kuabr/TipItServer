package de.tipit.server.model.work;

import javax.persistence.EntityManager;

import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.BetCommunityDataArgumentTO;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.RuleBookDataTO;
import de.tipit.server.transfer.data.UserDataTO;
import de.tipit.server.transfer.data.UserGroupDataTO;

public class DataVerifier {

    // private final EntityManager entityManager;

    public DataVerifier(final EntityManager entityManager) {
        // this.entityManager = entityManager;
    }

    void checkData(ContextTO context, UserDataTO userData) throws GeneralError {
        // TODO
    }

    void checkData(ContextTO context, UserGroupDataTO userGroupData) throws GeneralError {
        // TODO
    }

    void checkData(ContextTO context, BetCommunityDataArgumentTO betCommunityData) throws GeneralError {
        // TODO
    }

    void checkData(ContextTO context, RuleBookDataTO ruleBookData) throws GeneralError {
        // TODO
    }
}
