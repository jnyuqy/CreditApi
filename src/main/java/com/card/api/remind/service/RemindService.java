package com.card.api.remind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.api.remind.bean.RemindBean;
import com.card.api.remind.dao.RemindDAO;
import com.card.api.user.bean.UserBean;
import com.card.api.user.dao.UserDAO;
import com.card.core.constants.SysMsgConstants;
import com.card.core.criteria.Criteria;
import com.card.core.criteria.Restrictions;
import com.card.core.exception.LogicException;
import com.card.core.service.BaseService;
import com.card.core.utils.ValidatorUtils;

/**
 * 还款提醒业务逻辑<br>
 * 
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：RemindService <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 下午5:50:45 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 下午5:50:45 <br>
 * 修改备注：
 */
@Service
public class RemindService extends BaseService<RemindBean>{

	@Autowired
	private RemindDAO remindDAO;
	
	@Autowired
	private UserDAO userDAO;
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = 9101484516625053136L;
	
	/**
	 * 添加还款提醒 2017年1月6日 下午6:00:43<br>
	 * save<br>
	 * 
	 * @param phone
	 *            手机号码<br>
	 * @param remind
	 *            还款提醒实体<br>
	 * @return void
	 */
	public void save(String phone,RemindBean remind)
	{
		//验证参数传入是否正确
		if(ValidatorUtils.isEmpty(phone) || !ValidatorUtils.isMobile(phone))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
		}
		//所属银行为空
		else if (ValidatorUtils.isEmpty(remind.getBank()) || ValidatorUtils.isEmpty(remind.getBank().getId())
				|| remind.getBank().getId() == 0.0d)
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "所属银行");
		}
		//根据手机号码查询用户实体
		UserBean userBean = userDAO.findByPhone(phone);
		if(ValidatorUtils.isEmpty(userBean))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "未检索到该手机号用户", "添加还款提醒");
		}
		//设置用户实体
		remind.setUser(userBean);
		//添加还款提醒数据
		remindDAO.save(remind);
	}
	
	/**
	 * 还款提醒列表<br>
	 * 2017年1月6日 下午6:26:38<br>
	 * list<br>
	 * 
	 * @param remind
	 *            还款提醒实体<br>
	 * @return
	 * @return List<RemindBean>
	 */
	public List<RemindBean> list(String phone)
	{
		//验证参数传入是否正确
		if(ValidatorUtils.isEmpty(phone) || !ValidatorUtils.isMobile(phone))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
		}
		//查询用户实体
		UserBean user = userDAO.findByPhone(phone);
		//添加查询条件
		Criteria<RemindBean> criteria = new Criteria<RemindBean>();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		//执行查询
		return remindDAO.findAll(criteria);
	}
	
	@Override
	public RemindBean findById(Long pk) {
		// TODO Auto-generated method stub
		return remindDAO.findOne(pk);
	}

}
