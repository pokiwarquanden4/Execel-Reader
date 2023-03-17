package com.example.excelreader.Service.ExportExcel;
import com.example.excelreader.entity.User;
import com.example.excelreader.repository.UserRepo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExportExcel {
    @Autowired
    UserRepo userRepo;
    public void exportExcel(String fileName, String path) throws IOException {
        List<String> headers = userRepo.findHeaders();
        List<User> users = userRepo.getAllUser();

        //Create WorkBook
        Workbook workbook = new XSSFWorkbook();

        //Create Sheet
        Sheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("List User"));

        Row row = sheet.createRow(0);
        for (int i=0 ;i<headers.size(); i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(headers.get(i));
        }

        for (int i=0 ;i<users.size(); i++){
            Row rowInner = sheet.createRow(i+1);

            ArrayList<String> value = new ArrayList<>();
            value.add(String.valueOf(users.get(i).getId()));
            value.add(users.get(i).getName());
            value.add(users.get(i).getPass());
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
