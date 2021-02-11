package gui;

import externalcode.data.to.operate.ExternalCodeStarter;
import org.apache.poi.ss.usermodel.CellType;


import javax.swing.*;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.concurrent.TimeUnit;


public class ToolBar extends JPanel implements ActionListener {

    private JButton filechooseButtonOne;
    private JButton filechooseButtonTwo;
    private JButton filechooseButtonThree;
    private JButton filechooseButtonFour;
    public static StringListener textListener;
    private JFileChooser fch;
    private static boolean isOnePressed = false;
    private static boolean isTwoPressed = false;
    private static boolean isThreePressed = false;
    private static boolean isFourPressed = false;

    File file1;
    File file2;
    File file3;
    File file4;



    ToolBar() {

/*        file1 = new File("inputdata/Технологии выращивания.xlsx");
        file2 = new File("inputdata/Структура посевных площадей.xlsx");
        file3 = new File("inputdata/Список самоходной техники и прицепного оборудования.xlsx");
        file4 = new File("inputdata/Виды работ и нормы выработки.xlsx");*/


        setBorder(BorderFactory.createEtchedBorder());


        filechooseButtonOne = new JButton("Технологии выращивания");
        filechooseButtonTwo = new JButton("Структура посевных площадей");
        filechooseButtonThree = new JButton("Список самоходной техники и прицепного оборудования");
        filechooseButtonFour = new JButton("Виды работ и нормы выработки");
        fch = new JFileChooser();

        filechooseButtonOne.addActionListener(this);
        filechooseButtonTwo.addActionListener(this);
        filechooseButtonThree.addActionListener(this);
        filechooseButtonFour.addActionListener(this);

        //setLayout(new FlowLayout(FlowLayout.LEFT));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        add(filechooseButtonOne);
        add(filechooseButtonTwo);
        add(filechooseButtonThree);
        add(filechooseButtonFour);
 /*       DataUtilities.processButtonOne(file1);
        DataUtilities.processButtonTwo(file2);
        DataUtilities.processButtonThree(file3);
        DataUtilities.processButtonFour(file4);*/
        //ExternalCodeStarter ex = new ExternalCodeStarter();
    }

    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        JButton clicked = (JButton) e.getSource();

        if (e.getSource() == filechooseButtonOne && !ToolBar.isOnePressed) {
            if (textListener != null) {

                int flag = fch.showOpenDialog(this);
                if (flag == JFileChooser.APPROVE_OPTION) {
                    file1 = fch.getSelectedFile();
                    if (file1.getName().equals("Технологии выращивания.xlsx")) {
                        textListener.textEmmited("Файл " + file1.getName() + " успешно загружен " + "." + '\n');
                        filechooseButtonOne.setBackground(Color.green);
                        DataUtilities.processButtonOne(file1);

/*
                        for (Crop crop : Data.getCropList()) {
                            for (AgroProcedure procedure : crop.getListOfProcedures()) {
                                textListener.textEmmited("Имя процедуры " + procedure.getName() + " начало " +
                                        procedure.getStartOfProcedure()+
                                        " конец " + procedure.getEndOfProcedure() + '\n');
                            }

                        }*/


                    } else if ((!file1.getName().equals("Технологии выращивания.xlsx"))) {
                        textListener.textEmmited("Неправильное имя или расширение файла. Попробуйте запустить программу еще раз!");
                        filechooseButtonOne.setBackground(Color.red);
                        ToolBar.isOnePressed = false;
                    }
                } else {
                    textListener.textEmmited("Открытие файла отменено пользователем" + "." + '\n');
                }
                ToolBar.isOnePressed = true;

            }

        } else if (e.getSource() == filechooseButtonTwo && !ToolBar.isTwoPressed) {
            if (textListener != null) {

                int flag = fch.showOpenDialog(this);
                if (flag == JFileChooser.APPROVE_OPTION) {
                    file2 = fch.getSelectedFile();
                    if (file2.getName().equals("Структура посевных площадей.xlsx")) {
                        textListener.textEmmited("Файл " + file2.getName() + " успешно загружен " + "." + '\n');
                        filechooseButtonTwo.setBackground(Color.green);
                        DataUtilities.processButtonTwo(file2);
/*

                        for (String st : Data.getListOfField().keySet()) {
                            textListener.textEmmited(Data.getListOfField().get(st).getRowNum() + " Поле: "
                                    + " " + Data.getListOfField().get(st).getNameId()
                                    + " " + Data.getListOfField().get(st).getArea() + " " +
                                    Data.getListOfField().get(st).getCrop() + '\n');
                        }
*/


                    } else if ((!file2.getName().equals("Структура посевных площадей.xlsx"))) {
                        textListener.textEmmited("Неправильное имя или расширение файла. Попробуйте запустить программу еще раз!");
                        filechooseButtonTwo.setBackground(Color.red);
                    }

                } else {
                    textListener.textEmmited("Открытие файла отменено пользователем" + "." + '\n');
                }
                ToolBar.isTwoPressed = true;

            }

        } else if (e.getSource() == filechooseButtonThree && !ToolBar.isThreePressed) {
            if (textListener != null) {

                int flag = fch.showOpenDialog(this);
                if (flag == JFileChooser.APPROVE_OPTION) {

                    file3 = fch.getSelectedFile();
                    if (file3.getName().equals("Список самоходной техники и прицепного оборудования.xlsx")) {
                        textListener.textEmmited("Файл " + file3.getName() + " успешно загружен " + "." + '\n');
                        filechooseButtonThree.setBackground(Color.green);

                        DataUtilities.processButtonThree(file3);
                        int i = 2;
/*                        for (Selfmoveable sm : Data.getTractorList()) {

                            textListener.textEmmited(i++ + "              " + sm.getName() + "                 " + sm.getType() + '\n');
                        }*/



                    } else if ((!file3.getName().equals("Список самоходной техники и прицепного оборудования.xlsx"))) {
                        textListener.textEmmited("Неправильное имя или расширение файла. Попробуйте запустить программу еще раз!");
                        filechooseButtonThree.setBackground(Color.red);
                    }
                } else {
                    textListener.textEmmited("Открытие файла отменено пользователем" + "." + '\n');
                }
            }
            ToolBar.isThreePressed = true;
        } else if (e.getSource() == filechooseButtonFour && !ToolBar.isFourPressed) {
            if (textListener != null) {

                int flag = fch.showOpenDialog(this);
                if (flag == JFileChooser.APPROVE_OPTION) {

                    file4 = fch.getSelectedFile();
                    if (file4.getName().equals("Виды работ и нормы выработки.xlsx")) {
                        textListener.textEmmited("Файл " + file4.getName() + " успешно загружен " + "." + '\n');
                        filechooseButtonFour.setBackground(Color.green);

                        DataUtilities.processButtonFour(file4);



                    } else if ((!file4.getName().equals("Виды работ и нормы выработки.xlsx"))) {
                        textListener.textEmmited("Неправильное имя или расширение файла. Попробуйте запустить программу еще раз!");
                        filechooseButtonFour.setBackground(Color.red);
                    }
                } else {
                    textListener.textEmmited("Открытие файла отменено пользователем" + "." + '\n');
                }
            }
            ToolBar.isFourPressed = true;


        }
    }
}
