package controller;

import model.ArbolExpresion;
import view.ExpressionTreeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpressionController {
    private ArbolExpresion model;
    private ExpressionTreeView view;

    public ExpressionController(ExpressionTreeView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.getGenerateButton().addActionListener(e -> generateTree());
        view.getClearButton().addActionListener(e -> clearFields());
        
        // Add listener for traversal type changes
        String[] traversalTypes = {"inorder", "preorder", "postorder"};
        for (String type : traversalTypes) {
            // This will be triggered when radio buttons change
        }
    }

    private void generateTree() {
        String expression = view.getExpression();
        if (expression.trim().isEmpty()) {
            view.setOutput("Error: Expression cannot be empty.");
            return;
        }

        try {
            model = ArbolExpresion.buildFromExpression(expression);
            
            // Update graphical tree visualization
            view.getTreePanel().setTree(model.getRoot());
            view.getTreePanel().setTraversalType(view.getSelectedTraversal());
            
            // Update detailed results tab
            StringBuilder sb = new StringBuilder();
            sb.append("═══════════════════════════════════════════════\n");
            sb.append("  EXPRESSION TREE\n");
            sb.append("═══════════════════════════════════════════════\n\n");
            sb.append("Expression: ").append(expression).append("\n\n");
            
            sb.append("Tree structure:\n");
            sb.append(model.printTree());
            sb.append("\n");
            
            sb.append("───────────────────────────────────────────────\n");
            sb.append("TREE TRAVERSALS\n");
            sb.append("───────────────────────────────────────────────\n\n");
            sb.append("• Infix Traversal:   ").append(model.toInfix()).append("\n");
            sb.append("• Prefix Traversal:  ").append(model.toPrefix()).append("\n");
            sb.append("• Postfix Traversal: ").append(model.toPostfix()).append("\n\n");

            sb.append("───────────────────────────────────────────────\n");
            sb.append("EVALUATION\n");
            sb.append("───────────────────────────────────────────────\n\n");
            try {
                double result = model.evaluate();
                sb.append("✓ Result: ").append(result).append("\n");
            } catch (Exception evalEx) {
                sb.append("⚠ Cannot evaluate: non-numeric expression\n");
            }

            view.setOutput(sb.toString());
            
            // Switch to graphical visualization tab
            view.getTabbedPane().setSelectedIndex(0);

        } catch (Exception ex) {
            view.setOutput("❌ Error generating tree: " + ex.getMessage());
            view.getTabbedPane().setSelectedIndex(1);
        }
    }

    private void clearFields() {
        view.clear();
    }

    public void showView() {
        view.setVisible(true);
    }
}
