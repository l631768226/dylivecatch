package com.ruoyi.dylive.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylive.mapper.DyLiveInfoMapper;
import com.ruoyi.dylive.domain.DyLiveInfo;
import com.ruoyi.dylive.service.IDyLiveInfoService;

/**
 * 抖音直播评论获取Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
@Service
public class DyLiveInfoServiceImpl implements IDyLiveInfoService 
{
    @Autowired
    private DyLiveInfoMapper dyLiveInfoMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMdd");
    /**
     * 查询抖音直播评论获取
     * 
     * @param id 抖音直播评论获取主键
     * @return 抖音直播评论获取
     */
    @Override
    public DyLiveInfo selectDyLiveInfoById(Integer id)
    {
        return dyLiveInfoMapper.selectDyLiveInfoById(id);
    }

    /**
     * 查询抖音直播评论获取列表
     * 
     * @param dyLiveInfo 抖音直播评论获取
     * @return 抖音直播评论获取
     */
    @Override
    public List<DyLiveInfo> selectDyLiveInfoList(DyLiveInfo dyLiveInfo)
    {
        return dyLiveInfoMapper.selectDyLiveInfoList(dyLiveInfo);
    }

    /**
     * 新增抖音直播评论获取
     * 
     * @param dyLiveInfo 抖音直播评论获取
     * @return 结果
     */
    @Override
    public int insertDyLiveInfo(DyLiveInfo dyLiveInfo)
    {
        dyLiveInfo.setCreateTime(DateUtils.getNowDate());

        Date nowDate = new Date();
        String dateStr = dateSdf.format(nowDate);
        String timeStr = sdf.format(nowDate);

        dyLiveInfo.setDateInfo(dateStr);
        dyLiveInfo.setSendtime(timeStr);

        return dyLiveInfoMapper.insertDyLiveInfo(dyLiveInfo);
    }

    /**
     * 修改抖音直播评论获取
     * 
     * @param dyLiveInfo 抖音直播评论获取
     * @return 结果
     */
    @Override
    public int updateDyLiveInfo(DyLiveInfo dyLiveInfo)
    {
        dyLiveInfo.setUpdateTime(DateUtils.getNowDate());
        return dyLiveInfoMapper.updateDyLiveInfo(dyLiveInfo);
    }

    /**
     * 批量删除抖音直播评论获取
     * 
     * @param ids 需要删除的抖音直播评论获取主键
     * @return 结果
     */
    @Override
    public int deleteDyLiveInfoByIds(Integer[] ids)
    {
        return dyLiveInfoMapper.deleteDyLiveInfoByIds(ids);
    }

    /**
     * 删除抖音直播评论获取信息
     * 
     * @param id 抖音直播评论获取主键
     * @return 结果
     */
    @Override
    public int deleteDyLiveInfoById(Integer id)
    {
        return dyLiveInfoMapper.deleteDyLiveInfoById(id);
    }
}
