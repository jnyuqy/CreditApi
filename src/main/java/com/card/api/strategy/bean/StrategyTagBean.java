package com.card.api.strategy.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

/**
 * 动态标签<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：StrategyTagBean <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 上午11:30:12 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 上午11:30:12 <br>
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_STRATEGY_TAG)
public class StrategyTagBean extends BaseBean {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = -7408780680610908394L;

	@Id
	@GeneratedValue
	@Column(name = "ccst_id")
	private Long id;

	@Column(name = "ccst_name")
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
