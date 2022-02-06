package com.chandler.demo;

import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.*;
import com.github.javafaker.Faker;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;

import java.util.Locale;


public class BaseFunc  {

    private static Faker faker = new Faker(Locale.CHINA);

    @ScalarFunction(value = "is_null", calledOnNullInput = true)
    @Description("Returns TRUE if the argument is NULL")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNull(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice string) {
        return (string == null);
    }


    @ScalarFunction(value = "datagen_address", calledOnNullInput = true)
    @Description("Returns random address string")
    @TypeParameter("T")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenAddress(@SqlNullable @SqlType("T") Object value) {
        return Slices.utf8Slice(faker.address().fullAddress());
    }



}
