package com.card.api.privilege.controller;

import com.alibaba.fastjson.JSONObject;
import com.card.api.organ.bean.OrganBean;
import com.card.api.privilege.bean.PrivilegeTypeBean;
import com.card.api.privilege.service.PrivilegeTypeService;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.privilege.controller
 * 创建人：yuqy
 * 创建时间：2017/1/13 16:23
 * 修改人：yuqy
 * 修改时间：2017/1/13 16:23
 * 修改备注：
 */
@RestController
@RequestMapping(value = "/privilege")
public class PrivilegeTypeController extends BaseController
{

    @Autowired
    private PrivilegeTypeService privilegeTypeService;

    /**
     * 查询信用卡特权类型列表
     * @param time 时间戳
     * @param api_key apikey
     * @param sign 签名
     * @return
     */
    @ApiOperation(value = "信用卡特权类型列表", notes = "查询出信用卡特权类型列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String",required = true),
            @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String",required = true),
            @ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String",required = true)
    })
    @RequestMapping(value = "/{time}/{api_key}/{sign}", method = RequestMethod.GET)
    public JSONObject list
            (
                    @PathVariable String time,
                    @PathVariable String api_key,
                    @PathVariable String sign
            )
    {
        //标识，默认为true
        boolean flag = true;
        //清空集合
        returnJson.clear();
        try {
            //验证接口是否有权访问
            if (SecurityUtils.validate(new HashMap<String, Object>() {
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
            }))
            {
                //查询列表
                List<PrivilegeTypeBean> privilegeTypes = privilegeTypeService.list();
                //返回列表
                returnJson.put(RETURN_RESULT,privilegeTypes);
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
