package org.assertj.eclipse.collections.api.multimap.list;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_ContainsValues_Contract;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.multimap.list.ListMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Multimaps;

public class ListMultimapAssert_ContainsValues_Test implements AbstractMultimapAssert_ContainsValues_Contract<String, String, ListMultimap<String, String>, ListMultimapAssert<String, String>> {
  @Override
  public ListMultimap<String, String> testInput() {
    MutableListMultimap<String, String> multimap = Multimaps.mutable.list.of();
    multimap.putAll("TOS", Lists.immutable.of("Kirk", "Spock", "McCoy", "Scotty", "Uhura", "Sulu", "Chekov"));
    multimap.putAll("TNG", Lists.immutable.of("Picard", "Riker", "Data", "Geordi", "Troi", "Crusher", "Worf"));
    multimap.putAll("DS9", Lists.immutable.of("Sisko", "Kira", "Obrien", "Dax", "Odo", "Bashir", "Worf", "Quark", "Jake"));
    multimap.putAll("VOY", Lists.immutable.of("Janeway", "Chakotay", "Torres", "Paris", "The Doctor", "Tuvok", "Kim", "Seven"));
    multimap.putAll("ENT", Lists.immutable.of("Archer", "Trip", "Tpol", "Reed", "Hoshi", "Phlox", "Mayweather"));
    return multimap;
  }

  @Override
  public ListMultimap<String, String> emptyInput() {
    return Multimaps.immutable.list.empty();
  }

  @Override
  public ListMultimapAssert<String, String> assertion(ListMultimap<String, String> testInput) {
    return ListMultimapAssert.assertThat(testInput);
  }

  @Override
  public ListMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, ListMultimap<String, String> testInput) {
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
