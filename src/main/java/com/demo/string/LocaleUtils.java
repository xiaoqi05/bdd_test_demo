package com.demo.string;

import java.util.Locale;

public class LocaleUtils {

    private static final String EMPTY = "";
    private static final String INVALID_LOCALE_FORMAT = "Invalid locale format: ";
    private static final int THREE = 3;
    private static final char UNDERLINED_CHAR = '_';
    private static final int FIVE = 5;
    private static final int BEGIN_INDEX = 4;

    public Locale toLocale(final String str) {
        if (str == null) {
            return null;
        }
        // LANG-941 - JDK 8 introduced an empty locale where all fields are blank
        if (str.isEmpty()) {
            return new Locale(EMPTY, EMPTY);
        }
        // LANG-879 - Cannot handle Java 7 script & extensions
        if (str.contains("#")) {
            throw new IllegalArgumentException(INVALID_LOCALE_FORMAT + str);
        }
        final int len = str.length();
        if (len < 2) {
            throw new IllegalArgumentException(INVALID_LOCALE_FORMAT + str);
        }
        final char ch0 = str.charAt(0);
        if (ch0 == UNDERLINED_CHAR) {
            if (len < THREE) {
                throw new IllegalArgumentException(INVALID_LOCALE_FORMAT + str);
            }
            final char ch1 = str.charAt(1);
            final char ch2 = str.charAt(2);
            if (!Character.isUpperCase(ch1) || !Character.isUpperCase(ch2)) {
                throw new IllegalArgumentException(INVALID_LOCALE_FORMAT + str);
            }
            if (len == THREE) {
                return new Locale(EMPTY, str.substring(1, THREE));
            }
            if (len < FIVE) {
                throw new IllegalArgumentException(INVALID_LOCALE_FORMAT + str);
            }
            if (str.charAt(THREE) != UNDERLINED_CHAR) {
                throw new IllegalArgumentException(INVALID_LOCALE_FORMAT + str);
            }
            return new Locale(EMPTY, str.substring(1, THREE), str.substring(BEGIN_INDEX));
        }

        return parseLocale(str);
    }

    private Locale parseLocale(final String str) {
        if (isIso639LanguageCode(str)) {
            return new Locale(str);
        }

        final String[] segments = str.split("_", -1);
        final String language = segments[0];
        boolean iso639LanguageCode = isIso639LanguageCode(language);
        if (segments.length == 2) {
            final String country = segments[1];
            if (iso639LanguageCode && isIso3166CountryCode(country)
                    || isNumericAreaCode(country)) {
                return new Locale(language, country);
            }
        } else if (segments.length == THREE) {
            final String country = segments[1];
            final String variant = segments[2];
            if (iso639LanguageCode && (country.length() == 0
                    || isIso3166CountryCode(country) || isNumericAreaCode(country))
                    && variant.length() > 0) {
                return new Locale(language, country, variant);
            }
        }
        throw new IllegalArgumentException(INVALID_LOCALE_FORMAT + str);
    }

    private boolean isIso639LanguageCode(final String str) {
        return isAllLowerCase(str) && (str.length() == 2 || str.length() == THREE);
    }

    private boolean isIso3166CountryCode(final String str) {
        return isAllUpperCase(str) && str.length() == 2;
    }

    private boolean isNumericAreaCode(final String str) {
        return isNumeric(str) && str.length() == THREE;
    }

    private boolean isAllLowerCase(final CharSequence charSequence) {
        if (charSequence == null || isEmpty(charSequence)) {
            return false;
        }
        final int sz = charSequence.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLowerCase(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllUpperCase(final CharSequence charSequence) {
        if (charSequence == null || isEmpty(charSequence)) {
            return false;
        }
        final int sz = charSequence.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isUpperCase(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    private boolean isNumeric(final CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        final int sz = charSequence.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
