/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonar.sslr.squid;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.sonar.squid.api.CodeVisitor;
import org.sonar.squid.api.SourceCode;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.AstNodeType;
import com.sonar.sslr.api.AstVisitor;
import com.sonar.sslr.api.Comments;
import com.sonar.sslr.api.Grammar;

/**
 * Base class to visit an AST (Abstract Syntactic Tree) generated by a parser.
 * 
 * Methods are visited in the following sequential order : init(), visitFile(), visitNode(), leaveNode(), leaveFile() and destroy()
 */
public class SquidAstVisitor<GRAMMAR extends Grammar> implements CodeVisitor, AstVisitor {
	
  private Stack<SourceCode> sourceCodeStack;
  protected GRAMMAR grammar;
  private Comments comments;
  private File file;
  private List<AstNodeType> astNodeTypesToVisit = new ArrayList<AstNodeType>();

  /**
   * This method can't be overridden. The method subscribeTo(AstNodeType... astNodeTypes) must be used to while overriding the public void
   * init() method.
   */
  public final List<AstNodeType> getAstNodeTypesToVisit() {
    return astNodeTypesToVisit;
  }

  /**
   * This method must called into the init() method when an AST visitor wants to subscribe to a set of AST node type.
   */
  public final void subscribeTo(AstNodeType... astNodeTypes) {
    for (AstNodeType type : astNodeTypes) {
      astNodeTypesToVisit.add(type);
    }
  }

  /**
   * Do not use
   */
  public final void setSourceCodeStack(Stack<SourceCode> sourceCodeStack) {
    this.sourceCodeStack = sourceCodeStack;
  }

  /**
   * Do not use
   */
  public final void setGrammar(GRAMMAR grammar) {
    this.grammar = grammar;
  }

  /**
   * Do not use
   */
  public final void setComments(Comments comments) {
    this.comments = comments;
  }

  public final Comments getComments() {
    return comments;
  }

  /**
   * Do not use
   */
  public final void addSourceCode(SourceCode child) {
    peekSourceCode().addChild(child);
    sourceCodeStack.add(child);
  }

  /**
   * Do not use
   */
  public final void popSourceCode() {
    sourceCodeStack.pop();
  }

  /**
   * Do not use
   */
  public final SourceCode peekSourceCode() {
    return sourceCodeStack.peek();
  }

  /**
   * Initialize the visitor. This is the time to verify that the visitor has everything required to perform it job. This method is called
   * once.
   */
  public void init() {
  }

  /**
   * {@inheritDoc}
   */
  public void visitFile(AstNode astNode) {
  }

  /**
   * {@inheritDoc}
   */
  public void visitNode(AstNode astNode) {
  }

  /**
   * {@inheritDoc}
   */
  public void leaveFile(AstNode astNode) {
  }

  /**
   * {@inheritDoc}
   */
  public void leaveNode(AstNode astNode) {
  }

  /**
   * Destroy the visitor. It is being retired from service.
   */
  public void destroy() {
  }

  public final void setFile(File file) {
    this.file = file;
  }

  public final File getFile() {
    return file;
  }

  public final GRAMMAR getGrammar() {
    return grammar;
  }

}
