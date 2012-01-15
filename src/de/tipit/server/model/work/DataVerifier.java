package de.tipit.server.model.work;

import javax.persistence.EntityManager;

import de.tipit.server.model.i18n.error.BaseError;
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

    void checkData(ContextTO context, UserDataTO userData) throws BaseError {
        // TODO
    }

    void checkData(ContextTO context, UserGroupDataTO userGroupData) throws BaseError {
        // TODO
    }

    void checkData(ContextTO context, BetCommunityDataArgumentTO betCommunityData) throws BaseError {
        // TODO
    }

    void checkData(ContextTO context, RuleBookDataTO ruleBookData) throws BaseError {
        // TODO
    }
}
