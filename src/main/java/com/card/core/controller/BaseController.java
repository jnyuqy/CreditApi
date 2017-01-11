package com.card.core.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.card.core.constants.RequestConstants;
import com.card.core.constants.SysConstants;
import com.card.core.constants.SysMsgConstants;
import com.card.core.utils.LogUtils;
import com.card.core.utils.MsgUtils;

/**
*    所有控制器父级
*    该控制器预先定义了一系列的对象，<br>如：日志，系统消息格式化，request对象<br>
*    该控制器实现Serializable,SysMsgConstants,SysConstants接口，接口内常量即可直接使用<br>
* <br>项目名称：CreditCardCore
* <br>项目版本：V1.0
* <br>类名称：BaseController
* <br>创建人：yuqy
* <br>创建时间：2016年12月15日 下午5:24:56
* <br>修改人：yuqy
* <br>修改时间：2016年12月15日 下午5:24:56
* <br>修改备注：
*/
public class BaseController implements Serializable, SysMsgConstants, SysConstants, RequestConstants {
    
    private static final long serialVersionUID = 7271321639289822635L;
    
    //日志工具类,打印日志消息
    protected LogUtils logUtils = new LogUtils(this.getClass());
    
    //系统消息工具类，用来解析系统消息,根据key以及参数即可获取系统消息
    protected MsgUtils msgUtils = new MsgUtils();
    
    //每次请求spring都会自动注入该对象,socpe为每个请求
    @Autowired
    protected HttpServletRequest request;
    
    //返回json对象
    protected JSONObject returnJson = new JSONObject();
}
