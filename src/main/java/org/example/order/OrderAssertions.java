package org.example.order;

import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;


public class OrderAssertions {

    public void assertCreatedOrderSuccessfully(ValidatableResponse response) {
        int track = response
                .assertThat()
                .statusCode(201)
                .extract()
                .path("track");

        assert track != 0;
    }

    public void statusCodeIsOk (ValidatableResponse response) {
        response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
        ;
    }
}
