# Shake Programming Language Features

## 1 Operators

### 1.0 Mathematical Operators

_Shake has 6 different types of simple mathematical operators_

```js
10 + 3   // plus
10 – 3   // minus
10 * 3   // multiply
10 / 3   // divide
10 % 3   // modulo
10 ** 3  // power (>> 10 * 10 * 10)
```

### 1.1 Comparison Operators

_These are Shake's comparison-operators_

```js 
9 == 8  // equals
9 >= 8  // bigger Equals
9 <= 8  // lower Equals
9 > 8   // bigger
9 < 8   // lower
```

### 1.2 Logical Operators

```js
true || false  // or (at least one of them has to be correct) 
true && false  // and (both of them have to be correct)
```

### 1.3 Brackets & Priorities

#### 1.3.0 Brackets
If you are working with bigger terms, there are priorities, e.g.
multiplication and divisions are preferred before plus and minus.

If you want to prefer plus over multiply you can use brackets around
the addition to give it a higher priority

eg.
```js
4 * 10 + 3    // >> 43
4 * (10 + 3)  // >> 52
```

#### 1.3.1 Priorities

_implemented priorities_

1. [brackets](#1.3.0-Brackets)
2. [logical operators](#1.2-Logical-Operators)
3. [comparison](#1.1-Comparison-Operators) > and
4. [comparison](#1.1-Comparison-Operators) > or
5. [mathematical](#1.0-Mathematical-operators) > square
6. [mathematical](#1.0-Mathematical-operators) > multiply, divide, modulo
7. [mathematical](#1.0-Mathematical-operators) > plus, minus
