package com.card.api.opinion.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 意见反馈类型
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.opinion.bean
 * 创建人：yuqy
 * 创建时间：2017/1/9 18:58
 * 修改人：yuqy
 * 修改时间：2017/1/9 18:58
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_COMMON_OPINION_TYPE)
public class OpinionTypeBean extends BaseBean
{
    public OpinionTypeBean(Long id)
    {
        this.id = id;
    }
    @Id
    @GeneratedValue
    @Column(name = "cot_id")
    private Long id;

    @Column(name = "cot_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
