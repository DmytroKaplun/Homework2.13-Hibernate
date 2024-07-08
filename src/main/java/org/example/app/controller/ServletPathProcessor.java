package org.example.app.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.controller.config.TemplateConfig;

public interface ServletPathProcessor {
    void process(HttpServletRequest req, HttpServletResponse resp, TemplateConfig templateConfig);

    EnumPath getProcessorType();
}
