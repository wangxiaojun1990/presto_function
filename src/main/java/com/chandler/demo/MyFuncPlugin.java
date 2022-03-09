package com.chandler.demo;

import com.google.common.collect.ImmutableSet;
import io.prestosql.spi.Plugin;

import java.util.Set;

public class MyFuncPlugin implements Plugin {
    @Override
    public Set<Class<?>> getFunctions() {
        return ImmutableSet.<Class<?>>builder()
                .add(BaseFunc.class)
                .add(AddressFunction.class)
                .add(PersionInfoFunction.class)
                .add(InternetFunction.class)
                .build();
    }
}
