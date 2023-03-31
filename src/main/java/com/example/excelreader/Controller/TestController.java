package com.example.excelreader.Controller;

import com.example.excelreader.Model.Path;
import com.example.excelreader.Service.ExportExcel.ExportExcel;
import com.example.excelreader.Service.ReadExcel.ReadExcelDTB;
import com.example.excelreader.Service.ReadExcel.ReadExcelFileService;
import com.example.excelreader.Model.ResponseData;
import com.example.excelreader.entity.Template;
import com.example.excelreader.repository.ImeisRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
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
    public String excelReader(@RequestParam("file") MultipartFile file, @RequestParam("template") String template) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Template object = mapper.readValue(template, Template.class);
        readExcelDTB.writeToDB(readExcelFileService.readExcelFile(file));
        return "OK";
    }

    @PostMapping("/exportExcel/{fileName}")
    public String exportExcel(@PathVariable String fileName, @RequestBody Path path) throws IOException {
        exportExcel.exportExcel(fileName, path.getData());
        return "OK";
    }

    @GetMapping("/getUsers")
    public ResponseData exportExcel() {
        return new ResponseData(imeisRepo.findHeaders(), imeisRepo.getAllImeis());
    }
}
