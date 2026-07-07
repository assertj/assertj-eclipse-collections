/*
 * Copyright 2025-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.assertj.eclipse.collections.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

import static java.lang.String.format;

public class ShouldHaveDistinctSizeLessThan extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldHaveDistinctSizeLessThan}</code>.
   *
   * @param actual the actual value in the failed assertion.
   * @param actualSize the distinct size of {@code actual}.
   * @param expectedMaxSize the expected distinct size.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveDistinctSizeLessThan(Object actual, int actualSize, int expectedMaxSize) {
    return new ShouldHaveDistinctSizeLessThan(actual, actualSize, expectedMaxSize);
  }

  private ShouldHaveDistinctSizeLessThan(Object actual, int actualSize, int expectedSize) {
    super(format("%n" +
        "Expecting distinct size of:%n" +
        "  %%s%n" +
        "to be less than %s but was %s", expectedSize, actualSize),
      actual);
  }
}
