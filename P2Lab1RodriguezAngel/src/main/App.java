package main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import controller.ExpressionController;
import view.ExpressionTreeView;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                // If Nimbus is not available, the default Look and Feel will be used.
            }
            ExpressionTreeView view = new ExpressionTreeView();
            ExpressionController controller = new ExpressionController(view);
            controller.showView();
        });
    }
}
