package org.example.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class CourierLoginPositiveTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @After
    public void deleteCourier(){
        ValidatableResponse delete = client.delete(courierId);
        check.deletedSuccessfully(delete);
    }

    @Test
    @DisplayName ("Создание курьера и логин")
    public void courierCreateAndLogin() {

        var courier = CourierGenerator.random();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        Credentials creds = Credentials.from(courier);

        ValidatableResponse loginResponse = client.login(creds);
        courierId = check.loggedIsSuccessfully(loginResponse);
        check.checkLoggedIdNotNull(loginResponse);
    }
}