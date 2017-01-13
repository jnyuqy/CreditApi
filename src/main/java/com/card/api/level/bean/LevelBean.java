package com.card.api.level.bean;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.level.bean
 * 创建人：yuqy
 * 创建时间：2017/1/13 14:52
 * 修改人：yuqy
 * 修改时间：2017/1/13 14:52
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_CREDIT_LEVEL_INFO)
public class LevelBean extends BaseBean
{
    //主键
    @Id
    @GeneratedValue
    @Column(name = "ccl_id")
    private Long id;
    //名称
    @Column(name = "ccl_name")
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
