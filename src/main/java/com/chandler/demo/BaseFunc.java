package com.chandler.demo;

import com.github.javafaker.Faker;
import io.airlift.slice.Slice;
import io.airlift.slice.Slices;
import io.prestosql.spi.function.Description;
import io.prestosql.spi.function.ScalarFunction;
import io.prestosql.spi.function.SqlNullable;
import io.prestosql.spi.function.SqlType;
import io.prestosql.spi.type.StandardTypes;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;


public class BaseFunc {

    private static final Faker FAKER = new Faker(Locale.CHINA);


    @ScalarFunction(value = "datagen_int", deterministic = false)
    @Description("Returns random int value")
    @SqlNullable
    @SqlType(StandardTypes.INTEGER)
    public static Long DatagenInt(
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        Integer result = FAKER.random().nextInt(min.intValue(), max.intValue());
        return Long.valueOf(result);

    }


    @ScalarFunction(value = "datagen_Long", deterministic = false)
    @Description("Returns random Long value")
    @SqlNullable
    @SqlType(StandardTypes.BIGINT)
    public static Long DatagenLong(@SqlType(StandardTypes.BOOLEAN) boolean gtzero) {
        if (gtzero) {
            return FAKER.random().nextLong(Long.MAX_VALUE - 1);
        } else {
            return FAKER.random().nextLong();
        }
    }


    @ScalarFunction(value = "datagen_alphabetic", deterministic = false)
    @Description("Returns random alphabetic string value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenAlphabetic(
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        return Slices.utf8Slice(RandomStringUtils.randomAlphabetic(min.intValue(), max.intValue()));
    }

    @ScalarFunction(value = "datagen_alphanumeric", deterministic = false)
    @Description("Returns random alphanumeric string value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenAlphanumeric(
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        return Slices.utf8Slice(RandomStringUtils.randomAlphanumeric(min.intValue(), max.intValue()));
    }

    @ScalarFunction(value = "datagen_numeric", deterministic = false)
    @Description("Returns random numeric string value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenNumeric(
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        return Slices.utf8Slice(RandomStringUtils.randomNumeric(min.intValue(), max.intValue()));
    }

    @ScalarFunction(value = "datagen_ascii", deterministic = false)
    @Description("Returns random ascii string value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenAscii(
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        return Slices.utf8Slice(RandomStringUtils.randomAscii(min.intValue(), max.intValue()));
    }

    @ScalarFunction(value = "datagen_decimal", deterministic = false)
    @Description("Returns random decimal string value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenDecimal(
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long scala,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long min,
            @SqlNullable @SqlType(StandardTypes.INTEGER) Long max) {
        double v = FAKER.number().randomDouble(scala.intValue(), min, max);
        return Slices.utf8Slice(Double.toString(v));
    }

    @ScalarFunction(value = "datagen_bool", deterministic = false)
    @Description("Returns random boolean value")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean DatagenBoolen() {
        return FAKER.random().nextBoolean();
    }


    @ScalarFunction(value = "datagen_enum", deterministic = false)
    @Description("Returns random enum value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenEnum(
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice arr,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice sep) {
        String[] enums = arr.toStringUtf8().split(sep.toStringUtf8());
        return Slices.utf8Slice(enums[FAKER.random().nextInt(enums.length)]);
    }

    @ScalarFunction(value = "datagen_datetime", deterministic = false)
    @Description("Returns random datetime value")
    @SqlNullable
    @SqlType(StandardTypes.TIMESTAMP)
    public static Long DatagenDatetime(
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice min,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice max) throws ParseException {
        SimpleDateFormat datetime_df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date st = datetime_df.parse(min.toStringUtf8());
        Date et = datetime_df.parse(max.toStringUtf8());
        return FAKER.date().between(st, et).getTime();
    }


    @ScalarFunction(value = "datagen_date", deterministic = false)
    @Description("Returns random date value")
    @SqlNullable
    @SqlType(StandardTypes.DATE)
    public static Long DatagenDate(
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice min,
            @SqlNullable @SqlType(StandardTypes.VARCHAR) Slice max) throws ParseException {
        SimpleDateFormat date_df = new SimpleDateFormat("yyyy-MM-dd");
        Date st = date_df.parse(min.toStringUtf8());
        Date et = date_df.parse(max.toStringUtf8());
        Date middate = FAKER.date().between(st, et);
        return MILLISECONDS.toDays(middate.getTime());
    }

    @ScalarFunction(value = "datagen_uuid", deterministic = false)
    @Description("Returns random uuid value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenUUID() {
        return Slices.utf8Slice(UUID.randomUUID().toString());
    }

    @ScalarFunction(value = "datagen_md5", deterministic = false)
    @Description("Returns random md5 value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenMd5() {
        return Slices.utf8Slice(FAKER.crypto().md5());
    }

    @ScalarFunction(value = "datagen_sha256", deterministic = false)
    @Description("Returns random sha256 value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenSha256() {
        return Slices.utf8Slice(FAKER.crypto().sha256());
    }

    @ScalarFunction(value = "datagen_sha1", deterministic = false)
    @Description("Returns random sha1 value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenSha1() {
        return Slices.utf8Slice(FAKER.crypto().sha1());
    }

    @ScalarFunction(value = "datagen_sha512", deterministic = false)
    @Description("Returns random sha512 value")
    @SqlNullable
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenSha512() {
        return Slices.utf8Slice(FAKER.crypto().sha512());
    }

    @ScalarFunction(value = "datagen_numberify", deterministic = false)
    @Description("Returns random numberify value")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenNumerify(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice value) {
        return Slices.utf8Slice(FAKER.numerify(value.toStringUtf8()));
    }

    @ScalarFunction(value = "datagen_letterify", deterministic = false)
    @Description("Returns random letterify value")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice DatagenLetterify(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice value,
                                         @SqlNullable @SqlType(StandardTypes.BOOLEAN) Boolean isUpper) {
        return Slices.utf8Slice(FAKER.letterify(value.toStringUtf8(), isUpper));
    }


    public static void main(String[] args) {
//        System.out.println(IntegerType.INTEGER.getJavaType());
//        System.out.println(BooleanType.BOOLEAN.getJavaType());
//        System.out.println(RandomStringUtils.randomNumeric(20, 30));
//        System.out.println(RandomStringUtils.randomAlphabetic(20, 30));
//        System.out.println(RandomStringUtils.randomAlphanumeric(20, 30));
//        System.out.println(DecimalType.createDecimalType().getJavaType());
//        System.out.println(FAKER.random().nextLong(Long.MAX_VALUE - 1));
//        System.out.println("----------------------------------------------");
//        System.out.println(UUID.randomUUID().toString());
//        System.out.println(FAKER.crypto().md5());
//        System.out.println(FAKER.crypto().sha1());
//        System.out.println(FAKER.crypto().sha256());
//        System.out.println(FAKER.crypto().sha512());
//        System.out.println(FAKER.numerify("LC_#########".toString()));
//        System.out.println(Slices.utf8Slice(FAKER.numerify("LC_#########".toString())));
//        System.out.println(VarcharType.VARCHAR.getJavaType());
    }
}
