package com.chandler.demo;

import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.Description;
import com.facebook.presto.spi.function.ScalarFunction;
import com.facebook.presto.spi.function.SqlType;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakerIDN;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class InternetFunction {
    private static final Faker FAKER = new Faker();


    @ScalarFunction(value = "datagen_ipv4", deterministic = false)
    @Description("Returns random ipv4 string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenIpv4() {
        return Slices.utf8Slice(FAKER.internet().ipV4Address());
    }


    @ScalarFunction(value = "datagen_ipv6", deterministic = false)
    @Description("Returns random ipv6 string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenIpv6() {
        return Slices.utf8Slice(FAKER.internet().ipV6Address());
    }

    @ScalarFunction(value = "datagen_mac", deterministic = false)
    @Description("Returns random mac string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenMac() {
        return Slices.utf8Slice(FAKER.internet().macAddress());
    }

    @ScalarFunction(value = "datagen_url", deterministic = false)
    @Description("Returns random url string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenUrl() {
        return Slices.utf8Slice(FAKER.internet().url());
    }

    @ScalarFunction(value = "datagen_image_url", deterministic = false)
    @Description("Returns random image url string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenImageUrl() {
        return Slices.utf8Slice(FAKER.internet().image());
    }

    @ScalarFunction(value = "datagen_useragent", deterministic = false)
    @Description("Returns random useragent string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenUserAgent() {
        return Slices.utf8Slice(FAKER.internet().userAgentAny());
    }



    @ScalarFunction(value = "datagen_email", deterministic = false)
    @Description("Returns random email string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenEmail() {
        return Slices.utf8Slice(FAKER.internet().emailAddress());
    }


    public static void main(String[] args) {
        System.out.println(FAKER.internet().ipV4Address());
        System.out.println(FAKER.internet().ipV6Address());
        System.out.println(FAKER.internet().macAddress());
        System.out.println(FAKER.internet().url());
        System.out.println(FAKER.internet().emailAddress());
        System.out.println(FAKER.internet().userAgentAny());
        System.out.println(FAKER.internet().image());
//        for (int i = 0; i < 100; i++) {
//            System.out.println(FAKER.internet().image());
//
//        }

    }
}
