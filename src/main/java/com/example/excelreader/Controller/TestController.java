package com.example.excelreader.Controller;

import com.example.excelreader.Model.Path;
import com.example.excelreader.Service.ExportExcel.ExportExcel;
import com.example.excelreader.Service.ReadExcel.ReadExcelDTB;
import com.example.excelreader.Service.ReadExcel.ReadExcelFileService;
import com.example.excelreader.Model.ResponseData;
import com.example.excelreader.repository.ImeisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    ImeisRepo imeisRepo;
    @PostMapping("/excelReader")
    @CrossOrigin(origins = "http://localhost:3000")
    public String excelReader(@RequestParam("file") MultipartFile file) throws IOException {
        readExcelDTB.writeToDB(readExcelFileService.readExcelFile(file));
        return "OK";
    }

    @PostMapping("/exportExcel/{fileName}")
    @CrossOrigin(origins = "http://localhost:3000")
    public String exportExcel(@PathVariable String fileName, @RequestBody Path path) throws IOException {
        exportExcel.exportExcel(fileName, path.getData());
        return "OK";
    }

    @GetMapping("/getUsers")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseData exportExcel() {
        return new ResponseData(imeisRepo.findHeaders(), imeisRepo.getAllImeis());
    }
}
