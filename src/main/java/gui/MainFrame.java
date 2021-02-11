package gui;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import java.awt.*;

import static gui.ToolBar.textListener;


class MainFrame extends JFrame {

    private TextPanel textPanel;
    private ToolBar toolbar;
    private FormPanel formPanel;
    final static String THEME = "Ocean";
    final static String LOOKANDFEEL = "Ocean";

    private static void initLookAndFeel() throws UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        String lookAndFeel = null;



        UIManager.setLookAndFeel("javax.swing.plaf.metal.OceanTheme");


    }



    MainFrame() {
        super("Hackathon HSE");

        //UIManager.put("nimbusBase", new Color(120,53,51));
        //UIManager.put("nimbusBlueGrey", new Color(103,101,101));
        UIManager.put("control", new Color(49,53,51));
        UIManager.put("TextArea.background", new Color(103,101,101));
        UIManager.put("TextArea.foreground", new Color(255,255,255));
        //UIManager.put("InternalFrame.background", new Color(49,53,51));

        //UIManager.put("activeCaption", new Color(49,53,51));
        //UIManager.put("InternalFrame.activeTitleForeground", new Color(49,53,51));
        //setBackground(new Color(49,53,51));

        //UIManager.put("Button.background", new Color(49,53,51));
        //UIManager.put("Button.foreground", new Color(255,255,255));
        //UIManager.put("TextArea.font", new Color(255,85,85));
        //UIManager.put("control", new Color(49,53,51));
        //UIManager.put("control", new Color(40,42,54));

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        //setDefaultLookAndFeelDecorated(true);

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

        setPreferredSize(new Dimension(1600, 900));
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
