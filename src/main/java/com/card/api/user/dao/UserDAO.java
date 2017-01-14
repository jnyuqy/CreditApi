package com.card.api.user.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

import com.card.api.constants.TableConstants;
import com.card.api.user.bean.UserBean;
import com.card.core.dao.BaseDAO;

/**
 * 用户数据接口 <br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：UserDAO <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月3日 上午11:27:12<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月3日 上午11:27:12<br>
 * 修改备注：<br>
 */
public interface UserDAO extends BaseDAO<UserBean> {

	//根据用户名查询用户详情
	@Cacheable(value = "demo", key = "#p0")
	@Query(value = "select * from " + TableConstants.SYSTEM_USERS_INFO + " where cui_name = ?1", nativeQuery = true)
	public UserBean findOne(String userName);
	
	//根据手机号查询
	@Cacheable(value = "demo", key = "#p0")
	public UserBean findByPhone(String phone);
}
