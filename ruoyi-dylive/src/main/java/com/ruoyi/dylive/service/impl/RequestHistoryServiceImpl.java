package com.ruoyi.dylive.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.dylive.domain.HeaderInfo;
import com.ruoyi.dylive.model.*;
import com.ruoyi.dylive.utils.CommenMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylive.mapper.RequestHistoryMapper;
import com.ruoyi.dylive.domain.RequestHistory;
import com.ruoyi.dylive.service.IRequestHistoryService;

/**
 * 请求历史信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
@Service
public class RequestHistoryServiceImpl implements IRequestHistoryService 
{
    @Autowired
    private RequestHistoryMapper requestHistoryMapper;

    /**
     * 查询请求历史信息
     * 
     * @param id 请求历史信息主键
     * @return 请求历史信息
     */
    @Override
    public RequestHistory selectRequestHistoryById(Integer id)
    {
        return requestHistoryMapper.selectRequestHistoryById(id);
    }

    /**
     * 查询请求历史信息列表
     * 
     * @param requestHistory 请求历史信息
     * @return 请求历史信息
     */
    @Override
    public List<RequestHistory> selectRequestHistoryList(RequestHistory requestHistory)
    {
        return requestHistoryMapper.selectRequestHistoryList(requestHistory);
    }

    /**
     * 新增请求历史信息
     * 
     * @param requestHistory 请求历史信息
     * @return 结果
     */
    @Override
    public int insertRequestHistory(RequestHistory requestHistory)
    {
        return requestHistoryMapper.insertRequestHistory(requestHistory);
    }

    /**
     * 修改请求历史信息
     * 
     * @param requestHistory 请求历史信息
     * @return 结果
     */
    @Override
    public int updateRequestHistory(RequestHistory requestHistory)
    {
        return requestHistoryMapper.updateRequestHistory(requestHistory);
    }

    /**
     * 批量删除请求历史信息
     * 
     * @param ids 需要删除的请求历史信息主键
     * @return 结果
     */
    @Override
    public int deleteRequestHistoryByIds(Integer[] ids)
    {
        return requestHistoryMapper.deleteRequestHistoryByIds(ids);
    }

    /**
     * 删除请求历史信息信息
     * 
     * @param id 请求历史信息主键
     * @return 结果
     */
    @Override
    public int deleteRequestHistoryById(Integer id)
    {
        return requestHistoryMapper.deleteRequestHistoryById(id);
    }

    /**
     * API调用接口
     * @param data 请求信息
     * @return 结果信息
     */
    @Override
    public AjaxResult send(RequestSendRec data) throws Exception {
        //获取请求地址
        String url = data.getUrl();
        //获取请求方法
        String method = data.getRequestMethod();
        //请求体内容
        RequestInfo requestInfo = data.getRequestInfo();

        if (StringUtils.isBlank(url) || StringUtils.isBlank(method) || requestInfo == null){
            //请求地址、请求方法或请求内容为空
            return AjaxResult.error();
        }
        //请求头信息
        List<HeaderModel> headerModels = requestInfo.getHeaders();
        //判断请求头
        if (headerModels == null || headerModels.isEmpty()){
            return AjaxResult.error("请求头信息不能缺失");
        }

        Date date = new Date();
        //将请求头转换成Map格式
        Map<String, String> headerMap = headerModels.stream()
                // 过滤 keyInfo 为 null 的元素（避免Map的key为null）
                .filter(header -> header.getKeyInfo() != null)
                .collect(Collectors.toMap(
                        HeaderModel::getKeyInfo,  // 键：HeaderModel的keyInfo
                        HeaderModel::getValueInfo, // 值：HeaderModel的valueInfo
                        (oldValue, newValue) -> newValue // 若key重复，保留新值（覆盖旧值）
                ));

        //获取请求体
        BodyInfo boyInfo = requestInfo.getBody();
        List<FormData> formDataList = new ArrayList<>();

        //请求头map
        Map<String, String> headMap = headerModels.stream()
                .filter(headerModle -> headerModle.getKeyInfo() != null) // 过滤key为null的元素
                .collect(Collectors.toMap(
                        HeaderModel::getKeyInfo,    // 键：HeaderModel的key
                        HeaderModel::getValueInfo,  // 值：HeaderModel的value
                        (oldValue, newValue) -> newValue // 重复key时保留新值
                ));
        //非表单格式的请求内容
        String content = "";
        if (boyInfo != null){
            String type = boyInfo.getTypeName();
            if (StringUtils.isBlank(type)){
                return AjaxResult.error("请求类型不能为空");
            }
            String contentTypeStr = "";
            switch (type){
                case "none":
                    //无传参
                    break;
                case "raw":
                    //字符串
                    RawData rawData = boyInfo.getRaw();
                    if (rawData != null){
                        String rawDataType = rawData.getType();
                        if (StringUtils.isNotBlank(rawDataType)){
                            switch (rawDataType){
                                case "JSON":
                                    contentTypeStr = "application/json";
                                    break;
                                case "XML":
                                    contentTypeStr = "application/xml";
                                    break;
                                case "Text":
                                    contentTypeStr = "text/plain";
                                    break;
                                case "JavaScript":
                                    contentTypeStr = "application/javascript";
                                    break;
                                case "HTML":
                                    contentTypeStr = "text/html";
                                    break;
                                default:
                                    break;
                            }
                        }
                        content = rawData.getInfo();
                    }
                    break;
                case "form-data":
                    //form表单
                    formDataList = boyInfo.getFormData();
                    contentTypeStr = "multipart/form-data";
                    break;
                case "x-www-form-urlencoded":
                    formDataList = boyInfo.getFormData();
                    contentTypeStr = "application/x-www-form-urlencoded";
                    break;
                default:
                    break;
            }
            if (StringUtils.isNotBlank(contentTypeStr)){
                headMap.put("Content-Type", contentTypeStr);
            }
        }
        //调用请求方法 获取结果
        RequestResult result = CommenMethod.sendRequest(url, headerMap, method, formDataList, content);
        String resultStr = "";
        int code = result.getCode();
        if (code == 200){
            //成功
            resultStr = result.getResult();
        }else{
            //失败
            resultStr = result.getMsg();
        }

        Gson gson = new Gson();
        //请求内容字符串（用于保存）
        RequestHistory requestHistory = new RequestHistory();
        String requestStr = gson.toJson(requestInfo);
        requestHistory.setRequestInfo(requestStr);
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null){
            Long userId = loginUser.getUser().getUserId();
            requestHistory.setUserId(userId);
        }
        requestHistory.setUrl(url);
        requestHistory.setRequestMethod(method);
        requestHistory.setRequestDate(date);
        //请求信息存入数据库
        requestHistoryMapper.insertRequestHistory(requestHistory);
        return AjaxResult.success(resultStr);
    }
}
