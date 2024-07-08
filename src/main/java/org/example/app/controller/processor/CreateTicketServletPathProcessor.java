package org.example.app.controller.processor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.controller.EnumPath;
import org.example.app.controller.ServletPathProcessor;
import org.example.app.controller.config.TemplateConfig;
import org.example.app.dao.model.Client;
import org.example.app.dao.model.Ticket;
import org.example.app.service.ServiceFactory;
import org.example.app.service.SpaceTravelService;
import org.example.app.util.RequestMapper;
import org.thymeleaf.context.Context;

import java.io.IOException;

public class CreateTicketServletPathProcessor implements ServletPathProcessor {
    private final SpaceTravelService<Ticket> ticketService;
    private final SpaceTravelService<Client> clientService;

    public CreateTicketServletPathProcessor() {
        ticketService = ServiceFactory.getInstance().getTicketService();
        clientService = ServiceFactory.getInstance().getClientService();
    }
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateConfig templateConfig) {
        System.out.println("CreateTicketCommand process() called");
        Ticket ticket = RequestMapper.mapToEntity(req.getParameterMap(), Ticket.class);
        ticket.setClient(clientService.findById(Long.parseLong(req.getParameter("clientId"))));
        System.out.println("Before calling ticketService.create()");
        ticketService.create(ticket);
        System.out.println("After calling ticketService.create()");

        Context context = new Context();
        context.setVariable("action", "showTicket");
        context.setVariable("ticket", ticket);
        try {
            templateConfig.process("index", context, resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EnumPath getProcessorType() {
        return null;
    }
}
