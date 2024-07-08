package org.example.app.service;

import org.example.app.dao.SpaceTravelDao;
import org.example.app.dao.model.Client;
import org.example.app.dao.model.Ticket;
import org.example.app.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class ServiceFactory {
    private final SessionFactory sessionFactory;
    private final SpaceTravelService<Client> clientService;
    private final SpaceTravelService<Ticket> ticketService;

    private ServiceFactory() {
        sessionFactory = HibernateUtil.getInstance().getSessionFactory();
        clientService = new SpaceTravelService<>(new SpaceTravelDao<>(Client.class, sessionFactory));
        ticketService = new SpaceTravelService<>(new SpaceTravelDao<>(Ticket.class, sessionFactory));
    }

    public SpaceTravelService<Client> getClientService() {
        return clientService;
    }

    public SpaceTravelService<Ticket> getTicketService() {
        return ticketService;
    }

    private static final class InstanceHolder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }
}
