package com.zh.oapi_common.stream;

import java.util.Random;
import java.util.function.Supplier;

public class PersonSupplier implements Supplier<ForList> {
    private int index = 0;
    private Random random = new Random();
    @Override
    public ForList get() {
        return new ForList(index++, "StormTestUser" + index, random.nextInt(100) * 100);
    }
}
