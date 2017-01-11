package com.card.api.systemMsg.controller;

import com.alibaba.fastjson.JSONObject;
import com.card.api.bank.bean.BankServiceBean;
import com.card.api.systemMsg.bean.SystemMsgBean;
import com.card.api.systemMsg.service.SystemMsgService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Hashtable;
import java.util.List;

/**
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.systemMsg.controller
 * 创建人：yuqy
 * 创建时间：2017/1/10 13:33
 * 修改人：yuqy
 * 修改时间：2017/1/10 13:33
 * 修改备注：
 */
@RestController
@RequestMapping(value = "/systemMsg")
public class SystemMsgController extends BaseController
{
    //消息业务逻辑
    @Autowired
    private SystemMsgService systemMsgService;

    /**
     * 查询消息列表
     * @param phone 手机号码
     * @param time 时间戳
     * @param api_key apikey
     * @param sign 签名
     * @return
     */
    @ApiOperation(value = "查询消息列表", notes = "根据手机号码查询系统消息、用户消息列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String"),
            @ApiImplicitParam(name = "systemMsg", value = "查询实体,id=>根据主键查询详情", dataType = "SystemMsgBean"),
            @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
            @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
            @ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
    @RequestMapping(value = "/{phone}/{time}/{api_key}/{sign}", method = { RequestMethod.POST })
    public JSONObject list(@PathVariable String phone, @PathVariable String time, @PathVariable String api_key, @PathVariable String sign,
                           @RequestBody SystemMsgBean systemMsg)
    {
        returnJson.clear();
        // 标识，默认为true
        boolean flag = true;
        try {
            // 验证接口是否有权访问
            if (SecurityUtils.validate(new Hashtable<String, Object>() {
                /**
                 * 序列号
                 */
                private static final long serialVersionUID = 2138548644834576384L;
                {
                    put("phone",phone);
                    put(SecurityUtils._TIME, time);
                    put(SecurityUtils._API_KEY, api_key);
                    put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
                    put(SecurityUtils._SIGN, sign);
                }
            })) {
                // 执行查询消息列表
                List<SystemMsgBean> msgs = systemMsgService.findByUser(phone,systemMsg);
                //设置返回消息列表
                returnJson.put(RETURN_RESULT, msgs);
            }
        } // 业务异常
        catch (LogicException e) {
            e.printStackTrace();
            flag = false;
            // 设置返回错误消息
            returnJson.put(RETURN_MSG, msgUtils.getValue(e.getMsgId(), e.getParams()));
        }
        // 系统异常
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
            returnJson.put(RETURN_MSG, msgUtils.getValue(SysMsgConstants.SYSTEM_ERROR));
        }
        // 返回执行标识
        returnJson.put(RETURN_FLAG, flag);
        return returnJson;
    }
}
