package de.tipit.server.test;

import de.tipit.server.client.ContextTO;
import de.tipit.server.client.Language;
import de.tipit.server.client.LoginParameterTO;
import de.tipit.server.client.SessionTO;
import de.tipit.server.client.UserAccountTO;
import de.tipit.server.client.UserSessionServiceService;
import de.tipit.server.client.UserSessionService;

public class TestClient {

    public static void main(String[] args) {
        UserSessionServiceService service = new UserSessionServiceService();
        UserSessionService userSessionService = service.getUserSessionPort();

        ContextTO context = new ContextTO();
        context.setLanguage(Language.DE);

        UserAccountTO userAccount = new UserAccountTO();
        userAccount.setUserName("test");
        userAccount.setPassword("1234");

        LoginParameterTO loginParameter = new LoginParameterTO();

        SessionTO session = userSessionService.doLogin(context, userAccount, loginParameter);
        System.out.println("SESSION-ID: " + session.getSessionId());
    }
}
