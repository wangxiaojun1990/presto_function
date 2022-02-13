package com.chandler.demo;

import com.facebook.presto.common.type.*;
import com.facebook.presto.spi.function.*;
import com.facebook.presto.spi.function.TypeParameter;
import com.github.javafaker.Faker;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;


public class BaseFunc {

    private static Faker FAKER = new Faker(Locale.CHINA);



    @ScalarFunction(value = "datagen_int", calledOnNullInput = true)
    @Description("Returns random int value")
    @TypeParameter("T")
    @SqlNullable
    @SqlType(StandardTypes.INTEGER)
    public static Long DatagenInt(
            @SqlNullable @SqlType("T") Object seed,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        Integer result = FAKER.random().nextInt(min.intValue(), max.intValue());
        return Long.valueOf(result);

    }


    @ScalarFunction(value = "datagen_Long", calledOnNullInput = true)
    @Description("Returns random Long value")
    @TypeParameter("T")
    @SqlNullable
    @SqlType(StandardTypes.BIGINT)
    public static Long DatagenLong(
            @SqlNullable @SqlType("T") Object seed, @SqlType(StandardTypes.BOOLEAN) boolean gtzero) {
        if (gtzero) {
            return FAKER.random().nextLong(Long.MAX_VALUE - 1);
        } else {
            return FAKER.random().nextLong();
        }
    }


    @ScalarFunction(value = "datagen_string", calledOnNullInput = true)
    @Description("Returns random string value")
    @TypeParameter("T")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenString(
            @SqlNullable @SqlType("T") Object seed,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        return Slices.utf8Slice(RandomStringUtils.randomAscii(min.intValue(), max.intValue()));
    }


    @ScalarFunction(value = "datagen_decimal", calledOnNullInput = true)
    @Description("Returns random decimal value")
    @TypeParameter("T")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenDecimal(
            @SqlNullable @SqlType("T") Object seed,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long scala,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        double v = FAKER.number().randomDouble(scala.intValue(), min, max);
        return Slices.utf8Slice(Double.toString(v));
    }

    @ScalarFunction(value = "datagen_bool", calledOnNullInput = true)
    @Description("Returns random decimal value")
    @TypeParameter("T")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean DatagenBoolen(
            @SqlNullable @SqlType("T") Object seed) {
        return FAKER.random().nextBoolean();
    }


    @ScalarFunction(value = "datagen_enum", calledOnNullInput = true)
    @Description("Returns random decimal value")
    @TypeParameter("T")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenEnum(
            @SqlNullable @SqlType("T") Object seed,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice arr,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice sep) {
        String[] enums = arr.toStringUtf8().split(sep.toStringUtf8());
        return Slices.utf8Slice(enums[FAKER.random().nextInt(enums.length)]);
    }

    @ScalarFunction(value = "datagen_datetime", calledOnNullInput = true)
    @Description("Returns random datetime value")
    @TypeParameter("T")
    @SqlNullable
    @SqlType(StandardTypes.TIMESTAMP)
    public static Long DatagenDatetime(
            @SqlNullable @SqlType("T") Object seed,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice min,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice max) throws ParseException {
        SimpleDateFormat datetime_df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date st = datetime_df.parse(min.toStringUtf8());
        Date et = datetime_df.parse(max.toStringUtf8());
        return FAKER.date().between(st, et).getTime();
    }


    @ScalarFunction(value = "datagen_date", calledOnNullInput = true)
    @Description("Returns random date value")
    @TypeParameter("T")
    @SqlNullable
    @SqlType(StandardTypes.DATE)
    public static Long DatagenDate(
            @SqlNullable @SqlType("T") Object seed,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice min,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice max) throws ParseException {
        SimpleDateFormat date_df = new SimpleDateFormat("yyyy-MM-dd");
        Date st = date_df.parse(min.toStringUtf8());
        Date et = date_df.parse(max.toStringUtf8());
        Date middate = FAKER.date().between(st, et);
        return MILLISECONDS.toDays(middate.getTime());
    }

    public static void main(String[] args) {
        System.out.println(IntegerType.INTEGER.getJavaType());
        System.out.println(BooleanType.BOOLEAN.getJavaType());
        System.out.println(DecimalType.createDecimalType().getJavaType());
        System.out.println(BaseFunc.DatagenDecimal(null, 3L, 6000L, 10000L).toStringUtf8());
        System.out.println(DateType.DATE.getJavaType());
        for (int i = 0; i < 100; i++) {
            System.out.println(BaseFunc.DatagenEnum(null, Slices.utf8Slice("a,B,C,D,E,F"), Slices.utf8Slice(",")).toStringUtf8());
        }
    }
}
