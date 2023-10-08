package org.example.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CourierLoginNegativeTest {

    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;
    @After
    public void deleteCourier() {
        ValidatableResponse delete = client.delete(courierId);
        check.deletedSuccessfully(delete);
    }

    @Test
    public void courierLoginWithoutAllFields() {

        var courier = CourierGenerator.random();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        Credentials creds = Credentials.from(courier);

        ValidatableResponse loginResponseSuccessfully = client.login(creds);
        courierId = check.loggedIsSuccessfully(loginResponseSuccessfully);

        Map <String, String> logData = new HashMap<>();
        logData.put("login", courier.getLogin());
        logData.put("password", "");

        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully(loginResponse);

    }
    @Test
    @DisplayName ("Логин курьера с неверным паролем")
    public void courierLoginWithWrongPass() {

        var courier = CourierGenerator.random();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        Credentials creds = Credentials.from(courier);

        ValidatableResponse loginResponseSuccessfully = client.login(creds);
        courierId = check.loggedIsSuccessfully(loginResponseSuccessfully);

        Map <String, String> logData = new HashMap<>();
        logData.put("login", courier.getLogin());
        logData.put("password", courier.getPassword()+"11");

        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully404(loginResponse);
    }
    @Test
    @DisplayName("Логин курьера с неверным логином")
    public void courierLoginWithWrongLog() {

        var courier = CourierGenerator.random();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        Credentials creds = Credentials.from(courier);

        ValidatableResponse loginResponseSuccessfully = client.login(creds);
        courierId = check.loggedIsSuccessfully(loginResponseSuccessfully);

        Map <String, String> logData = new HashMap<>();
        logData.put("login", "1231");
        logData.put("password", courier.getPassword());

        ValidatableResponse loginResponse = client.loginNotAllBody(logData);
        check.loggedNotSuccessfully404(loginResponse);
    }
}
