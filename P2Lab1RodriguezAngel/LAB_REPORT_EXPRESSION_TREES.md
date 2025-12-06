# LABORATORY REPORT: EXPRESSION TREES

## 1. COVER PAGE

**Institution:** [Your Institution Name]  
**Course:** Data Structures and Algorithms II  
**Laboratory Topic:** Implementation of Expression Trees with Graphical User Interface  
**Student Name:** [Your Name]  
**Student ID:** [Your ID]  
**Date:** December 5, 2025  
**Instructor:** [Instructor Name]

---

## 2. OBJECTIVES

### General Objective
Implement an expression tree system with a graphical user interface that allows the creation, visualization, and evaluation of arithmetic expressions, applying concepts of binary trees and object-oriented design patterns.

### Specific Objectives
1. Develop a graphical user interface using Java Swing for expression tree manipulation
2. Implement algorithms for parsing infix expressions into binary tree structures
3. Visualize the expression tree structure graphically with nodes and connections
4. Implement the three main tree traversal algorithms (Inorder, Preorder, Postorder)
5. Provide interactive visualization of traversal order through numbered nodes
6. Apply the Model-View-Controller (MVC) architectural pattern
7. Evaluate arithmetic expressions using the recursive tree structure

---

## 3. THEORETICAL FRAMEWORK

### 3.1 Expression Trees
An expression tree is a specialized binary tree used to represent arithmetic or logical expressions. In this data structure:
- **Leaf nodes** contain operands (numbers or variables)
- **Internal nodes** contain operators (+, -, *, /, ^)
- Each subtree represents a sub-expression

### 3.2 Binary Trees
A binary tree is a hierarchical data structure where each node has at most two children, referred to as the left child and right child. Key properties:
- **Root**: The topmost node without a parent
- **Leaves**: Nodes without children
- **Height**: Longest path from root to a leaf
- **Depth**: Distance from the root to a specific node

### 3.3 Tree Traversal Algorithms

#### Inorder Traversal (Left-Root-Right)
1. Visit left subtree
2. Visit root node
3. Visit right subtree

For expression trees, inorder traversal produces the original infix notation.

#### Preorder Traversal (Root-Left-Right)
1. Visit root node
2. Visit left subtree
3. Visit right subtree

This traversal produces prefix notation (Polish notation).

#### Postorder Traversal (Left-Right-Root)
1. Visit left subtree
2. Visit right subtree
3. Visit root node

This traversal produces postfix notation (Reverse Polish notation), ideal for evaluation.

### 3.4 Operator Precedence
Mathematical operators follow a hierarchy:
1. **Exponentiation (^)**: Highest precedence (level 3)
2. **Multiplication (*) and Division (/)**: Medium precedence (level 2)
3. **Addition (+) and Subtraction (-)**: Lowest precedence (level 1)

### 3.5 MVC Architectural Pattern
Model-View-Controller is a design pattern that separates an application into three interconnected components:
- **Model**: Manages data and business logic (tree structures, evaluation algorithms)
- **View**: Handles user interface and visualization
- **Controller**: Coordinates between Model and View, processing user input

### 3.6 Java Swing and Graphics2D
Java Swing is a GUI toolkit providing components like JFrame, JPanel, and JButton. Graphics2D offers advanced 2D graphics capabilities including:
- Antialiasing for smooth rendering
- Custom shape drawing
- Color management
- Font rendering

---

## 4. METHODOLOGY AND PROCEDURES

### 4.1 System Architecture
The application follows the MVC pattern with the following structure:

```
Model Layer:
├── Node.java - Binary tree node structure
├── ArbolBinario.java - Base binary tree implementation
├── ArbolExpresion.java - Expression-specific tree logic
├── PilaLista.java - Stack implementation for parsing
└── NodoPila.java - Stack node structure

View Layer:
├── ExpressionTreeView.java - Main GUI window
└── TreePanel.java - Custom tree visualization panel

Controller Layer:
└── ExpressionController.java - Coordinates Model and View

Main:
└── App.java - Application entry point
```

