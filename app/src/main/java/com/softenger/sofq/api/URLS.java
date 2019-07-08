package com.softenger.sofq.api;

public interface URLS {

    String BASE_URL = "http://192.168.25.113:";
    //String BASE_URL = "http://192.168.8.106:";

    String PORT = "9092";
    //String PORT = "9090";

    String NODEJS_PORT = "3000";

    String URL_DIVIDER = "/";

    String URL_LOGIN = BASE_URL + PORT + URL_DIVIDER + "login";

    String URL_FORGOT_PASSWORD = BASE_URL + PORT + URL_DIVIDER + "forgotPassword";

    String URL_GET_USER_LIST = BASE_URL + PORT + URL_DIVIDER + "users";

    String URL_GET_PRIVILEGES = BASE_URL + PORT + URL_DIVIDER + "previlages";

    String URL_CHANGE_PASSWORD = BASE_URL + PORT + URL_DIVIDER + "changePassword";

    String URL_ADMIN_CHANGE_PASSWORD = URL_CHANGE_PASSWORD + URL_DIVIDER + "admin";

    String URL_GET_PRIVILEGES_LIST = BASE_URL + PORT + URL_DIVIDER + "roles";

    String URL_GET_ROLE_LIST = BASE_URL + PORT + URL_DIVIDER + "roles";

    String URL_BLOCKCHAIN_GET_ASSET = BASE_URL + NODEJS_PORT + URL_DIVIDER + "api/User";

    String URL_BLOCKCHAIN_SET_ASSET = BASE_URL + NODEJS_PORT + URL_DIVIDER + "api/Login";

    String URL_BLOCKCHAIN_TRANSACTION = BASE_URL + NODEJS_PORT + URL_DIVIDER + "api/LoginTransaction";

    String URL_BLOCKCHAIN_SET_PARTICIPANT = BASE_URL + NODEJS_PORT + URL_DIVIDER + "api/User";

}
