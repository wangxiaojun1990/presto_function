package com.chandler.demo;


import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import io.prestosql.spi.function.Description;
import io.prestosql.spi.function.ScalarFunction;
import io.prestosql.spi.function.SqlType;
import io.prestosql.spi.type.StandardTypes;

import java.util.Locale;

public class AddressFunction {
    private static final Faker FAKER = new Faker(Locale.CHINA);


    @ScalarFunction(value = "datagen_address", deterministic = false)
    @Description("Returns random address string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenAddress() {
        return Slices.utf8Slice(FAKER.address().fullAddress());
    }

    @ScalarFunction(value = "datagen_country", deterministic = false)
    @Description("Returns random address string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenCountry() {
        return Slices.utf8Slice(FAKER.address().country());
    }

    @ScalarFunction(value = "datagen_state", deterministic = false)
    @Description("Returns random address string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenState() {
        return Slices.utf8Slice(FAKER.address().state());
    }

    @ScalarFunction(value = "datagen_city", deterministic = false)
    @Description("Returns random city string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenCity() {
        return Slices.utf8Slice(FAKER.address().city());
    }

    @ScalarFunction(value = "datagen_county", deterministic = false)
    @Description("Returns random county string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenCounty() {
        return Slices.utf8Slice(FAKER.address().county());
    }

    @ScalarFunction(value = "datagen_postcode", deterministic = false)
    @Description("Returns random postcode string")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenZipCode() {
        return Slices.utf8Slice(FAKER.regexify("(0[1234567]|1[012356]|2[01234567]|3[0123456]|4[01234567]|5[1234567]|6[1234567]|7[012345]|8[013456])\\d{4}"));
    }

    public static void main(String[] args) {
        System.out.println(FAKER.address().fullAddress());
        System.out.println(FAKER.address().county());
        System.out.println(FAKER.regexify("(0[1234567]|1[012356]|2[01234567]|3[0123456]|4[01234567]|5[1234567]|6[1234567]|7[012345]|8[013456])\\d{4}"));
    }
}
