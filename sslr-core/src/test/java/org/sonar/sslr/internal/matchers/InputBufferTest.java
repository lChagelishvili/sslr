/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.sslr.internal.matchers;

import org.junit.Test;
import org.sonar.sslr.internal.matchers.InputBuffer.Position;

import static org.fest.assertions.Assertions.assertThat;

public class InputBufferTest {

  @Test
  public void test() {
    InputBuffer inputBuffer = new InputBuffer("foo\r\nbar\nbaz\rqux\r".toCharArray());

    assertThat(inputBuffer.getLineCount()).isEqualTo(4);

    assertThat(inputBuffer.extractLine(1)).isEqualTo("foo\r\n");
    assertThat(inputBuffer.extractLine(2)).isEqualTo("bar\n");
    assertThat(inputBuffer.extractLine(3)).isEqualTo("baz\r");
    assertThat(inputBuffer.extractLine(4)).isEqualTo("qux\r");

    assertThat(inputBuffer.getPosition(0)).isEqualTo(new Position(1, 1));
    assertThat(inputBuffer.getPosition(4)).isEqualTo(new Position(1, 5));
    assertThat(inputBuffer.getPosition(5)).isEqualTo(new Position(2, 1));
    assertThat(inputBuffer.getPosition(8)).isEqualTo(new Position(2, 4));
    assertThat(inputBuffer.getPosition(9)).isEqualTo(new Position(3, 1));
    assertThat(inputBuffer.getPosition(12)).isEqualTo(new Position(3, 4));
    assertThat(inputBuffer.getPosition(13)).isEqualTo(new Position(4, 1));
    assertThat(inputBuffer.getPosition(16)).isEqualTo(new Position(4, 4));
    assertThat(inputBuffer.getPosition(17)).isEqualTo(new Position(5, 1));
  }

}