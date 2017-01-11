package com.card.api.user.bean;

import javax.persistence.CascadeType;
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
 * 会员实体 <br>
 * 项目名称：CreditCard<br>
 * 项目版本：xxxx <br>
 * 类名称：UsersBean <br>
 * 创建人：zhaoss <br>
 * 创建时间：2016年12月26日 下午14:30:00 <br>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注：<br>
 */
@Entity
@Table(name = TableConstants.SYSTEM_USERS_INFO)
public class UserBean extends BaseBean {

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = 6662958186450293001L;

	// 主键
	@Id
	@Column(name = "cui_id")
	@GeneratedValue
	private Long id;

	// 会员名字
	@Column(name = "cui_name")
	private String name;

	// 会员昵称
	@Column(name = "cui_nick_name")
	private String nickName;
	
	//会员头像
	@Column(name = "cui_head_img")
	private String headImg;

	// 会员性别
	@Column(name = "cui_sex")
	private String sex;

	// 会员邮箱
	@Column(name = "cui_mail")
	private String mail;

	// 会员手机号
	@Column(name = "cui_phone")
	private String phone;

	// 会员状态
	@Column(name = "cui_status")
	private String status;

	// 会员省份

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cui_provinces_id") // 指定外键
	private ProvincesBean province;

	// 会员城市
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cui_citys_id")
	private CitysBean city;

	// 会员区域
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cui_area_id")
	private AreasBean areas;

	// 会员QQ
	@Column(name = "cui_qq")
	private String qq;

	// 登入密码
	@Column(name = "cui_pwd")
	private String pwd;

	// 会员身份证号码
	@Column(name = "cui_card_no")
	private String card_no;

	// 会员具体地址
	@Column(name = "cui_address")
	private String address;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProvincesBean getProvince() {
		return province;
	}

	public void setProvince(ProvincesBean province) {
		this.province = province;
	}

	public CitysBean getCity() {
		return city;
	}

	public void setCity(CitysBean city) {
		this.city = city;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AreasBean getAreas() {
		return areas;
	}

	public void setAreas(AreasBean areas) {
		this.areas = areas;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
}
