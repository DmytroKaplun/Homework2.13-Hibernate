package org.example.app.controller.processor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.controller.EnumPath;
import org.example.app.controller.ServletPathProcessor;
import org.example.app.controller.config.TemplateConfig;
import org.example.app.dao.model.Client;
import org.example.app.service.ServiceFactory;
import org.example.app.service.SpaceTravelService;
import org.example.app.util.RequestMapper;
import org.thymeleaf.context.Context;

import java.io.IOException;

public class CreateClientServletPathProcessor implements ServletPathProcessor {
    private final SpaceTravelService<Client> clientService;

    public CreateClientServletPathProcessor() {
        this.clientService = ServiceFactory.getInstance().getClientService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateConfig templateConfig) {
        Client client = RequestMapper.mapToEntity(req.getParameterMap(), Client.class);
        clientService.create(client);

        resp.addCookie(new Cookie("name", client.getName()));
        resp.addCookie(new Cookie("id", String.valueOf(client.getId())));

        Context context = new Context();
        context.setVariable("action", "showClient");
        context.setVariable("client", client.getName());
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
