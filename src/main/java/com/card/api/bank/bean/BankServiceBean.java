package com.card.api.bank.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

/**
 * 银行服务实体<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：BankServiceBean<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 下午3:42:08<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 下午3:42:08<br>
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_BANK_SERVICE_INFO)
public class BankServiceBean extends BaseBean {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = 4016420014842405551L;

	// 主键编号
	@Id
	@GeneratedValue
	@Column(name = "cbsi_id")
	private Long id;

	// 银行服务类型实体
	@OneToOne
	@JoinColumn(name = "cbsi_type_id")
	private BankServiceTypeBean type;

	// 服务内容
	@Column(name = "cbsi_content")
	private String content;

	// 银行实体
	@OneToOne
	@JoinColumn(name = "cbsi_bank_id")
	private BankBean bank = new BankBean();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BankServiceTypeBean getType() {
		return type;
	}

	public void setType(BankServiceTypeBean type) {
		this.type = type;
	}

	public BankBean getBank() {
		return bank;
	}

	public void setBank(BankBean bank) {
		this.bank = bank;
	}

}
