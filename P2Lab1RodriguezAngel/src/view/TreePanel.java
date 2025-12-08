package view;

import model.Node;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class TreePanel extends JPanel {
    private Node root;
    private java.util.List<Node> traversalOrder;
    private String traversalType;
    private static final int NODE_RADIUS = 30;
    private static final int VERTICAL_GAP = 80;
    private static final int MIN_HORIZONTAL_GAP = 50;
    private static final Color NODE_COLOR = new Color(70, 130, 180);
    private static final Color HIGHLIGHT_COLOR = new Color(255, 140, 0);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color LINE_COLOR = new Color(100, 100, 100);

    public TreePanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 600));
        traversalOrder = new ArrayList<>();
        traversalType = "inorder";
    }

    public void setTree(Node root) {
        this.root = root;
        updateTraversalOrder();
        repaint();
    }

    public void setTraversalType(String type) {
        this.traversalType = type;
        updateTraversalOrder();
        repaint();
    }

    private void updateTraversalOrder() {
        traversalOrder.clear();
        if (root == null) return;

        switch (traversalType) {
            case "inorder":
                inorderTraversal(root);
                break;
            case "preorder":
                preorderTraversal(root);
                break;
            case "postorder":
                postorderTraversal(root);
                break;
        }
    }

    private void inorderTraversal(Node node) {
        if (node == null) return;
        inorderTraversal(node.leftSubtree());
        traversalOrder.add(node);
        inorderTraversal(node.rightSubtree());
    }

    private void preorderTraversal(Node node) {
        if (node == null) return;
        traversalOrder.add(node);
        preorderTraversal(node.leftSubtree());
        preorderTraversal(node.rightSubtree());
    }

    private void postorderTraversal(Node node) {
        if (node == null) return;
        postorderTraversal(node.leftSubtree());
        postorderTraversal(node.rightSubtree());
        traversalOrder.add(node);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Antialiasing para gráficos más suaves
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (root != null) {
            int width = getWidth();
            int height = getHeight();
            drawTree(g2d, root, width / 2, 50, width / 4);
        }
    }

    private void drawTree(Graphics2D g2d, Node node, int x, int y, int horizontalGap) {
        if (node == null) return;

        // Draw lines to children first (so they stay behind the nodes)
        if (node.leftSubtree() != null) {
            int childX = x - horizontalGap;
            int childY = y + VERTICAL_GAP;
            g2d.setColor(LINE_COLOR);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(x, y, childX, childY);
            drawTree(g2d, node.leftSubtree(), childX, childY, horizontalGap / 2);
        }

        if (node.rightSubtree() != null) {
            int childX = x + horizontalGap;
            int childY = y + VERTICAL_GAP;
            g2d.setColor(LINE_COLOR);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(x, y, childX, childY);
            drawTree(g2d, node.rightSubtree(), childX, childY, horizontalGap / 2);
        }

        // Draw current node
        drawNode(g2d, node.nodeValue().toString(), x, y, node);
    }

    private void drawNode(Graphics2D g2d, String value, int x, int y, Node node) {
        // Draw circle with shadow
        g2d.setColor(new Color(0, 0, 0, 30));
        g2d.fillOval(x - NODE_RADIUS + 3, y - NODE_RADIUS + 3, NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Draw node circle
        g2d.setColor(NODE_COLOR);
        g2d.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Draw circle border
        g2d.setColor(NODE_COLOR.darker());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        // Draw centered text
        g2d.setColor(TEXT_COLOR);
        Font font = new Font("Arial", Font.BOLD, 16);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(value);
        int textHeight = fm.getAscent();
        g2d.drawString(value, x - textWidth / 2, y + textHeight / 2 - 2);

        // Draw traversal order number
        int orderIndex = traversalOrder.indexOf(node);
        if (orderIndex != -1) {
            String orderText = String.valueOf(orderIndex + 1);
            Font smallFont = new Font("Arial", Font.BOLD, 11);
            g2d.setFont(smallFont);
            FontMetrics smallFm = g2d.getFontMetrics();
            
            // Draw small circle for order number
            int circleX = x + NODE_RADIUS - 10;
            int circleY = y - NODE_RADIUS + 10;
            g2d.setColor(HIGHLIGHT_COLOR);
            g2d.fillOval(circleX - 10, circleY - 10, 20, 20);
            g2d.setColor(HIGHLIGHT_COLOR.darker());
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.drawOval(circleX - 10, circleY - 10, 20, 20);
            
            // Draw order number
            g2d.setColor(Color.WHITE);
            int orderTextWidth = smallFm.stringWidth(orderText);
            int orderTextHeight = smallFm.getAscent();
            g2d.drawString(orderText, circleX - orderTextWidth / 2, circleY + orderTextHeight / 2 - 2);
        }
    }
}
