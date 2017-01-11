package com.card.api.credit.bean;

import com.card.api.constants.TableConstants;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 分期对应期数
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
@Table(name = TableConstants.SYSTEM_SPLITNUM_INFO)
public class SplitNumBean extends BaseBean {


	/**
	 * (序列号) {@value}
	 */
	private static final long serialVersionUID = -6360882001498485399L;

	// 主键
	@Id
	@Column(name = "dsi_id")
	@Comment("分期期数主键")
	@GeneratedValue
	private Long id;

	// 具体期数,3期,6期,9期,12期,15期,18期,24期
	@Column(name = "dsi_name")
	@Comment("分期期数名称")
	@NotNull
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
