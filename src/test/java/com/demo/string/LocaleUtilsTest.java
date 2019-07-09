package com.demo.string;

import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class LocaleUtilsTest {

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
}
