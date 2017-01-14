package com.card.api.user.service;

import java.io.File;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.card.api.user.bean.UserBean;
import com.card.api.user.dao.UserDAO;
import com.card.core.constants.SysMsgConstants;
import com.card.core.exception.LogicException;
import com.card.core.security.MD5;
import com.card.core.service.BaseService;
import com.card.core.utils.ParseUtils;
import com.card.core.utils.ValidatorUtils;

/**
 * 用户业务逻辑 <br>
 * 项目名称：CreditCardApi<br>
 * 项目版本：V1.0 <br>
 * 类名称：UserService <br>
 * 创建人：yuqy <br>
 * 创建时间：2017年1月3日 上午11:29:09<br>
 * 修改人：yuqy <br>
 * 修改时间：2017年1月3日 上午11:29:09<br>
 * 修改备注：<br>
 */
@Service
@Transactional
public class UserService extends BaseService<UserBean>{

	//用户数据接口
	@Autowired
	private UserDAO userDAO;
	
	/**  
	* 序列号
	* {@value}
	*/ 
	private static final long serialVersionUID = 3262482571768902493L;
	
	/**
	 * 用户登录 2017年1月3日 上午11:31:23<br>
	 * login<br>
	 * 
	 * @param user
	 *            登录用户信息<br>
	 * @return<br>
	 * @throws Exception
	 * @return UserBean
	 */
	public UserBean login(UserBean user) throws Exception {
		//传入参数登录名异常
		if(ValidatorUtils.isEmpty(user.getName()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "登录名");
		}
		//传入参数密码异常
		else if(ValidatorUtils.isEmpty(user.getPwd()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "登录密码");
		}
		//根据用户名查询出用户对象
		UserBean dbUser = userDAO.findOne(user.getName());
		if(ValidatorUtils.isEmpty(dbUser))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "未检索到该用户信息", "登录");
		}
		//判断密码是否符合
		if(!MD5.MD5(user.getPwd()).equals(dbUser.getPwd()))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "密码不符合", "登录");
		}
		//返回用户对象
		return dbUser;
	}
	
	/**
	 * 用户基本信息注册<br>
	 * 2017年1月4日 下午3:25:23<br>
	 * register<br>
	 * 
	 * @param user<br>
	 * @return<br>
	 * @return UserBean
	 */
	public UserBean register(UserBean user) throws Exception {
		//传入参数登录名异常
		if(ValidatorUtils.isEmpty(user.getPhone()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "注册手机号码");
		}
		//验证手机号是否存在
		UserBean validateHave = userDAO.findByPhone(user.getPhone());
		if(!ValidatorUtils.isEmpty(validateHave))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "该手机号用户已经存在", "注册");
		}
		//执行保存用户
		userDAO.save(user);
		if(ValidatorUtils.isEmpty(user.getId()))
		{
			throw new LogicException(SysMsgConstants.SYSTEM_ERROR);
		}
		return user;
	}
	
	/**
	 * 修改用户密码<br>
	 * 2017年1月4日 下午4:09:39<br>
	 * modifyPwd<br>
	 * 
	 * @param user<br>
	 * @return<br>
	 * @return UserBean
	 */
	public void modifyPwd(UserBean user) throws Exception {
		//传入参数登录名异常
		if(ValidatorUtils.isEmpty(user.getPhone()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
		}
		//传入参数密码异常
		else if(ValidatorUtils.isEmpty(user.getPwd()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "登录密码");
		}
		//验证手机号是否存在
		UserBean validateHave = userDAO.findByPhone(user.getPhone());
		if(ValidatorUtils.isEmpty(validateHave))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "未检索到该手机号用户", "修改密码");
		}
		//md5加密后的新密码
		String new_pwd = MD5.MD5(user.getPwd());
		//判断密码是否相同
		if(new_pwd.equals(validateHave.getPwd()))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "不能与原密码相同", "修改密码");
		}
		//设置新密码
		validateHave.setPwd(new_pwd);
		userDAO.save(validateHave);
	}
	
	/**
	 * 修改昵称<br>
	 * 1/验证手机号是否存在用户
	 * 2/验证是否与原昵称相同
	 * 2017年1月5日 上午11:34:58<br>
	 * modifyNickName<br>
	 * 
	 * @param user<br>
	 * @return void<br>
	 */
	public void modifyNickName(UserBean user) throws Exception
	{
		//传入参数登录名异常
		if(ValidatorUtils.isEmpty(user.getPhone()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
		}
		//传入参数密码异常
		else if(ValidatorUtils.isEmpty(user.getNickName()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "昵称");
		}
		//验证手机号是否存在
		UserBean validateHave = userDAO.findByPhone(user.getPhone());
		if(ValidatorUtils.isEmpty(validateHave))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "未检索到该手机号用户", "修改昵称");
		}
		//验证是否与原昵称相同
		if(validateHave.getNickName().equals(user.getNickName()))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "不能与原昵称相同", "修改昵称");
		}
		//设置昵称
		validateHave.setNickName(user.getNickName());
		//修改昵称
		userDAO.save(validateHave);
	}
	
	/**
	 * 修改邮箱<br>
	 * 2017年1月5日 上午11:56:08<br>
	 * modifyMail<br>
	 * 
	 * @param user<br>
	 * @return void
	 */
	public void modifyMail(UserBean user) throws Exception
	{
		//传入参数登录名异常
		if(ValidatorUtils.isEmpty(user.getPhone()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
		}
		//传入参数密码异常
		else if(ValidatorUtils.isEmpty(user.getMail()))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "邮箱");
		}
		//验证手机号是否存在
		UserBean validateHave = userDAO.findByPhone(user.getPhone());
		if(ValidatorUtils.isEmpty(validateHave))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "未检索到该手机号用户", "修改邮箱");
		}
		//验证是否与原昵称相同
		if(validateHave.getMail().equals(user.getMail()))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "不能与原邮箱相同", "修改邮箱");
		}
		//修改昵称
		validateHave.setMail(user.getMail());
		userDAO.save(validateHave);
	}
	
	/**
	 * 上传图片<br>
	 * 2017年1月5日 下午1:51:27<br>
	 * uploadImg<br>
	 * 
	 * @param file
	 *            上传文件<br>
	 * @return<br>
	 * @return String
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public String uploadHeadImg(String phone,CommonsMultipartFile file) throws IllegalStateException, IOException
	{
		//传入参数登录名异常
		if(ValidatorUtils.isEmpty(phone))
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "手机号码");
		}
		//如果文件为空
		else if(file.isEmpty())
		{
			throw new LogicException(SysMsgConstants.PARAM_IS_NULL, "上传文件");
		}
		//验证手机号是否存在
		UserBean validateHave = userDAO.findByPhone(phone);
		if(ValidatorUtils.isEmpty(validateHave))
		{
			throw new LogicException(SysMsgConstants.XX_ERROR, "未检索到该手机号用户", "修改邮箱");
		}
		//新文件名
		String _new_file_name = ParseUtils.toString(System.nanoTime());
		//追加后缀
		_new_file_name += file.getOriginalFilename();
		//头像保存位置
		String path = this.getClass().getResource("/static/uploads/").getPath();
		//创建新对象
		File _new_file = new File(path + _new_file_name);
		//写入新文件内
		file.transferTo(_new_file);
		//头像相对路径
		_new_file_name = "/uploads/" + _new_file_name;
		
		//设置头像路径
		validateHave.setHeadImg(_new_file_name);
		//写入数据库
		userDAO.save(validateHave);
		
		//返回新文件名
		return _new_file_name;
	}
	
	@Override
	public UserBean findById(Long arg0) {
		return userDAO.findOne(arg0);
	}
}
