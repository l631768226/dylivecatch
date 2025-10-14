package com.ruoyi.dylive.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 请求历史信息对象 request_history
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
public class RequestHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 请求地址 */
    @Excel(name = "请求地址")
    private String url;

    /** 请求信息 */
    @Excel(name = "请求信息")
    private String requestInfo;

    /** 请求发起人id */
    @Excel(name = "请求发起人id")
    private Long userId;

    /** 请求时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "请求时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date requestDate;

    /** 请求方式 */
    @Excel(name = "请求方式")
    private String requestMethod;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setRequestInfo(String requestInfo) 
    {
        this.requestInfo = requestInfo;
    }

    public String getRequestInfo() 
    {
        return requestInfo;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRequestDate(Date requestDate) 
    {
        this.requestDate = requestDate;
    }

    public Date getRequestDate() 
    {
        return requestDate;
    }
    public void setRequestMethod(String requestMethod) 
    {
        this.requestMethod = requestMethod;
    }

    public String getRequestMethod() 
    {
        return requestMethod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("url", getUrl())
            .append("requestInfo", getRequestInfo())
            .append("userId", getUserId())
            .append("requestDate", getRequestDate())
            .append("requestMethod", getRequestMethod())
            .toString();
    }
}
