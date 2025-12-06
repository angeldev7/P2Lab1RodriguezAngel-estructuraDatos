package view;

import javax.swing.*;
import java.awt.*;

public class ExpressionTreeView extends JFrame {
    private JTextField expressionField;
    private JTextArea outputArea;
    private JButton generateButton;
    private JButton clearButton;
    private JScrollPane scrollPane;
    private TreePanel treePanel;
    private JTabbedPane tabbedPane;
    private JRadioButton inorderRadio;
    private JRadioButton preorderRadio;
    private JRadioButton postorderRadio;
    private ButtonGroup traversalGroup;

    public ExpressionTreeView() {
        setTitle("Expression Tree Viewer");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Colors and fonts
        Color background = new Color(240, 240, 240);
        Color panelBackground = new Color(220, 220, 220);
        Color buttonColor = new Color(70, 130, 180);
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font monoFont = new Font("Consolas", Font.PLAIN, 14);

        // Content pane configuration
        Container contentPane = getContentPane();
        contentPane.setBackground(background);
        contentPane.setLayout(new BorderLayout(15, 15));
        ((JPanel) contentPane).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            " Mathematical Expression ",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            labelFont,
            new Color(50, 50, 50)
        ));
        inputPanel.setBackground(panelBackground);
        inputPanel.setOpaque(true);

        expressionField = new JTextField();
        expressionField.setFont(textFont);
        expressionField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        inputPanel.add(expressionField, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(panelBackground);
        
        generateButton = new JButton("Generate Tree");
        clearButton = new JButton("Clear");

        // Button styling
        for (JButton btn : new JButton[]{generateButton, clearButton}) {
            btn.setFont(labelFont);
            btn.setBackground(buttonColor);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        buttonPanel.add(generateButton);
        buttonPanel.add(clearButton);
        inputPanel.add(buttonPanel, BorderLayout.EAST);

        // Output area
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(monoFont);
        outputArea.setBackground(new Color(250, 250, 250));
        outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(panelBackground);

        // Tree visualization panel
        treePanel = new TreePanel();
        JScrollPane treeScrollPane = new JScrollPane(treePanel);
        treeScrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Traversal selection panel
        JPanel traversalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        traversalPanel.setBackground(Color.WHITE);
        traversalPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JLabel traversalLabel = new JLabel("Traversal Type:");
        traversalLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        traversalLabel.setForeground(new Color(50, 50, 50));

        inorderRadio = new JRadioButton("Inorder", true);
        preorderRadio = new JRadioButton("Preorder");
        postorderRadio = new JRadioButton("Postorder");

        traversalGroup = new ButtonGroup();
        traversalGroup.add(inorderRadio);
        traversalGroup.add(preorderRadio);
        traversalGroup.add(postorderRadio);

        Font radioFont = new Font("Segoe UI", Font.PLAIN, 12);
        for (JRadioButton radio : new JRadioButton[]{inorderRadio, preorderRadio, postorderRadio}) {
            radio.setFont(radioFont);
            radio.setBackground(Color.WHITE);
            radio.setFocusPainted(false);
            radio.addActionListener(e -> {
                treePanel.setTraversalType(getSelectedTraversal());
            });
        }

        traversalPanel.add(traversalLabel);
        traversalPanel.add(inorderRadio);
        traversalPanel.add(preorderRadio);
        traversalPanel.add(postorderRadio);

        // Panel combining traversal controls and tree
        JPanel treeWithControls = new JPanel(new BorderLayout());
        treeWithControls.add(traversalPanel, BorderLayout.NORTH);
        treeWithControls.add(treeScrollPane, BorderLayout.CENTER);

        // Tabbed pane to switch between graphical visualization and text
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(labelFont);
        tabbedPane.addTab("Graphical Visualization", treeWithControls);
        tabbedPane.addTab("Detailed Results", scrollPane);
        tabbedPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            " Results ",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            labelFont,
            new Color(50, 50, 50)
        ));

        // Main layout
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(tabbedPane, BorderLayout.CENTER);
    }

    public String getExpression() {
        return expressionField.getText();
    }

    public void setOutput(String text) {
        outputArea.setText(text);
    }

    public void clear() {
        expressionField.setText("");
        outputArea.setText("");
        treePanel.setTree(null);
    }

    public JButton getGenerateButton() {
        return generateButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public TreePanel getTreePanel() {
        return treePanel;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public String getSelectedTraversal() {
        if (inorderRadio.isSelected()) return "inorder";
        if (preorderRadio.isSelected()) return "preorder";
        if (postorderRadio.isSelected()) return "postorder";
        return "inorder";
    }
}
