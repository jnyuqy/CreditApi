package com.card.api.bank.service;

import java.util.List;

import com.card.api.bank.bean.QBankBean;
import com.card.core.utils.JSONUtils;
import com.card.core.utils.ValidatorUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.card.api.bank.bean.BankBean;
import com.card.api.bank.dao.BankDAO;
import com.card.core.service.BaseService;

/**
 * 
 * 银行业务逻辑<br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：BankService <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月5日 下午4:01:12 <br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月5日 下午4:01:12 <br>
 * 修改备注：<br>
 */
@Service
public class BankService extends BaseService<BankBean>{

	//银行数据接口
	@Autowired
	private BankDAO bankDAO;
	
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = 6861690770005001938L;

	/**
	 * 根据查询条件查询银行列表 2017年1月5日 下午4:20:01 list
	 * 
	 * @param bank
	 * @return
	 * @return List<BankBean>
	 */
	public List<BankBean> list(BankBean bank)
	{
		System.out.println(JSONUtils.toJSONString(bank));
		//银行查询实体构建
		QBankBean _bank = QBankBean.bankBean;
		//查询条件接口
		BooleanExpression expression = _bank.hot.eq(bank.getHot());
		//如果银行名称不为空，根据名称查询
		if(!ValidatorUtils.isEmpty(bank.getName())) {
			expression = expression.and(_bank.name.eq(bank.getName()));
		}
		//返回分页数据
		return bankDAO.findAll(expression,new PageRequest(bank.getPage() - 1, bank.getSize())).getContent();
	}
	
	@Override
	public BankBean findById(Long arg0) {
		return bankDAO.findOne(arg0);
	}

}
