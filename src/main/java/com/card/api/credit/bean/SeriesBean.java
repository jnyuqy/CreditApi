package com.card.api.credit.bean;

import com.card.api.bank.bean.BankBean;
import com.card.api.constants.TableConstants;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 信用卡系列
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
@Table(name = TableConstants.SYSTEM_SERIES_INFO)
public class SeriesBean extends BaseBean {

	/**
	 * (序列号) {@value}
	 */
	private static final long serialVersionUID = 8666051908600101578L;
	// 主键
	@Id
	@Column(name = "ccs_id")
	@Comment("信用卡系列编号")
	@GeneratedValue
	private Long id;
	// 信用卡所属银行id
	@OneToOne(cascade = CascadeType.ALL)
	// 外键字段
	@JoinColumn(name = "css_bank_id")
	@Comment("所属银行外键")
	@NotNull
	private BankBean bank;
	// 信用卡系列名称
	@Column(name = "ccs_name")
	@Comment("信用卡系列名称")
	@NotNull
	private String name;
	// 信用卡系列包含的等级，多个采用“,”隔开
	@Column(name = "ccs_levels")
	@Comment("信用卡系列所包含的等级")
	@NotNull
	private String levels;
	// 系列币种，1：人民币，2：多币卡
	@Column(name = "ccs_money_type")
	@Comment("系列币种，1：人民币，2：多币卡")
	@NotNull
	private String moneyType;
	// 年费政策
	@Column(name = "ccs_year_money")
	@Comment("年费政策")
	@NotNull
	private String yearMoney;
	// 备注
	@Column(name = "css_mark")
	@Comment("备注")
	private String mark;
	// 积分规则
	@Column(name = "css_score")
	@Comment("积分规则")
	private String score;
	// 积分有效期
	@Column(name = "css_score_effective")
	@Comment("积分有效期")
	private String scoreEffective;
	// 最低取现费用
	@Column(name = "ccs_cash_min")
	@Comment("最低取现费用")
	@NotNull
	private Integer cashMin;
	// 可取现百分比
	@Column(name = "css_cash_percent")
	@Comment("可取现百分比")
	private Integer cashPercent;
	// 取现手续费为取现金额的百分比
	@Column(name = "css_cash_poundage")
	@Comment("取现手续费为取现金额的百分比")
	private Integer cashPoundage;
	// 最短免息天
	@Column(name = "css_free_interest_min")
	@Comment("最短免息天")
	private Integer freeInterestMin;
	// 最长免息天
	@Column(name = "css_free_interest_max")
	@Comment("最长免息天")
	private Integer freeInterestMax;
	// 短信通知
	@Column(name = "css_phone_message")
	@Comment("短信通知")
	private String phoneMessage;
	// 丢卡保障
	@Column(name = "css_loss_card")
	@Comment("丢卡保障")
	private String lossCard;
	// 申请条件
	@Column(name = "css_apply_need")
	@Comment("申请条件")
	private String applyNeed;
	// 申请资料
	@Column(name = "css_apply_data")
	@Comment("申请资料")
	private String applyData;
	// 最低还款金额为本月账单的百分比
	@Column(name = "css_also_money_min")
	@Comment("最低还款金额为本月账单的百分比")
	private Integer alsoMoneyMin;
	// 日息百分比
	@Column(name = "css_day_interest")
	@Comment("日息百分比")
	private Integer dayInterest;
	// 还款低于最低还款金额的部分需缴的纳滞纳金百分比
	@Column(name = "css_late_money")
	@Comment("还款低于最低还款金额的部分需缴的纳滞纳金百分比")
	private Integer lateMoney;
	// 纳滞纳金最低收费10元
	@Column(name = "css_late_money_min")
	@Comment("纳滞纳金最低收费10元")
	private Integer lateMoneyMin;
	// 超过信用额度的百分比
	@Column(name = "css_trans_money")
	@Comment(" 超过信用额度的百分比")
	private Integer transMoney;
	// 超过信用额度最低收取费用
	@Column(name = "css_trans_money_min")
	@Comment(" 超过信用额度最低收取费用")
	private Integer transMoneyMin;
	// 存入金额超出欠款的部分，取出时收取的百分比
	@Column(name = "css_overflow_money")
	@Comment("存入金额超出欠款的部分，取出时收取的百分")
	private Integer overflowMoney;
	// 挂失费
	@Column(name = "css_lose_money")
	@Comment("挂失费")
	private Integer loseMoney;
	// 补卡费
	@Column(name = "css_supple_money")
	@Comment("补卡费")
	private Integer suppleMoney;
	// 该系列办卡数量
	@Column(name = "css_handle_count")
	@Comment("该系列办卡数量")
	private Integer handleCount;

