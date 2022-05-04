package org.agoncal.application.petstore.web;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Credentials {

    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String password2;

}
