package gui;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FormPanel extends JPanel  implements ActionListener {

/*    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;*/

    private JButton filechooseButtonOne;
    private JButton filesaveButtonTwo;
    /*    private JButton filechooseButtonThree;
        private JButton filechooseButtonFour;*/
    private StringListener textListener;
    private JFileChooser fch;
    private static boolean isOnePressed = false;
    private static boolean isTwoPressed = false;
    private static boolean isThreePressed = false;
    private static boolean isFourPressed = false;

    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        filechooseButtonOne = new JButton("Обработка");
        filechooseButtonOne.setPreferredSize(new Dimension(200, 50));

        filesaveButtonTwo = new JButton("Экспорт");
        filesaveButtonTwo.setPreferredSize(new Dimension(200, 50));

  /*      filechooseButtonThree = new JButton("Список самоходной техники и прицепного оборудования");
        filechooseButtonThree.setPreferredSize(new Dimension(200,50));
        filechooseButtonFour = new JButton("Виды работ и нормы выработки");
        filechooseButtonFour.setPreferredSize(new Dimension(200,50));*/


        /*
        nameLabel = new JLabel("Name: ");
*//*        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okBtn = new JButton("ОК");*//*
         */

        Border innerBorder = BorderFactory.createTitledBorder("");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.LINE_AXIS);
        setLayout(boxLayout);
        fch = new JFileChooser();
        filechooseButtonOne.setAlignmentX(0);
        filechooseButtonOne.setAlignmentY(0);
        filesaveButtonTwo.setAlignmentX(0);
        filesaveButtonTwo.setAlignmentY(0);
        filesaveButtonTwo.addActionListener(this);
        add(filechooseButtonOne, boxLayout);
        add(filesaveButtonTwo, boxLayout);
        setVisible(true);




    }

    @Override
    public void actionPerformed(ActionEvent e) {


        JButton clicked = (JButton) e.getSource();

        if (e.getSource() == filesaveButtonTwo) {
            int returnVal = fch.showSaveDialog(FormPanel.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fch.getSelectedFile();
                //This is where a real application would open the file.
                textListener.textEmmited("Сохраняется файл: " + file.getName() + "." + '\n');
            } else {
                textListener.textEmmited("Сохранение файла отменено пользователем" + '\n');
            }
            //log.setCaretPosition(log.getDocument().getLength());

        }
    }
}
