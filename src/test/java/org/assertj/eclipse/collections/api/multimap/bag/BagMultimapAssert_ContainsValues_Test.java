package org.assertj.eclipse.collections.api.multimap.bag;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_ContainsValues_Contract;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

public class BagMultimapAssert_ContainsValues_Test implements AbstractMultimapAssert_ContainsValues_Contract<String, String, BagMultimap<String, String>, BagMultimapAssert<String, String>> {
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
  public String[] expectedValues() {
    return new String[] {"Kirk", "Picard", "Sisko", "Janeway", "Archer"};
  }

  @Override
  public String missingValue() {
    return "Kes";
  }
}
