package com.exortions.pluginutils.collection;

import java.util.*;

/**
 * @author Exortions
 * @since unknown
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
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

    public static String[] subArray(String[] array) {
        List<String> ls = new ArrayList<>(Arrays.asList(array));
        ls.remove(0);
        String str = "";
        for (String s : ls) {
            str = str.concat(s + " ");
        }
        str = str.substring(0, str.length()-1);
        return str.split("\\s");
    }

    public void command() {
        String[] args = null;
        String str = "";
        for (String s : args) {
            str = str.concat(s + " ");
        }
        str = str.substring(0, str.length()-1);

    }

}
