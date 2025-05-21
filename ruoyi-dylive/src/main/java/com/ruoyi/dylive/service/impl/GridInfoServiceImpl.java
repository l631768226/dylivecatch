package com.ruoyi.dylive.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.dylive.domain.PollutionTimeInfo;
import com.ruoyi.dylive.mapper.PollutionTimeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylive.mapper.GridInfoMapper;
import com.ruoyi.dylive.domain.GridInfo;
import com.ruoyi.dylive.service.IGridInfoService;

/**
 * 网格信息Service业务层处理
 * 
 * @author LY
 * @date 2025-05-21
 */
@Service
public class GridInfoServiceImpl implements IGridInfoService 
{
    @Autowired
    private GridInfoMapper gridInfoMapper;

    @Autowired
    private PollutionTimeInfoMapper pollutionTimeInfoMapper;

    /**
     * 查询网格信息
     * 
     * @param id 网格信息主键
     * @return 网格信息
     */
    @Override
    public GridInfo selectGridInfoById(Long id)
    {
        return gridInfoMapper.selectGridInfoById(id);
    }

    /**
     * 查询网格信息列表
     * 
     * @param gridInfo 网格信息
     * @return 网格信息
     */
    @Override
    public List<GridInfo> selectGridInfoList(GridInfo gridInfo)
    {
        return gridInfoMapper.selectGridInfoList(gridInfo);
    }

    /**
     * 新增网格信息
     * 
     * @param gridInfo 网格信息
     * @return 结果
     */
    @Override
    public int insertGridInfo(GridInfo gridInfo)
    {
        return gridInfoMapper.insertGridInfo(gridInfo);
    }

    /**
     * 修改网格信息
     * 
     * @param gridInfo 网格信息
     * @return 结果
     */
    @Override
    public int updateGridInfo(GridInfo gridInfo)
    {
        return gridInfoMapper.updateGridInfo(gridInfo);
    }

    /**
     * 批量删除网格信息
     * 
     * @param ids 需要删除的网格信息主键
     * @return 结果
     */
    @Override
    public int deleteGridInfoByIds(Long[] ids)
    {
        return gridInfoMapper.deleteGridInfoByIds(ids);
    }

    /**
     * 删除网格信息信息
     * 
     * @param id 网格信息主键
     * @return 结果
     */
    @Override
    public int deleteGridInfoById(Long id)
    {
        return gridInfoMapper.deleteGridInfoById(id);
    }

