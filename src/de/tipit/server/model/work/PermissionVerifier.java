package de.tipit.server.model.work;

import javax.persistence.EntityManager;

import de.tipit.server.model.entity.BetCommunityEO;
import de.tipit.server.model.entity.RuleBookEO;
import de.tipit.server.model.entity.UserEO;
import de.tipit.server.model.entity.UserGroupEO;
import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.data.ContextTO;

public class PermissionVerifier {

    // private final EntityManager entityManager;

    public PermissionVerifier(final EntityManager entityManager) {
        // this.entityManager = entityManager;
    }

    public void checkGeneralPermission(ContextTO context) throws GeneralError {
        // TODO
    }

    public void checkGeneralPermission(ContextTO context, UserEO user) throws GeneralError {
        // TODO
    }

    public void checkUserGroupPermission(ContextTO context, UserEO user) throws GeneralError {
        // TODO
    }

    public void checkUserGroupPermission(ContextTO context, UserEO user, UserGroupEO userGroup) throws GeneralError {
        // TODO
    }

    public void checkBetCommunityPermission(ContextTO context, UserEO user) throws GeneralError {
        // TODO
    }

    public void checkBetCommunityPermission(ContextTO context, UserEO user, BetCommunityEO betCommunity) throws GeneralError {
        // TODO
    }

    public void checkRuleBookPermission(ContextTO context, UserEO user) throws GeneralError {
        // TODO
    }

    public void checkRuleBookPermission(ContextTO context, UserEO user, RuleBookEO ruleBook) throws GeneralError {
        // TODO
    }
}
