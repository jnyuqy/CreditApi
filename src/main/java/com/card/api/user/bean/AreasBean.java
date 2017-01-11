package com.card.api.user.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.card.api.constants.TableConstants;
import com.card.core.bean.BaseBean;

/**
 * 区县实体 <br>
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
@Table(name = TableConstants.SYSTEM_AREAS_INFO)
public class AreasBean extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8508110609056107516L;

	
	// 主键
	@Id
	@Column(name = "dai_id")
	@GeneratedValue
	private Long id;

	//县代码
	@Column(name = "dai_code")
	private Long dai_code;
	
	// 名字
	@Column(name = "dai_name")
	private String name;
	
	//对应城市代码
	@Column(name = "dai_city_code")
	private Long dai_city_code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDai_code() {
		return dai_code;
	}

	public void setDai_code(Long dai_code) {
		this.dai_code = dai_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDai_city_code() {
		return dai_city_code;
	}

	public void setDai_city_code(Long dai_city_code) {
		this.dai_city_code = dai_city_code;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
