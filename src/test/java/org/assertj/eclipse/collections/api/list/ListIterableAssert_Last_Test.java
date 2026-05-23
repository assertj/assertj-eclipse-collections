package org.assertj.eclipse.collections.api.list;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.eclipse.collections.api.ListIterableAssert;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ListIterableAssert_Last_Test {
  @Test
  void passes() {
    assertThatNoException().isThrownBy(() -> {
      ImmutableList<String> list = Lists.immutable.of("TOS", "TNG", "DS9", "VOY", "ENT");
      new ListIterableAssert<>(list).last().isEqualTo("ENT");
    });
  }

  @Test
  void passesWithCustomAssertFactory() {
    assertThatNoException().isThrownBy(() -> {
      ImmutableList<String> list = Lists.immutable.of("TOS", "TNG", "DS9", "VOY", "ENT");
      new ListIterableAssert<>(list).last(InstanceOfAssertFactories.STRING).startsWith("E");
    });
  }

  @Test
  void throwsExceptionWhenActualIsNull() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() ->
      new ListIterableAssert<>(null).last().isEqualTo("ENT")
    ).withMessageContaining("Expecting actual not to be null");
  }
}
