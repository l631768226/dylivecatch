package com.ruoyi.dylive.model;

import lombok.Data;

import java.util.List;

@Data
public class RequestInfo {
    //请求头信息
    private List<HeaderModel> headers;

    //请求参数信息
    private BodyInfo body;
}
