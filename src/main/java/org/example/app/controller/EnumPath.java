package org.example.app.controller;

import lombok.Getter;

@Getter
public enum EnumPath {
    HOME_PAGE("/"),
    CLIENT_FORM("/createClientForm"),
    CREATE_CLIENT("/createClient"),
    CREATE_TICKET("/createTicket"),
    TICKET_FORM("/createTicketForm");

    private String path;

    EnumPath(String path) {
        this.path = path;
    }

    public static EnumPath pathOf(String incomePath) {
        for (EnumPath v : values()) {
            if (v.path.equalsIgnoreCase(incomePath)) return v;
        }
        throw new IllegalArgumentException("No such path: " + incomePath);
    }
}
