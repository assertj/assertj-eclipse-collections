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
package org.assertj.eclipse.collections.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.eclipse.collections.test.ExposedTypesInPublicApiContractArchCondition.notExposeTypesInPackage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ConditionEvents;

class ExposedTypesInPublicApiContractArchConditionTest {

  private static final String FORBIDDEN_PACKAGE = "java.util.concurrent.";

  @Test
  void cleanClassProducesNoViolations() {
    assertNoViolations(ValidClass.class);
  }

  @Test
  void publicFieldWithForbiddenTypeIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenPublicField.class, "AtomicInteger");
  }

  @Test
  void protectedFieldWithForbiddenTypeIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenProtectedField.class, "AtomicInteger");
  }

  @Test
  void privateFieldWithForbiddenTypeIsNotFlagged() {
    assertNoViolations(ValidPrivateField.class);
  }

  @Test
  void publicMethodReturnTypeWithForbiddenTypeIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenReturnType.class, "AtomicInteger");
  }

  @Test
  void publicMethodParameterWithForbiddenTypeIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenMethodParameter.class, "AtomicInteger");
  }

  @Test
  void publicConstructorParameterWithForbiddenTypeIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenConstructorParameter.class, "AtomicInteger");
  }

  @Test
  void forbiddenParentClassIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenParentClass.class, "AtomicReference");
  }

  @Test
  void forbiddenInterfaceIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenInterface.class, "Callable");
  }

  @Test
  void forbiddenGenericTypeArgumentIsFlagged() {
    assertHasViolationContaining(ExposesForbiddenGenericArgumentInField.class, "AtomicInteger");
  }

  @Test
  void deeplyNestedForbiddenGenericTypeArgumentIsFlagged() {
    assertHasViolationContaining(ExposesDeeplyNestedGenericArgument.class, "AtomicInteger");
  }

  private static List<String> violationsFor(Class<?> fixture) {
    JavaClass javaClass = new ClassFileImporter().importClass(fixture);
    ConditionEvents events = ConditionEvents.Factory.create();
    notExposeTypesInPackage(FORBIDDEN_PACKAGE).check(javaClass, events);
    return events.getViolating().stream()
      .flatMap(event -> event.getDescriptionLines().stream())
      .toList();
  }

  private static void assertNoViolations(Class<?> fixture) {
    assertThat(violationsFor(fixture)).isEmpty();
  }

  private static void assertHasViolationContaining(Class<?> fixture, String expectedSnippet) {
    assertThat(violationsFor(fixture)).anyMatch(line -> line.contains(expectedSnippet));
  }

  @SuppressWarnings("unused")
  static class ValidClass {
    public String validField;

    public String validMethod(int input) {
      return String.valueOf(input);
    }
  }

  static class ExposesForbiddenPublicField {
    @SuppressWarnings("unused")
    public AtomicInteger counter;
  }

  static class ExposesForbiddenProtectedField {
    @SuppressWarnings("unused")
    protected AtomicInteger counter;
  }

  static class ValidPrivateField {
    @SuppressWarnings("unused")
    private AtomicInteger counter;

    public int value() {
      return counter == null ? 0 : counter.get();
    }
  }

  static class ExposesForbiddenReturnType {
    @SuppressWarnings("unused")
    public AtomicInteger create() {
      return new AtomicInteger();
    }
  }

  static class ExposesForbiddenMethodParameter {
    @SuppressWarnings("unused")
    public void consume(AtomicInteger value) {
      // Do nothing
    }
  }

  static class ExposesForbiddenConstructorParameter {
    @SuppressWarnings("unused")
    public ExposesForbiddenConstructorParameter(AtomicInteger value) {
      // Do nothing
    }
  }

  static class ExposesForbiddenParentClass extends AtomicReference<String> {}

  abstract static class ExposesForbiddenInterface implements Callable<String> {}

  static class ExposesForbiddenGenericArgumentInField {
    @SuppressWarnings("unused")
    public List<AtomicInteger> items;
  }

  static class ExposesDeeplyNestedGenericArgument {
    @SuppressWarnings("unused")
    public Map<String, List<Future<AtomicInteger>>> deeplyNested;
  }
}
