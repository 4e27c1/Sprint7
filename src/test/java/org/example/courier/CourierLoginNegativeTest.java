package org.example.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CourierLoginNegativeTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @Test
    public void courierLoginWithoutAllFields() {

        var courier = CourierGenerator.random();

        Map <String, String> logData = new HashMap<>();
        logData.put("login", courier.getLogin());
        logData.put("password", "");

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully(loginResponse);
    }
    @Test
    @DisplayName ("Логин курьера с неверным паролем")
    public void courierLoginWithWrongPass() {

        var courier = CourierGenerator.random();

        Map <String, String> logData = new HashMap<>();
        logData.put("login", courier.getLogin());
        logData.put("password", courier.getPassword()+"11");

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully404(loginResponse);
    }
    @Test
    @DisplayName("Логин курьера с неверным логином")
    public void courierLoginWithWrongLog() {

        var courier = CourierGenerator.random();

        Map <String, String> logData = new HashMap<>();
        logData.put("login", "1231");
        logData.put("password", courier.getPassword());

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully404(loginResponse);
    }
}
