package com.example.excelreader.Controller;

import com.example.excelreader.Service.ReadExcelDTB;
import com.example.excelreader.Service.ReadExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class TestController {
    @Autowired
    ReadExcelFileService readExcelFileService;
    @Autowired
    ReadExcelDTB readExcelDTB;
    @GetMapping("/excelReader/{fileName}")
    public String excelReader(@PathVariable String fileName) throws IOException {
        readExcelDTB.writeToDB(readExcelFileService.readExcelFile(fileName));
        return "OK";
    }
}
