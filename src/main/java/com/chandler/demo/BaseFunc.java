package com.chandler.demo;

import com.facebook.presto.common.type.StandardTypes;
import com.facebook.presto.spi.function.Description;
import com.facebook.presto.spi.function.ScalarFunction;
import com.facebook.presto.spi.function.SqlNullable;
import com.facebook.presto.spi.function.SqlType;
import io.airlift.slice.Slice;

public class BaseFunc  {
    @ScalarFunction(value = "is_null", calledOnNullInput = true)
    @Description("Returns TRUE if the argument is NULL")
    @SqlType(StandardTypes.BOOLEAN)
    public static boolean isNull(@SqlNullable @SqlType(StandardTypes.VARCHAR) Slice string)
    {
        return (string == null);
    }
}
