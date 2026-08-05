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
package org.assertj.eclipse.collections.api.arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.assertj.eclipse.collections.test.ExposedTypesInPublicApiContractArchCondition.notExposeTypesInPackage;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;

public class PublicApiExposedTypesTest {
  @Test
  void publicApiDoesNotExposeEclipseCollectionsImplTypes() {
    JavaClasses importedClasses = new ClassFileImporter().importPackages("org.assertj.eclipse.collections");
    ArchRule rule = classes().that()
      .resideInAnyPackage(
        "org.assertj.eclipse.collections.api..",
        "org.assertj.eclipse.collections.error..",
        "org.assertj.eclipse.collections.util.."
      )
      .should(notExposeTypesInPackage("org.eclipse.collections.impl.")
        .as("not expose types contained in package org.eclipse.collections.impl"));

    rule.check(importedClasses);
  }
}
