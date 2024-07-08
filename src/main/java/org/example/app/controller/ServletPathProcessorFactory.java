package org.example.app.controller;


import org.example.app.controller.processor.*;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServletPathProcessorFactory {
    private static ServletPathProcessorFactory INSTANCE;
    private Map<EnumPath, ServletPathProcessor> processorMap;

    public ServletPathProcessorFactory() {
        processorMap = Stream.of(
                new CreateClientServletPathProcessor(),
                new ClientFormServletPathProcessor(),
                new GuestServletPathProcessor(),
                new TicketFormServletPathProcessor(),
                new CreateTicketServletPathProcessor()).collect(Collectors.toMap(
                        ServletPathProcessor::getProcessorType, Function.identity()));

    }

    public ServletPathProcessor getProcessor(EnumPath processorType) {
        return processorMap.get(processorType);
    }

    public static ServletPathProcessorFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServletPathProcessorFactory();
        }
        return INSTANCE;
    }
}
