package com.ruoyi.dylive.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.ruoyi.common.core.domain.AjaxResult;
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

    private Gson gson = new Gson();

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
        //获取请求体
        BodyInfo boyInfo = requestInfo.getBody();
        Map<String, String> formMap = new HashMap<String, String>();
        String content = "";
        if (boyInfo != null){
            String type = boyInfo.getTypeName();
            if (StringUtils.isBlank(type)){
                return AjaxResult.error("请求类型不能为空");
            }
            switch (type){
                case "none":
                    //无传参
                    break;
                case "raw":
                    //字符串
                    RawData rawData = boyInfo.getRaw();
                    if (rawData != null){
                        content = rawData.getInfo();
                    }
                    break;
                case "form-data":
                case "x-www-form-urlencoded":
                    //form表单
                    List<FormData> formDataList = boyInfo.getFormData();
                    if (formDataList != null && !formDataList.isEmpty()){
                        formMap = formDataList.stream()
                                .filter(formData -> formData.getKey() != null) // 过滤key为null的元素
                                .collect(Collectors.toMap(
                                        FormData::getKey,    // 键：FormData的key
                                        FormData::getValue,  // 值：FormData的value
                                        (oldValue, newValue) -> newValue // 重复key时保留新值
                                ));
                    }
                    break;
                default:
                    break;
            }
        }

        RequestResult result = CommenMethod.sendRequest(url, headerModels, method, formMap, content);
        String resultStr = "";
        int code = result.getCode();
        if (code == 200){
            //成功
            resultStr = result.getResult();
        }else{
            //失败
            resultStr = result.getMsg();
        }

        //请求内容字符串（用于保存）
        String requestStr = gson.toJson(requestInfo);

        return AjaxResult.success(resultStr);
    }
}
