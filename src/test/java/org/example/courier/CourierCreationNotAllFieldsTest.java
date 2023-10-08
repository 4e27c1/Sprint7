package org.example.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class CourierCreationNotAllFieldsTest {
    private final CourierClient client = new CourierClient();
    private final CourierAssertions check = new CourierAssertions();
    @Test
    @DisplayName("Создание курьера не все поля заполнены")
    public void courierCreationNotAllFields(){
        var courier = CourierGenerator.generic();
        ValidatableResponse response = client.create(courier);
        check.creationWithBadRequest(response);
    }
}