### 4.2 Implementation Steps

#### Step 1: Model Layer Development
**Node Class**: Created a generic binary tree node with:
- Data storage for operator or operand
- Left and right child pointers
- Getter and setter methods

**ArbolBinario Class**: Base tree class providing:
- Root node management
- Basic tree operations
- isEmpty() verification

**ArbolExpresion Class**: Extended binary tree with:
- Expression parsing from infix notation
- Operator precedence handling
- Recursive evaluation algorithm
- Conversion to prefix and postfix notations

**Stack Classes**: Implemented stack data structure for:
- Operator management during parsing
- Parentheses matching
- Expression validation

#### Step 2: Expression Parsing Algorithm
Implemented a parsing algorithm that:
1. Reads the infix expression character by character
2. Uses operator precedence to determine tree structure
3. Handles parentheses for grouping
4. Builds the tree bottom-up using a stack

Pseudocode:
```
function buildFromExpression(expression):
    for each character in expression:
        if character is operand:
            create leaf node
        else if character is operator:
            compare precedence with stack top
            pop operators with higher/equal precedence
            push current operator
    pop remaining operators and build tree
```

#### Step 3: Evaluation Algorithm
Recursive evaluation using postorder traversal:
1. If node is a leaf (operand), return its numeric value
2. If node is an operator:
   - Recursively evaluate left subtree
   - Recursively evaluate right subtree
   - Apply the operator to the two results

#### Step 4: View Layer Development
**ExpressionTreeView**: Created main window with:
- Input text field for entering expressions
- "Generate" button to create the tree
- "Clear" button to reset the application
- Radio buttons for selecting traversal type
- JTabbedPane with two tabs:
  - Graphical Visualization tab (TreePanel)
  - Detailed Results tab (text output)

**TreePanel**: Custom JPanel with:
- Override paintComponent() for custom drawing
- Recursive tree drawing algorithm
- Node positioning calculation
- Traversal order visualization

#### Step 5: Tree Visualization Algorithm
Implemented recursive drawing:
```
function drawTree(node, x, y, horizontalSpacing, level):
    if node is null:
        return
    
    // Draw current node
    drawCircle(x, y)
    drawText(node.data, x, y)
    
    // Draw left subtree
    if node.hasLeftChild():
        drawLine(x, y, leftChildX, leftChildY)
        drawTree(node.left, leftChildX, leftChildY, 
                 horizontalSpacing/2, level+1)
    
    // Draw right subtree
    if node.hasRightChild():
        drawLine(x, y, rightChildX, rightChildY)
        drawTree(node.right, rightChildX, rightChildY,
                 horizontalSpacing/2, level+1)
```

#### Step 6: Traversal Visualization
Added interactive traversal visualization:
1. Implemented three traversal methods that return visit order
2. Added radio buttons for traversal selection
3. Modified node drawing to include order numbers
4. Order numbers displayed in orange circles on each node

#### Step 7: Controller Implementation
**ExpressionController**: Manages user interactions:
- Retrieves expression from input field
- Creates ArbolExpresion instance
- Updates both visualization and text tabs
- Handles error conditions
- Manages view updates

#### Step 8: Styling and UI Enhancement
Applied modern design:
- Nimbus Look and Feel for contemporary appearance
- Custom color scheme:
  - Node color: RGB(70, 130, 180) - Steel Blue
  - Highlight color: RGB(255, 140, 0) - Dark Orange
  - Line color: RGB(100, 100, 100) - Dark Gray
- Custom fonts: Segoe UI, Consolas
- Antialiasing for smooth graphics
- Proper spacing and padding

#### Step 9: Bug Fixes and Optimization
- Fixed Clear button to properly reset TreePanel
- Implemented proper null handling
- Added input validation
- Ensured Java 11+ compatibility with --release flag

