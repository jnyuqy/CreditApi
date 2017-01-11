package com.card.api.bank.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

/**
 * 银行服务类型实体 <br>
 * 项目名称：CreditCard<br>
 * 项目版本：V1.0 <br>
 * 类名称：BankServiceTypeBean <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月5日 下午4:11:34 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月5日 下午4:11:34 <br>
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_BANK_SERVICE_TYPE_INFO)
public class BankServiceTypeBean extends BaseBean {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = -7282514100834226787L;

	// 主键
	@Id
	@Column(name = "cbst_id")
	@GeneratedValue
	private Long id;

	// 银行服务类型名称
	@Column(name = "cbst_name")
	private String name;

	// 银行服务分组
	@Column(name = "cbst_group")
	private Integer group;

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

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

}
