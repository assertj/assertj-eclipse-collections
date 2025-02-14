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
package org.assertj.eclipse.collections.api.multimap;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.eclipse.collections.api.multimap.Multimap;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public interface AbstractMultimapAssert_HasSizeBetween_Contract<KEY, VALUE, I extends Multimap<KEY, VALUE>, A extends AbstractMultimapAssert<A, I, KEY, VALUE>> {
    I testInput();

    I emptyInput();

    A assertion(I testInput);

    A softAssertion(SoftAssertions softAssertions, I testInput);

    Boundaries withinBoundaries();

    Boundaries withinBoundariesInclusiveUpper();

    Boundaries withinBoundariesInclusiveLower();

    Boundaries belowLowerBoundary();

    Boundaries aboveUpperBoundary();

    /**
     * Test data input that always returns null. Used for testing how assertions handle null.
     */
    default I nullInput() {
        return null;
    }

    @Test
    default void passesSizeBetween() {
        assertion(testInput()).hasSizeBetween(withinBoundaries().lowerBoundary(), withinBoundaries().upperBoundary());
    }

    @Test
    default void passesSizeBetweenInclusiveUpper() {
        assertion(testInput()).hasSizeBetween(withinBoundariesInclusiveUpper().lowerBoundary(), withinBoundariesInclusiveUpper().upperBoundary());
    }

    @Test
    default void passesSizeBetweenInclusiveLower() {
        assertion(testInput()).hasSizeBetween(withinBoundariesInclusiveLower().lowerBoundary(), withinBoundariesInclusiveLower().upperBoundary());
    }

    @Test
    default void failsSizeFallsBelowLowerBoundary() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertion(testInput()).hasSizeBetween(belowLowerBoundary().lowerBoundary(), belowLowerBoundary().upperBoundary()))
                .withMessageContaining(String.format("Expected size to be between: %s and %s but was: %s", belowLowerBoundary().lowerBoundary(), belowLowerBoundary().upperBoundary(), testInput().size()));
    }

    @Test
    default void failsSizeFallsAboveUpperBoundary() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertion(testInput()).hasSizeBetween(aboveUpperBoundary().lowerBoundary(), aboveUpperBoundary().upperBoundary()))
                .withMessageContaining(String.format("Expected size to be between: %s and %s but was: %s", aboveUpperBoundary().lowerBoundary(), aboveUpperBoundary().upperBoundary(), testInput().size()));
    }

    @Test
    default void failsNullMultimap() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertion(nullInput()).hasSizeBetween(withinBoundaries().lowerBoundary(), withinBoundaries().upperBoundary()))
                .withMessageContaining("Expecting actual not to be null");
    }

    @Test
    default void failsEmptyMultimap() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> assertion(emptyInput()).hasSizeBetween(withinBoundaries().lowerBoundary(), withinBoundaries().upperBoundary()))
                .withMessageContaining(String.format("Expected size to be between: %s and %s but was: %s", withinBoundaries().lowerBoundary(), withinBoundaries().upperBoundary(), emptyInput().size()));
    }

    @Test
    default void softAssertionPasses() {
        SoftAssertions.assertSoftly(softly -> softAssertion(softly, testInput()).hasSizeBetween(withinBoundaries().lowerBoundary(), withinBoundaries().upperBoundary()));
    }

    final class Boundaries {
        private final int lowerBoundary;
        private final int upperBoundary;

        public Boundaries(int lowerBoundary, int upperBoundary) {
            this.lowerBoundary = lowerBoundary;
            this.upperBoundary = upperBoundary;
        }

        public int lowerBoundary() {
            return lowerBoundary;
        }

        public int upperBoundary() {
            return upperBoundary;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Boundaries)) return false;
            Boundaries that = (Boundaries) o;
            return lowerBoundary == that.lowerBoundary && upperBoundary == that.upperBoundary;
        }

        @Override
        public int hashCode() {
            return Objects.hash(lowerBoundary, upperBoundary);
        }

        @Override
        public String toString() {
            return lowerBoundary + ".." + upperBoundary;
        }
    }
}
