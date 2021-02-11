package gui;
import externalcode.data.to.operate.ExternalCodeStarter;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FormPanel extends JPanel  implements ActionListener {

/*    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;*/

    private JButton start;
    private JButton export;
    /*    private JButton filechooseButtonThree;
        private JButton filechooseButtonFour;*/
    private StringListener textListener;
    private JFileChooser fch;
    private static boolean isStartPressed = false;
    private static boolean isExportPressed = false;


    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        start = new JButton("Старт");
        start.setPreferredSize(new Dimension(180, 40));

        export = new JButton("Экспорт");
        export.setPreferredSize(new Dimension(180, 40));


        Border innerBorder = BorderFactory.createTitledBorder("");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        //BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        //setLayout(boxLayout);
        fch = new JFileChooser();
        start.setAlignmentX(0);
        start.setAlignmentY(1);
        export.setAlignmentX(0);
        export.setAlignmentY(1);

        export.addActionListener(this);
        start.addActionListener(this);

        add(start);
        add(export);
        setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == export) {
            int returnVal = fch.showSaveDialog(FormPanel.this);
            //FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xlsx","*.*");
            Workbook wb = DataUtilities.processExportButton();
            //fch.setFileFilter(filter);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fch.getSelectedFile();
               try (OutputStream fileOut2 = new FileOutputStream(fch.getSelectedFile())) {
                   wb.write(fileOut2);

               } catch (FileNotFoundException fileNotFoundException) {
                   fileNotFoundException.printStackTrace();
               } catch (Exception ee) {

               }
                textListener.textEmmited("Сохраняется файл: "  + "." + '\n');

            } else {
                textListener.textEmmited("Сохранение файла отменено пользователем" + '\n');
            }


        } else if (e.getSource() == start && !isStartPressed) {
                 ExternalCodeStarter ex = new ExternalCodeStarter();
                 if (ex == null) {
                     start.setBackground(Color.red);
                 }
                 isStartPressed = true;
                 start.setBackground(Color.green);

        }
    }
}
