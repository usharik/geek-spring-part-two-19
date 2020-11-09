package ru.geekbrains.chat;

import java.security.Principal;

public class ChatPrincipal implements Principal {

    private String name;

    public ChatPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
