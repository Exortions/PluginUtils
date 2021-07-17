package com.exortions.pluginutils.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

    public static List<String> stringList(String... strings) {
        List<String> ls = new ArrayList<>();
        Collections.addAll(ls, strings);
        return ls;
    }

}
