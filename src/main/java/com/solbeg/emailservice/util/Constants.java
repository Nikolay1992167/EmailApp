package com.solbeg.emailservice.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {

    public static String USER_FROM_SEND = "kiril.bondarev.96@mail.ru";
    public static String USER_ADMIN = "wulik1081@mail.ru";
    public static String SUBJECT_ACTIVATION = "Journalist Access Request";
    public static String SUBJECT_EXPIRATION = "Bad request to the system";
    public static String SUBJECT_WELCOME = "Welcome to the System";
    public static String MESSAGE_TOKEN_EXPIRATION = "Hello, your token is expired.";
    public static String MESSAGE_ACTIVATION = "A journalist named %s %s has requested access to the system. To activate their account, please click the following link: <a href=\"%s\">Activate account</a>";
    public static String MESSAGE_WELCOME = "Hello %s %s, We have confirmed your access to our system, and you can now log in.";

}
