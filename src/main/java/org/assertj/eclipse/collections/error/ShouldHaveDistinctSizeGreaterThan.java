/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2025-2025 the original author or authors.
 */
package org.assertj.eclipse.collections.error;

import static java.lang.String.format;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies a minimum distinct size failed.
 */
public class ShouldHaveDistinctSizeGreaterThan extends BasicErrorMessageFactory {
  /**
   * Creates a new {@link ShouldHaveDistinctSizeGreaterThan}.
   *
   * @param actual          the actual value in the failed assertion.
   * @param actualSize      the size of {@code actual}.
   * @param expectedMinSize the expected size.
   * @return the created {@code ErrorMessageFactory}.
   */
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
