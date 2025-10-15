package com.ruoyi.dylive.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.dylive.model.RequestSendRec;
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
import com.ruoyi.dylive.domain.RequestHistory;
import com.ruoyi.dylive.service.IRequestHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 请求历史信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
@RestController
@RequestMapping("/dylive/history")
public class RequestHistoryController extends BaseController
{
    @Autowired
    private IRequestHistoryService requestHistoryService;

    /**
     * 查询请求历史信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(RequestHistory requestHistory)
    {
        startPage();
        List<RequestHistory> list = requestHistoryService.selectRequestHistoryList(requestHistory);
        return getDataTable(list);
    }

    /**
     * 导出请求历史信息列表
     */
    @PreAuthorize("@ss.hasPermi('dylive:history:export')")
    @Log(title = "请求历史信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RequestHistory requestHistory)
    {
        List<RequestHistory> list = requestHistoryService.selectRequestHistoryList(requestHistory);
        ExcelUtil<RequestHistory> util = new ExcelUtil<RequestHistory>(RequestHistory.class);
        util.exportExcel(response, list, "请求历史信息数据");
    }

    /**
     * 获取请求历史信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(requestHistoryService.selectRequestHistoryById(id));
    }

    /**
     * 新增请求历史信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:history:add')")
    @Log(title = "请求历史信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RequestHistory requestHistory)
    {
        return toAjax(requestHistoryService.insertRequestHistory(requestHistory));
    }

    /**
     * 修改请求历史信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:history:edit')")
    @Log(title = "请求历史信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RequestHistory requestHistory)
    {
        return toAjax(requestHistoryService.updateRequestHistory(requestHistory));
    }

    /**
     * 删除请求历史信息
     */
    @PreAuthorize("@ss.hasPermi('dylive:history:remove')")
    @Log(title = "请求历史信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(requestHistoryService.deleteRequestHistoryByIds(ids));
    }

    /**
     * API调用接口
     * @param data 请求信息
     * @return 结果信息
     */
    @PostMapping("/send")
    public AjaxResult send(@RequestBody RequestSendRec data) throws Exception {
        return requestHistoryService.send(data);
    }
}
