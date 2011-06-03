/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.structural.search;

import com.sonar.sslr.api.AstNode;

public class StructuralSearchPattern extends CompositeMatcher {

  public AstNode match(AstNode astNode) {
    return matcher.match(astNode);
  }

  public boolean isMatching(AstNode astNode) {
    return match(astNode) != null;
  }

}
