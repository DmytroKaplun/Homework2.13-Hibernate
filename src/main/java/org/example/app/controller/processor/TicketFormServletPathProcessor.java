package org.example.app.controller.processor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.controller.EnumPath;
import org.example.app.controller.ServletPathProcessor;
import org.example.app.controller.config.TemplateConfig;
import org.thymeleaf.context.Context;

import java.io.IOException;

public class TicketFormServletPathProcessor implements ServletPathProcessor {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateConfig templateConfig) {
        Context context = new Context();
        context.setVariable("action", "createTicket");

        String clientName = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("name".equals(cookie.getName())) {
                    clientName = cookie.getValue();
                    break;
                }
            }
        }

        context.setVariable("client", clientName);
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
