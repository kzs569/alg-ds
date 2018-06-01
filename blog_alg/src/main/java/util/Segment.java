package util;

import java.util.regex.Pattern;

public class Segment {

    String value;
    Pattern pattern;

    public Segment(String value, Pattern pattern) {
        this.value = value;
        this.pattern = pattern;
    }
}
