package com.card.api.credit.service;

import com.card.api.credit.bean.CreditBean;
import com.card.api.credit.dao.CreditDAO;
import com.card.core.service.BaseService;
import com.card.core.utils.JSONUtils;
import com.card.core.utils.ParseUtils;
import com.card.core.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.credit.service
 * 创建人：yuqy
 * 创建时间：2017/1/11 20:08
 * 修改人：yuqy
 * 修改时间：2017/1/11 20:08
 * 修改备注：
 */
@Service
public class CreditService
        extends BaseService<CreditBean>
{
    @Autowired
    private CreditDAO creditDAO;

    /**
     * 根据如下参数查询信用卡列表：
     *1、银行
     *2、用途
     *3、等级
     *4、年费
     *5、币种
     *6、组织
     *7、特权
     *8、颜色
     *9、主题
     *10、当前页码
     *11、每页显示条目数
     * @param params
     */
    public List<HashMap<String,Object>> list(Object ...params)
    {
        //所属银行
        Long bankId = ValidatorUtils.isEmpty(params[0])? 0 : Long.valueOf(ParseUtils.toString(params[0]));
        //用途
        Long useId = ValidatorUtils.isEmpty(params[1])? 0 : Long.valueOf(ParseUtils.toString(params[1]));
        //等级
        Long levelId = ValidatorUtils.isEmpty(params[2])? 0 : Long.valueOf(ParseUtils.toString(params[2]));
        //年费
        Long yearMoney = ValidatorUtils.isEmpty(params[3])? 0 : Long.valueOf(ParseUtils.toString(params[3]));
        //币种
        String moneyType = ParseUtils.toString(params[4]);
        //组织编号
        Long organId = ValidatorUtils.isEmpty(params[5])? 0 : Long.valueOf(ParseUtils.toString(params[5]));
        //特权编号
        Long privilegeId = ValidatorUtils.isEmpty(params[6])? 0 : Long.valueOf(ParseUtils.toString(params[6]));
        //颜色
        Long color = ValidatorUtils.isEmpty(params[7])? 0 : Long.valueOf(ParseUtils.toString(params[7]));
        //主题
        Long themeId = ValidatorUtils.isEmpty(params[8])? 0 : Long.valueOf(ParseUtils.toString(params[8]));
        //当前页码
        int page = ParseUtils.toInteger(params[9]);
        //每页条目数
        int size = ParseUtils.toInteger(params[10]);
        //执行查询存储过程返回信用卡列表
        return creditDAO.list(bankId,useId,levelId,yearMoney,moneyType,organId,privilegeId,color,themeId,page,size);
    }

    @Override
    public CreditBean findById(Long pk) {
        return creditDAO.findOne(pk);
    }

}
