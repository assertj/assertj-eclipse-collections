package org.assertj.eclipse.collections.api.multimap.bag;

import static org.eclipse.collections.impl.tuple.Tuples.pair;

import java.util.Map;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_Contains_Contract;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;

public class BagMultimapAssert_Contains_Test implements AbstractMultimapAssert_Contains_Contract<String, String, BagMultimap<String, String>, BagMultimapAssert<String, String>> {
  @Override
  public BagMultimap<String, String> testInput() {
    MutableBagMultimap<String, String> multimap = Multimaps.mutable.bag.of();
    multimap.putAll("TOS", Bags.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
    multimap.putAll("TNG", Bags.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
    multimap.putAll("DS9", Bags.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
    multimap.putAll("VOY", Bags.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
    multimap.putAll("ENT", Bags.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
    return multimap;
  }

  @Override
  public BagMultimap<String, String> emptyInput() {
    return Multimaps.immutable.bag.empty();
  }

  @Override
  public BagMultimapAssert<String, String> assertion(BagMultimap<String, String> testInput) {
    return BagMultimapAssert.assertThat(testInput);
  }

  @Override
  public BagMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, BagMultimap<String, String> testInput) {
    return softAssertions.assertThat(testInput);
  }

  @Override
  public Pair<String, String>[] expectedPairs() {
    return new Pair[]{pair("TOS", "McCoy"), pair("TNG", "Crusher")};
  }

  @Override
  public Map.Entry<String, String>[] expectedEntries() {
    return new Map.Entry[]{pair("TOS", "McCoy").toEntry(), pair("TNG", "Crusher").toEntry()};
  }

  @Override
  public Pair<String, String> missingPair() {
    return pair("VOY", "Kes");
  }

  @Override
  public Map.Entry<String, String> missingEntry() {
    return missingPair().toEntry();
  }
}
