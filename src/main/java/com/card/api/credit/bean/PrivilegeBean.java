package com.card.api.credit.bean;

import com.card.api.constants.TableConstants;
import com.card.core.annotation.Comment;
import com.card.core.bean.BaseBean;

import javax.persistence.*;

/**
 * 信用卡特权
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
@Table(name = TableConstants.SYSTEM_PRIVILEGE_INFO)
public class PrivilegeBean extends BaseBean {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 7927139291694578507L;

	
	   // 主键
		@Id
		@Column(name = "ccp_id")
		@GeneratedValue
		@Comment("主键")
		private Long id;
	
		// 标题
		@Column(name = "ccp_title")
		@Comment("标题")
		private String title;

		// 内容
		@Column(name = "ccp_content")
		@Comment("内容")
		private   String  content ;


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



		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
