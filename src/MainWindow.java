import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class MainWindow extends JFrame //tworzenie zmiennych do korzystania w całej klasie (a nie tylko w metodzie)
{

    public File file = new File("tablica.csv");
    public Table table_class = new Table();
    public Help help = null;
    public Info info = null;

    private static final long serialVersionUID = -685999662274732866L;
    Icon add_icon, average_icon, help_icon, info_icon, max_icon,
            min_icon, reset_icon, save_icon, sum_icon;

    JPanel mainPanel, contentPanel, panel_1, panel_2, panel_3, panel_4, panel_5, panel_6, panel_8;

    JButton reset_btn, add_btn, save_btn, calculate_btn, btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3,
            btnNewButton_4, btnNewButton_5, btnNewButton_6, btnNewButton_7, btnNewButton_8;

    JMenuItem mntmNewMenuItem, mntmNewMenuItem_1, mntmNewMenuItem_2, mntmNewMenuItem_3, mntmNewMenuItem_4,
            mntmNewMenuItem_5, mntmNewMenuItem_6, mntmNewMenuItem_7, mntmNewMenuItem_8, mntmNewMenuItem_9,
            mntmNewMenuItem_10;

    JToolBar toolBar;

    JComboBox combo_box;

    TitledBorder titledBorder;

    JSpinner column_spinner, row_spinner;

    JTable table;

    JTextField textField, info_textfield = new JTextField("INFO PANEL");

    JTextArea txtrCos;

    String[] tableNames = {"1", "2", "3", "4", "5"}; //nazwa tabel

    public MainWindow() {
        createIcons();
        createMenuBar();
        createToolBar();
        panelInitialization();
        initialize();

        mainPanel = (JPanel) this.getContentPane();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(toolBar, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private void createMenuBar() //tworzenie menu
    {
        JMenuBar menuBar = new JMenuBar(); //tworzenie obiektu Jmenubar
        setJMenuBar(menuBar); //wrzucenie setJmenubar z menubar

        JMenu mnNewMenu = new JMenu("Plik"); //pasek Menu
        menuBar.add(mnNewMenu);

        mntmNewMenuItem = new JMenuItem("Info"); // atrybuty w pasku menu
        mnNewMenu.add(mntmNewMenuItem);

        mntmNewMenuItem_1 = new JMenuItem("Zakończ");
        mnNewMenu.add(mntmNewMenuItem_1);

        JMenu mnNewMenu_1 = new JMenu("Edycja");
        menuBar.add(mnNewMenu_1);

        mntmNewMenuItem_2 = new JMenuItem("Dodaj");
        mnNewMenu_1.add(mntmNewMenuItem_2);

        mntmNewMenuItem_3 = new JMenuItem("Zeruj");
        mnNewMenu_1.add(mntmNewMenuItem_3);

        mntmNewMenuItem_4 = new JMenuItem("Zapisz");
        mnNewMenu_1.add(mntmNewMenuItem_4);

        mntmNewMenuItem_5 = new JMenuItem("Oblicz");
        mnNewMenu_1.add(mntmNewMenuItem_5);

        JMenu mnNewMenu_3 = new JMenu("Obliczenia");
        menuBar.add(mnNewMenu_3);

        mntmNewMenuItem_6 = new JMenuItem("Suma");
        mnNewMenu_3.add(mntmNewMenuItem_6);

        mntmNewMenuItem_7 = new JMenuItem("Min");
        mnNewMenu_3.add(mntmNewMenuItem_7);

        mntmNewMenuItem_8 = new JMenuItem("Max");
        mnNewMenu_3.add(mntmNewMenuItem_8);

        mntmNewMenuItem_9 = new JMenuItem("Średnia");
        mnNewMenu_3.add(mntmNewMenuItem_9);

        JMenu mnNewMenu_4 = new JMenu("Pomoc");
        menuBar.add(mnNewMenu_4);

        mntmNewMenuItem_10 = new JMenuItem("Pomoc");
        mnNewMenu_4.add(mntmNewMenuItem_10);
    }

    private void createToolBar() //przyciski
    {
        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        btnNewButton = createJButtonToolBar("Dodaj liczbe do tablicy", add_icon);
        toolBar.add(btnNewButton);

        btnNewButton_1 = createJButtonToolBar("Zapisz tabele do pliku", save_icon);
        toolBar.add(btnNewButton_1);

        btnNewButton_2 = createJButtonToolBar("Wyzeruj tabele", reset_icon);
        toolBar.add(btnNewButton_2);

        JSeparator separator = new JSeparator();
        toolBar.add(separator);

        btnNewButton_3 = createJButtonToolBar("Oblicz sume", sum_icon);
        toolBar.add(btnNewButton_3);

        btnNewButton_4 = createJButtonToolBar("Oblicz średnia", average_icon);
        toolBar.add(btnNewButton_4);

        btnNewButton_5 = createJButtonToolBar("Wyznacz wartosc minimalna", min_icon);
        toolBar.add(btnNewButton_5);

        btnNewButton_6 = createJButtonToolBar("Wyznacz wartosc maksymalna", max_icon);
        toolBar.add(btnNewButton_6);

        JSeparator separator_1 = new JSeparator();
        toolBar.add(separator_1);

        btnNewButton_7 = createJButtonToolBar("Uzyskaj pomoc", help_icon);
        toolBar.add(btnNewButton_7);

        btnNewButton_8 = createJButtonToolBar("Informacje o autorze", info_icon);
        toolBar.add(btnNewButton_8);
    }

    private JPanel createButtonsPanel() // przyciski
    {
        panel_1 = new JPanel();
        panel_1.setLayout(new GridLayout(3, 1, 1, 20));

        reset_btn = new JButton("Zeruj");
        add_btn = new JButton("Dodaj");
        save_btn = new JButton("Zapisz");

        panel_1.add(reset_btn);
        panel_1.add(add_btn);
        panel_1.add(save_btn);

        return panel_1;
    }

    private JPanel createCalculationPanel() //panel z combobox
    {
        panel_2 = new JPanel();
        panel_2.setLayout(new FlowLayout(FlowLayout.LEFT));

        String[] comboBoxContent = {"Suma elementow", "Średnia elementów", "Wartość max i min"};
        combo_box = new JComboBox(comboBoxContent); // rozsuwany pasek
        calculate_btn = new JButton("Oblicz");

        JLabel calculationLabel = new JLabel("Obliczenia");
        combo_box.setEditable(false);
        combo_box.setPreferredSize(new Dimension(200, 20));

        panel_2.add(calculationLabel);
        panel_2.add(combo_box);
        panel_2.add(calculate_btn);

        return panel_2;
    }

    private JPanel createTableAndButtonsPanel() //tworzenie tabel
    {
        panel_3 = new JPanel();
        table = new JTable(table_class.getTable_data(), tableNames);
        table.getTableHeader().setReorderingAllowed(false); //zablokowanie przesuwanie tablic
        JScrollPane scrollPane = new JScrollPane(table); //
        panel_3.setLayout(new FlowLayout(FlowLayout.LEFT));
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer(); //LICZBY DO PRAWEJ
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        for (int i = 0; i < 5; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
        }
        table.setEnabled(false);
        table.setRowHeight(25);
        scrollPane.setPreferredSize(new Dimension(500, 150));

        panel_3.add(scrollPane, BorderLayout.CENTER);
        panel_3.add(createButtonsPanel());

        return panel_3;
    }

    private JPanel createNorthNorthPanel() {
        panel_4 = new JPanel();

        SpinnerModel column_spinner_value = new SpinnerNumberModel(1, 1, 5, 1);
        SpinnerModel row_spinner_value = new SpinnerNumberModel(1, 1, 5, 1);

        JLabel column_label = new JLabel("Numer kolumny:");
        column_spinner = new JSpinner(column_spinner_value);
        JLabel row_label = new JLabel("Numer wiersza:");
        row_spinner = new JSpinner(row_spinner_value);
        JLabel number_label = new JLabel("Wprowadź liczbę:");
        textField = new JTextField();

        textField.setText("0");
        textField.setColumns(10);

        panel_4.add(number_label); //dodanie rzeczy do panelu
        panel_4.add(textField);
        panel_4.add(row_label);
        panel_4.add(row_spinner);
        panel_4.add(column_label);
        panel_4.add(column_spinner);

        return panel_4;
    }

    private JPanel createSouthPanel() //DODANIE DOLNEGO PANELU
    {
        panel_5 = new JPanel();
        panel_6 = new JPanel();
        JPanel panel_7 = new JPanel();

        panel_7.setLayout(new BorderLayout());
        panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS)); //tekst od lewej do prawej
        panel_5.setLayout(new BorderLayout());

        JLabel info_label = new JLabel("Info");
        JLabel status_label = new JLabel("Status");
        JTextField status_textfield = new JTextField("ON");

        txtrCos = new JTextArea();
        txtrCos.setWrapStyleWord(true);
        txtrCos.setEditable(false);
        txtrCos.setLineWrap(true);
        txtrCos.setBorder(new LineBorder(new Color(0, 0, 0)));
        txtrCos.setFont(new Font("Monospaced", Font.PLAIN, 18));

        info_textfield.setEnabled(false);
        status_textfield.setEnabled(false);
        info_textfield.setDisabledTextColor(Color.black);
        status_textfield.setDisabledTextColor(Color.black);

        titledBorder = BorderFactory.createTitledBorder("Uzyskany rezultat");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        panel_7.setBorder(titledBorder);

        panel_5.add(createCalculationPanel(), BorderLayout.NORTH); //dodawanie do paneli
        panel_7.add(new JScrollPane(txtrCos));
        panel_5.add(panel_7, BorderLayout.CENTER);
        panel_6.add(Box.createHorizontalStrut(10));
        panel_6.add(info_label);
        panel_6.add(Box.createHorizontalStrut(10));
        panel_6.add(info_textfield);
        panel_6.add(Box.createHorizontalStrut(10));
        panel_6.add(status_label);
        panel_6.add(Box.createHorizontalStrut(10));
        panel_6.add(status_textfield);

        panel_6.setPreferredSize(new Dimension(1, 30)); //wysokosc info panelu
        panel_5.add(panel_6, BorderLayout.SOUTH);

        return panel_5;
    }

    private JPanel createNorthPanel() {
        panel_8 = new JPanel();
        panel_8.setLayout(new BorderLayout());

        panel_8.add(createNorthNorthPanel(), BorderLayout.NORTH);
        panel_8.add(createTableAndButtonsPanel(), BorderLayout.CENTER);

        return panel_8;
    }

    private void panelInitialization() // panel łaczacy polnocny i południowy, aby były pod sobą
    {
        contentPanel = new JPanel();

        contentPanel.setLayout(new GridLayout(2, 1, 10, 10));

        contentPanel.add(createNorthPanel());
        contentPanel.add(createSouthPanel());
    }

    private void initialize() //
    {
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public JButton createJButtonToolBar(String tooltip,Icon icon) {
        JButton jb = new JButton("",icon); //
        jb.setToolTipText(tooltip); //
        return jb;
    }

    public Icon createMyIcon(String file) //funkcja do pobierania ikon
    {
        String name = "/Graphics/" + file;
        Icon icon = new ImageIcon(getClass().getResource(name)); //wziecie nazwy folderu
        return icon;
    }

    private void createIcons()
    {
        try
        {
            add_icon = createMyIcon("add.png");
            average_icon = createMyIcon("average.png");
            help_icon = createMyIcon("help.png");
            info_icon = createMyIcon("info.png");
            max_icon = createMyIcon("max.png");
            min_icon = createMyIcon("min.png");
            reset_icon = createMyIcon("reset.png");
            save_icon = createMyIcon("save.jpg");
            sum_icon = createMyIcon("sum.png");
        }
        catch (Exception e)
        {
            System.out.println("ERROR - BLAD TWORZENIA IKONEK");
        }
    }

    public void CloseWindowListener(WindowAdapter wi) {addWindowListener(wi);}

    public void CloseApplicationActionListener(ActionListener al) //akcja po kliknieciu przycisku
    {
        mntmNewMenuItem_1.addActionListener(al);
    }

    public void InfoActionListener(ActionListener al) {
        btnNewButton_8.addActionListener(al);
        mntmNewMenuItem.addActionListener(al);
    }

    public void HelpActionListener(ActionListener al) {
        mntmNewMenuItem_10.addActionListener(al);
        btnNewButton_7.addActionListener(al);
    }

    public void AddActionListener(ActionListener al) {
        add_btn.addActionListener(al);
        btnNewButton.addActionListener(al);
        mntmNewMenuItem_2.addActionListener(al);
    }

    public void SumActionListener(ActionListener al) {
        mntmNewMenuItem_6.addActionListener(al);
        btnNewButton_3.addActionListener(al);
    }

    public void AverageActionListener(ActionListener al) {
        btnNewButton_4.addActionListener(al);
        mntmNewMenuItem_9.addActionListener(al);
    }

    public void MinActionListener(ActionListener al) {
        btnNewButton_5.addActionListener(al);
        mntmNewMenuItem_7.addActionListener(al);
    }

    public void MaxActionListener(ActionListener al) {
        btnNewButton_6.addActionListener(al);
        mntmNewMenuItem_8.addActionListener(al);
    }

    public void ResetActionListener(ActionListener al) {
        btnNewButton_2.addActionListener(al);
        mntmNewMenuItem_3.addActionListener(al);
        reset_btn.addActionListener(al);
    }

    public void SaveActionListener(ActionListener al) {
        save_btn.addActionListener(al);
        btnNewButton_1.addActionListener(al);
        mntmNewMenuItem_4.addActionListener(al);
    }

    public void CalculateActionListener(ActionListener al) {
        calculate_btn.addActionListener(al);
        mntmNewMenuItem_5.addActionListener(al);
    }

    public int getColumnSpinner() // blad przy wprowadzaniu liter
    {
        int value = 0;
        try {
            value = (Integer) column_spinner.getValue() - 1;
        } catch (Exception ex) {
            showError("Proszę o podanie prawidłowej liczby!");
        }
        return value;
    }

    public int getRowSpinner() {
        int value = 0;
        try {
            value = (Integer) row_spinner.getValue() - 1;
        } catch (Exception ex) {
            showError("Proszę o podanie prawidłowej liczby!");
        }
        return value;
    }

    public String getUserInput() {return textField.getText();} //wziecie informacji z geetext

    public void writeTextArea(String text)
    {
        txtrCos.setText(text);
    }

    public int getSelectedComboBoxItem() {
        return combo_box.getSelectedIndex();
    }

    public void addToTable(int value, int row, int column)
    {
        table_class.setValue(row, column, value);
    }

    public void showError(String msg) // pokazanie erroerów
    {
        JOptionPane.showMessageDialog(this, msg);
    }
}

class Handlers {
    MainWindow window;
    Table table_class;
    File file;
    Handlers(MainWindow window, Table table_class, File file)
    {
        this.window = window;
        this.table_class = table_class;
        this.file = file;
    }
    public void createActions()
    {
        window.SumActionListener(new Listener.SumListener(window, table_class));
        window.CloseApplicationActionListener(new Listener.CloseApplicationListener(window, table_class));
        window.InfoActionListener(new Listener.InfoListener(window, table_class));
        window.HelpActionListener(new Listener.HelpListener(window, table_class));
        window.AddActionListener(new Listener.AddListener(window, table_class));
        window.AverageActionListener(new Listener.AverageListener(window, table_class));
        window.MinActionListener(new Listener.MinListener(window, table_class));
        window.MaxActionListener(new Listener.MaxListener(window, table_class));
        window.ResetActionListener(new Listener.ResetListener(window, table_class));
        window.SaveActionListener(new Listener.SaveListener(window, table_class, file));
        window.CalculateActionListener(new Listener.CalculateListener(window, table_class));
    }
}

class Listener {
    static class CloseApplicationListener implements ActionListener // zamkniecie aplikacji
    {
        MainWindow window;
        Table table_class;

        public CloseApplicationListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    static class SumListener implements ActionListener {
        MainWindow window;
        Table table_class;

        public SumListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int ans = table_class.AddTableElements();

            String helper = "Suma elementów wynosi " + Double.toString(ans);
            window.writeTextArea(helper);
        }
    }

    static class InfoListener implements ActionListener {
        MainWindow window;
        Table table_class;

        public InfoListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            window.info = new Info();
            window.info.setVisible(true);
        }
    }

    static class HelpListener implements ActionListener {
        MainWindow window;
        Table table_class;

        public HelpListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            window.help = new Help();
            window.help.setVisible(true);
        }
    }

    static class AddListener implements ActionListener //dodanie
    {
        MainWindow window;
        Table table_class;

        public AddListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            try {
                int column = window.getColumnSpinner();
                int row = window.getRowSpinner();
                String userOutput = window.getUserInput();

                int value = Integer.parseInt(userOutput); //litera na cyfre, tutaj blad, wyjatek

                window.addToTable(value, row, column);
                table_class.setValue(row, column, value);
                window.table.repaint(); //AUTOMATYCZNIE DODANIE
                String temp = "Wartość została dodana do tablicy";
                window.writeTextArea(temp);
            } catch (Exception ex)
            {
                window.showError("Błąd wprowadzonej wartości");
                window.info_textfield.setText("ERROR, wprowadź poprawną liczbę!");
                Timer t = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        window.info_textfield.setText("");
                    }
                });
                t.setRepeats(false);
                t.start();
            }
        }
    }

    static class AverageListener implements ActionListener //średnia
    {
        MainWindow window;
        Table table_class;

        AverageListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            double ans = table_class.AverageTableElements();

            String temp = "Srednia elementów wynosi " + Double.toString(ans);
            window.writeTextArea(temp);
        }
    }

    static class MinListener implements ActionListener {
        MainWindow window;
        Table table_class;

        public MinListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int ans = table_class.MinTableElement();

            String temp = "Najmniejszy z elementów wynosi " + Double.toString(ans);
            window.writeTextArea(temp);
        }
    }

    static class MaxListener implements ActionListener {
        MainWindow window;
        Table table_class;

        public MaxListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int ans = table_class.MaxTableElement();

            String temp = "Największy z elementów wynosi " + Double.toString(ans);
            window.writeTextArea(temp);
        }
    }

    static class MinMaxListener implements ActionListener {
        MainWindow window;
        Table table_class;

        public MinMaxListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int max = table_class.MaxTableElement();
            int min = table_class.MinTableElement();

            String temp = "Największy z elementów wynosi " + Double.toString(max) + '\n' + "Najmniejszy z elementów " +
                    "wynosi " + Double.toString(min);
            window.writeTextArea(temp);
        }

    }

    static class ResetListener implements ActionListener //reset
    {
        MainWindow window;
        Table table_class;

        public ResetListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            table_class.resetValues();

            String temp = "Tablica została wyzerowana.";
            window.writeTextArea(temp);
        }
    }

    static class SaveListener implements ActionListener //zapisanie
    {
        MainWindow window;
        Table table_class;
        File file;

        public SaveListener(MainWindow window, Table table_class, File file) {
            this.window = window;
            this.table_class = table_class;
            this.file = file;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String ObjButtons[] = {"Tak", "Nie"};
            int PromptResult = JOptionPane.showOptionDialog(null,
                    "Czy aby napewno chcesz zapisać tablcę do pliku?", "Zapis do pliku",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
            if (PromptResult == 0) {
                file.saveToFile(table_class.getTable_data());
                String temp = "Tablica została zapisana";
                window.writeTextArea(temp);
            }
        }
    }

    static class CalculateListener implements ActionListener //combobox
    {
        MainWindow window;
        Table table_class;

        public CalculateListener(MainWindow window, Table table_class) {
            this.window = window;
            this.table_class = table_class;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) //combobox
        {
            int index = window.getSelectedComboBoxItem();

            switch (index) {
                case 0:
                    SumListener sum = new SumListener(window, table_class);
                    sum.actionPerformed(actionEvent);
                    break;
                case 1:
                    AverageListener average = new AverageListener(window, table_class);
                    average.actionPerformed(actionEvent);
                    break;
                case 2:
                    MinMaxListener minmax = new MinMaxListener(window, table_class);
                    minmax.actionPerformed(actionEvent);
                    break;
            }
        }
    }
}