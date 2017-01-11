package com.card.core.exception;

import com.card.core.utils.MsgUtils;

/**
 * 
 * 自定义业务逻辑异常
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：LogicException <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月21日 下午6:37:54 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月21日 下午6:37:54 <br>
 * 修改备注：
 */
public class LogicException extends RuntimeException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 4739217421366827850L;
	
	//构造函数私有化,禁止使用无参数构造函数实例化该类
	@SuppressWarnings("unused")
	private LogicException() {}
	
	//业务异常编号
	private String msgId;
	//业务异常参数列表
	private Object[] params;
	
	/**
	 * 
	 * 创建一个新的实例 LogicException.
	 * 
	 * @param msgId 业务异常编码，对应SysMsgConstants接口内的常量
	 * @param params 对应SysMsgConstants接口内的常量所需要的参数列表，参数长度可变
	 */
	public LogicException(String msgId,Object...params)
	{
		super(new MsgUtils().getValue(msgId, params));
		this.msgId = msgId;
		this.params = params;
	}

	
	//getter/setter
	public String getMsgId() {
		return msgId;
	}

	public Object[] getParams() {
		return params;
	}
	
	
}
