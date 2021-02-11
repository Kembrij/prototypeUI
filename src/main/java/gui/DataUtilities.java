package gui;

import externalcode.action.DynamicDataHolder;
import externalcode.technology.GrowingTechEntry;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class DataUtilities {
    public static DynamicDataHolder mydataholder;

    static void processButtonOne(File file) {
        int x;


        try {
            Workbook workbook = new XSSFWorkbook(file);
            ArrayDeque<Cell> ad = new ArrayDeque<>();
            Iterator<Sheet> sheetiter = workbook.sheetIterator();
            while (sheetiter.hasNext()) {

                Sheet sheet = sheetiter.next();
                Crop crop = new Crop(sheet.getSheetName());
                Iterator<Row> rowiter = sheet.iterator();

                while (rowiter.hasNext()) {

                    Row row = rowiter.next();
                    if (row.getRowNum() > 0) {
                        Iterator<Cell> celliter = row.cellIterator();

                        while (celliter.hasNext()) {
                            ad.add(celliter.next());
                        }

                        AgroProcedure ap = new AgroProcedure(ad.pop().getStringCellValue());
                        Date startDate = ad.pop().getDateCellValue();
                        Date finishDate = ad.pop().getDateCellValue();

                        LocalDate sDate = Instant.ofEpochMilli(startDate.getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

                        LocalDate fLocalDate = Instant.ofEpochMilli(finishDate.getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

                        Calendar startLocalDate = new GregorianCalendar.Builder().setDate(sDate.getYear(),
                                sDate.getMonthValue(), sDate.getDayOfMonth()).build();
                        Calendar finishLocalDate = new GregorianCalendar.Builder().setDate(fLocalDate.getYear(),
                                fLocalDate.getMonthValue(), fLocalDate.getDayOfMonth()).build();


                        ap.setStartOfProcedure(startLocalDate);
                        ap.setEndOfProcedure(finishLocalDate);
                        Data.addProcedureToSet(ap);
                        crop.addAgroProcedure(ap);
                    }
                }
                Data.addToCropList(crop);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    static void processButtonTwo(File file) {

        try {
            Workbook workbook = new XSSFWorkbook(file);

            ArrayDeque<Cell> ad = new ArrayDeque<>();
            Iterator<Sheet> sheetiter = workbook.sheetIterator();

            Sheet sheet = sheetiter.next();

            //Crop crop = new Crop(sheet.getSheetName());

            Iterator<Row> rowiter = sheet.iterator();

            while (rowiter.hasNext()) {
                Row row = rowiter.next();
                if (row.getRowNum() > 1) {
                    Iterator<Cell> celliter = row.cellIterator();
                    while (celliter.hasNext()) {
                        ad.add(celliter.next());
                    }

                    Cell one = ad.pop();
                    Cell two = ad.pop();
                    Cell three = ad.pop();

                    String nameId;
                    Field field;

                    if (one.getCellType() == CellType.STRING) {
                        nameId = one.getStringCellValue();
                        field = new Field(nameId);
                        if (two.getCellType() == CellType.NUMERIC) {
                            field.setArea(two.getNumericCellValue());
                        }
                        if (three.getCellType() == CellType.STRING)
                            field.setCrop(three.getStringCellValue());
                        field.setRowNum(row.getRowNum() + 1);
                        Data.addToFieldList(field.getNameId(), field);
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();

        }
    }

    static void processButtonThree(File file) {

        try {
            Workbook workbook = new XSSFWorkbook(file);
            ArrayDeque<Cell> ad = new ArrayDeque<>();
            Iterator<Sheet> sheetiter = workbook.sheetIterator();
            while (sheetiter.hasNext()) {

                Sheet sheet = sheetiter.next();
                if (sheet.getSheetName().equals("Самоходная техника")) {
                    Iterator<Row> rowiter = sheet.iterator();

                    while (rowiter.hasNext()) {
                        Tractor tr = new Tractor();
                        Sprayer spr = new Sprayer();
                        Row row = rowiter.next();

                        if (row.getRowNum() > 0) {
                            Iterator<Cell> celliter = row.cellIterator();
                            while (celliter.hasNext()) {
                                ad.add(celliter.next());
                            }
                            Cell one = ad.pop();
                            Cell two = ad.pop();

                            if (one.getCellType() == CellType.STRING) {
                                if (!(one.getStringCellValue().contains("Опрыскиватель"))) {
                                    tr.setName(one.getStringCellValue());
                                    //Data.addToTractorList(tr);
                                } else {
                                    spr.setName(one.getStringCellValue());
                                    //Data.addToTractorList(spr);
                                }


                            }
                            if (two.getCellType() == CellType.STRING) {
                                if (!(two.getStringCellValue().contains("Опрыскиватель"))) {
                                    tr.setType(two.getStringCellValue());
                                    Data.addToTractorList(tr);
                                    Data.addNumToTractorsRegistry(tr.getName());
                                } else {
                                    spr.setType(two.getStringCellValue());
                                    Data.addToTractorList(spr);
                                    //Data.addNumToTractorsRegistry(spr.getName());
                                }
                            }
                        }


                    }
                } else if (sheet.getSheetName().equals("Прицепное оборудование")) {
                    Iterator<Row> rowiter = sheet.iterator();
                    while (rowiter.hasNext()) {
                        TrailedMachine trm = new TrailedMachine();

                        Row row = rowiter.next();
                        if (row.getRowNum() > 0) {
                            Iterator<Cell> celliter = row.cellIterator();
                            while (celliter.hasNext()) {
                                ad.add(celliter.next());
                            }

                            Cell one = ad.pop();
                            Cell two = ad.pop();

                            if (one.getCellType() == CellType.STRING) {
                                trm.setName(one.getStringCellValue());
                            }
                            if (two.getCellType() == CellType.STRING) {
                                trm.setType(two.getStringCellValue());
                            }
                        }
                        Data.addToTrailedMachinesList(trm);
                        Data.addNumToTrailedMachinesRegistry(trm.getName());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    static void processButtonFour(File file) {

        try {
            Workbook workbook = new XSSFWorkbook(file);
            ArrayDeque<Cell> ad = new ArrayDeque<>();
            Iterator<Sheet> sheetiter = workbook.sheetIterator();

            while (sheetiter.hasNext()) {

                Sheet sheet = sheetiter.next();
                Iterator<Row> rowiter = sheet.iterator();

                if (sheet.getSheetName().equals("Виды работ")) {
                    while (rowiter.hasNext()) {
                        Row row = rowiter.next();
                        if (row.getRowNum() > 0) {
                            Iterator<Cell> celliter = row.cellIterator();
                            while (celliter.hasNext()) {
                                ad.add(celliter.next());
                            }
                            Cell one = ad.pop();
                            Cell two = ad.pop();
                            StringBuilder sb = new StringBuilder();
                            sb.append(one.getStringCellValue());
                            sb.append(";");
                            sb.append(two.getNumericCellValue());
                            Data.addTolistOfTypeAndShiftDependency(sb.toString());

                            if (one.getCellType() == CellType.STRING && two.getCellType() == CellType.NUMERIC) {

                                for (int i = 0; i < Data.getCropList().size(); i++) {
                                    for (int ii = 0; ii < Data.getCropList().get(i).getListOfProcedures().size(); ii++) {
                                        if (Data.getCropList().get(i).getListOfProcedures().get(ii).getName()
                                                .equals(one.getStringCellValue())) {
                                            Data.getCropList().get(i).getListOfProcedures().get(ii).
                                                    setNumberOfShifts(two.getNumericCellValue());
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (sheet.getSheetName().equals("Нормы выработки")) {

                    while (rowiter.hasNext()) {
                        Row row = rowiter.next();
                        if (row.getRowNum() > 0) {
                            Iterator<Cell> celliter = row.cellIterator();
                            while (celliter.hasNext()) {
                                Cell cell = celliter.next();
                                // System.out.println(cell.getAddress());
                                ad.add(cell);
                            }

                            //System.out.println(new ArrayList<>(ad));
                            Cell one = ad.pop();
                            Cell two = ad.pop();
                            Cell three = ad.pop();
                            Cell four = ad.pop();

                            StringBuilder sb = new StringBuilder();
                            sb.append(one.getStringCellValue());
                            sb.append(";");
                            sb.append(two.getStringCellValue());
                            sb.append(";");
                            sb.append(three.getStringCellValue());
                            sb.append(";");
                            sb.append(four.getNumericCellValue());
                            Data.addTostringOutputStandards(sb.toString());


                            if (one.getStringCellValue().contains("Опрыскиватель") ||
                                    one.getStringCellValue().contains("опрыскиватель")) {
                                for (Selfmoveable sm : Data.getTractorList()) {
                                    if (sm.getClass().equals(Sprayer.class)) {
                                        sm.setRateOfOutPut(four.getNumericCellValue());
                                    }
                                }
                            } else if (one.getCellType() == CellType.STRING &&
                                    four.getCellType() == CellType.NUMERIC) {
                                for (TrailedMachine tm : Data.getTrailedMachinesList()) {
                                    if (one.getStringCellValue().equals(tm.getType())) {
                                        tm.setRateOfOutPut(four.getNumericCellValue());
                                    }

                                }
                            }

                        }
                    }
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }


    public static Workbook processExportButton() {
        List<GrowingTechEntry> list = mydataholder.getFailedOperations();
        Workbook workbook = new XSSFWorkbook();


        CreationHelper creationHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("Результаты");

        int i = 0;
        int y = 0;
        while (i < 100) {
            Row row1 = sheet.createRow(i);
            i++;
            while (y < 100) {
                Cell cell = row1.createCell(y);
                y++;
            }

            // Cell cell = row.createCell(0);
            // cell.setCellValue("Хелло");
            ArrayDeque<String> ad = new ArrayDeque<>();
            ad.add("Имя культуры");
            ad.add("Вид работ");
            ad.add("Начало работ");
            ad.add("Конец работ");
            ad.add("Период работ");
            ad.add("Остаток от площади");
            ad.add("Срок окончания");
            ad.add("Дневной рэйт остатка");
            ad.add("Приоритет");


            Iterator<Row> rowiter = sheet.iterator();

            while (rowiter.hasNext()) {
                Row row2 = rowiter.next();

                if (row2.getRowNum() == 0) {
                    Iterator<Cell> celliter = row2.cellIterator();
                    int k = 0;
                    while (celliter.hasNext() && k < ad.size()) {
                        Cell cell = celliter.next();
                        k++;
                    }
                }



            }

        }
        return workbook;
    }

}


