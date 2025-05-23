package com.ruoyi.dylive.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylive.mapper.PollutionLeakTimeInfoMapper;
import com.ruoyi.dylive.domain.PollutionLeakTimeInfo;
import com.ruoyi.dylive.service.IPollutionLeakTimeInfoService;

/**
 * 污染物时间序列信息Service业务层处理
 * 
 * @author LY
 * @date 2025-05-22
 */
@Service
public class PollutionLeakTimeInfoServiceImpl implements IPollutionLeakTimeInfoService 
{
    @Autowired
    private PollutionLeakTimeInfoMapper pollutionLeakTimeInfoMapper;

    /**
     * 查询污染物时间序列信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 污染物时间序列信息
     */
    @Override
    public PollutionLeakTimeInfo selectPollutionLeakTimeInfoById(Long id)
    {
        return pollutionLeakTimeInfoMapper.selectPollutionLeakTimeInfoById(id);
    }

    /**
     * 查询污染物时间序列信息列表
     * 
     * @param pollutionLeakTimeInfo 污染物时间序列信息
     * @return 污染物时间序列信息
     */
    @Override
    public List<PollutionLeakTimeInfo> selectPollutionLeakTimeInfoList(PollutionLeakTimeInfo pollutionLeakTimeInfo)
    {
        return pollutionLeakTimeInfoMapper.selectPollutionLeakTimeInfoList(pollutionLeakTimeInfo);
    }

    /**
     * 新增污染物时间序列信息
     * 
     * @param pollutionLeakTimeInfo 污染物时间序列信息
     * @return 结果
     */
    @Override
    public int insertPollutionLeakTimeInfo(PollutionLeakTimeInfo pollutionLeakTimeInfo)
    {
        return pollutionLeakTimeInfoMapper.insertPollutionLeakTimeInfo(pollutionLeakTimeInfo);
    }

    /**
     * 修改污染物时间序列信息
     * 
     * @param pollutionLeakTimeInfo 污染物时间序列信息
     * @return 结果
     */
    @Override
    public int updatePollutionLeakTimeInfo(PollutionLeakTimeInfo pollutionLeakTimeInfo)
    {
        return pollutionLeakTimeInfoMapper.updatePollutionLeakTimeInfo(pollutionLeakTimeInfo);
    }

    /**
     * 批量删除污染物时间序列信息
     * 
     * @param ids 需要删除的污染物时间序列信息主键
     * @return 结果
     */
    @Override
    public int deletePollutionLeakTimeInfoByIds(Long[] ids)
    {
        return pollutionLeakTimeInfoMapper.deletePollutionLeakTimeInfoByIds(ids);
    }

    /**
     * 删除污染物时间序列信息信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 结果
     */
    @Override
    public int deletePollutionLeakTimeInfoById(Long id)
    {
        return pollutionLeakTimeInfoMapper.deletePollutionLeakTimeInfoById(id);
    }
}
