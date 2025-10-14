package com.ruoyi.dylive.mapper;

import java.util.List;
import com.ruoyi.dylive.domain.RequestHistory;

/**
 * 请求历史信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
public interface RequestHistoryMapper 
{
    /**
     * 查询请求历史信息
     * 
     * @param id 请求历史信息主键
     * @return 请求历史信息
     */
    public RequestHistory selectRequestHistoryById(Integer id);

    /**
     * 查询请求历史信息列表
     * 
     * @param requestHistory 请求历史信息
     * @return 请求历史信息集合
     */
    public List<RequestHistory> selectRequestHistoryList(RequestHistory requestHistory);

    /**
     * 新增请求历史信息
     * 
     * @param requestHistory 请求历史信息
     * @return 结果
     */
    public int insertRequestHistory(RequestHistory requestHistory);

    /**
     * 修改请求历史信息
     * 
     * @param requestHistory 请求历史信息
     * @return 结果
     */
    public int updateRequestHistory(RequestHistory requestHistory);

    /**
     * 删除请求历史信息
     * 
     * @param id 请求历史信息主键
     * @return 结果
     */
    public int deleteRequestHistoryById(Integer id);

    /**
     * 批量删除请求历史信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRequestHistoryByIds(Integer[] ids);
}
