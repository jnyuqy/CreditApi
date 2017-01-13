package com.card.api.use.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.use.bean
 * 创建人：yuqy
 * 创建时间：2017/1/13 14:27
 * 修改人：yuqy
 * 修改时间：2017/1/13 14:27
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_CREDIT_USE_INFO)
public class UseBean extends BaseBean
{
    //主键
    @Id
    @GeneratedValue
    @Column(name = "ccu_id")
    private Long id;

    //名称
    @Column(name = "ccu_name")
    private String name;

    //用途描述
    @Column(name = "ccu_desc")
    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
