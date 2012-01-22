package de.tipit.server.transfer.access.community_admin;

import org.simpleframework.xml.Element;

import de.tipit.server.transfer.access.CommunityAdmin;
import de.tipit.server.transfer.access.CommunityAdminTask;
import de.tipit.server.transfer.access.GeneralError;
import de.tipit.server.transfer.access.InvocationResult;
import de.tipit.server.transfer.access.ResultData;
import de.tipit.server.transfer.data.BetCommunityDataArgumentTO;
import de.tipit.server.transfer.data.BetCommunityIdTO;
import de.tipit.server.transfer.data.ContextTO;
import de.tipit.server.transfer.data.SessionIdTO;

public class CreateOrUpdateBetCommunity implements CommunityAdminTask {

    public static class Result implements ResultData {

        @Element(required = true, name = "betCommunityId")
        private final BetCommunityIdTO betCommunityId;

        @Override
        public Object getValue() {
            return betCommunityId;
        }

        public Result(@Element(name = "betCommunityId") BetCommunityIdTO betCommunityId) {
            this.betCommunityId = betCommunityId;
        }

        public BetCommunityIdTO getBetCommunityId() {
            return betCommunityId;
        }
    }

    @Element(required = true)
    private ContextTO context;

    @Element(required = true)
    private SessionIdTO sessionId;

    @Element(required = true)
    private BetCommunityDataArgumentTO betCommunityData;

    @Override
    public InvocationResult execute(CommunityAdmin delegate) {
        try {
            return new InvocationResult(new Result(delegate.createOrUpdateBetCommunity(context, sessionId, betCommunityData)));
        } catch (GeneralError exc) {
            return new InvocationResult(exc);
        } catch (RuntimeException exc) {
            return new InvocationResult(exc);
        }
    }

    public ContextTO getContext() {
        return context;
    }

    public void setContext(ContextTO context) {
        this.context = context;
    }

    public SessionIdTO getSessionId() {
        return sessionId;
    }

    public void setSessionId(SessionIdTO sessionId) {
        this.sessionId = sessionId;
    }

    public BetCommunityDataArgumentTO getBetCommunityData() {
        return betCommunityData;
    }

    public void setBetCommunityData(BetCommunityDataArgumentTO betCommunityData) {
        this.betCommunityData = betCommunityData;
    }
}