---

## 5. RESULTS

### 5.1 Functional Testing

#### Test Case 1: Simple Expression
- **Input**: `3+5`
- **Expected Tree Structure**:
  ```
      +
     / \
    3   5
  ```
- **Result**: ✓ Correct visualization
- **Evaluation**: 8
- **Inorder**: `3 + 5`
- **Preorder**: `+ 3 5`
- **Postorder**: `3 5 +`

#### Test Case 2: Complex Expression with Precedence
- **Input**: `3+5*2`
- **Expected Tree Structure**:
  ```
      +
     / \
    3   *
       / \
      5   2
  ```
- **Result**: ✓ Correct - multiplication evaluated first
- **Evaluation**: 13
- **Inorder**: `3 + 5 * 2`
- **Preorder**: `+ 3 * 5 2`
- **Postorder**: `3 5 2 * +`

#### Test Case 3: Parentheses Expression
- **Input**: `(3+5)*2`
- **Expected Tree Structure**:
  ```
      *
     / \
    +   2
   / \
  3   5
  ```
- **Result**: ✓ Correct - parentheses override precedence
- **Evaluation**: 16
- **Inorder**: `( 3 + 5 ) * 2`
- **Preorder**: `* + 3 5 2`
- **Postorder**: `3 5 + 2 *`

#### Test Case 4: Exponentiation
- **Input**: `2^3+1`
- **Expected Tree Structure**:
  ```
      +
     / \
    ^   1
   / \
  2   3
  ```
- **Result**: ✓ Correct - exponentiation has highest precedence
- **Evaluation**: 9
- **Inorder**: `2 ^ 3 + 1`
- **Preorder**: `+ ^ 2 3 1`
- **Postorder**: `2 3 ^ 1 +`

#### Test Case 5: Division
- **Input**: `8/2/2`
- **Expected Tree Structure**:
  ```
      /
     / \
    /   2
   / \
  8   2
  ```
- **Result**: ✓ Correct - left associativity
- **Evaluation**: 2
- **Inorder**: `8 / 2 / 2`
- **Preorder**: `/ / 8 2 2`
- **Postorder**: `8 2 / 2 /`

### 5.2 Traversal Visualization Testing

#### Inorder Traversal (3+5*2)
Visit Order: 3 → + → 5 → * → 2
- Node 3: Order number 1 (leftmost leaf)
- Node +: Order number 2 (root after left subtree)
- Node 5: Order number 3 (left of * node)
- Node *: Order number 4 (after its left subtree)
- Node 2: Order number 5 (rightmost leaf)

Result: ✓ Produces infix notation

#### Preorder Traversal (3+5*2)
Visit Order: + → 3 → * → 5 → 2
- Node +: Order number 1 (root first)
- Node 3: Order number 2 (left subtree)
- Node *: Order number 3 (right subtree root)
- Node 5: Order number 4 (left of *)
- Node 2: Order number 5 (right of *)

Result: ✓ Produces prefix notation

#### Postorder Traversal (3+5*2)
Visit Order: 3 → 5 → 2 → * → +
- Node 3: Order number 1 (leftmost leaf)
- Node 5: Order number 2 (left leaf of *)
- Node 2: Order number 3 (right leaf of *)
- Node *: Order number 4 (after both children)
- Node +: Order number 5 (root last)

Result: ✓ Produces postfix notation, ideal for evaluation

### 5.3 User Interface Testing

| Feature | Status | Notes |
|---------|--------|-------|
| Expression Input | ✓ Working | Text field accepts alphanumeric input |
| Generate Button | ✓ Working | Creates tree and updates all views |
| Clear Button | ✓ Working | Resets all fields and visualizations |
| Graphical Tab | ✓ Working | Displays tree with blue nodes |
| Results Tab | ✓ Working | Shows all traversals and evaluation |
| Radio Buttons | ✓ Working | Switch between traversal types |
| Order Numbers | ✓ Working | Orange badges show visit sequence |
| Real-time Update | ✓ Working | Changes reflect immediately |
| Nimbus Theme | ✓ Working | Modern appearance applied |
| Antialiasing | ✓ Working | Smooth node and line rendering |