    @Override
    public AjaxResult initData() throws IOException {

        List<GridInfo> gridInfoList = gridInfoMapper.selectGridInfoList(null);
        if (gridInfoList != null && !gridInfoList.isEmpty()) {
            //网格序号+对应的网格信息 组成map
            Map<Long, GridInfo> gridInfoMap = new HashMap<Long, GridInfo>();
            //根据list构建map
            gridInfoMap = gridInfoList.stream().collect(Collectors.toMap(
                    GridInfo:: getIndexInfo,
                    gridInfo -> gridInfo,
                    (oldValue, newValue) -> newValue
            ));

            String basePath = "C:\\Users\\LY\\PycharmProjects\\ins_test";

            String doFilePath = basePath + "\\ee_wc_do.txt";
            String tnFilePath = basePath + "\\ee_wc_tn.txt";
            String tpFilePath = basePath + "\\ee_wc_tp.txt";
            String moFilePath = basePath + "\\ee_wc_mo.txt";
            String nhFilePath = basePath + "\\ee_wc_nh.txt";

            //共30天
            int dateCount = 30;
            //89个网格
            int gridCount = 89;
            //目标数据的次序(天数标志到第一条目标数据的间隔)
            int dataOrder = 2;
            //目标数据的种类（同一天不同网格的同一种数据的间隔）
            int dataTypeCount = 2;
            //第0天对应的日期
            String dateStartStr = "2025-06-01";
            //数据光标指向初始位置（第0天标志之前）
            int lineIndex = 20;
            List<PollutionTimeInfo> dataInfoList = new ArrayList<PollutionTimeInfo>();

            for(int i = 0; i <= dateCount; i++) {
                //自第0天开始 到目标天数
                //计算第N天的日期字符串表达式
                String dateStr = calculateFutureDate(dateStartStr, i);
                //第一次进入时 光标调整到第0天的标志位置
                //网格的循环结束时，光标指向第N天最后一个网格的目标数据位置，将光标调整到第N+1天的位置
                //调整公式为 数据种类数（相邻网格同一种物质数据的间隔数 - 该种物质的顺序数（第几种物质） + 1）
                lineIndex += (dataTypeCount - dataOrder + 1);
                //记录是否第一次进入下面的循环
                int tag = 0;
                for(int j = 1; j <= gridCount; j++) {
                    //自第1个网格开始，到最后一个网格

                    PollutionTimeInfo dataInfo = new PollutionTimeInfo();

                    //获取网格编号
                    Long indexL = (long) j;
                    //获取网格信息
                    GridInfo gridInfo = gridInfoMap.get(indexL);

                    if(gridInfo != null) {
                        //每次进入该循环时，在操作之前，光标在第N天的标记数据位置
                        //光标移到当前数据网格的目标数据，需要+次序数据
                        if(tag == 0) {
                            lineIndex += dataOrder;
                        }else {
                            //第二次循环开始，每次需要将光标调整到下一条目标数据，应该+数据种类数（同一天不同网格同一个数据的间隔）
                            lineIndex += dataTypeCount;
                        }

                        //记录日期
                        dataInfo.setDateInfo(dateStr);
                        //记录EFDC中横纵坐标
                        dataInfo.setI(gridInfo.getI());
                        dataInfo.setJ(gridInfo.getJ());
                        //记录网格坐标数据
                        dataInfo.setLocationOneX(gridInfo.getLocationOneX());
                        dataInfo.setLocationOneY(gridInfo.getLocationOneY());
                        dataInfo.setLocationTwoX(gridInfo.getLocationTwoX());
                        dataInfo.setLocationTwoY(gridInfo.getLocationTwoY());
                        dataInfo.setLocationThreeX(gridInfo.getLocationThreeX());
                        dataInfo.setLocationThreeY(gridInfo.getLocationThreeY());
                        dataInfo.setLocationFourX(gridInfo.getLocationFourX());
                        dataInfo.setLocationFourY(gridInfo.getLocationFourY());
                        //记录第几天
                        dataInfo.setDateIndex((long)i);
                        //记录第几个网格
                        dataInfo.setIndexInfo((long)j);

                        //溶解氧数据
                        String doxy = getLineFromFile(doFilePath, lineIndex);
                        //总氮
                        String tn = getLineFromFile(tnFilePath, lineIndex);
                        //总磷
                        String tp = getLineFromFile(tpFilePath, lineIndex);
                        //高锰酸盐
                        String mo = getLineFromFile(moFilePath, lineIndex);
                        //氨氮
                        String nh = getLineFromFile(nhFilePath, lineIndex);

                        dataInfo.setDoxy(doxy);
                        dataInfo.setTn(tn);
                        dataInfo.setTp(tp);
                        dataInfo.setMo(mo);
                        dataInfo.setNh(nh);

                        dataInfoList.add(dataInfo);

                        //最终记录与网格数相同
                        tag += 1;
                    }else{
                        break;
                    }
                }
            }

            //循环结束，数据准备完毕
            if (dataInfoList.isEmpty()){
                return AjaxResult.error();
            }else{
                //数量应该为 89 * 31，89个网格自第0天开始到第30天为止，共31天的数据
                System.out.println(dataInfoList.size());
                //批量插入之前 按每1000条数据划分list
                List<List<PollutionTimeInfo>> batches = splitList(dataInfoList, 1000);
                if (!batches.isEmpty()) {
                    for (List<PollutionTimeInfo> batch : batches) {
                        //划分完毕 批量插入
                        pollutionTimeInfoMapper.batchInsert(batch);
                    }
                }
                return AjaxResult.success();
            }
        }
        return AjaxResult.error();
    }

    /**
     * 读取文件某一指定行的数据
     * @param filePath 文件路径
     * @param lineNumber 行号
     * @return
     * @throws IOException
     */
    private String getLineFromFile(String filePath, int lineNumber) throws IOException {
        if (lineNumber <= 0) {
            throw new IllegalArgumentException("行号必须从1开始");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    return line;
                }
                currentLine++;
            }
            return null; // 文件行数不足
        }
    }

    /**
     * 计算给定日期后N天的日期
     * @param dateStr 输入的日期字符串，格式为yyyy-MM-dd
     * @param daysToAdd 要增加的天数
     * @return 计算后的日期字符串，格式为yyyy-MM-dd
     */
    private String calculateFutureDate(String dateStr, int daysToAdd) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析输入的日期
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // 计算N天后的日期
        LocalDate futureDate = date.plusDays(daysToAdd);

        // 格式化为字符串并返回
        return futureDate.format(formatter);
    }

    /**
     * 将列表按指定大小分割为多个子列表
     * @param list 原始列表
     * @param batchSize 每个子列表的最大元素数量
     * @param <T> 列表元素类型
     * @return 分割后的子列表集合
     */
    private <T> List<List<T>> splitList(List<T> list, int batchSize) {
        if (list == null || list.isEmpty() || batchSize <= 0) {
            return new ArrayList<>();
        }

        List<List<T>> result = new ArrayList<>();
        int totalSize = list.size();

        // 计算分割后的子列表数量
        int batchCount = (totalSize + batchSize - 1) / batchSize;

        for (int i = 0; i < batchCount; i++) {
            int fromIndex = i * batchSize;
            int toIndex = Math.min((i + 1) * batchSize, totalSize);
            result.add(list.subList(fromIndex, toIndex));
        }

        return result;
    }
}
