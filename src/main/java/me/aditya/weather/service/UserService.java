package me.aditya.weather.service;

import lombok.AllArgsConstructor;
import me.aditya.weather.database.dao.UserDAO;

@AllArgsConstructor
public class UserService {
    private UserDAO userDAO;
}
