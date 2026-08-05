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

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaCodeUnit;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.domain.JavaParameterizedType;
import com.tngtech.archunit.core.domain.JavaType;
import com.tngtech.archunit.core.domain.properties.HasModifiers;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * An ArchUnit condition that checks if the given package's types are exposed in the public API contract. The public
 * contract refers to the following:
 *
 * <p>
 *   <ul>
 *     <li>Types used as a parent class</li>
 *     <li>Types used as an interface</li>
 *     <li>Types used as a public or protected field type</li>
 *     <li>Types used as a constructor argument</li>
 *     <li>Types used as a method argument or return type</li>
 *   </ul>
 * </p>
 *
 * <p>These checks include parameterized types/generics.</p>
 */
public final class ExposedTypesInPublicApiContractArchCondition extends ArchCondition<JavaClass> {
  private final String packageName;

  /**
   * Entry point for using this condition.
   *
   * @param packageName The package name to check for exposed types.
   * @return An instance of ExposedTypesInPublicApiContractArchCondition.
   */
  public static ExposedTypesInPublicApiContractArchCondition notExposeTypesInPackage(String packageName) {
    return new ExposedTypesInPublicApiContractArchCondition(packageName, "not expose types contained in package %s", packageName);
  }

  private ExposedTypesInPublicApiContractArchCondition(String packageName, String description, Object... args) {
    super(description, args);
    this.packageName = packageName;
  }

  @Override
  public void check(JavaClass item, ConditionEvents events) {
    checkParentClass(item, events, packageName);
    checkInterfaces(item, events, packageName);
    checkFields(item, events, packageName);
    checkConstructors(item, events, packageName);
    checkMethods(item, events, packageName);
  }

  private static void checkParentClass(JavaClass javaClass, ConditionEvents events, String packageName) {
    javaClass.getRawSuperclass()
      .ifPresent(parentClass -> checkType(javaClass, parentClass, events, packageName, "parent class"));
  }

  private static void checkInterfaces(JavaClass javaClass, ConditionEvents events, String packageName) {
    javaClass.getRawInterfaces().stream()
      .filter(ExposedTypesInPublicApiContractArchCondition::isPublicOrProtected)
      .forEach(interFaceType -> checkType(javaClass, interFaceType, events, packageName, "interface"));
  }

  private static void checkFields(JavaClass javaClass, ConditionEvents events, String packageName) {
    javaClass.getFields().stream()
      .filter(ExposedTypesInPublicApiContractArchCondition::isPublicOrProtected)
      .forEach(field -> checkType(javaClass, field.getType(), events, packageName, "field " + field.getFullName()));
  }

  private static void checkConstructors(JavaClass javaClass, ConditionEvents events, String packageName) {
    javaClass.getConstructors().stream()
      .filter(ExposedTypesInPublicApiContractArchCondition::isPublicOrProtected)
      .forEach(constructor -> checkParameterTypes(javaClass, constructor, events, packageName));
  }

  private static void checkMethods(JavaClass javaClass, ConditionEvents events, String packageName) {
    javaClass.getMethods().stream()
      .filter(ExposedTypesInPublicApiContractArchCondition::isPublicOrProtected)
      .forEach(method -> {
        checkType(javaClass, method.getReturnType(), events, packageName, "return type of " + method.getFullName());
        checkParameterTypes(javaClass, method, events, packageName);
      });
  }

  private static void checkParameterTypes(JavaClass owner, JavaCodeUnit codeUnit, ConditionEvents events, String packageName) {
    codeUnit.getParameterTypes()
      .forEach(parameterType -> checkType(owner, parameterType, events, packageName, "parameter of " + codeUnit.getFullName()));
  }

  private static void checkType(JavaClass owner, JavaType type, ConditionEvents events, String packageName, String usageDescription) {
    if (isTypeContainedInPackage(type.toErasure(), packageName)) {
      events.add(SimpleConditionEvent.violated(owner, "%s exposes %s in %s".formatted(owner.getFullName(), type.getName(), usageDescription)));
    }
    recursiveCheckType(owner, type, events, packageName, usageDescription, new HashSet<>());
  }

  /**
   * Don't call this method directly. Use {@link #checkType(JavaClass, JavaType, ConditionEvents, String, String)}
   * instead.
   */
  private static void recursiveCheckType(JavaClass owner, JavaType type, ConditionEvents events,
                                         String packageName, String usageDescription, Set<String> visitedTypes) {
    if (!visitedTypes.add(type.getName())) {
      return;
    }

    if (isTypeContainedInPackage(type.toErasure(), packageName)) {
      events.add(SimpleConditionEvent.violated(owner,
        "%s exposes %s in %s".formatted(owner.getFullName(), type.getName(), usageDescription)));
    }

    if (type instanceof JavaParameterizedType parameterizedType) {
      parameterizedType.getActualTypeArguments()
        .forEach(typeArgument -> recursiveCheckType(owner, typeArgument, events, packageName, usageDescription, visitedTypes));
    }
  }

  public static boolean isPublicOrProtected(HasModifiers member) {
    return member.getModifiers().contains(JavaModifier.PUBLIC) || member.getModifiers().contains(JavaModifier.PROTECTED);
  }

  private static boolean isTypeContainedInPackage(JavaClass type, String packageName) {
    return type.getFullName().startsWith(packageName);
  }
}
