/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.dsl.condition;

public class NotEqual extends ConditionOfCompositeExpression {

  public boolean value() {
    return firstExp.value() != secondExp.value();
  }
}
