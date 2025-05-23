package com.ruoyi.dylive.controller;

import java.io.IOException;
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
import com.ruoyi.dylive.domain.GridInfo;
import com.ruoyi.dylive.service.IGridInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 网格信息Controller
 * 
 * @author LY
 * @date 2025-05-21
 */
@RestController
@RequestMapping("/dylive/gridinfo")
public class GridInfoController extends BaseController
{
    @Autowired
    private IGridInfoService gridInfoService;

    /**
     * 查询网格信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:gridinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GridInfo gridInfo)
    {
        startPage();
        List<GridInfo> list = gridInfoService.selectGridInfoList(gridInfo);
        return getDataTable(list);
    }

    /**
     * 导出网格信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:gridinfo:export')")
    @Log(title = "网格信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GridInfo gridInfo)
    {
        List<GridInfo> list = gridInfoService.selectGridInfoList(gridInfo);
        ExcelUtil<GridInfo> util = new ExcelUtil<GridInfo>(GridInfo.class);
        util.exportExcel(response, list, "网格信息数据");
    }

    /**
     * 获取网格信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:gridinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(gridInfoService.selectGridInfoById(id));
    }

    /**
     * 新增网格信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:gridinfo:add')")
    @Log(title = "网格信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GridInfo gridInfo)
    {
        return toAjax(gridInfoService.insertGridInfo(gridInfo));
    }

    /**
     * 修改网格信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:gridinfo:edit')")
    @Log(title = "网格信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GridInfo gridInfo)
    {
        return toAjax(gridInfoService.updateGridInfo(gridInfo));
    }

    /**
     * 删除网格信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:gridinfo:remove')")
    @Log(title = "网格信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(gridInfoService.deleteGridInfoByIds(ids));
    }

    /**
     * 数据初始化
     * @return
     */
    @PostMapping("/initData")
    public AjaxResult initData() throws IOException {
        return gridInfoService.initData();
    }

    /**
     * 数据初始化
     * @return
     */
    @PostMapping("/initLeakData")
    public AjaxResult initLeakData() throws IOException {
        return gridInfoService.initLeakData();
    }
}
