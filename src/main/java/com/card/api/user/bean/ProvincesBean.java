package com.card.api.user.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

/**
 * 省份实体 <br>
 * 项目名称：CreditCard<br>
 * 项目版本：V1.0 <br>
 * 类名称：ProvincesBean <br>
 * 创建人：zhaoss <br>
 * 创建时间：2016年12月26日 下午15:09:00 <br>
 * 修改人： <br>
 * 修改时间：<br>
 * 修改备注：<br>
 */

@Entity
@Table(name = TableConstants.SYSTEM_PROVINCES_INFO)
public class ProvincesBean extends BaseBean {
	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = -3259066793563846146L;

	// 省份主键
	@Id
	@Column(name = "cpi_id")
	@GeneratedValue
	private Long id;

	// 省份名字
	@Column(name = "cpi_name")
	private String name;
	
    //省份代码
	@Column(name = "cpi_code")
    private Long cpi_code;
	
	
	public Long getCpi_code() {
		return cpi_code;
	}

	public void setCpi_code(Long cpi_code) {
		this.cpi_code = cpi_code;
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
