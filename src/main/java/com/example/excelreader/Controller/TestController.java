package com.example.excelreader.Controller;

import com.example.excelreader.Service.ExportExcel.ExportExcel;
import com.example.excelreader.Service.ReadExcel.ReadExcelDTB;
import com.example.excelreader.Service.ReadExcel.ReadExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    ReadExcelFileService readExcelFileService;
    @Autowired
    ReadExcelDTB readExcelDTB;
    @Autowired
    ExportExcel exportExcel;
    @GetMapping("/excelReader/{fileName}")
    public String excelReader(@PathVariable String fileName) throws IOException {
        readExcelDTB.writeToDB(readExcelFileService.readExcelFile(fileName));
        return "OK";
    }

    @GetMapping("/exportExcel/{fileName}")
    public String exportExcel(@PathVariable String fileName) throws IOException {
        exportExcel.exportExcel(fileName);
        return "OK";
    }
}
