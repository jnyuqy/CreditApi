package com.card.api.opinion.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.jdo.annotations.Join;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 意见反馈
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.opinion.bean
 * 创建人：yuqy
 * 创建时间：2017/1/9 18:46
 * 修改人：yuqy
 * 修改时间：2017/1/9 18:46
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_COMMON_OPINION)
public class OpinionBean extends BaseBean
{

    //主键
    @Id
    @GeneratedValue
    @Column(name = "cco_id")
    private Long id;

    //所属用户
    @Column(name = "cco_user_id")
    private Long userId;

    //反馈意见类型
    @OneToOne
    @JoinColumn(name = "cco_type_id")
    private OpinionTypeBean type;

    //反馈意见内容
    @Column(name = "cco_content")
    private String content;

    //反馈时间
    @Column(name = "cco_time",insertable = false,updatable = false)
    private Timestamp time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public OpinionTypeBean getType() {
        return type;
    }

    public void setType(OpinionTypeBean type) {
        this.type = type;
    }
}
