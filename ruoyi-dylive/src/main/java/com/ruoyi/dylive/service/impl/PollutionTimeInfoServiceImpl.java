package com.ruoyi.dylive.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylive.mapper.PollutionTimeInfoMapper;
import com.ruoyi.dylive.domain.PollutionTimeInfo;
import com.ruoyi.dylive.service.IPollutionTimeInfoService;

/**
 * 污染物时间序列信息Service业务层处理
 * 
 * @author LY
 * @date 2025-05-21
 */
@Service
public class PollutionTimeInfoServiceImpl implements IPollutionTimeInfoService 
{
    @Autowired
    private PollutionTimeInfoMapper pollutionTimeInfoMapper;

    /**
     * 查询污染物时间序列信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 污染物时间序列信息
     */
    @Override
    public PollutionTimeInfo selectPollutionTimeInfoById(Long id)
    {
        return pollutionTimeInfoMapper.selectPollutionTimeInfoById(id);
    }

    /**
     * 查询污染物时间序列信息列表
     * 
     * @param pollutionTimeInfo 污染物时间序列信息
     * @return 污染物时间序列信息
     */
    @Override
    public List<PollutionTimeInfo> selectPollutionTimeInfoList(PollutionTimeInfo pollutionTimeInfo)
    {
        return pollutionTimeInfoMapper.selectPollutionTimeInfoList(pollutionTimeInfo);
    }

    /**
     * 新增污染物时间序列信息
     * 
     * @param pollutionTimeInfo 污染物时间序列信息
     * @return 结果
     */
    @Override
    public int insertPollutionTimeInfo(PollutionTimeInfo pollutionTimeInfo)
    {
        return pollutionTimeInfoMapper.insertPollutionTimeInfo(pollutionTimeInfo);
    }

    /**
     * 修改污染物时间序列信息
     * 
     * @param pollutionTimeInfo 污染物时间序列信息
     * @return 结果
     */
    @Override
    public int updatePollutionTimeInfo(PollutionTimeInfo pollutionTimeInfo)
    {
        return pollutionTimeInfoMapper.updatePollutionTimeInfo(pollutionTimeInfo);
    }

    /**
     * 批量删除污染物时间序列信息
     * 
     * @param ids 需要删除的污染物时间序列信息主键
     * @return 结果
     */
    @Override
    public int deletePollutionTimeInfoByIds(Long[] ids)
    {
        return pollutionTimeInfoMapper.deletePollutionTimeInfoByIds(ids);
    }

    /**
     * 删除污染物时间序列信息信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 结果
     */
    @Override
    public int deletePollutionTimeInfoById(Long id)
    {
        return pollutionTimeInfoMapper.deletePollutionTimeInfoById(id);
    }
}
