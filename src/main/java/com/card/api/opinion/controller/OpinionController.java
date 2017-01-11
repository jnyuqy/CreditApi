package com.card.api.opinion.controller;

import com.alibaba.fastjson.JSONObject;
import com.card.api.opinion.bean.OpinionBean;
import com.card.api.opinion.service.OpinionService;
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

/**
 * 意见反馈api控制器
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.opinion.controller
 * 创建人：yuqy
 * 创建时间：2017/1/9 19:41
 * 修改人：yuqy
 * 修改时间：2017/1/9 19:41
 * 修改备注：
 */
@RestController
@RequestMapping(value = "opinion/")
public class OpinionController extends BaseController
{
    //意见反馈业务逻辑
    @Autowired
    private OpinionService opinionService;
    /**
     * 添加意见反馈
     * @param phone 手机号
     * @param time 时间戳
     * @param api_key apikey
     * @param sign 签名
     * @return
     */
    @ApiOperation(value = "添加反馈", notes = "添加反馈意见")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "phone", value = "手机号码", dataType = "String",required = true),
        @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String",required = true),
        @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String",required = true),
        @ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String",required = true)
    })
    @RequestMapping(value = "/add/{phone}/{time}/{api_key}/{sign}", method = RequestMethod.POST)
    public JSONObject add(@PathVariable String phone, @PathVariable String time, @PathVariable String api_key,
                          @PathVariable String sign, @RequestBody OpinionBean opinionBean)
    {
        //清空集合
        returnJson.clear();
        //标识，默认为true
        boolean flag = true;
        try {
            //验证接口是否有权访问
            if (SecurityUtils.validate(new Hashtable<String, Object>() {
                /**
                 * 序列号
                 */
                private static final long serialVersionUID = 2138548644834576384L;
                {
                    put("phone", phone);
                    put(SecurityUtils._TIME, time);
                    put(SecurityUtils._API_KEY, api_key);
                    put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
                    put(SecurityUtils._SIGN, sign);
                }
            }))
            {
                // 执行添加意见反馈业务
                opinionService.add(phone,opinionBean);
            }
        }
        //业务异常
        catch (LogicException e) {
            e.printStackTrace();
            flag = false;
            //设置返回错误消息
            returnJson.put(RETURN_MSG, msgUtils.getValue(e.getMsgId(), e.getParams()));
        }
        //系统异常
        catch (Exception e) {
            e.printStackTrace();
            flag = false;
            returnJson.put(RETURN_MSG, msgUtils.getValue(SysMsgConstants.SYSTEM_ERROR));
        }
        //返回执行标识
        returnJson.put(RETURN_FLAG, flag);
        return returnJson;
    }
}
