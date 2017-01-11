package com.card.api.credit.bean;

import com.card.api.constants.TableConstants;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 分期实体
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
@Table(name = TableConstants.SYSTEM_SPLIT_INFO)
public class SplitBean extends BaseBean {

	/**
	 * (说明) {@value}
	 */
	private static final long serialVersionUID = 7522629201070842832L;

	@Id
	@Column(name = "ccsi_id")
	@Comment("分期表主键")
	@GeneratedValue
	private Long id;


	// 分期期数
	@OneToOne(cascade = CascadeType.ALL)
	// 外键字段
	@JoinColumn(name = "ccsi_nper")
	@Comment("所属类型外键")
	private SplitNumBean snb;

	@Column(name = "ccsi_series_id")
	@Comment("所属系列")
	private Long seriesId;

	// 分期期数
	@Column(name = "ccsi_money")
	@Comment("分期手续费百分比")
	private Double money;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}

	public SplitNumBean getSnb() {
		return snb;
	}

	public void setSnb(SplitNumBean snb) {
		this.snb = snb;
	}

}
