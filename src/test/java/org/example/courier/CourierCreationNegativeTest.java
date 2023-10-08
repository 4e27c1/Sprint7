package org.example.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CourierCreationNegativeTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    private int courierId;

    @After
    public void deleteCourier() {
        ValidatableResponse delete = client.delete(courierId);
        check.deletedSuccessfully(delete);
    }

    @Test
    @DisplayName ("Создание курьера с существующим ID")
    public void courierCreationSameId(){
        var courier = CourierGenerator.random();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        Credentials creds = Credentials.from(courier);

        ValidatableResponse loginResponseSuccessfully = client.login(creds);
        courierId = check.loggedIsSuccessfully(loginResponseSuccessfully);

        ValidatableResponse responseNew = client.create(courier);
        check.createdNotSuccessfully(responseNew);
    }
}
