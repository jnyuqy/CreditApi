package com.card.api.credit.controller;

import com.alibaba.fastjson.JSONObject;
import com.card.api.credit.bean.CreditBean;
import com.card.api.credit.service.CreditService;
import com.card.api.user.bean.UserBean;
import com.card.api.utils.SecurityUtils;
import com.card.core.constants.SysMsgConstants;
import com.card.core.controller.BaseController;
import com.card.core.exception.LogicException;
import com.card.core.utils.JSONUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 信用卡api接口
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.credit.controller
 * 创建人：yuqy
 * 创建时间：2017/1/11 20:12
 * 修改人：yuqy
 * 修改时间：2017/1/11 20:12
 * 修改备注：
 */
@RestController
@RequestMapping(value = "/credit")
public class CreditController extends BaseController
{
    @Autowired
    private CreditService creditService;

    @ApiOperation(value = "查询信用卡列表", notes = "查询信用卡列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "bankId", value = "银行编号", dataType = "Long"),
            @ApiImplicitParam(name = "useId", value = "用途编号", dataType = "Long"),
            @ApiImplicitParam(name = "levelId", value = "等级编号", dataType = "Long"),
            @ApiImplicitParam(name = "yearMoney", value = "年费政策类型，1：终身免年费，2：交易免年费", dataType = "Long"),
            @ApiImplicitParam(name = "moneyType", value = "币种，1：人民币，2：美元，3：其他", dataType = "String"),
            @ApiImplicitParam(name = "organId", value = "信用卡组织编号", dataType = "Long"),
            @ApiImplicitParam(name = "privilegeId", value = "特权编号", dataType = "Long"),
            @ApiImplicitParam(name = "color", value = "卡面颜色：1 金色, 2 红色, 3 蓝色, 4 绿色, 5 黑色, 6 白色, 7 彩色", dataType = "Long"),
            @ApiImplicitParam(name = "themeId", value = "卡面主题编号", dataType = "Long"),
            @ApiImplicitParam(name = "size",value = "分页属性：一页数据条数【默认20】",dataType = "Integer"),
            @ApiImplicitParam(name = "page",value = "分页属性：当前页码【默认1】",dataType = "Integer"),
            @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
            @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
            @ApiImplicitParam(name = "sign", value = "签名,(参数列表+time+api_key)生成url串首字母排序正序=sign", dataType = "String", required = true) })
    @RequestMapping(value = "/{time}/{api_key}/{sign}", method = RequestMethod.GET)
    public JSONObject list
            (
                    @PathVariable String time,
                    @PathVariable String api_key,
                    @PathVariable String sign,
                    @RequestParam(value = "bankId",defaultValue = "") Long bankId,
                    @RequestParam(value = "useId",defaultValue = "") Long useId,
                    @RequestParam(value = "levelId",defaultValue = "") Long levelId,
                    @RequestParam(value = "yearMoney",defaultValue = "") Long yearMoney,
                    @RequestParam(value = "moneyType",defaultValue = "") String moneyType,
                    @RequestParam(value = "organId",defaultValue = "") Long organId,
                    @RequestParam(value = "privilegeId",defaultValue = "") Long privilegeId,
                    @RequestParam(value = "color",defaultValue = "") Long color,
                    @RequestParam(value = "themeId",defaultValue = "") Long themeId,
                    @RequestParam(value = "size",defaultValue = "20") Integer size ,
                    @RequestParam(value = "page",defaultValue = "1") Integer page
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
                    put("bankId", bankId);
                    put("useId", useId);
                    put("levelId", levelId);
                    put("yearMoney", yearMoney);
                    put("moneyType", moneyType);
                    put("organId", organId);
                    put("privilegeId", privilegeId);
                    put("color", color);
                    put("themeId", themeId);
                    put(SecurityUtils._TIME, time);
                    put(SecurityUtils._API_KEY, api_key);
                    put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
                    put(SecurityUtils._SIGN, sign);
                }
            }))
            {
                //返回信用卡列表
                List<HashMap<String,Object>> result = creditService.list
                        (
                                bankId,
                                useId,
                                levelId,
                                yearMoney,
                                moneyType,
                                organId,
                                privilegeId,
                                color,
                                themeId,
                                page,
                                size
                        );
                returnJson.put(RETURN_RESULT,result);
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

    /**
     * 查询信用卡详情
     * @param time 时间戳
     * @param api_key 授权码
     * @param sign 签名
     * @param creditId 信用卡编号
     * @return
     */
    @ApiOperation(value = "查询信用卡详情", notes = "查询信用卡详情")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "creditId", value = "信用卡编号", dataType = "Long",required = true),
            @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String", required = true),
            @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String", required = true),
            @ApiImplicitParam(name = "sign", value = "签名,(参数列表+time+api_key)生成url串首字母排序正序=sign", dataType = "String", required = true) })
    @RequestMapping(value = "/{creditId}/{time}/{api_key}/{sign}", method = RequestMethod.GET)
    public JSONObject detail
            (
                    @PathVariable Long creditId,
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
                    put("creditId", creditId);
                    put(SecurityUtils._TIME, time);
                    put(SecurityUtils._API_KEY, api_key);
                    put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
                    put(SecurityUtils._SIGN, sign);
                }
            }))
            {
                //返回信用卡详情
                CreditBean credit = creditService.findById(creditId);
                returnJson.put(RETURN_RESULT,credit);
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