### 5.4 Performance Analysis
- **Parsing Time**: O(n) where n is expression length
- **Evaluation Time**: O(n) where n is number of nodes
- **Traversal Time**: O(n) for each traversal type
- **Drawing Time**: O(n) for recursive rendering
- **Memory Usage**: O(n) for tree storage + O(h) for recursion stack where h is height

### 5.5 Screenshots Description

**Main Window**:
- Clean interface with input field at top
- Generate and Clear buttons with custom styling
- Tabbed pane showing both visualization and results

**Graphical Visualization**:
- Blue circular nodes with white text
- Black connecting lines between parent and children
- Orange numbered circles showing traversal order
- Hierarchical layout with proper spacing

**Detailed Results**:
- Text area displaying:
  - Original expression
  - Infix notation
  - Prefix notation
  - Postfix notation
  - Numeric evaluation result
  - Tree structure representation

---

## 6. ANALYSIS OF RESULTS

### 6.1 Algorithm Correctness
The implemented algorithms correctly handle:
- **Operator precedence**: Exponentiation > Multiplication/Division > Addition/Subtraction
- **Associativity**: Left-to-right for same precedence operators
- **Parentheses**: Properly override precedence rules
- **Nested expressions**: Recursive structure handles arbitrary depth

### 6.2 Tree Structure Validation
Visual inspection confirms:
- Operators are always internal nodes
- Operands are always leaf nodes
- Binary tree property is maintained (max 2 children per node)
- Tree height is minimized based on precedence

### 6.3 Traversal Algorithm Accuracy
Each traversal produces the expected notation:
- **Inorder**: Natural mathematical notation (infix)
- **Preorder**: Polish notation - operator before operands
- **Postorder**: Reverse Polish notation - operator after operands

The visual order numbers correctly reflect the visit sequence, providing educational value for understanding recursion.

### 6.4 Evaluation Accuracy
All test cases produce mathematically correct results:
- Respects operator precedence
- Handles negative numbers
- Performs integer and decimal arithmetic
- Follows mathematical conventions

### 6.5 User Experience
The GUI provides:
- **Intuitive Interface**: Clear input/output separation
- **Visual Feedback**: Immediate tree visualization
- **Educational Value**: Traversal order visualization helps understand algorithms
- **Error Handling**: Graceful handling of invalid input
- **Responsiveness**: Quick rendering and updates

### 6.6 Code Quality
The implementation demonstrates:
- **Clean Architecture**: MVC pattern separation of concerns
- **Modularity**: Independent, reusable classes
- **Maintainability**: English variable names and clear structure
- **Extensibility**: Easy to add new operators or features
- **Robustness**: Null checking and error validation

---

## 7. CONCLUSIONS

### 7.1 Achievement of Objectives
All specific objectives were successfully accomplished:
1. ✓ Functional graphical user interface with Java Swing
2. ✓ Correct infix expression parsing algorithm
3. ✓ Professional tree visualization with geometric shapes
4. ✓ Three traversal algorithms fully implemented
5. ✓ Interactive traversal visualization with numbered nodes
6. ✓ MVC pattern properly applied
7. ✓ Accurate expression evaluation

### 7.2 Technical Insights
Key learnings from the implementation:
- **Binary trees** provide an elegant representation of hierarchical relationships
- **Recursive algorithms** naturally map to tree structures
- **Operator precedence** can be encoded in the tree structure itself
- **MVC pattern** significantly improves code organization and testability
- **Custom graphics** in Java require understanding of coordinate systems and recursive drawing

### 7.3 Challenges and Solutions

