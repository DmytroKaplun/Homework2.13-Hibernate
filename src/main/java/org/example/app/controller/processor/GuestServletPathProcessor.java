package org.example.app.controller.processor;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.controller.EnumPath;
import org.example.app.controller.ServletPathProcessor;
import org.example.app.controller.config.TemplateConfig;
import org.thymeleaf.context.Context;

import java.io.IOException;

public class GuestServletPathProcessor implements ServletPathProcessor {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp, TemplateConfig templateConfig) {
        String name = null;

        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("name")) {
                name = cookie.getValue();
                break;
            }
        }

        Context context = new Context();
        context.setVariable("action", "showClient");
        context.setVariable("client", name);
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
