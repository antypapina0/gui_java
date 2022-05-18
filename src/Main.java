import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow window = new MainWindow();
                    Handlers handler = new Handlers(window, window.table_class, window.file);
                    handler.createActions();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
