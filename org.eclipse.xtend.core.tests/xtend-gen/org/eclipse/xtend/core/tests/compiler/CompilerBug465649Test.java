/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug465649Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <E> E[] newArray(E... arr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return arr");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("newArray(1, 1)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <E extends Object> E[] newArray(final E... arr) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return arr;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Integer[] m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return C.<Integer>newArray(Integer.valueOf(1), Integer.valueOf(1));");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <E> E[] newArray(E... arr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return arr");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("newArray(1, 1.1)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <E extends Object> E[] newArray(final E... arr) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return arr;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Number[] m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return C.<Number>newArray(Integer.valueOf(1), Double.valueOf(1.1));");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <E> E[] newArray(E... arr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return arr");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("newArray(1, \'1\')");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <E extends Object> E[] newArray(final E... arr) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return arr;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Object[] m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return C.<Object>newArray(Integer.valueOf(1), \"1\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <E> E[] newArray(E... arr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return arr");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("newArray(1, 1.1, \'1\')");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <E extends Object> E[] newArray(final E... arr) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return arr;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Object[] m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return C.<Object>newArray(Integer.valueOf(1), Double.valueOf(1.1), \"1\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <E> E[] newArray(E... arr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return arr");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <E> Class<? extends E[]> getClass(E[] arr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return (arr as Object).getClass as Class<? extends E[]>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val arr1 = newArray(1, 1)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val arr2 = newArray(1, 1.1)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val arr3 = newArray(1, \'1\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val arr4 = newArray(1, 1.1, \'1\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("println(arr1.class.name)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("println(arr2.class.name)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("println(arr3.class.name)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("println(arr4.class.name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <E extends Object> E[] newArray(final E... arr) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return arr;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <E extends Object> Class<? extends E[]> getClass(final E[] arr) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Class<?> _class = ((Object) arr).getClass();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ((Class<? extends E[]>) _class);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String... args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Integer[] arr1 = C.<Integer>newArray(Integer.valueOf(1), Integer.valueOf(1));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Number[] arr2 = C.<Number>newArray(Integer.valueOf(1), Double.valueOf(1.1));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Object[] arr3 = C.<Object>newArray(Integer.valueOf(1), \"1\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Object[] arr4 = C.<Object>newArray(Integer.valueOf(1), Double.valueOf(1.1), \"1\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("InputOutput.<String>println(C.<Integer>getClass(arr1).getName());");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("InputOutput.<String>println(C.<Number>getClass(arr2).getName());");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("InputOutput.<String>println(C.<Object>getClass(arr3).getName());");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("InputOutput.<String>println(C.<Object>getClass(arr4).getName());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <E> E[] newArray(E... arr) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return arr");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// internally we know more about the type of the elements");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("newArray(1, \'1\').get(0).compareTo(null)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <E extends Object> E[] newArray(final E... arr) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return arr;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static int m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Object[] _newArray = C.<Object>newArray(Integer.valueOf(1), \"1\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Object _get = _newArray[0];");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ((Comparable<?>)_get).compareTo(null);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
