package org.example.app.controller.config;

import lombok.Getter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.io.IOException;

public class TemplateConfig {
    private static TemplateConfig INSTANCE;
    private static TemplateEngine templateEngine;

    private TemplateConfig() {
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
    }

    private static ITemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    public static TemplateConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TemplateConfig();
        }
        return INSTANCE;
    }

    public void process(String templateName, Context context,
                        jakarta.servlet.http.HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process(templateName, context, response.getWriter());
    }
}
