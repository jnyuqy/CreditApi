package com.card.api.bank.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.card.api.constants.TableConstants;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

@Entity
@Table(name = TableConstants.SYSTEM_BANK_INFO)
public class BankBean extends BaseBean {

	/**
	 * (序列号) {@value}
	 */
	private static final long serialVersionUID = -3277167672964768721L;
	// 主键
	@Id
	@Column(name = "cbi_id")
	@Comment("银行编号")
	@GeneratedValue
	private Long id;

	// 银行名称
	@Column(name = "cbi_name")
	@Comment("银行名称")
	@NotNull
	private String name;

	// 银行图标
	@Column(name = "cbi_icon")
	@Comment("银行图标")
	private String icon;

	// 银行热度，1热门银行，0非热门
	@Column(name = "cbi_hot")
	@Comment("银行热度")
	private String hot;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}
}
