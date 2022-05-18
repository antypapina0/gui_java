import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Help extends JDialog {
    private JPanel contentPanel = new JPanel();
    private static final long serialVersionUID = 1L;

    public Help() {
        this.setTitle("Pomoc");
        this.setModal(false);
        this.setResizable(true);
        this.setSize(310,200);

        this.addWindowListener	(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                setVisible(false);
            }
        });

        Dimension dialogSize = getSize();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(dialogSize.height > screenSize.height)
            dialogSize.height = screenSize.height;
        if(dialogSize.width > screenSize.width)
            dialogSize.height = screenSize.width;

        setLocation((screenSize.width-dialogSize.width)/2,
                (screenSize.height-dialogSize.height)/2);

        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Aby uzyskać pomoc proszę udać się do specjalisty.");

        this.add(label, BorderLayout.CENTER);
    }
}