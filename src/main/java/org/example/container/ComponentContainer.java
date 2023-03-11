package org.example.container;

import org.example.dto.User;
import org.springframework.stereotype.Component;

@Component
public class ComponentContainer {
    public static User user = new User();
}
