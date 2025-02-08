package org.assertj.eclipse.collections.api.multimap.bag;

import org.assertj.core.api.Condition;
import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_HasKeySatisfying_Contract;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.api.multimap.bag.BagMultimap;
import org.eclipse.collections.api.multimap.bag.MutableBagMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

class BagMultimapAssert_HasKeySatisfying_Test implements AbstractMultimapAssert_HasKeySatisfying_Contract<String, String, BagMultimap<String, String>, BagMultimapAssert<String, String>> {
  @Override
  public BagMultimapAssert<String, String> assertion(BagMultimap<String, String> testInput) {
    return BagMultimapAssert.assertThat(testInput);
  }

  @Override
  public BagMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, BagMultimap<String, String> testInput) {
    return softAssertions.assertThat(testInput);
  }

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
  public Condition<String> passingCondition() {
    return new Condition<>(value -> value.equals("DS9"), "key equals DS9");
  }

  @Override
  public Condition<String> failingCondition() {
    return new Condition<>(value -> value.equals("DIS"), "value equals DIS");
  }
}
