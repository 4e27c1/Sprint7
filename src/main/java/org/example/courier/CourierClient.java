package org.example.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

public class CourierClient extends org.example.Client {
    public static final String COURIER_PATH = "/api/v1/courier";

    public ValidatableResponse create(Courier courier) {
        return getRequestSpecification()
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    public ValidatableResponse login (Credentials creds) {
        return getRequestSpecification()
                .body(creds)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }
    public ValidatableResponse loginNotAllBody(Map login) {
        return getRequestSpecification()
                .body(login)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    public ValidatableResponse delete(int courierId) {
        return getRequestSpecification()
                .body(Map.of("id", String.valueOf(courierId)))
                .when()
                .delete(COURIER_PATH + "/" + courierId)
                .then().log().all();
    }
}

