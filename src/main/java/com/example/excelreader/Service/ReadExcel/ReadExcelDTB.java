package com.example.excelreader.Service.ReadExcel;

import com.example.excelreader.entity.Template;
import com.example.excelreader.repository.ImeisRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadExcelDTB {
    @Autowired
    ImeisRepo userRepo;

    public void writeToDB (List<List<String>> values){
        //Check first row
        boolean firstRow = true;

        //Add to DB
        for (List<String> list : values){
            if (firstRow){
                firstRow = false;
            }else {
                Template newTemplate = new Template(list.get(0));
                Template user = userRepo.findByImeis(newTemplate.getImeis());
                if (user != null){
                    userRepo.updateImeis(newTemplate.getId(), newTemplate.getImeis());
                }else {
                    userRepo.save(newTemplate);
                };
            }
        }
    }
}
