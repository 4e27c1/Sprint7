package org.example.courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static Courier generic () {
        var courier = new Courier("", "1234", "saske");
        return courier;
    }

    public static Courier random() {
        return new Courier(RandomStringUtils.randomAlphanumeric(5, 10), "1234", "saske");
    }
}
