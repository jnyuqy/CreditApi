package com.card.api.privilegeType.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.privilegeType.bean
 * 创建人：yuqy
 * 创建时间：2017/1/13 16:22
 * 修改人：yuqy
 * 修改时间：2017/1/13 16:22
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_PRIVILEGE_TYPE_INFO)
public class PrivilegeTypeBean extends BaseBean
{
    @Id
    @GeneratedValue
    @Column(name = "cpt_id")
    private Long id;

    //名称
    @Column(name = "cpt_name")
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
