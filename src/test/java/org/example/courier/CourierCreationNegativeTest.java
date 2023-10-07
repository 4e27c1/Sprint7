package org.example.courier;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CourierCreationNegativeTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    @Test
    @DisplayName ("Создание курьера с существующим ID")
    public void courierCreationSameId(){
        var courier = CourierGenerator.random();

        ValidatableResponse response = client.create(courier);
        check.createdSuccessfully(response);

        ValidatableResponse responseNew = client.create(courier);
        check.createdNotSuccessfully(responseNew);

    }
    @Test
    @DisplayName("Создание курьера не все поля заполнены")
    public void courierCreationNotAllFields(){
        var courier = CourierGenerator.generic();
        ValidatableResponse response = client.create(courier);
        check.creationWithBadRequest(response);

    }
}
