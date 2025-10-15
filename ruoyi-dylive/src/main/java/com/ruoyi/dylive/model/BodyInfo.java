package com.ruoyi.dylive.model;

import lombok.Data;

import java.util.List;

@Data
public class BodyInfo {
    //请求体类型  none form-data x-www-form-data  raw binary GraphQL
    private String typeName;

    private List<FormData> formData;

    private RawData raw;
}
