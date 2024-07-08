package org.example.app.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.controller.config.TemplateConfig;

@WebServlet(urlPatterns = {"/","/createClientForm", "/createClient" })
public class FrontController extends HttpServlet {
    private final ServletPathProcessorFactory pathProcessorFactory = ServletPathProcessorFactory.getInstance();
    private TemplateConfig templateConfig;

    @Override
    public void init() {
        templateConfig = TemplateConfig.getInstance();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp){
        String servletPath = req.getServletPath();
        ServletPathProcessor processor = pathProcessorFactory.getProcessor(EnumPath.pathOf(servletPath));

        processor.process(req, resp, templateConfig);
    }
}
