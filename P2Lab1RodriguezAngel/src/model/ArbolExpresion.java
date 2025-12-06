package model;

import model.PilaLista;

public class ArbolExpresion extends ArbolBinario {

    public ArbolExpresion() {
        super();
    }
    public ArbolExpresion(Node root) {
        super(root);
    }
    public static ArbolExpresion buildFromExpression(String expression) throws Exception {
        if (expression == null || expression.trim().isEmpty()) {
            throw new Exception("Empty or null expression");
        }
        expression = expression.replaceAll("\\s+", "");
        Node root = buildNodeFromExpression(expression);
        return new ArbolExpresion(root);
    }
    private static Node buildNodeFromExpression(String expression) throws Exception {
        if (expression.startsWith("(") && expression.endsWith(")")) {
            expression = expression.substring(1, expression.length() - 1);
        }
        int balance = 0;
        int opPos = -1;
        int priority = Integer.MAX_VALUE;

        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (c == ')') {
                balance++;
            } else if (c == '(') {
                balance--;
            } else if (balance == 0 && isOperator(c)) {
                int p = getPriority(c);
                if (p <= priority) {
                    priority = p;
                    opPos = i;
                }
            }
        }

        if (opPos != -1) {
            String left = expression.substring(0, opPos);
            String right = expression.substring(opPos + 1);
            char op = expression.charAt(opPos);
            return new Node(buildNodeFromExpression(left), String.valueOf(op), buildNodeFromExpression(right));
        } else {
             if (!containsOperator(expression)) {
                return new Node(null, expression, null);
            } else {
                 throw new Exception("Malformed expression: " + expression);
            }
        }
    }
    
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private static int getPriority(char op) {
        switch (op) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }
    
    private static boolean containsOperator(String s) {
        for (char c : s.toCharArray()) {
            if (isOperator(c)) {
                return true;
            }
        }
        return false;
    }

	public double evaluate() throws Exception {
        return evaluateRecursive(root);
    }

    private double evaluateRecursive(Node node) throws Exception {
        if (node == null) {
            throw new Exception("Empty tree or null node in evaluation");
        }
        if (node.leftSubtree() == null && node.rightSubtree() == null) {
            try {
                return Double.parseDouble(node.nodeValue().toString());
            } catch (NumberFormatException e) {
                throw new Exception("Non-numeric operand: " + node.nodeValue());
            }
        }

        double left = evaluateRecursive(node.leftSubtree());
        double right = evaluateRecursive(node.rightSubtree());
        char op = node.nodeValue().toString().charAt(0);

        switch (op) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                if (right == 0) throw new ArithmeticException("Division by zero");
                return left / right;
            case '^':
                return Math.pow(left, right);
            default:
                throw new Exception("Unknown operator: " + op);
        }
    }

    public String toInfix() {
        return toInfixRecursive(root);
    }

    private String toInfixRecursive(Node node) {
        if (node == null) return "";
        if (node.leftSubtree() == null && node.rightSubtree() == null) {
            return node.nodeValue().toString();
        }
        String left = toInfixRecursive(node.leftSubtree());
        String right = toInfixRecursive(node.rightSubtree());
        return "(" + left + " " + node.nodeValue() + " " + right + ")";
    }

    public String toPrefix() {
        return toPrefixRecursive(root).trim();
    }

    private String toPrefixRecursive(Node node) {
        if (node == null) return "";
        String op = node.nodeValue().toString();
        String left = toPrefixRecursive(node.leftSubtree());
        String right = toPrefixRecursive(node.rightSubtree());
        return op + " " + left + right;
    }

    public String toPostfix() {
        return toPostfixRecursive(root).trim();
    }

    private String toPostfixRecursive(Node node) {
        if (node == null) return "";
        String left = toPostfixRecursive(node.leftSubtree());
        String right = toPostfixRecursive(node.rightSubtree());
        String op = node.nodeValue().toString();
        return left + right + op + " ";
    }
    
    public String printTree() {
        StringBuilder sb = new StringBuilder();
        printTree(root, 0, sb);
        return sb.toString();
    }

    private void printTree(Node n, int level, StringBuilder sb) {
        if (n != null) {
            printTree(n.rightSubtree(), level + 1, sb);
            for (int i = 0; i < level; i++) {
                sb.append("    ");
            }
            sb.append(n.nodeValue().toString()).append("\n");
            printTree(n.leftSubtree(), level + 1, sb);
        }
    }
}
