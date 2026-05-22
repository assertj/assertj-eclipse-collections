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
package org.assertj.eclipse.collections.api.richiterable;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.assertj.eclipse.collections.api.BagAssert;
import org.assertj.eclipse.collections.api.ListIterableAssert;
import org.assertj.eclipse.collections.api.SetIterableAssert;
import org.assertj.eclipse.collections.api.StackIterableAssert;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.factory.Stacks;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

class RichIterableArgumentsProvider implements ArgumentsProvider {
  @Override
  public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) {
    return Stream.of(
      arguments(createBagAssert()),
      arguments(createListIterableAssert()),
      arguments(createSetIterableAssert()),
      arguments(createStackIterableAssert())
    );
  }

  private static RichIterableAssertFactory<Object> createBagAssert() {
    return new RichIterableAssertFactory<>(
      "Bag",
      elements -> new BagAssert<>(Bags.immutable.of(elements)),
      () -> new BagAssert<>(Bags.immutable.empty()),
      () -> new BagAssert<>(null),
      (softAssertions, elements) -> softAssertions.assertThat(Bags.immutable.of(elements))
    );
  }

  private static RichIterableAssertFactory<Object> createListIterableAssert() {
    return new RichIterableAssertFactory<>(
      "List",
      elements -> new ListIterableAssert<>(Lists.immutable.of(elements)),
      () -> new ListIterableAssert<>(Lists.immutable.empty()),
      () -> new ListIterableAssert<>(null),
      (softAssertions, elements) -> softAssertions.assertThat(Lists.immutable.of(elements))
    );
  }

  private static RichIterableAssertFactory<Object> createSetIterableAssert() {
    return new RichIterableAssertFactory<>(
      "Set",
      elements -> new SetIterableAssert<>(Sets.immutable.of(elements)),
      () -> new SetIterableAssert<>(Sets.immutable.empty()),
      () -> new SetIterableAssert<>(null),
      (softAssertions, elements) -> softAssertions.assertThat(Sets.immutable.of(elements))
    );
  }

  private static RichIterableAssertFactory<Object> createStackIterableAssert() {
    return new RichIterableAssertFactory<>(
      "Stack",
      elements -> new StackIterableAssert<>(Stacks.immutable.of(elements)),
      () -> new StackIterableAssert<>(Stacks.immutable.empty()),
      () -> new StackIterableAssert<>(null),
      (softAssertions, elements) -> softAssertions.assertThat(Stacks.immutable.of(elements))
    );
  }
}
