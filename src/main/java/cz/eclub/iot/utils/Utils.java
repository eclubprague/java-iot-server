package cz.eclub.iot.utils;

import org.apache.commons.lang3.StringEscapeUtils;

public class Utils {
    public static String escape(String s){
        return StringEscapeUtils.escapeHtml4(s);
    }
}
