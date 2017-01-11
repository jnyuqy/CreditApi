package com.card.api.constants;

/**
 * 该类作为系统消息格式化参数常量<br>
 * 如需使用请先检索接口内是否存在所需字符串常量，如果没有请自行添加<br>
 * 项目名称：CreditCard<br>
 * 项目版本：V1.0 <br>
 * 类名称：SysMsgParamsConstants<br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月16日 下午3:24:35<br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月16日 下午3:24:35<br>
 * 修改备注：<br>
 */
public interface SysMsgParamsConstants {

	/** 模块：登录 */
	public static final String MODULE_LOGIN = "登录";

	/** 模块：退出登录 */
	public static final String MODULE_LOGOUT = "退出登录";

	/** 用户名不存在 */
	public static final String FIELD_NAME_NOT_FOUND = "用户名不存在";

	/** 密码不匹配 */
	public static final String FIELD_PWD_NOT_MATCH = "密码不匹配";

	/** 模块：删除 */
	public static final String MODULE_DELETE = "删除";

	/** 模块：添加 */
	public static final String MODULE_SAVE = "添加";

	/** 模块：修改 */
	public static final String MODULE_UPDATE = "修改";
	
	/** 模块："该数据与信用卡系列表存在关联数据" */
	public static final String MODULE_SERIES= "该数据与信用卡系列表存在关联数据";
}
