package com.demo.string;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class LocaleUtilsTest {

    private static final Locale LOCALE_EN = new Locale("en", "");
    private static final Locale LOCALE_EN_US = new Locale("en", "US");
    private static final Locale LOCALE_EN_US_ZZZZ = new Locale("en", "US", "ZZZZ");
    private static final Locale LOCALE_FR = new Locale("fr", "");
    private static final Locale LOCALE_FR_CA = new Locale("fr", "CA");
    private static final Locale LOCALE_QQ = new Locale("qq", "");
    private static final Locale LOCALE_QQ_ZZ = new Locale("qq", "ZZ");
    private LocaleUtils localeUtils;

    @Before
    public void setUp() {
        this.localeUtils = new LocaleUtils();
    }

    @Test
    public void should_get_null_when_to_locale_with_null() {
        //given
        //when
        Locale locale = localeUtils.toLocale(null);
        //then
        assertThat(locale).isNull();
    }

    @Test
    public void should_get_an_empty_locale_where_all_fields_are_blank() {
        //given
        String blankStr = "";
        Locale emptyLocale = new Locale("", "");
        //when
        Locale locale = localeUtils.toLocale(blankStr);
        //then
        assertThat(locale).isEqualTo(emptyLocale);
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_not_handle_java_7_script_and_extensions() {
        //given
        String specialStr = "x#x";
        //when
        localeUtils.toLocale(specialStr);
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_when_input_str_length_below_2() {
        //given
        String shortStr = "1";
        //when
        localeUtils.toLocale(shortStr);
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_when_input_str_length_below_3_and_start_with_underlined() {
        //given
        String shortStr = "_1";
        //when
        localeUtils.toLocale(shortStr);
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_when_input_str_length_equal_4_and_start_with_underlined() {
        //given
        String fourLengthStr = "_AAA";
        //when
        localeUtils.toLocale(fourLengthStr);
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_for_special_str_length_above_4_and_start_with_underlined() {
        //given
        String fourLengthStr = "_AAaa";
        //when
        localeUtils.toLocale(fourLengthStr);
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_when_lower_case_in_2_index_and_start_with_underlined() {
        //given
        String inputStr = "_aA";
        //when
        localeUtils.toLocale(inputStr);
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_when_lower_case_in_3_index_and_start_with_underlined() {
        //given
        String inputStr = "_Aa";
        //when
        localeUtils.toLocale(inputStr);
        //then
    }

    @Test
    public void should_get_locale_for_alpha_3_language_code_and_start_with_underlined() {
        //given
        String inputStr = "_EN";
        Locale localeEn = new Locale("", "EN");
        //when
        Locale locale = localeUtils.toLocale(inputStr);
        //then
        assertThat(locale).isEqualTo(localeEn);
    }

    @Test
    public void should_get_locale_for_with_two_part() {
        //given
        String inputStr = "_EN_US";
        Locale localeEn = new Locale("", "EN", "US");
        //when
        Locale locale = localeUtils.toLocale(inputStr);
        //then
        assertThat(locale).isEqualTo(localeEn);
    }

    @Test
    public void should_get_local_for_iso639_language_code() {
        //given
        String inputStr = "en";
        //when
        Locale locale = localeUtils.toLocale(inputStr);
        //then
        assertThat(locale).isEqualTo(Locale.ENGLISH);
    }

    @Test
    public void should_get_local_for_iso639_language_code_with_alpha_3() {
        //given
        String inputStr = "ens";
        Locale expected = new Locale(inputStr);
        //when
        Locale locale = localeUtils.toLocale(inputStr);
        //then
        assertThat(locale).isEqualTo(expected);
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_for_country_not_uppercase() {
        //given
        //when
        localeUtils.toLocale("en_Us");
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_for_country_not_uppercase_with_empty_variant() {
        //given
        //when
        localeUtils.toLocale("en_CA_");
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_for_language_not_uppercase() {
        //given
        //when
        localeUtils.toLocale("EN_us");
        //then
    }

    @Test (expected = IllegalArgumentException.class)
    public void should_throw_exception_for_not_underscore() {
        //given
        //when
        localeUtils.toLocale("en-Us");
        //then
    }

    @Test
    public void should_get_locale_for_numeric_area_code_country() {
        //given
        Locale numericAreaCodeLocale = new Locale("en", "490");
        //when
        Locale locale = localeUtils.toLocale("en_490");
        //then
        assertThat(locale).isEqualTo(numericAreaCodeLocale);
    }

    @Test
    public void should_get_locale_for_iso3166_country_code_and_variant() {
        //given
        Locale iso3166Locale = new Locale("en", "CA", "CB");
        //when
        Locale locale = localeUtils.toLocale("en_CA_CB");
        //then
        assertThat(locale).isEqualTo(iso3166Locale);
    }

}
