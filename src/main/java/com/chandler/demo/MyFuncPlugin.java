package com.chandler.demo;

import com.facebook.presto.spi.Plugin;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

public class MyFuncPlugin implements Plugin {
    @Override
    public Set<Class<?>> getFunctions() {
        return ImmutableSet.<Class<?>>builder()
                .add(BaseFunc.class)
                .add(AddressFunction.class)
                .add(PersionInfoFunction.class)
                .build();
    }
}
