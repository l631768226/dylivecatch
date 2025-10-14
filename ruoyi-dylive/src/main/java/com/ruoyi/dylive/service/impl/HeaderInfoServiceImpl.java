package com.ruoyi.dylive.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylive.mapper.HeaderInfoMapper;
import com.ruoyi.dylive.domain.HeaderInfo;
import com.ruoyi.dylive.service.IHeaderInfoService;

/**
 * 请求头信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
@Service
public class HeaderInfoServiceImpl implements IHeaderInfoService 
{
    @Autowired
    private HeaderInfoMapper headerInfoMapper;

    /**
     * 查询请求头信息
     * 
     * @param id 请求头信息主键
     * @return 请求头信息
     */
    @Override
    public HeaderInfo selectHeaderInfoById(Long id)
    {
        return headerInfoMapper.selectHeaderInfoById(id);
    }

    /**
     * 查询请求头信息列表
     * 
     * @param headerInfo 请求头信息
     * @return 请求头信息
     */
    @Override
    public List<HeaderInfo> selectHeaderInfoList(HeaderInfo headerInfo)
    {
        return headerInfoMapper.selectHeaderInfoList(headerInfo);
    }

    /**
     * 新增请求头信息
     * 
     * @param headerInfo 请求头信息
     * @return 结果
     */
    @Override
    public int insertHeaderInfo(HeaderInfo headerInfo)
    {
        return headerInfoMapper.insertHeaderInfo(headerInfo);
    }

    /**
     * 修改请求头信息
     * 
     * @param headerInfo 请求头信息
     * @return 结果
     */
    @Override
    public int updateHeaderInfo(HeaderInfo headerInfo)
    {
        return headerInfoMapper.updateHeaderInfo(headerInfo);
    }

    /**
     * 批量删除请求头信息
     * 
     * @param ids 需要删除的请求头信息主键
     * @return 结果
     */
    @Override
    public int deleteHeaderInfoByIds(Long[] ids)
    {
        return headerInfoMapper.deleteHeaderInfoByIds(ids);
    }

    /**
     * 删除请求头信息信息
     * 
     * @param id 请求头信息主键
     * @return 结果
     */
    @Override
    public int deleteHeaderInfoById(Long id)
    {
        return headerInfoMapper.deleteHeaderInfoById(id);
    }
}
