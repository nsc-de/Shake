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





## 2. Variables

### 2.0 Primitive Variable Types

<table>
<thead>
  <tr>
    <th>name</th>
    <th>size</th>
    <th>content</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>byte</td>
    <td>1 byte</td>
    <td>Stores whole numbers from -128 to 127</td>
  </tr>
  <tr>
    <td>short</td>
    <td>2 bytes</td>
    <td>Stores whole numbers from -32,768 to 32,767</td>
  </tr>
  <tr>
    <td>int</td>
    <td>4 bytes</td>
    <td>Stores whole numbers from -2,147,483,648 to 2,147,483,647</td>
  </tr>
  <tr>
    <td>long</td>
    <td>4 bytes</td>
    <td>Stores whole numbers from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807</td>
  </tr>
  <tr>
    <td>float</td>
    <td>4 bytes</td>
    <td>Stores fractional numbers. Sufficient for storing 6 to 7 decimal digits</td>
  </tr>
  <tr>
    <td>double</td>
    <td>8 bytes</td>
    <td>Stores fractional numbers. Sufficient for storing 15 decimal digits</td>
  </tr>
  <tr>
    <td>char</td>
    <td>2 bytes</td>
    <td>Stores a single character/letter or ASCII values</td>
  </tr>
  <tr>
    <td>boolean</td>
    <td>1 bit</td>
    <td>Stores true / false values</td>
  </tr>
</tbody>
</table>

**These values may vary when compiling into different environments, e.g in javascript there are just dynamic variables and no variable-types.**


### 2.1 Variable Declaration

You can initialize a variable in two ways. Which of these you use is more or less down to your personal preference.



#### C-Style:

```c
int i;
int i = 10;
```

Using the variable type at the beginning of the statement, then the name and if you need it a assignment at the end. 

This is common in many languages like `c`, `c++`, `c#`, `dart`, `java` and many more.


#### The Typescript Way


```typescript
var i : int;
var i : int = 10;
```

Using the keyword `var`, `let` or `const` (for a constant variable), then the identifier and then after the '`:`' the type you can also declare variables. 
But be careful: you still have to use the c-style variable-types, not `number` like you would do in typescript!

This is also common in some languages like `typescript` or `kotlin`.

