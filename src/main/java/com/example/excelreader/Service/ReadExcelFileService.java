package com.example.excelreader.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadExcelFileService {
    public List<List<String>> readExcelFile(String fileName) throws IOException {
        //Check First Row
        boolean firstRow = true;
        //Đường dẫn file
        String URL = "C:/Users/Admin/Downloads/basic-authen/" + fileName + ".xlsx";
        //Logic
        FileInputStream file = new FileInputStream(new File(URL));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        List<List<String>> data = new ArrayList<>();
        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();
            for (Cell cell : row) {
                DataFormatter dataFormatter = new DataFormatter();
                String cellValue = dataFormatter.formatCellValue(cell);
                rowData.add(cellValue);
            }
            data.add(rowData);
        }
        workbook.close();
        file.close();
        return data;
    }
}
