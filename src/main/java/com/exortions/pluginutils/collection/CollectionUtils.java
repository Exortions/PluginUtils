package com.exortions.pluginutils.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CollectionUtils {

    public static List<String> stringList(String... strings) {
        List<String> ls = new ArrayList<>();
        Collections.addAll(ls, strings);
        return ls;
    }

    public static String[] createStringArray(int len, String... strings) {
        String[] array = new String[len];
        for (int i = 0; i < len; i++) {
            for (String string : strings) {
                array[i] = string;
            }
        }
        return array;
    }

}
