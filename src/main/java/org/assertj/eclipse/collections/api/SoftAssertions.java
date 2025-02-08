package org.assertj.eclipse.collections.api;

import org.assertj.core.api.AbstractSoftAssertions;
import org.assertj.core.api.SoftAssertionsProvider;

import java.util.function.Consumer;

public class SoftAssertions extends AbstractSoftAssertions implements EclipseCollectionsSoftAssertionsProvider {
  public SoftAssertions() {
    // Do nothing
  }

  public static void assertSoftly(Consumer<SoftAssertions> softly) {
    SoftAssertionsProvider.assertSoftly(SoftAssertions.class, softly);
  }
}
