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


## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). See the MIT License documentation for more details.


