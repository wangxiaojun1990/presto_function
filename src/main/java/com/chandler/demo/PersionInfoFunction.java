package com.chandler.demo;

import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.*;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import java.util.Locale;

public class PersionInfoFunction {
    private static Faker FAKER = new Faker(Locale.CHINA);

    @ScalarFunction(value = "datagen_name", calledOnNullInput = true)
    @Description("Returns random postcode string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenName(@SqlNullable @SqlType("T") Object value) {
       return Slices.utf8Slice(FAKER.name().name());
    }

    @ScalarFunction(value = "datagen_phone", calledOnNullInput = true)
    @Description("Returns random postcode string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenPhoneNumber(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.phoneNumber().cellPhone());

    }

    @ScalarFunction(value = "datagen_ssn", calledOnNullInput = true)
    @Description("Returns random postcode string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenSsn(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(FAKER.regexify("[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}([0-9]|X)"));
    }

    public static void main(String[] args) {
        System.out.println(FAKER.name().name());
        System.out.println(FAKER.regexify("[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}([0-9]|X)"));
        System.out.println(FAKER.internet().emailAddress());
        System.out.println(FAKER.internet().emailAddress("kye"));
    }
}
