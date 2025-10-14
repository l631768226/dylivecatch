package com.ruoyi.dylive.service.impl;

import java.util.List;
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
}
