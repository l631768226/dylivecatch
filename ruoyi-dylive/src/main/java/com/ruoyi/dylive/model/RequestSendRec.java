package com.ruoyi.dylive.model;

import lombok.Data;

@Data
public class RequestSendRec {

    private String url;

    private String requestMethod;

    private RequestInfo requestInfo;

}
