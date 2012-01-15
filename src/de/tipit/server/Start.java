package de.tipit.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;

import de.tipit.server.model.work.DatabaseManager;
import de.tipit.server.model.work.TransactionProxy;
import de.tipit.server.transfer.access.*;
import de.tipit.server.transfer.service.*;

public class Start {

    public static void main(String[] args) throws JAXBException {
        System.out.println("Starting services at 'localhost:9000' ...");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAManager");
        EntityManager em = emf.createEntityManager();

        DatabaseManager dbManager = new DatabaseManager(em);
        Object transProxy = TransactionProxy.newInstance(dbManager, em);

        Endpoint endpoint1 = Endpoint.publish("http://localhost:9000/analysis", new AnalysisService((Analysis) transProxy));
        Endpoint endpoint2 = Endpoint.publish("http://localhost:9000/bet_result", new BetResultService((BetResult) transProxy));
        Endpoint endpoint3 = Endpoint.publish("http://localhost:9000/community_admin", new CommunityAdminService((CommunityAdmin) transProxy));
        Endpoint endpoint4 = Endpoint.publish("http://localhost:9000/meta_data_admin", new MetaDataAdminService((MetaDataAdmin) transProxy));
        Endpoint endpoint5 = Endpoint.publish("http://localhost:9000/rules_admin", new RulesAdminService((RulesAdmin) transProxy));
        Endpoint endpoint6 = Endpoint.publish("http://localhost:9000/tourn_admin", new TournamentAdminService((TournamentAdmin) transProxy));
        Endpoint endpoint7 = Endpoint.publish("http://localhost:9000/user_session", new UserSessionService((UserSession) transProxy));

        JOptionPane.showMessageDialog(null, "Stop services?");

        System.out.println("Stopping services ...");

        endpoint7.stop();
        endpoint6.stop();
        endpoint5.stop();
        endpoint4.stop();
        endpoint3.stop();
        endpoint2.stop();
        endpoint1.stop();

        System.out.println("End.");
    }
}
