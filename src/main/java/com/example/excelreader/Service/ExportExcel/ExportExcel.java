package com.example.excelreader.Service.ExportExcel;
import com.example.excelreader.entity.Template;
import com.example.excelreader.repository.ImeisRepo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExportExcel {
    @Autowired
    ImeisRepo userRepo;
    public void exportExcel(String fileName, String path) throws IOException {
        List<String> headers = userRepo.findHeaders();
        List<Template> imeis = userRepo.getAllImeis();

        //Create WorkBook
        Workbook workbook = new XSSFWorkbook();

        //Create Sheet
        Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Imeis"));

        //Create Total Requests
        Row rowTotalRequests = sheet.createRow(0);
        Cell cellTotalRequests = rowTotalRequests.createCell(0);
        cellTotalRequests.setCellValue("Total Requests: ");

        //Create Successful Requests
        Row rowSuccessfulRequests = sheet.createRow(1);
        Cell cellSuccessfulRequests = rowSuccessfulRequests.createCell(0);
        cellSuccessfulRequests.setCellValue("Total Successful Requests: ");

        //Create Header
        Row row = sheet.createRow(2);
        for (int i=0 ;i<headers.size(); i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(headers.get(i));
        }

        //Create Value
        for (int i=0 ;i<imeis.size(); i++){
            Row rowInner = sheet.createRow(i+3);
            ArrayList<String> value = new ArrayList<>();
            value.add(String.valueOf(imeis.get(i).getId()));
            value.add(imeis.get(i).getImeis());
            System.out.println(value);
            for (int j=0; j<value.size(); j++){
                Cell cell = rowInner.createCell(j);
                cell.setCellValue(value.get(j));
            }
        }

        //Export
        saveExcel(workbook,fileName, path );
    }

    public void saveExcel(Workbook workbook, String fileName, String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + fileName + ".xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }
}
