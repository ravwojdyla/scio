---
layout: docs
title: Style Guide 
---

## General Guidelines

### IntelliJ IDEA

Most of us write Scala code in IntelliJ IDEA and it's wise to let the IDE do most of the work including managing imports and formatting code. We also want to avoid custom settings as much as possible to make on-boarding new developers easier. Hence we use IntelliJ IDEA's default settings with the following exceptions:

- Set _Code Style &rarr; Default Options &rarr; Right margin (columns)_ to 100
- Turn off _Code Style &rarr; Scala &rarr; ScalaDoc &rarr; Use scaladoc indent for leading asterisk_
- Under _Copyright &rarr; Copyright Profiles_, add the following template.

```
Copyright $today.year Spotify AB.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
```

### ScalaStyle

We use ScalaStyle to cover the basics and the entire code base should pass. In case of exceptions, wrap the violating code with a pair of comments to temporarily suppress the warning.

```scala
// scalastyle:off regex
println("hello")
// scalastyle:on regex
```

### Consistency

There are many different ways to write Scala code and it's hard to enforce a strict style. ScalaStyle's rule set is also not as comprehensive as Java's CheckStyle. When in doubt, be consistent with the rest of the code base.

### References

We want to adhere to the styles of well known Scala projects and use the following documents as references. We follow the Databricks Scala Guide mainly with a few differences described in the next section.

- [Databricks Scala Guide](https://github.com/databricks/scala-style-guide)
- The Official [Scala Style Guide](http://docs.scala-lang.org/style)
- Twitter's [Effective Scala](http://twitter.github.io/effectivescala/)

## Differences from Databricks Scala Guide

### Spacing and Indentation

- For method declarations, align parameters when they don't fit in a single line. Return types can be either on the same line as the last parameter, or put to next line with no indent.

```scala
def saveAsBigQuery(table: TableReference, schema: TableSchema,
                   writeDisposition: WriteDisposition,
                   createDisposition: CreateDisposition)
                  (implicit ev: T <:< TableRow): Future[Tap[TableRow]] = {
  // method body
}

def saveAsBigQuery(table: TableReference, schema: TableSchema,
                   writeDisposition: WriteDisposition,
                   createDisposition: CreateDisposition)
                  (implicit ev: T <:< TableRow)
: Future[Tap[TableRow]] = {
  // method body
}
```

- For classes whose header doesn't fit in a single line, align the next line and add a blank line after class header.

```scala
class Foo(val param1: String,
          val param2: String,
          val param3: Array[Byte])
  extends FooInterface  // 2 space indent here
  with Logging {

  def firstMethod(): Unit = { ... }  // blank line above

}
```

### Blank Lines (Vertical Whitespace)

- A single blank line appears:
  - Between consecutive members (or initializers) of a class: fields, constructors, methods, nested classes, static initializers, instance initializers.
  - Within method bodies, as needed to create logical groupings of statements.
  - Before the first member and after the last member of the class.
- A blank line is optional:
  - Between consecutive one-liner fields or methods of a class that have no ScalaDoc.
  - Before the first member and after the last member of a short class with one-liner members only.
- Use one blank line to separate class definitions.
- Excessive number of blank lines is discouraged.

```scala
class Foo {

  val x: Int  // blank line before the first member
  val y: Int
  val z: Int  // no blank line between one-liners that have no ScalaDoc

  def hello(): {
    // body
  }  // blank line after the last member

}

// no blank line before the first member and after the last member
class Bar {
  def x = { /* body */ }
  def y = { /* body */ }
}
```

### Curly Braces

Put curly braces even around one-line conditional or loop statements. The only exception is if you are using if/else as an one-line ternary operator that is also side-effect free.

```scala
// the only exception for omitting braces
val x = if (true) expression1 else expression2
```

### Documentation Style

Use Java docs style instead of Scala docs style. One-liner ScalaDoc is acceptable. Annotations like `@tparam`, `@param`, `@return` are optional if they are obvious to the user.

ScalaDoc `/** */` should only be used for documenting API to end users. Use regular comments e.g. `/* */` and `//` for explaining code to developers.

```scala
/** This is a correct one-liner, short description. */

/**
 * This is correct multi-line JavaDoc comment. And
 * this is my second line, and if I keep typing, this would be
 * my third line.
 */

/** In Spark, we don't use the ScalaDoc style so this
  * is not correct.
  */

// @param xs, @tparam T and @return are obvious and no need to document
/** Sum up a sequence with an Algebird Semigroup. */
def sum[T: Semigroup](xs: Seq[T]): T = xs.reduce(implicitly[Semigroup[T]].plus)
```
### Ordering within a Class

If a class is long and has many methods, group them logically into different sections, and use comment headers to organize them.

```scala
class ScioContext {

  // =======================================================================
  // Read operations
  // =======================================================================

  // =======================================================================
  // Accumulators
  // =======================================================================

}
```

Of course, the situation in which a class grows this long is strongly discouraged, and is generally reserved only for building certain public APIs.

### Imports

- Mutable collections
  - Always prefix a mutable collection type with `M` when importing, e.g. `import scala.collection.mutable.{Map => MMap}`
  - Or import `scala.collection.mutable` package and use `mutable.Map`
- Sort imports in IntelliJ IDEA's default order:
  - `java.*`
  - All other imports
  * `scala.*`
- It should look like this in _Imports &rarr; Import Layout_

```
java
_______ blank line _______
all other imports
_______ blank line _______
scala
```