package com.example.excelreader.Service.ExportExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportExcel {
    private List<List<String>> defaultValue = new ArrayList<>();

    @Bean
    public void generateData() {
        List<String> rowData0 = new ArrayList<>();
        rowData0.add("id");
        rowData0.add("name");
        rowData0.add("pass");
        defaultValue.add(rowData0);

        List<String> rowData1 = new ArrayList<>();
        rowData1.add("1");
        rowData1.add("Quang");
        rowData1.add("12345");
        defaultValue.add(rowData1);

        List<String> rowData2 = new ArrayList<>();
        rowData2.add("2");
        rowData2.add("Hai");
        rowData2.add("11111");
        defaultValue.add(rowData2);

        List<String> rowData3 = new ArrayList<>();
        rowData3.add("3");
        rowData3.add("Phuc");
        rowData3.add("22222");
        defaultValue.add(rowData3);
    }

    public void exportExcel(String fileName) throws IOException {
        boolean firstRow = true;

        //Create WorkBook
        Workbook workbook = new XSSFWorkbook();

        //Create Sheet
        Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("List User"));

        //Create row
        for (int i=0; i<defaultValue.size(); i++){
            //Create Row
            Row row = sheet.createRow(i );

            //Create Cell
            for (int j=0 ;j<defaultValue.get(i).size(); j++){
                Cell cell = row.createCell(j);
                cell.setCellValue(defaultValue.get(i).get(j));
            }
        }

        //Export
        saveExcel(workbook,fileName );
    }

    public void saveExcel(Workbook workbook, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/Admin/Downloads/basic-authen/" + fileName + ".xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }
}
