package com.example.excelreader.Model;

import com.example.excelreader.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private List<String> headers;
    private List<User> value;
}
