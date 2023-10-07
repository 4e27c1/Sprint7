package org.example.order;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

import static io.restassured.RestAssured.given;

public class OrderClient {


    public static final String ORDER_CREATE_PATH = "/api/v1/orders";
    public static final String GET_ORDERS_PATH = "/api/v1/orders";

    public ValidatableResponse createOrder(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Client.BASE_URI)
                .body(order)
                .when()
                .post(ORDER_CREATE_PATH)
                .then().log().all();
    }

    public ValidatableResponse getOrderList () {

        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Client.BASE_URI)
                .when()
                .get(GET_ORDERS_PATH)
                .then().log().all();
    }
}
