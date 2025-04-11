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

import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.impl.factory.Multimaps;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class MultimapTestData {
  public static Stream<Arguments> emptyMultimaps() {
    return Stream.of(
      arguments(Multimaps.mutable.bag.empty()),
      arguments(Multimaps.mutable.list.empty()),
      arguments(Multimaps.mutable.set.empty())
    );
  }

  public static Stream<Arguments> nonEmptyMultimaps() {
    return Stream.of(
      arguments(mutableBagMultimap()),
      arguments(mutableListMultimap()),
      arguments(mutableSetMultimap())
    );
  }

  public static Stream<Arguments> sizeUpperBoundaryTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 50),
      arguments(mutableListMultimap(), 50),
      arguments(mutableSetMultimap(), 50)
    );
  }

  public static Stream<Arguments> sizeLowerBoundaryTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 5),
      arguments(mutableListMultimap(), 5),
      arguments(mutableSetMultimap(), 5)
    );
  }

  public static Stream<Arguments> sizeEqualsTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 38),
      arguments(mutableListMultimap(), 38),
      arguments(mutableSetMultimap(), 38)
    );
  }

  public static Stream<Arguments> sizeBetweenTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 25, 50),
      arguments(mutableListMultimap(), 25, 50),
      arguments(mutableSetMultimap(), 25, 50)
    );
  }

  public static Stream<Arguments> sizeBetweenInclusiveUpperTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 25, 38),
      arguments(mutableListMultimap(), 25, 38),
      arguments(mutableSetMultimap(), 25, 38)
    );
  }

  public static Stream<Arguments> sizeBetweenInclusiveLowerTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 38, 50),
      arguments(mutableListMultimap(), 38, 50),
      arguments(mutableSetMultimap(), 38, 50)
    );
  }

  public static Stream<Arguments> sizeBelowLowerBoundaryTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 50, 57),
      arguments(mutableListMultimap(), 50, 57),
      arguments(mutableSetMultimap(), 50, 57)
    );
  }

  public static Stream<Arguments> sizeAboveUpperBoundaryTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 25, 32),
      arguments(mutableListMultimap(), 25, 32),
      arguments(mutableSetMultimap(), 25, 32)
    );
  }

  public static Stream<Arguments> emptyMultimapsWithExpectedSize() {
    return Stream.of(
      arguments(Multimaps.mutable.bag.empty(), 38),
      arguments(Multimaps.mutable.list.empty(), 38),
      arguments(Multimaps.mutable.set.empty(), 38)
    );
  }

  public static Stream<Arguments> distinctSizeEqualsTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 5),
      arguments(mutableListMultimap(), 5),
      arguments(mutableSetMultimap(), 5)
    );
  }

  public static Stream<Arguments> emptyMultimapsWithExpectedDistinctSize() {
    return Stream.of(
      arguments(Multimaps.mutable.bag.empty(), 5),
      arguments(Multimaps.mutable.list.empty(), 5),
      arguments(Multimaps.mutable.set.empty(), 5)
    );
  }

  public static Stream<Arguments> distinctSizeLowerBoundaryTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 2),
      arguments(mutableListMultimap(), 2),
      arguments(mutableSetMultimap(), 2)
    );
  }

  public static Stream<Arguments> distinctSizeUpperBoundaryTestData() {
    return Stream.of(
      arguments(mutableBagMultimap(), 10),
      arguments(mutableListMultimap(), 10),
      arguments(mutableSetMultimap(), 10)
    );
  }

  public static BagMultimap<String, String> mutableBagMultimap() {
    MutableBagMultimap<String, String> multimap = Multimaps.mutable.bag.of();
    multimap.putAll("TOS", Bags.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
    multimap.putAll("TNG", Bags.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
    multimap.putAll("DS9", Bags.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
    multimap.putAll("VOY", Bags.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
    multimap.putAll("ENT", Bags.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
    return multimap;
  }

  public static ListMultimap<String, String> mutableListMultimap() {
    MutableListMultimap<String, String> multimap = Multimaps.mutable.list.of();
    multimap.putAll("TOS", Lists.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
    multimap.putAll("TNG", Lists.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
    multimap.putAll("DS9", Lists.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
    multimap.putAll("VOY", Lists.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
    multimap.putAll("ENT", Lists.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
    return multimap;
  }

  public static SetMultimap<String, String> mutableSetMultimap() {
    MutableSetMultimap<String, String> multimap = Multimaps.mutable.set.of();
    multimap.putAll("TOS", Sets.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
    multimap.putAll("TNG", Sets.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
    multimap.putAll("DS9", Sets.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
    multimap.putAll("VOY", Sets.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
    multimap.putAll("ENT", Sets.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
    return multimap;
  }

  public static Stream<Arguments> shipMultimaps() {
    return Stream.of(
      arguments(mutableBagShipMultimap()),
      arguments(mutableListShipMultimap()),
      arguments(mutableSetShipMultimap())
    );
  }

  public static BagMultimap<String, String> mutableBagShipMultimap() {
    MutableBagMultimap<String, String> multimap = Multimaps.mutable.bag.of();
    multimap.put("TNG", "Enterprise");
    multimap.put("DS9", "Deep Space Nine");
    multimap.put("DS9", "Defiant");
    multimap.put("VOY", "Voyager");
    return multimap;
  }

  public static ListMultimap<String, String> mutableListShipMultimap() {
    MutableListMultimap<String, String> multimap = Multimaps.mutable.list.of();
    multimap.put("TNG", "Enterprise");
    multimap.put("DS9", "Deep Space Nine");
    multimap.put("DS9", "Defiant");
    multimap.put("VOY", "Voyager");
    return multimap;
  }

  public static SetMultimap<String, String> mutableSetShipMultimap() {
    MutableSetMultimap<String, String> multimap = Multimaps.mutable.set.of();
    multimap.put("TNG", "Enterprise");
    multimap.put("DS9", "Deep Space Nine");
    multimap.put("DS9", "Defiant");
    multimap.put("VOY", "Voyager");
    return multimap;
  }
}
