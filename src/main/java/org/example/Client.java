package org.example;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Client {
    public static final String BASE_URI = "http://qa-scooter.praktikum-services.ru";

    public RequestSpecification getRequestSpecification () {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }
}
