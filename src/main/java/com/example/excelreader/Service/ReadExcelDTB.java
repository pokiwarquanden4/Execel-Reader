package com.example.excelreader.Service;

import com.example.excelreader.entity.User;
import com.example.excelreader.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadExcelDTB {
    @Autowired
    UserRepo userRepo;

    public void writeToDB (List<List<String>> values){
        //Check first row
        boolean firstRow = true;

        //Add to DB
        for (List<String> list : values){
            if (firstRow){
                firstRow = false;
            }else {
                User user = userRepo.findByName(list.get(1));
                if (user != null){
                    userRepo.updateUser(user.getId(), list.get(1), list.get(2));
                }else {
                    userRepo.save(new User(list.get(1), list.get(2)));
                };
            }
        }
    }
}
