# Solver: A Lexical Arithmetic Expression Evaluator

Solver is a Java-based library designed to evaluate mathematical expressions with operator precedence. It provides robust support for operations like addition, subtraction, multiplication, division, factorials, exponentiation, and parenthesis-based grouping, following standard mathematical rules.

## Features

- **Operator Precedence**: Handles the correct order of operations (e.g., multiplication/division before addition/subtraction).
- **Parentheses Support**: Supports nested expressions with parentheses.
- **Factorial and Exponentiation**: Includes support for factorial (`!`) and exponentiation (`^`) operators.
- **Error Handling**: Includes detailed exception handling for invalid expressions.
- **Customizable**: Easily extendable for additional operators or functionalities.

## How It Works

The `Solver` class evaluates mathematical expressions using a recursive descent parser. This allows the library to handle complex arithmetic expressions while maintaining clarity and modularity.

Example:
```java
Solver solver = new Solver("3 + 5 * (2 - 8)^2!");
long result = solver.evaluate();
System.out.println(result); // Output: 183
```
## Supported Operators

The following operators are supported:

- `+` (Addition)
- `-` (Subtraction)
- `*` (Multiplication)
- `/` (Division)
- `!` (Factorial)
- `^` (Exponentiation)
- `()` (Parentheses for grouping)

# Maven Configuration

``` xml
<dependencies>
	<dependency>
		<groupId>com.github.embuc</groupId>
		<artifactId>equation-solver</artifactId>
		<version>1.0</version>
	</dependency>
</dependencies>
```

# ChangeLog Release Notes

See the [ChangeLog.txt file](changelog.txt).

## License

This project is licensed under the [ISC License](https://opensource.org/license/isc-license-txt). See the ISC License documentation for more details (There is also 
one in the root of the project). 


