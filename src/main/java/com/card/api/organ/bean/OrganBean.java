package com.card.api.organ.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.organ.bean
 * 创建人：yuqy
 * 创建时间：2017/1/13 15:02
 * 修改人：yuqy
 * 修改时间：2017/1/13 15:02
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_ORGAN_INFO)
public class OrganBean extends BaseBean
{
    @Id
    @GeneratedValue
    @Column(name = "cco_id")
    private Long id;

    @Column(name = "cco_name")
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
