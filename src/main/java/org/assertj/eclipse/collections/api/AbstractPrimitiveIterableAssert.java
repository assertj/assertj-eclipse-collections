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
package org.assertj.eclipse.collections.api;

import static org.assertj.core.error.ShouldBeAnArray.shouldBeAnArray;
import static org.assertj.core.error.ShouldBeEmpty.shouldBeEmpty;
import static org.assertj.core.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.assertj.core.error.ShouldHaveSameSizeAs.shouldHaveSameSizeAs;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.error.ShouldHaveSizeBetween.shouldHaveSizeBetween;
import static org.assertj.core.error.ShouldHaveSizeGreaterThan.shouldHaveSizeGreaterThan;
import static org.assertj.core.error.ShouldHaveSizeGreaterThanOrEqualTo.shouldHaveSizeGreaterThanOrEqualTo;
import static org.assertj.core.error.ShouldHaveSizeLessThan.shouldHaveSizeLessThan;
import static org.assertj.core.error.ShouldHaveSizeLessThanOrEqualTo.shouldHaveSizeLessThanOrEqualTo;
import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.eclipse.collections.util.RichIterableUtil.sizeOf;

import java.lang.reflect.Array;

import org.assertj.core.api.AbstractAssert;
import org.eclipse.collections.api.PrimitiveIterable;

/**
 * Base class for all assertions for the {@link PrimitiveIterable} interface.
 *
 * @param <SELF>   the "self" type of this assertion class. Please read &quot;<a href="https://bit.ly/1IZIRcY"
 *                 target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *                 for more details.
 * @param <ACTUAL> the type of the "actual" value.
 */
public abstract class AbstractPrimitiveIterableAssert<SELF extends AbstractPrimitiveIterableAssert<SELF, ACTUAL>, ACTUAL extends PrimitiveIterable> extends AbstractAssert<SELF, ACTUAL> {

  protected AbstractPrimitiveIterableAssert(ACTUAL actual, Class<?> selfType) {
    super(actual, selfType);
  }

  public SELF hasSameSizeAs(PrimitiveIterable other) {
    return executeAssertion(() -> {
      isNotNull();

      int otherSize = sizeOf(other);
      int actualSize = actual.size();
      if (actualSize == otherSize) {
        return;
      }

      throw assertionError(shouldHaveSameSizeAs(actual, other, actualSize, otherSize));
    });
  }

  public SELF hasSameSizeAs(Iterable<?> other) {
    return executeAssertion(() -> {
      isNotNull();

      int otherSize = sizeOf(other);
      int actualSize = actual.size();
      if (actualSize == otherSize) {
        return;
      }

      throw assertionError(shouldHaveSameSizeAs(actual, other, actualSize, otherSize));
    });
  }

  public SELF hasSameSizeAs(Object other) {
    return executeAssertion(() -> {
      isNotNull();

      if (!(other != null && other.getClass().isArray())) {
        throw assertionError(shouldBeAnArray(other));
      }

      int otherSize = Array.getLength(other);
      int actualSize = actual.size();
      if (actualSize == otherSize) {
        return;
      }

      throw assertionError(shouldHaveSameSizeAs(actual, other, actualSize, otherSize));
    });
  }

  public SELF hasSize(int expectedSize) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize == expectedSize) {
        return;
      }

      throw assertionError(shouldHaveSize(actual, actualSize, expectedSize));
    });
  }

  public SELF hasSizeBetween(int lowerBoundary, int higherBoundary) {
    return executeAssertion(() -> {
      isNotNull();

      if (!(higherBoundary >= lowerBoundary)) {
        throw new IllegalArgumentException("The higher boundary <%s> must be greater than the lower boundary <%s>.".formatted(
          higherBoundary,
          lowerBoundary));
      }

      int actualSize = actual.size();
      if (actualSize >= lowerBoundary && actualSize <= higherBoundary) {
        return;
      }

      throw assertionError(shouldHaveSizeBetween(actual, actualSize, lowerBoundary, higherBoundary));
    });
  }

  public SELF hasSizeGreaterThan(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize > boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeGreaterThan(actual, actualSize, boundary));
    });
  }

  public SELF hasSizeGreaterThanOrEqualTo(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize >= boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeGreaterThanOrEqualTo(actual, actualSize, boundary));
    });
  }

  public SELF hasSizeLessThan(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize < boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeLessThan(actual, actualSize, boundary));
    });
  }

  public SELF hasSizeLessThanOrEqualTo(int boundary) {
    return executeAssertion(() -> {
      isNotNull();

      int actualSize = actual.size();
      if (actualSize <= boundary) {
        return;
      }

      throw assertionError(shouldHaveSizeLessThanOrEqualTo(actual, actualSize, boundary));
    });
  }

  public void isEmpty() {
    executeAssertion(() -> {
      isNotNull();

      if (actual.isEmpty()) {
        return;
      }

      throw assertionError(shouldBeEmpty(actual));
    });
  }

  public SELF isNotEmpty() {
    return executeAssertion(() -> {
      isNotNull();

      if (actual.notEmpty()) {
        return;
      }

      throw assertionError(shouldNotBeEmpty());
    });
  }

  public void isNullOrEmpty() {
    executeAssertion(() -> {
      if (actual == null || actual.isEmpty()) {
        return;
      }

      throw assertionError(shouldBeNullOrEmpty(actual));
    });
  }
}
