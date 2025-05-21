package com.ruoyi.dylive.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 抖音直播评论获取对象 dy_live_info
 * 
 * @author ruoyi
 * @date 2024-10-26
 */
public class DyLiveInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @Excel(name = "主键id")
    private Integer id;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 发送内容 */
    @Excel(name = "发送内容")
    private String content;

    /** 发送时间 */
    @Excel(name = "发送时间")
    private String sendtime;

    /** 主播昵称 */
    @Excel(name = "主播昵称")
    private String anchorNickname;

    /** 主播头像地址 */
    @Excel(name = "主播头像地址")
    private String anchorImg;

    /** 主播主页地址 */
    @Excel(name = "主播主页地址")
    private String anchorUrl;

    /** 消息id */
    @Excel(name = "消息id")
    private String dataId;

    /** 在线观众数 */
    @Excel(name = "在线观众数")
    private Integer liveCount;

    /** 点赞数量 */
    @Excel(name = "点赞数量")
    private Integer thumbsCount;

    /** 是否为管理员 */
    @Excel(name = "是否为管理员")
    private Integer isManager;

    /** 直播日期 */
    @Excel(name = "直播日期")
    private String dateInfo;

    /** 删除标识 */
    private String delFlag;

    /** 用户等级 */
    @Excel(name = "用户等级")
    private Integer userLevel;

    /** 是否为vip */
    @Excel(name = "是否为vip")
    private Integer isVip;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSendtime(String sendtime) 
    {
        this.sendtime = sendtime;
    }

    public String getSendtime() 
    {
        return sendtime;
    }
    public void setAnchorNickname(String anchorNickname) 
    {
        this.anchorNickname = anchorNickname;
    }

    public String getAnchorNickname() 
    {
        return anchorNickname;
    }
    public void setAnchorImg(String anchorImg) 
    {
        this.anchorImg = anchorImg;
    }

    public String getAnchorImg() 
    {
        return anchorImg;
    }
    public void setAnchorUrl(String anchorUrl) 
    {
        this.anchorUrl = anchorUrl;
    }

    public String getAnchorUrl() 
    {
        return anchorUrl;
    }
    public void setDataId(String dataId) 
    {
        this.dataId = dataId;
    }

    public String getDataId() 
    {
        return dataId;
    }
    public void setLiveCount(Integer liveCount) 
    {
        this.liveCount = liveCount;
    }

    public Integer getLiveCount() 
    {
        return liveCount;
    }
    public void setThumbsCount(Integer thumbsCount) 
    {
        this.thumbsCount = thumbsCount;
    }

    public Integer getThumbsCount() 
    {
        return thumbsCount;
    }
    public void setIsManager(Integer isManager) 
    {
        this.isManager = isManager;
    }

    public Integer getIsManager() 
    {
        return isManager;
    }
    public void setDateInfo(String dateInfo) 
    {
        this.dateInfo = dateInfo;
    }

    public String getDateInfo() 
    {
        return dateInfo;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setUserLevel(Integer userLevel) 
    {
        this.userLevel = userLevel;
    }

    public Integer getUserLevel() 
    {
        return userLevel;
    }
    public void setIsVip(Integer isVip) 
    {
        this.isVip = isVip;
    }

    public Integer getIsVip() 
    {
        return isVip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nickname", getNickname())
            .append("content", getContent())
            .append("sendtime", getSendtime())
            .append("anchorNickname", getAnchorNickname())
            .append("anchorImg", getAnchorImg())
            .append("anchorUrl", getAnchorUrl())
            .append("dataId", getDataId())
            .append("liveCount", getLiveCount())
            .append("thumbsCount", getThumbsCount())
            .append("isManager", getIsManager())
            .append("dateInfo", getDateInfo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("delFlag", getDelFlag())
            .append("userLevel", getUserLevel())
            .append("isVip", getIsVip())
            .toString();
    }
}
