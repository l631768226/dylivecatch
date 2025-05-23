package com.ruoyi.dylive.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dylive.domain.PollutionLeakTimeInfo;
import com.ruoyi.dylive.service.IPollutionLeakTimeInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 污染物时间序列信息Controller
 * 
 * @author LY
 * @date 2025-05-22
 */
@RestController
@RequestMapping("/dylive/pollutionleakinfo")
public class PollutionLeakTimeInfoController extends BaseController
{
    @Autowired
    private IPollutionLeakTimeInfoService pollutionLeakTimeInfoService;

    /**
     * 查询污染物时间序列信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutionleakinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(PollutionLeakTimeInfo pollutionLeakTimeInfo)
    {
        startPage();
        List<PollutionLeakTimeInfo> list = pollutionLeakTimeInfoService.selectPollutionLeakTimeInfoList(pollutionLeakTimeInfo);
        return getDataTable(list);
    }

    /**
     * 导出污染物时间序列信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutionleakinfo:export')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PollutionLeakTimeInfo pollutionLeakTimeInfo)
    {
        List<PollutionLeakTimeInfo> list = pollutionLeakTimeInfoService.selectPollutionLeakTimeInfoList(pollutionLeakTimeInfo);
        ExcelUtil<PollutionLeakTimeInfo> util = new ExcelUtil<PollutionLeakTimeInfo>(PollutionLeakTimeInfo.class);
        util.exportExcel(response, list, "污染物时间序列信息数据");
    }

    /**
     * 获取污染物时间序列信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutionleakinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pollutionLeakTimeInfoService.selectPollutionLeakTimeInfoById(id));
    }

    /**
     * 新增污染物时间序列信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutionleakinfo:add')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PollutionLeakTimeInfo pollutionLeakTimeInfo)
    {
        return toAjax(pollutionLeakTimeInfoService.insertPollutionLeakTimeInfo(pollutionLeakTimeInfo));
    }

    /**
     * 修改污染物时间序列信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutionleakinfo:edit')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PollutionLeakTimeInfo pollutionLeakTimeInfo)
    {
        return toAjax(pollutionLeakTimeInfoService.updatePollutionLeakTimeInfo(pollutionLeakTimeInfo));
    }

    /**
     * 删除污染物时间序列信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutionleakinfo:remove')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pollutionLeakTimeInfoService.deletePollutionLeakTimeInfoByIds(ids));
    }
}
