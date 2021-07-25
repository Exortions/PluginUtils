package com.exortions.pluginutils.string;

/**
 * @author Exortions
 * @since 0.4.24.23
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public class StringUtils {

    public static String capitalizeString(String string) {
        String tmp = "";
        int i = 0;
        for (char chr : string.toCharArray()) {
            i++;
            String s = String.valueOf(chr);
            if (i == 1) {
                tmp = tmp.concat(s.toUpperCase());
            } else {
                tmp = tmp.concat(s);
            }
        }
        return tmp;
    }

    public static String capitalizeWords(String string) {
        String[] strings = string.split("\\s");
        String tmp;
        String fnl = "";
        int i = 0;
        for (String s : strings) {
            tmp = "";
            int i1 = 0;
            for (char chr : s.toCharArray()) {
                i1++;
                String str = String.valueOf(chr);
                if (i1 == 1) {
                    tmp = tmp.concat(str.toUpperCase());
                } else {
                    tmp = tmp.concat(str);
                }
            }
            fnl = fnl.concat(tmp);
            fnl = fnl.concat(" ");
        }
        return fnl.substring(0, fnl.length() - 1);
    }

}
