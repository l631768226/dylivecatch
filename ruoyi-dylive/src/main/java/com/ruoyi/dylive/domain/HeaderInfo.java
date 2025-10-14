package com.ruoyi.dylive.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 请求头信息对象 header_info
 * 
 * @author ruoyi
 * @date 2025-10-14
 */
public class HeaderInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 请求头键值 */
    @Excel(name = "请求头键值")
    private String keyInfo;

    /** 请求头键值对应默认值 */
    @Excel(name = "请求头键值对应默认值")
    private String valueInfo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setKeyInfo(String keyInfo) 
    {
        this.keyInfo = keyInfo;
    }

    public String getKeyInfo() 
    {
        return keyInfo;
    }
    public void setValueInfo(String valueInfo) 
    {
        this.valueInfo = valueInfo;
    }

    public String getValueInfo() 
    {
        return valueInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("keyInfo", getKeyInfo())
            .append("valueInfo", getValueInfo())
            .toString();
    }
}
