package com.example.excelreader.Model;

import com.example.excelreader.entity.Template;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private List<String> headers;
    private List<Template> value;
}
