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
package org.assertj.eclipse.collections.api.multimap.set;

import org.assertj.core.api.Condition;
import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_HasValueSatisfying_Contract;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

class SetMultimapAssert_HasValueSatisfying_Test implements AbstractMultimapAssert_HasValueSatisfying_Contract<String, String, SetMultimap<String, String>, SetMultimapAssert<String, String>> {
  @Override
  public SetMultimapAssert<String, String> assertion(SetMultimap<String, String> testInput) {
    return SetMultimapAssert.assertThat(testInput);
  }

  @Override
  public SetMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, SetMultimap<String, String> testInput) {
    return softAssertions.assertThat(testInput);
  }

  @Override
  public SetMultimap<String, String> testInput() {
    MutableSetMultimap<String, String> multimap = Multimaps.mutable.set.of();
    multimap.putAll("TOS", Sets.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
    multimap.putAll("TNG", Sets.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
    multimap.putAll("DS9", Sets.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
    multimap.putAll("VOY", Sets.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
    multimap.putAll("ENT", Sets.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
    return multimap;
  }

  @Override
  public SetMultimap<String, String> emptyInput() {
    return Multimaps.immutable.set.empty();
  }

  @Override
  public Condition<String> passingCondition() {
    return new Condition<>(value -> value.equals("Janeway"), "value equals Janeway");
  }

  @Override
  public Condition<String> failingCondition() {
    return new Condition<>(value -> value.equals("Kes"), "value equals Kes");
  }
}
