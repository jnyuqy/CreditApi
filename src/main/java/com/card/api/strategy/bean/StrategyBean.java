package com.card.api.strategy.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

/**
 * 动态实体<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：StrategyBean<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 上午11:21:56<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 上午11:21:56<br>
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.SYSTEM_STRATEGY_INFO)
public class StrategyBean extends BaseBean {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = 5814104315952231650L;

	// 主键
	@Id
	@GeneratedValue
	@Column(name = "ccsi_id")
	private Long id;

	// 标题
	@Column(name = "ccsi_title")
	private String title;

	//描述
	@Column(name = "ccsi_desc")
	private String desc;
	// 内容
	@Column(name = "ccsi_content")
	private String content;

	// 点击量
	@Column(name = "ccsi_click_count")
	private int clickCount;

	// 标签对象
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ccsi_tag_id")
	private StrategyTagBean tag;

	// 1：信用卡攻略，2：信用卡须知，3：达人专栏，4：信用卡新闻，5：信用卡评测报告
	@Column(name = "ccsi_type")
	private int type;

	// 热度查询，1：最热
	@Transient
	private int hot;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public StrategyTagBean getTag() {
		return tag;
	}

	public void setTag(StrategyTagBean tag) {
		this.tag = tag;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
