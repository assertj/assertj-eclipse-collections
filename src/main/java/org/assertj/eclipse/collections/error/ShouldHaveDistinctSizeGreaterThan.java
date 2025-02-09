package org.assertj.eclipse.collections.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

import static java.lang.String.format;

public class ShouldHaveDistinctSizeGreaterThan extends BasicErrorMessageFactory {
  public static ErrorMessageFactory shouldHaveDistinctSizeGreaterThan(Object actual, int actualSize, int expectedMinSize) {
    return new ShouldHaveDistinctSizeGreaterThan(actual, actualSize, expectedMinSize);
  }

  private ShouldHaveDistinctSizeGreaterThan(Object actual, int actualSize, int expectedSize) {
    super(format("%n" +
        "Expecting distinct size of:%n" +
        "  %%s%n" +
        "to be greater than %s but was %s", expectedSize, actualSize),
      actual);
  }
}
