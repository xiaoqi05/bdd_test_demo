package com.demo.string;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;

import org.junit.Test;

public class StringUtilsTest {

  private StringUtils stringUtils;

  @Before
  public void setUp() {
    stringUtils = new StringUtils("xx");
  }

  @Test
  public void should_get_is_empty_for_blank_string() {
    // given
    // when
    boolean result = stringUtils.isEmpty("");
    // then
    assertThat(result).isTrue();
  }

  @Test
  public void should_get_is_alpha_for_empty_string() {
    // given

    // when
    boolean isAlpha = stringUtils.isAlpha("");

    // then
    assertThat(isAlpha).isFalse();
  }

  @Test
  public void should_get_is_empty_for_null() {
    // given
    // when
    boolean result = stringUtils.isEmpty(null);
    // then
    assertThat(result).isTrue();
  }

  @Test
  public void should_get_is_blank_for_blank_string() {
    // given

    // when
    boolean isBlank = stringUtils.isBlank("");
    // then
    assertThat(isBlank).isTrue();
  }

  @Test
  public void should_get_is_blank_for_muti_blank() {
    // given

    // when
    boolean isBlank = stringUtils.isBlank("   ");
    // then
    assertThat(isBlank).isTrue();
  }

  @Test
  public void should_get_is_blank_for_null() {
    // given

    // when
    boolean isBlank = stringUtils.isBlank(null);
    // then
    assertThat(isBlank).isTrue();
  }

  @Test
  public void should_get_is_blank_for_string() {
    // given

    // when
    boolean isBlank = stringUtils.isBlank("some string");
    // then
    assertThat(isBlank).isFalse();
  }

  @Test
  public void should_get_is_alpha_for_alpha_string() {
    // given

    // when
    boolean isAlpha = stringUtils.isAlpha("xxxx");

    // then
    assertThat(isAlpha).isTrue();
  }

  @Test
  public void should_get_is_alpha_for_num_string() {
    // given

    // when
    boolean isAlpha = stringUtils.isAlpha("123");

    // then
    assertThat(isAlpha).isFalse();
  }
}
