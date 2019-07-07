package com.demo.string;

public class StringUtils {

    private String value;

    public StringUtils(final String value) {
        this.value = value;
    }

    public boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public boolean isBlank(final CharSequence charSequence) {
        int strLen;
        if (charSequence == null || (strLen = charSequence.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAlpha(final CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        final int sz = charSequence.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLetter(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
