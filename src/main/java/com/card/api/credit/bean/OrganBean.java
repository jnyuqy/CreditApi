package com.card.api.credit.bean;

import com.card.api.constants.TableConstants;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 所属组织
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
@Table(name = TableConstants.SYSTEM_ORGAN_INFO)
public class OrganBean extends BaseBean {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3865922596525659809L;

	
	    // 主键
		@Id
		@Column(name = "cco_id")
		@GeneratedValue
		@Comment("主键")
		private Long id;
	
		// 名字
		@Column(name = "cco_name")
		@Comment("名字")
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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	
	
	
	
	
	
	
	
	
	
	
}
