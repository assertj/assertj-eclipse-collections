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
package org.assertj.eclipse.collections.api;

import org.assertj.core.api.AbstractSoftAssertions;
import org.assertj.core.api.SoftAssertionsProvider;
import org.opentest4j.MultipleFailuresError;

import java.util.function.Consumer;

/**
 * A soft assertions provider for Eclipse Collections assertions
 */
public class SoftAssertions extends AbstractSoftAssertions implements EclipseCollectionsSoftAssertionsProvider {
  /**
   * Creates a new {@link SoftAssertions}.
   */
  public SoftAssertions() {
    // Do nothing
  }

  /**
   * Convenience method for calling {@link EclipseCollectionsSoftAssertionsProvider#assertSoftly} for these assertion
   * types. Equivalent to {@code SoftAssertion.assertSoftly(SoftAssertions.class, softly)}.
   *
   * @param softly the Consumer containing the code that will make the soft assertions.
   *               Takes one parameter (the SoftAssertions instance used to make the assertions).
   * @throws MultipleFailuresError if possible or SoftAssertionError if any proxied assertion objects threw an {@link
   *                               AssertionError}
   */
  public static void assertSoftly(Consumer<SoftAssertions> softly) {
    SoftAssertionsProvider.assertSoftly(SoftAssertions.class, softly);
  }
}