**Challenge 1: Operator Precedence Handling**
- Problem: Correctly ordering operators in the tree
- Solution: Implemented precedence comparison in parsing algorithm

**Challenge 2: Tree Visualization Layout**
- Problem: Positioning nodes to avoid overlap
- Solution: Recursive calculation with exponentially decreasing horizontal spacing

**Challenge 3: Clear Button Bug**
- Problem: Application wouldn't work after clearing
- Solution: Added explicit null setting to reset TreePanel state

**Challenge 4: Traversal Order Visualization**
- Problem: Showing visit sequence without cluttering display
- Solution: Small orange numbered circles overlaid on nodes

### 7.4 Practical Applications
Expression trees are fundamental in:
- **Compilers**: Parse and optimize mathematical expressions
- **Calculators**: Evaluate complex formulas
- **Computer Algebra Systems**: Symbolic mathematics
- **Query Optimization**: Database query execution plans
- **Syntax Analysis**: Programming language parsers

### 7.5 Future Enhancements
Potential improvements:
1. Support for additional operators (modulo, absolute value, trigonometric functions)
2. Variable support (x, y, z) for symbolic expressions
3. Expression simplification (combining like terms)
4. Animation of traversal algorithms
5. Export tree as image file
6. Undo/redo functionality
7. Expression history
8. Step-by-step evaluation visualization

### 7.6 Final Remarks
This laboratory successfully demonstrated the practical application of binary tree data structures in solving real-world problems. The combination of solid algorithmic implementation with an intuitive graphical interface creates an effective educational tool for understanding tree traversals and expression evaluation. The MVC architecture ensures the codebase is maintainable and extensible for future enhancements.

---

## 8. RECOMMENDATIONS

### For Students
1. Practice with various expression types to understand operator precedence
2. Experiment with the three traversal types to see how they produce different notations
3. Study the recursive algorithms to understand tree processing patterns
4. Trace through the parsing algorithm manually to understand tree construction
5. Modify the code to add new features and deepen understanding

### For Future Development
1. Add comprehensive unit tests for all model classes
2. Implement input validation with specific error messages
3. Add keyboard shortcuts for common operations
4. Consider responsive design for different screen sizes
5. Add accessibility features (screen reader support, high contrast mode)
6. Implement internationalization for multiple languages
7. Add logging for debugging purposes

### For Educational Use
1. Use this tool to demonstrate tree concepts in data structures courses
2. Show the relationship between tree structure and evaluation order
3. Compare iterative vs recursive implementations
4. Analyze time and space complexity of different approaches
5. Discuss the importance of design patterns in software engineering

---

## 9. BIBLIOGRAPHY

1. Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.

2. Weiss, M. A. (2011). *Data Structures and Algorithm Analysis in Java* (3rd ed.). Pearson.

3. Goodrich, M. T., Tamassia, R., & Goldwasser, M. H. (2014). *Data Structures and Algorithms in Java* (6th ed.). Wiley.

4. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.

5. Horstmann, C. S. (2019). *Core Java Volume I – Fundamentals* (11th ed.). Prentice Hall.

6. Oracle. (2024). *Java Platform, Standard Edition Documentation*. Retrieved from https://docs.oracle.com/en/java/javase/

7. Oracle. (2024). *Java Swing Tutorial*. Retrieved from https://docs.oracle.com/javase/tutorial/uiswing/

8. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

9. Knuth, D. E. (1997). *The Art of Computer Programming, Volume 1: Fundamental Algorithms* (3rd ed.). Addison-Wesley.

10. Aho, A. V., Lam, M. S., Sethi, R., & Ullman, J. D. (2006). *Compilers: Principles, Techniques, and Tools* (2nd ed.). Addison-Wesley.

---

**End of Report**

*This document represents the complete laboratory work on Expression Trees implementation with graphical user interface, demonstrating the application of data structures, algorithms, and software design patterns in Java.*
