package com.card.api.remind.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.card.api.bank.bean.BankBean;
import com.card.api.constants.TableConstants;
import com.card.api.user.bean.UserBean;
import com.card.core.bean.BaseBean;

/**
 * 还款提醒业务逻辑接口<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：RemindBean <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 下午5:39:17 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 下午5:39:17 <br>
 * 修改备注：
 */
@Entity
@Table(name = TableConstants.COMMON_USER_REMIND)
public class RemindBean extends BaseBean {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = -7042841245107869868L;
	// 主键
	@Id
	@GeneratedValue
	@Column(name = "cur_id")
	private Long id;
	// 银行
	@OneToOne
	@JoinColumn(name = "cur_bank_id")
	private BankBean bank;
	// 用户
	@OneToOne
	@JoinColumn(name = "cur_user_id")
	private UserBean user;
	// 是否开启短信提醒
	@Column(name = "cur_phone_mess")
	private int phoneMess;
	// 短信提醒提前天数
	@Column(name = "cur_phone_mess_day")
	private int phoneMessDay;
	// 是否开启消息推送
	@Column(name = "cur_system_mess")
	private int systemMess;
	// 消息推送提醒提前天数
	@Column(name = "cur_system_mess_day")
	private int systemMessDay;

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

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public int getPhoneMess() {
		return phoneMess;
	}

	public void setPhoneMess(int phoneMess) {
		this.phoneMess = phoneMess;
	}

	public int getPhoneMessDay() {
		return phoneMessDay;
	}

	public void setPhoneMessDay(int phoneMessDay) {
		this.phoneMessDay = phoneMessDay;
	}

	public int getSystemMess() {
		return systemMess;
	}

	public void setSystemMess(int systemMess) {
		this.systemMess = systemMess;
	}

	public int getSystemMessDay() {
		return systemMessDay;
	}

	public void setSystemMessDay(int systemMessDay) {
		this.systemMessDay = systemMessDay;
	}

}
