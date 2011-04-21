/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.dsl.condition;

import static com.sonar.sslr.impl.matcher.Matchers.and;
import static com.sonar.sslr.impl.matcher.Matchers.opt;
import static com.sonar.sslr.impl.matcher.Matchers.or;

import com.sonar.sslr.api.Rule;
import com.sonar.sslr.dsl.Dsl;
import com.sonar.sslr.dsl.calculator.CalculatorDsl;

public class ConditionDsl extends Dsl {

  public Rule condition;
  public Rule primaryCondition;
  public Rule logicalAnd;
  public Rule logicalOr;
  public Rule equal;
  public Rule notEqual;
  public Rule expression = new CalculatorDsl().expression;

  public ConditionDsl() {
    equal.is(expression, or("=", "equals"), expression).plug(Equal.class);
    notEqual.is(expression, or(and("!", "="), and("not", "equals")), expression).plug(NotEqual.class);
    primaryCondition.isOr("true", "false").plug(PrimaryCondition.class);
    logicalAnd.is(or(primaryCondition, equal, notEqual), opt(or("and", and("&", "&")), logicalAnd)).skipIfOneChild().plug(LogicalAnd.class);
    logicalOr.is(logicalAnd, opt(or("or", and("|", "|")), logicalOr)).skipIfOneChild().plug(LogicalOr.class);
    condition.is(logicalOr).plug(Condition.class);
  }

  public Rule getRootRule() {
    return condition;
  }
}