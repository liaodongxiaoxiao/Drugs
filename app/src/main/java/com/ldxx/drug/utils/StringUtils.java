package com.ldxx.drug.utils;

public class StringUtils {
    private String str;
    private String start;
    private String end;
    private StringBuffer sb = new StringBuffer();

    public StringUtils(String str, String start, String end) {
        this.str = str;
        this.start = start;
        this.end = end;
    }

    public String getResult() {
        return sb.toString();
    }

    public void make() {

        if (str.indexOf(start) > 0 && str.indexOf(end) > 0) {
            sb.append(str.substring(0, str.indexOf(start)));
            //sb.append(str.substring(str.indexOf(end) + 4, str.length()));
            str = str.substring(str.indexOf(end) + 4, str.length());
            make();
        } else {
            sb.append(str);
        }
    }
}
