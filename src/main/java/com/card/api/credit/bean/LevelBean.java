package com.card.api.credit.bean;

import com.card.api.constants.TableConstants;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 信用卡等级
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
@Table(name = TableConstants.SYSTEM_LEVEL_INFO)
public class LevelBean extends BaseBean {

	/**
	 * (序列号) {@value}
	 */
	private static final long serialVersionUID = -2557175430877109362L;

	@Id
	@Column(name = "ccl_id")
	@Comment("等级编号")
	@GeneratedValue
	private Long id;

	// 等级名称
	@Column(name = "ccl_name")
	@Comment("等级名称")
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
