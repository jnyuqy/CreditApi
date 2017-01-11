package com.card.api.resource.controller;

import com.alibaba.fastjson.JSONObject;
import com.card.api.resource.bean.BannerBean;
import com.card.api.resource.service.BannerService;
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
@RequestMapping(value = "resource/")
public class ResourceController extends BaseController
{
    //意见反馈业务逻辑
    @Autowired
    private BannerService bannerService;
    /**
     * 添加意见反馈
     * @param time 时间戳
     * @param api_key apikey
     * @param sign 签名
     * @return
     */
    @ApiOperation(value = "获取banner列表", notes = "获取app所需banner全部列表")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "time", value = "时间戳,如：1484025494802(毫秒)", dataType = "String",required = true),
        @ApiImplicitParam(name = "api_key", value = "客户端授权码", dataType = "String",required = true),
        @ApiImplicitParam(name = "sign", value = "签名,参数列表首字母排序正序+time+api_key=sign", dataType = "String",required = true)
    })
    @RequestMapping(value = "/banner/{time}/{api_key}/{sign}", method = RequestMethod.GET)
    public JSONObject add(@PathVariable String time, @PathVariable String api_key,
                          @PathVariable String sign)
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
                    put(SecurityUtils._TIME, time);
                    put(SecurityUtils._API_KEY, api_key);
                    put(SecurityUtils._CLIENT_IP, SecurityUtils.getCliectIp(request));
                    put(SecurityUtils._SIGN, sign);
                }
            }))
            {
                // 获取banner列表
                List<BannerBean> banners = bannerService.list();
                //设置返回banner列表
                returnJson.put(RETURN_RESULT,banners);
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
