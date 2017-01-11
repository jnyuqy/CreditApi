package com.card.api.collection.controller;

import com.alibaba.fastjson.JSONObject;
import com.card.core.controller.BaseController;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户收藏api接口控制器
 * 项目名称：CreditCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.collection.controller
 * 创建人：yuqy
 * 创建时间：2017/1/9 18:19
 * 修改人：yuqy
 * 修改时间：2017/1/9 18:19
 * 修改备注：
 */
@RestController
@RequestMapping(value = "collection/")
public class CollectionController extends BaseController
{
    @ApiOperation(value = "用户登录", notes = "根据用户名以及密码获取用户实体对象")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String",required = true),
            @ApiImplicitParam(name = "pwd", value = "密码", dataType = "String",required = true),
            @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String",required = true),
            @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String",required = true),
            @ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String",required = true)
    })
    @RequestMapping(value = "/add/{time}/{api_key}/{sign}", method = RequestMethod.GET)
    public JSONObject add(@PathVariable String time,@PathVariable String api_key, @PathVariable String sign)
    {
        return returnJson;
    }
}
