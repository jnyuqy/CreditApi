package com.card.api.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.card.api.bank.bean.BankBean;
import com.card.api.bank.bean.BankServiceBean;
import com.card.api.bank.dao.BankDAO;
import com.card.api.bank.dao.BankServiceDAO;
import com.card.core.constants.SysMsgConstants;
import com.card.core.criteria.Criteria;
import com.card.core.criteria.Restrictions;
import com.card.core.exception.LogicException;
import com.card.core.service.BaseService;
import com.card.core.utils.JSONUtils;
import com.card.core.utils.ValidatorUtils;

/**
 * 银行服务业务逻辑接口<br>
 * <br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：BankServiceService<br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月6日 下午3:50:51<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月6日 下午3:50:51<br>
 * 修改备注：
 */
@CacheConfig(cacheNames = "bankService")
@Service
public class BankServiceService extends BaseService<BankServiceBean> {

	@Autowired
	private BankDAO bankDAO;
	
	@Autowired
	private BankServiceDAO bankServiceDAO;
	/**
	 * 序列号 {@value}
	 */
	private static final long serialVersionUID = -1050217604193339798L;

	/**
	 * 根据银行编号查询出服务列表<br>
	 * 2017年1月6日 下午3:52:55<br>
	 * list<br>
	 * 
	 * @param bankId
	 *            银行编号<br>
	 * @return
	 * @return List<BankServiceBean>
	 */
	@Cacheable(value = "demo", key = "#serviceBean.bank.id")
	public List<BankServiceBean> list(BankServiceBean serviceBean)
	{
		//银行编号传入异常
		if (ValidatorUtils.isEmpty(serviceBean.getBank()) || ValidatorUtils.isEmpty(serviceBean.getBank().getId())) {
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "银行编号");
		}
		//查询银行是否存在
		BankBean bank = bankDAO.findOne(serviceBean.getBank().getId());
		if(ValidatorUtils.isEmpty(bank))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "未检索到该银行", "查询银行服务");
		}
		//添加查询条件
		Criteria<BankServiceBean> criteria = new Criteria<BankServiceBean>();
		//根据银行主键查询
		criteria.add(Restrictions.eq("bank.id", serviceBean.getBank().getId()));
		//返回查询结果
		return bankServiceDAO.findAll(criteria);
	}

	@Override
	public BankServiceBean findById(Long pk) {
		return bankServiceDAO.findOne(pk);
	}

}
