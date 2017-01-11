package com.card.api.tutorial.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 教程实体
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.tutorial.bean
 * 创建人：yuqy
 * 创建时间：2017/1/10 14:46
 * 修改人：yuqy
 * 修改时间：2017/1/10 14:46
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_CREDIT_STRATRGY_INFO)
public class TutorialBean extends BaseBean
{
    //主键
    @Id
    @GeneratedValue
    @Column(name = "ccsi_id")
    private Long id;

    //教程标题
    @Column(name = "ccsi_title")
    private String title;

    //教程标题
    @Column(name = "ccsi_img")
    private String img;

    //教程内容
    @Column(name = "ccsi_content")
    private String content;

    //点击量
    @Column(name = "ccsi_click_count")
    private int clickCount;

    //攻略类型,1：信用卡攻略，2：信用卡须知，3：达人专栏，4：信用卡新闻，5：信用卡评测报告
    @Column(name = "ccsi_type")
    private int type;

    @OneToOne
    @JoinColumn(name = "ccsi_tag_id")
    private TutorialTagBean tag;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg( String img ) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount( int clickCount ) {
        this.clickCount = clickCount;
    }

    public int getType() {
        return type;
    }

    public void setType( int type ) {
        this.type = type;
    }

    public TutorialTagBean getTag() {
        return tag;
    }

    public void setTag( TutorialTagBean tag ) {
        this.tag = tag;
    }
}
