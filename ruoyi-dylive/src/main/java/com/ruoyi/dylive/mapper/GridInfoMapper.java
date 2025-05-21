package com.ruoyi.dylive.mapper;

import java.util.List;
import com.ruoyi.dylive.domain.GridInfo;

/**
 * 网格信息Mapper接口
 * 
 * @author LY
 * @date 2025-05-21
 */
public interface GridInfoMapper 
{
    /**
     * 查询网格信息
     * 
     * @param id 网格信息主键
     * @return 网格信息
     */
    public GridInfo selectGridInfoById(Long id);

    /**
     * 查询网格信息列表
     * 
     * @param gridInfo 网格信息
     * @return 网格信息集合
     */
    public List<GridInfo> selectGridInfoList(GridInfo gridInfo);

    /**
     * 新增网格信息
     * 
     * @param gridInfo 网格信息
     * @return 结果
     */
    public int insertGridInfo(GridInfo gridInfo);

    /**
     * 修改网格信息
     * 
     * @param gridInfo 网格信息
     * @return 结果
     */
    public int updateGridInfo(GridInfo gridInfo);

    /**
     * 删除网格信息
     * 
     * @param id 网格信息主键
     * @return 结果
     */
    public int deleteGridInfoById(Long id);

    /**
     * 批量删除网格信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGridInfoByIds(Long[] ids);
}
