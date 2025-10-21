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
import com.ruoyi.dylive.domain.HeaderInfo;
import com.ruoyi.dylive.service.IHeaderInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 请求头信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
@RestController
@RequestMapping("/dylive/info")
public class HeaderInfoController extends BaseController
{
    @Autowired
    private IHeaderInfoService headerInfoService;

    /**
     * 查询请求头信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(HeaderInfo headerInfo)
    {
        startPage();
        List<HeaderInfo> list = headerInfoService.selectHeaderInfoList(headerInfo);
        return getDataTable(list);
    }

    /**
     * 导出请求头信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:info:export')")
    @Log(title = "请求头信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HeaderInfo headerInfo)
    {
        List<HeaderInfo> list = headerInfoService.selectHeaderInfoList(headerInfo);
        ExcelUtil<HeaderInfo> util = new ExcelUtil<HeaderInfo>(HeaderInfo.class);
        util.exportExcel(response, list, "请求头信息数据");
    }

    /**
     * 获取请求头信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(headerInfoService.selectHeaderInfoById(id));
    }

    /**
     * 新增请求头信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:info:add')")
    @Log(title = "请求头信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HeaderInfo headerInfo)
    {
        return toAjax(headerInfoService.insertHeaderInfo(headerInfo));
    }

    /**
     * 修改请求头信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:info:edit')")
    @Log(title = "请求头信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HeaderInfo headerInfo)
    {
        return toAjax(headerInfoService.updateHeaderInfo(headerInfo));
    }

    /**
     * 删除请求头信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:info:remove')")
    @Log(title = "请求头信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(headerInfoService.deleteHeaderInfoByIds(ids));
    }

    /**
     * 获取带有默认值的head信息
     * @return head信息列表
     */
    @PostMapping("/headList")
    public AjaxResult headList(){
        return headerInfoService.headList();
    }
}
