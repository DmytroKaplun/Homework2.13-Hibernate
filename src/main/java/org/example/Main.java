package org.example;

import org.example.app.dao.model.Client;
import org.example.app.dao.model.Planet;
import org.example.app.dao.model.Ticket;
import org.example.app.service.ServiceFactory;
import org.example.app.service.SpaceTravelService;
import org.example.app.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        SpaceTravelService<Client> clientService = ServiceFactory.getInstance().getClientService();
        Client client = clientService.findById(5L);
//        client.setName("Charlie Manson");

        client.getTickets().forEach(System.out::println);


        SpaceTravelService<Ticket> ticketService = ServiceFactory.getInstance().getTicketService();
        Ticket ticket = Ticket.builder()
                .client(client)
                .fromPlanet(Planet.MARS)
                .toPlanet(Planet.URANUS)
                .build();
//        ticketService.create(ticket);
//        ticketService.delete(34L);
//        System.out.println(ticketService.findById(35L));
//        ticket.setToPlanet(Planet.JUPITER);
//        ticketService.update(ticket);

        HibernateUtil.getInstance().close();
    }
}