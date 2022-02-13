package com.chandler.demo;

import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.*;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import java.util.Locale;

public class AddressFunction {
    private static Faker FAKER = new Faker(Locale.CHINA);


    @ScalarFunction(value = "datagen_address", calledOnNullInput = true)
    @Description("Returns random address string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenAddress(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.address().fullAddress());
    }

    @ScalarFunction(value = "datagen_country", calledOnNullInput = true)
    @Description("Returns random address string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenCountry(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.address().country());
    }

    @ScalarFunction(value = "datagen_state", calledOnNullInput = true)
    @Description("Returns random address string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenState(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.address().state());
    }

    @ScalarFunction(value = "datagen_city", calledOnNullInput = true)
    @Description("Returns random city string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenCity(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.address().city());
    }

    @ScalarFunction(value = "datagen_county", calledOnNullInput = true)
    @Description("Returns random county string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenCounty(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.address().county());
    }

    @ScalarFunction(value = "datagen_postcode", calledOnNullInput = true)
    @Description("Returns random postcode string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenZipCode(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.regexify("(0[1234567]|1[012356]|2[01234567]|3[0123456]|4[01234567]|5[1234567]|6[1234567]|7[012345]|8[013456])\\d{4}"));
    }

    public static void main(String[] args) {
        System.out.println(FAKER.address().fullAddress());
        System.out.println(FAKER.address().county());
        System.out.println(FAKER.regexify("(0[1234567]|1[012356]|2[01234567]|3[0123456]|4[01234567]|5[1234567]|6[1234567]|7[012345]|8[013456])\\d{4}"));
    }
}
