package gui;
import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame {

    private TextPanel textPanel;
    private ToolBar toolbar;
    private FormPanel formPanel;

    MainFrame() {
        super("Hackathon HSE");


        setLayout(new BorderLayout());


        toolbar = new ToolBar();

        textPanel = new TextPanel();
        formPanel = new FormPanel();

        toolbar.setStringListener(new StringListener() {
            @Override
            public void textEmmited(String text) {
                textPanel.appendText(text);
            }
        });


        add(toolbar, BorderLayout.NORTH);

        add(formPanel, BorderLayout.WEST);
        add(textPanel, BorderLayout.CENTER);
        //setSize(1366,768);
        setPreferredSize(new Dimension(1100,768));
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
