package me.aditya.weather.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;
import java.util.List;

@Data
@AllArgsConstructor
public class UserInfo implements Principal {
    private final String userName;
    private final String password;
    private final String city;
    private final List<String> roles;


    @Override
    public String getName() {
        return this.getUserName();
    }
}
