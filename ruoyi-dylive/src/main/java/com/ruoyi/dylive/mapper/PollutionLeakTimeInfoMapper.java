package com.ruoyi.dylive.mapper;

import java.util.List;
import com.ruoyi.dylive.domain.PollutionLeakTimeInfo;

/**
 * 污染物时间序列信息Mapper接口
 * 
 * @author LY
 * @date 2025-05-22
 */
public interface PollutionLeakTimeInfoMapper 
{
    /**
     * 查询污染物时间序列信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 污染物时间序列信息
     */
    public PollutionLeakTimeInfo selectPollutionLeakTimeInfoById(Long id);

    /**
     * 查询污染物时间序列信息列表
     * 
     * @param pollutionLeakTimeInfo 污染物时间序列信息
     * @return 污染物时间序列信息集合
     */
    public List<PollutionLeakTimeInfo> selectPollutionLeakTimeInfoList(PollutionLeakTimeInfo pollutionLeakTimeInfo);

    /**
     * 新增污染物时间序列信息
     * 
     * @param pollutionLeakTimeInfo 污染物时间序列信息
     * @return 结果
     */
    public int insertPollutionLeakTimeInfo(PollutionLeakTimeInfo pollutionLeakTimeInfo);

    /**
     * 修改污染物时间序列信息
     * 
     * @param pollutionLeakTimeInfo 污染物时间序列信息
     * @return 结果
     */
    public int updatePollutionLeakTimeInfo(PollutionLeakTimeInfo pollutionLeakTimeInfo);

    /**
     * 删除污染物时间序列信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 结果
     */
    public int deletePollutionLeakTimeInfoById(Long id);

    /**
     * 批量删除污染物时间序列信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePollutionLeakTimeInfoByIds(Long[] ids);

    void batchInsert(List<PollutionLeakTimeInfo> batch);
}
