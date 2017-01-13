package com.card.api.credit.bean;

import com.card.api.constants.TableConstants;
import com.card.api.level.bean.LevelBean;
import com.card.api.organ.bean.OrganBean;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 信用卡基本信息
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.credit.bean
 * 创建人：yuqy
 * 创建时间：2017/1/11 20:08
 * 修改人：yuqy
 * 修改时间：2017/1/11 20:08
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_CREDIT_INFO)
public class CreditBean extends BaseBean
{
    // 主键
    @Id
    @Column(name = "cci_id")
    @GeneratedValue
    @Comment("信用卡主键")
    private Long id;

    // 信用卡组织编号外键
    @Comment("信用卡组织")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cci_organ_id") // 指定外键
    private OrganBean organ;

    // 信用卡等级编号外键
    @Comment("信用卡等级")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cci_level_id") // 指定外键
    private LevelBean level;

    // 信用卡主题外键编号
    @Comment("信用卡主题")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cci_theme_id") // 指定外键
    private ThemeBean theme;

    // 信用卡系列外键编号
    @Comment("信用卡系列")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cci_series_id") // 指定外键
    private SeriesBean series;

    // 信用卡年费
    @Column(name = "cci_year_money")
    @Comment("cci_year_money")
    private Integer yearMoney;

    // 信用卡卡片版面图片路径
    @Column(name = "cci_card_img")
    @Comment("信用卡卡面")
    private String img;

    // 信用卡卡片版面图片路径
    @Column(name = "cci_url")
    @Comment("信用卡链接")
    private String url;

    // 币种，1：人民币，2：美元，多个使用","隔开
    @Column(name = "cci_money_type")
    @Comment("系列币种")
    private String moneyType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrganBean getOrgan() {
        return organ;
    }

    public void setOrgan(OrganBean organ) {
        this.organ = organ;
    }

    public LevelBean getLevel() {
        return level;
    }

    public void setLevel(LevelBean level) {
        this.level = level;
    }

    public ThemeBean getTheme() {
        return theme;
    }

    public void setTheme(ThemeBean theme) {
        this.theme = theme;
    }

    public SeriesBean getSeries() {
        return series;
    }

    public void setSeries(SeriesBean series) {
        this.series = series;
    }

    public Integer getYearMoney() {
        return yearMoney;
    }

    public void setYearMoney(Integer yearMoney) {
        this.yearMoney = yearMoney;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }
}
