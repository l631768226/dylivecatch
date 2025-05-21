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
import com.ruoyi.dylive.domain.PollutionTimeInfo;
import com.ruoyi.dylive.service.IPollutionTimeInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 污染物时间序列信息Controller
 * 
 * @author LY
 * @date 2025-05-21
 */
@RestController
@RequestMapping("/dylive/pollutiontimeinfo")
public class PollutionTimeInfoController extends BaseController
{
    @Autowired
    private IPollutionTimeInfoService pollutionTimeInfoService;

    /**
     * 查询污染物时间序列信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutiontimeinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(PollutionTimeInfo pollutionTimeInfo)
    {
        startPage();
        List<PollutionTimeInfo> list = pollutionTimeInfoService.selectPollutionTimeInfoList(pollutionTimeInfo);
        return getDataTable(list);
    }

    /**
     * 导出污染物时间序列信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutiontimeinfo:export')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PollutionTimeInfo pollutionTimeInfo)
    {
        List<PollutionTimeInfo> list = pollutionTimeInfoService.selectPollutionTimeInfoList(pollutionTimeInfo);
        ExcelUtil<PollutionTimeInfo> util = new ExcelUtil<PollutionTimeInfo>(PollutionTimeInfo.class);
        util.exportExcel(response, list, "污染物时间序列信息数据");
    }

    /**
     * 获取污染物时间序列信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutiontimeinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pollutionTimeInfoService.selectPollutionTimeInfoById(id));
    }

    /**
     * 新增污染物时间序列信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutiontimeinfo:add')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PollutionTimeInfo pollutionTimeInfo)
    {
        return toAjax(pollutionTimeInfoService.insertPollutionTimeInfo(pollutionTimeInfo));
    }

    /**
     * 修改污染物时间序列信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutiontimeinfo:edit')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PollutionTimeInfo pollutionTimeInfo)
    {
        return toAjax(pollutionTimeInfoService.updatePollutionTimeInfo(pollutionTimeInfo));
    }

    /**
     * 删除污染物时间序列信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:pollutiontimeinfo:remove')")
    @Log(title = "污染物时间序列信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pollutionTimeInfoService.deletePollutionTimeInfoByIds(ids));
    }
}