	//信用卡系列分期列表
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="ccsi_series_id",insertable = false,updatable = false)
	private List<SplitBean> splits;

	//关联信用卡系列特权列表
	@OneToMany
	@JoinTable(
			name = "c_credir_series_privilege_uni",
			joinColumns = {@JoinColumn(name = "ccspu_series_id")},
			inverseJoinColumns = {@JoinColumn(name = "ccspu_privilege_id")})
	private List<PrivilegeBean> privileges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BankBean getBank() {
		return bank;
	}

	public void setBank(BankBean bank) {
		this.bank = bank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getYearMoney() {
		return yearMoney;
	}

	public void setYearMoney(String yearMoney) {
		this.yearMoney = yearMoney;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getScoreEffective() {
		return scoreEffective;
	}

	public void setScoreEffective(String scoreEffective) {
		this.scoreEffective = scoreEffective;
	}

	public Integer getCashMin() {
		return cashMin;
	}

	public void setCashMin(Integer cashMin) {
		this.cashMin = cashMin;
	}

	public Integer getCashPercent() {
		return cashPercent;
	}

	public void setCashPercent(Integer cashPercent) {
		this.cashPercent = cashPercent;
	}

	public Integer getCashPoundage() {
		return cashPoundage;
	}

	public void setCashPoundage(Integer cashPoundage) {
		this.cashPoundage = cashPoundage;
	}

	public Integer getFreeInterestMin() {
		return freeInterestMin;
	}

	public void setFreeInterestMin(Integer freeInterestMin) {
		this.freeInterestMin = freeInterestMin;
	}

	public Integer getFreeInterestMax() {
		return freeInterestMax;
	}

	public void setFreeInterestMax(Integer freeInterestMax) {
		this.freeInterestMax = freeInterestMax;
	}

	public String getPhoneMessage() {
		return phoneMessage;
	}

	public void setPhoneMessage(String phoneMessage) {
		this.phoneMessage = phoneMessage;
	}

	public String getLossCard() {
		return lossCard;
	}

	public void setLossCard(String lossCard) {
		this.lossCard = lossCard;
	}

	public String getApplyNeed() {
		return applyNeed;
	}

	public void setApplyNeed(String applyNeed) {
		this.applyNeed = applyNeed;
	}

	public String getApplyData() {
		return applyData;
	}

	public void setApplyData(String applyData) {
		this.applyData = applyData;
	}

	public Integer getAlsoMoneyMin() {
		return alsoMoneyMin;
	}

	public void setAlsoMoneyMin(Integer alsoMoneyMin) {
		this.alsoMoneyMin = alsoMoneyMin;
	}

	public Integer getDayInterest() {
		return dayInterest;
	}

	public void setDayInterest(Integer dayInterest) {
		this.dayInterest = dayInterest;
	}

	public Integer getLateMoney() {
		return lateMoney;
	}

	public void setLateMoney(Integer lateMoney) {
		this.lateMoney = lateMoney;
	}

	public Integer getLateMoneyMin() {
		return lateMoneyMin;
	}

	public void setLateMoneyMin(Integer lateMoneyMin) {
		this.lateMoneyMin = lateMoneyMin;
	}

	public Integer getTransMoney() {
		return transMoney;
	}

	public void setTransMoney(Integer transMoney) {
		this.transMoney = transMoney;
	}

	public Integer getTransMoneyMin() {
		return transMoneyMin;
	}

	public void setTransMoneyMin(Integer transMoneyMin) {
		this.transMoneyMin = transMoneyMin;
	}

	public Integer getOverflowMoney() {
		return overflowMoney;
	}

	public void setOverflowMoney(Integer overflowMoney) {
		this.overflowMoney = overflowMoney;
	}

	public Integer getLoseMoney() {
		return loseMoney;
	}

	public void setLoseMoney(Integer loseMoney) {
		this.loseMoney = loseMoney;
	}

	public Integer getSuppleMoney() {
		return suppleMoney;
	}

	public void setSuppleMoney(Integer suppleMoney) {
		this.suppleMoney = suppleMoney;
	}

	public Integer getHandleCount() {
		return handleCount;
	}

	public void setHandleCount(Integer handleCount) {
		this.handleCount = handleCount;
	}

	public List<SplitBean> getSplits() {
		return splits;
	}

	public void setSplits(List<SplitBean> splits) {
		this.splits = splits;
	}

	public List<PrivilegeBean> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<PrivilegeBean> privileges) {
		this.privileges = privileges;
	}
}