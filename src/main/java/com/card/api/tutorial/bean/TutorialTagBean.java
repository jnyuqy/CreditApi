package com.card.api.tutorial.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.tutorial.bean
 * 创建人：yuqy
 * 创建时间：2017/1/10 15:00
 * 修改人：yuqy
 * 修改时间：2017/1/10 15:00
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_CREDIT_STRATRGY_TAG)
public class TutorialTagBean extends BaseBean
{
    //标签编号
    @Id
    @GeneratedValue
    @Column(name = "ccst_id")
    private Long id;

    //标签名称
    @Column(name = "ccst_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
