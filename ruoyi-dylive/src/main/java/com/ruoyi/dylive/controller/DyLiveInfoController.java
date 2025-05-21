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
import com.ruoyi.dylive.domain.DyLiveInfo;
import com.ruoyi.dylive.service.IDyLiveInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 抖音直播评论获取Controller
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
@RestController
@RequestMapping("/dylive/dylive")
public class DyLiveInfoController extends BaseController
{
    @Autowired
    private IDyLiveInfoService dyLiveInfoService;

    /**
     * 查询抖音直播评论获取列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:dylive:list')")
    @GetMapping("/list")
    public TableDataInfo list(DyLiveInfo dyLiveInfo)
    {
        startPage();
        List<DyLiveInfo> list = dyLiveInfoService.selectDyLiveInfoList(dyLiveInfo);
        return getDataTable(list);
    }

    /**
     * 导出抖音直播评论获取列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:dylive:export')")
    @Log(title = "抖音直播评论获取", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DyLiveInfo dyLiveInfo)
    {
        List<DyLiveInfo> list = dyLiveInfoService.selectDyLiveInfoList(dyLiveInfo);
        ExcelUtil<DyLiveInfo> util = new ExcelUtil<DyLiveInfo>(DyLiveInfo.class);
        util.exportExcel(response, list, "抖音直播评论获取数据");
    }

    /**
     * 获取抖音直播评论获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:dylive:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(dyLiveInfoService.selectDyLiveInfoById(id));
    }

    /**
     * 新增抖音直播评论获取
     */
//    @PreAuthorize("@ss.hasPermi('dylive:dylive:add')")
    @Log(title = "抖音直播评论获取", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DyLiveInfo dyLiveInfo)
    {
        return toAjax(dyLiveInfoService.insertDyLiveInfo(dyLiveInfo));
    }

    /**
     * 修改抖音直播评论获取
     */
//    @PreAuthorize("@ss.hasPermi('dylive:dylive:edit')")
    @Log(title = "抖音直播评论获取", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DyLiveInfo dyLiveInfo)
    {
        return toAjax(dyLiveInfoService.updateDyLiveInfo(dyLiveInfo));
    }

    /**
     * 删除抖音直播评论获取
     */
    @PreAuthorize("@ss.hasPermi('dylive:dylive:remove')")
    @Log(title = "抖音直播评论获取", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(dyLiveInfoService.deleteDyLiveInfoByIds(ids));
    }
}
