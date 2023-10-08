package org.example.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ParameterizedOrderTest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] colour;
    private static final String[] BLACK = {"BLACK"};
    private static final String[] GRAY = {"GRAY"};
    private static final String[] BLACK_AND_GRAY = {"BLACK", "GRAY"};
    private static final String[] UNDEFINED_COLOUR = {};


    public  ParameterizedOrderTest (String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] colour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] orderData() {
        return new Object[][]{
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", BLACK},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", GRAY},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", BLACK_AND_GRAY},
                {"Naruto", "Uchiha", "Konoha, 142 apt.", 4, "+7 800 355 35 35", 5, "2020-06-06", "Saske, come back to Konoha", UNDEFINED_COLOUR},
        };
    }
    private final OrderClient client = new OrderClient();
    private final OrderAssertions check = new OrderAssertions();
    @Test
    @DisplayName("Параметризованный тест создания заказа")
    public void paramOrderCreateTest(){
        var order = new Order(firstName, lastName, address, metroStation,
                phone, rentTime, deliveryDate, comment, colour);
        ValidatableResponse response = client.createOrder(order);
        check.assertCreatedOrderSuccessfully(response);
    }
}
