package com.example.excelreader.Service.ReadExcel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadExcelFileService {
    public List<List<String>> readExcelFile(MultipartFile fileExcel) throws IOException {
        //Logic
        Workbook workbook = new XSSFWorkbook(fileExcel.getInputStream());
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
        return data;
    }
}
