package com.ruoyi.dylive.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 污染物时间序列信息对象 pollution_time_info
 * 
 * @author LY
 * @date 2025-05-21
 */
public class PollutionTimeInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** i坐标 */
    @Excel(name = "i坐标")
    private Long i;

    /** j坐标 */
    @Excel(name = "j坐标")
    private Long j;

    /** 溶解氧 */
    @Excel(name = "溶解氧")
    private String doxy;

    /** 总氮 */
    @Excel(name = "总氮")
    private String tn;

    /** 总磷 */
    @Excel(name = "总磷")
    private String tp;

    /** 高锰酸盐 */
    @Excel(name = "高锰酸盐")
    private String mo;

    /** 氨氮 */
    @Excel(name = "氨氮")
    private String nh;

    /** 第一个点位（左上）x坐标值（投影坐标） */
    @Excel(name = "第一个点位", readConverterExp = "左=上")
    private String locationOneX;

    /** 第一个点位（左上）y坐标值（投影坐标） */
    @Excel(name = "第一个点位", readConverterExp = "左=上")
    private String locationOneY;

    /** 第二个点位（右上）x坐标值（投影坐标） */
    @Excel(name = "第二个点位", readConverterExp = "右=上")
    private String locationTwoX;

    /** 第二个点位（右上）y坐标值（投影坐标） */
    @Excel(name = "第二个点位", readConverterExp = "右=上")
    private String locationTwoY;

    /** 第三个点位（右下）x坐标值（投影坐标） */
    @Excel(name = "第三个点位", readConverterExp = "右=下")
    private String locationThreeX;

    /** 第三个点位（右下）y坐标值（投影坐标） */
    @Excel(name = "第三个点位", readConverterExp = "右=下")
    private String locationThreeY;

    /** 第四个点位（左下）x坐标值（投影坐标） */
    @Excel(name = "第四个点位", readConverterExp = "左=下")
    private String locationFourX;

    /** 第四个点位（左下）y坐标值（投影坐标） */
    @Excel(name = "第四个点位", readConverterExp = "左=下")
    private String locationFourY;

    /** 日期信息 */
    @Excel(name = "日期信息")
    private String dateInfo;

    /** 日期序列（第几天） */
    @Excel(name = "日期序列", readConverterExp = "第=几天")
    private Long dateIndex;

    /** 序号 */
    @Excel(name = "序号")
    private Long indexInfo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setI(Long i) 
    {
        this.i = i;
    }

    public Long getI() 
    {
        return i;
    }
    public void setJ(Long j) 
    {
        this.j = j;
    }

    public Long getJ() 
    {
        return j;
    }
    public void setDoxy(String doxy) 
    {
        this.doxy = doxy;
    }

    public String getDoxy() 
    {
        return doxy;
    }
    public void setTn(String tn) 
    {
        this.tn = tn;
    }

    public String getTn() 
    {
        return tn;
    }
    public void setTp(String tp) 
    {
        this.tp = tp;
    }

    public String getTp() 
    {
        return tp;
    }
    public void setMo(String mo) 
    {
        this.mo = mo;
    }

    public String getMo() 
    {
        return mo;
    }
    public void setNh(String nh) 
    {
        this.nh = nh;
    }

    public String getNh() 
    {
        return nh;
    }
    public void setLocationOneX(String locationOneX) 
    {
        this.locationOneX = locationOneX;
    }

    public String getLocationOneX() 
    {
        return locationOneX;
    }
    public void setLocationOneY(String locationOneY) 
    {
        this.locationOneY = locationOneY;
    }

    public String getLocationOneY() 
    {
        return locationOneY;
    }
    public void setLocationTwoX(String locationTwoX) 
    {
        this.locationTwoX = locationTwoX;
    }

    public String getLocationTwoX() 
    {
        return locationTwoX;
    }
    public void setLocationTwoY(String locationTwoY) 
    {
        this.locationTwoY = locationTwoY;
    }

    public String getLocationTwoY() 
    {
        return locationTwoY;
    }
    public void setLocationThreeX(String locationThreeX) 
    {
        this.locationThreeX = locationThreeX;
    }

    public String getLocationThreeX() 
    {
        return locationThreeX;
    }
    public void setLocationThreeY(String locationThreeY) 
    {
        this.locationThreeY = locationThreeY;
    }

    public String getLocationThreeY() 
    {
        return locationThreeY;
    }
    public void setLocationFourX(String locationFourX) 
    {
        this.locationFourX = locationFourX;
    }

    public String getLocationFourX() 
    {
        return locationFourX;
    }
    public void setLocationFourY(String locationFourY) 
    {
        this.locationFourY = locationFourY;
    }

    public String getLocationFourY() 
    {
        return locationFourY;
    }
    public void setDateInfo(String dateInfo) 
    {
        this.dateInfo = dateInfo;
    }

    public String getDateInfo() 
    {
        return dateInfo;
    }
    public void setDateIndex(Long dateIndex) 
    {
        this.dateIndex = dateIndex;
    }

    public Long getDateIndex() 
    {
        return dateIndex;
    }
    public void setIndexInfo(Long indexInfo) 
    {
        this.indexInfo = indexInfo;
    }

    public Long getIndexInfo() 
    {
        return indexInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("i", getI())
            .append("j", getJ())
            .append("doxy", getDoxy())
            .append("tn", getTn())
            .append("tp", getTp())
            .append("mo", getMo())
            .append("nh", getNh())
            .append("locationOneX", getLocationOneX())
            .append("locationOneY", getLocationOneY())
            .append("locationTwoX", getLocationTwoX())
            .append("locationTwoY", getLocationTwoY())
            .append("locationThreeX", getLocationThreeX())
            .append("locationThreeY", getLocationThreeY())
            .append("locationFourX", getLocationFourX())
            .append("locationFourY", getLocationFourY())
            .append("dateInfo", getDateInfo())
            .append("dateIndex", getDateIndex())
            .append("indexInfo", getIndexInfo())
            .toString();
    }
}
