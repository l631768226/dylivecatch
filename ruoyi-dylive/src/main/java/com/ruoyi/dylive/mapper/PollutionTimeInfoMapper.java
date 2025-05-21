package com.ruoyi.dylive.mapper;

import java.util.List;
import com.ruoyi.dylive.domain.PollutionTimeInfo;

/**
 * 污染物时间序列信息Mapper接口
 * 
 * @author LY
 * @date 2025-05-21
 */
public interface PollutionTimeInfoMapper 
{
    /**
     * 查询污染物时间序列信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 污染物时间序列信息
     */
    public PollutionTimeInfo selectPollutionTimeInfoById(Long id);

    /**
     * 查询污染物时间序列信息列表
     * 
     * @param pollutionTimeInfo 污染物时间序列信息
     * @return 污染物时间序列信息集合
     */
    public List<PollutionTimeInfo> selectPollutionTimeInfoList(PollutionTimeInfo pollutionTimeInfo);

    /**
     * 新增污染物时间序列信息
     * 
     * @param pollutionTimeInfo 污染物时间序列信息
     * @return 结果
     */
    public int insertPollutionTimeInfo(PollutionTimeInfo pollutionTimeInfo);

    /**
     * 修改污染物时间序列信息
     * 
     * @param pollutionTimeInfo 污染物时间序列信息
     * @return 结果
     */
    public int updatePollutionTimeInfo(PollutionTimeInfo pollutionTimeInfo);

    /**
     * 删除污染物时间序列信息
     * 
     * @param id 污染物时间序列信息主键
     * @return 结果
     */
    public int deletePollutionTimeInfoById(Long id);

    /**
     * 批量删除污染物时间序列信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePollutionTimeInfoByIds(Long[] ids);

    int batchInsert(List<PollutionTimeInfo> batch);
}
