package com.card.api.tutorial.controller;

import com.alibaba.fastjson.JSONObject;
import com.card.api.systemMsg.bean.SystemMsgBean;
import com.card.api.tutorial.bean.TutorialBean;
import com.card.api.tutorial.service.TutorialService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/**
 * 教程api接口
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.tutorial.controller
 * 创建人：yuqy
 * 创建时间：2017/1/10 14:48
 * 修改人：yuqy
 * 修改时间：2017/1/10 14:48
 * 修改备注：
 */
@RestController
@RequestMapping(value = "/tutorial")
public class TutorialController extends BaseController
{
    //教程业务逻辑
    @Autowired
    public TutorialService tutorialService;

    /**
     * 查询教程列表
     * @param time 时间戳
     * @param api_key 授权码
     * @param sign 签名
     * @param tutorial 教程查询实体
     * @return
     */
    @ApiOperation(value = "查询教程列表", notes = "可查询出具体条数")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "tutorial", value = "查询实体,id=>根据主键查询详情 | type=>4查询头条新闻时同时要设置size=>1", dataType = "TutorialBean", required = true),
            @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
            @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
            @ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String", required = true) })
    @RequestMapping(value = "/{time}/{api_key}/{sign}", method = { RequestMethod.POST })
    public JSONObject list( @PathVariable String time, @PathVariable String api_key, @PathVariable String sign, @RequestBody TutorialBean tutorial)
    {
        returnJson.clear();
        // 标识，默认为true
        boolean flag = true;
        try {
            // 验证接口是否有权访问
            if (SecurityUtils.validate(new HashMap<String,Object>() {
                /**
                 * 序列号
                 */
                private static final long serialVersionUID = 2138548644834576384L;
                {
                    put(SecurityUtils._TIME, time);
                    put(SecurityUtils._API_KEY, api_key);
                    put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
                    put(SecurityUtils._SIGN, sign);
                }
            })) {
                // 执行查询消息列表
                List<TutorialBean> tutorials = tutorialService.list(tutorial);
                //设置返回消息列表
                returnJson.put(RETURN_RESULT, tutorials);
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
