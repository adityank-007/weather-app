package me.aditya.weather.filter;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import me.aditya.weather.model.UserInfo;

import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, UserInfo> {


    @Override
    public Optional<UserInfo> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        return Optional.empty();
    }
}
