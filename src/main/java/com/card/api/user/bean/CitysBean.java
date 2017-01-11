package com.card.api.user.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

/**
 * 城市实体 <br>
 * 项目名称：CreditCard<br>
 * 项目版本：V1.0 <br>
 * 类名称：CitysBean <br>
 * 创建人：zhaoss <br>
 * 创建时间：2016年12月26日 下午15:06:00 <br>
 * 修改人： <br>
 * 修改时间：<br>
 * 修改备注：<br>
 */


@Entity
@Table(name = TableConstants.SYSTEM_CITYS_INFO)
public class CitysBean extends BaseBean{

	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = 2567504160065572554L;
	

    //城市主键 
	@Id
	@Column(name = "cci_id")
	@GeneratedValue
	private Long id;
	
	//城市名字
	@Column(name = "cci_name")
	private String name;
	
	//城市代码
	@Column(name = "cci_code")
	private Long cci_code; 
	
	
	//城市对应省份的代码
	@Column(name = "cci_p_code")
	private Long cci_p_code; 
	
	
	

	public Long getCci_code() {
		return cci_code;
	}

	public void setCci_code(Long cci_code) {
		this.cci_code = cci_code;
	}

	public Long getCci_p_code() {
		return cci_p_code;
	}

	public void setCci_p_code(Long cci_p_code) {
		this.cci_p_code = cci_p_code;
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
