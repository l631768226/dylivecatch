package com.ruoyi.dylive.mapper;

import java.util.List;
import com.ruoyi.dylive.domain.HeaderInfo;

/**
 * 请求头信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
public interface HeaderInfoMapper 
{
    /**
     * 查询请求头信息
     * 
     * @param id 请求头信息主键
     * @return 请求头信息
     */
    public HeaderInfo selectHeaderInfoById(Long id);

    /**
     * 查询请求头信息列表
     * 
     * @param headerInfo 请求头信息
     * @return 请求头信息集合
     */
    public List<HeaderInfo> selectHeaderInfoList(HeaderInfo headerInfo);

    /**
     * 新增请求头信息
     * 
     * @param headerInfo 请求头信息
     * @return 结果
     */
    public int insertHeaderInfo(HeaderInfo headerInfo);

    /**
     * 修改请求头信息
     * 
     * @param headerInfo 请求头信息
     * @return 结果
     */
    public int updateHeaderInfo(HeaderInfo headerInfo);

    /**
     * 删除请求头信息
     * 
     * @param id 请求头信息主键
     * @return 结果
     */
    public int deleteHeaderInfoById(Long id);

    /**
     * 批量删除请求头信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHeaderInfoByIds(Long[] ids);
}
