package org.assertj.eclipse.collections.api.multimap.set;

import static org.eclipse.collections.impl.tuple.Tuples.pair;

import org.assertj.eclipse.collections.api.SoftAssertions;
import org.assertj.eclipse.collections.api.multimap.AbstractMultimapAssert_ContainsEntry_Contract;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.multimap.set.SetMultimap;
import org.eclipse.collections.api.multimap.set.MutableSetMultimap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.collections.impl.tuple.Tuples;

class SetMultimapAssert_ContainsEntry_Test implements AbstractMultimapAssert_ContainsEntry_Contract<String, String, SetMultimap<String, String>, SetMultimapAssert<String, String>> {
    @Override
    public SetMultimap<String, String> testInput() {
        MutableSetMultimap<String, String> multimap = Multimaps.mutable.set.empty();
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
    public SetMultimapAssert<String, String> assertion(SetMultimap<String, String> testInput) {
        return SetMultimapAssert.assertThat(testInput);
    }

    @Override
    public SetMultimapAssert<String, String> softAssertion(SoftAssertions softAssertions, SetMultimap<String, String> testInput) {
        return softAssertions.assertThat(testInput);
    }

  @Override
  public Pair<String, String> expectedEntry() {
    return Tuples.pair("ENT", "Reed");
  }

    @Override
    public Pair<String, String> missingPair() {
        return pair("VOY", "Kes");
    }
}
