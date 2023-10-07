package org.example.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class OrderTest {
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();

    @Test
    @DisplayName ("Создание заказа положительный тест")
    @Description("Создание заказа")
    public void orderCreatePositiveTest() {

        var order = new Order("Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", new String[]{"black"});

        ValidatableResponse response = client.createOrder(order);
        check.assertCreatedOrderSuccessfully(response);
    }

}
