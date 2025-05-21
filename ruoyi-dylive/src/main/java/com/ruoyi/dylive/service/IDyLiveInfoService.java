package com.ruoyi.dylive.service;

import java.util.List;
import com.ruoyi.dylive.domain.DyLiveInfo;

/**
 * 抖音直播评论获取Service接口
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
public interface IDyLiveInfoService 
{
    /**
     * 查询抖音直播评论获取
     * 
     * @param id 抖音直播评论获取主键
     * @return 抖音直播评论获取
     */
    public DyLiveInfo selectDyLiveInfoById(Integer id);

    /**
     * 查询抖音直播评论获取列表
     * 
     * @param dyLiveInfo 抖音直播评论获取
     * @return 抖音直播评论获取集合
     */
    public List<DyLiveInfo> selectDyLiveInfoList(DyLiveInfo dyLiveInfo);

    /**
     * 新增抖音直播评论获取
     * 
     * @param dyLiveInfo 抖音直播评论获取
     * @return 结果
     */
    public int insertDyLiveInfo(DyLiveInfo dyLiveInfo);

    /**
     * 修改抖音直播评论获取
     * 
     * @param dyLiveInfo 抖音直播评论获取
     * @return 结果
     */
    public int updateDyLiveInfo(DyLiveInfo dyLiveInfo);

    /**
     * 批量删除抖音直播评论获取
     * 
     * @param ids 需要删除的抖音直播评论获取主键集合
     * @return 结果
     */
    public int deleteDyLiveInfoByIds(Integer[] ids);

    /**
     * 删除抖音直播评论获取信息
     * 
     * @param id 抖音直播评论获取主键
     * @return 结果
     */
    public int deleteDyLiveInfoById(Integer id);
}
