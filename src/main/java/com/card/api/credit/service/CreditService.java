package com.card.api.credit.service;

import com.card.api.credit.bean.CreditBean;
import com.card.api.credit.dao.CreditDAO;
import com.card.core.service.BaseService;
import com.card.core.utils.ParseUtils;
import com.card.core.utils.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
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

        //执行本地查询调用存储过程
        Query query = entityManager.createNativeQuery("CALL proc_query_credit_list(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11)");
        //设置参数列表
        query.setParameter(1,bankId);
        query.setParameter(2,useId);
        query.setParameter(3,levelId);
        query.setParameter(4,yearMoney);
        query.setParameter(5,moneyType);
        query.setParameter(6,organId);
        query.setParameter(7,privilegeId);
        query.setParameter(8,color);
        query.setParameter(9,themeId);
        query.setParameter(10,page);
        query.setParameter(11,size);
        //执行查询并且返回列表
        List rows = query.getResultList();
        //最终返回列表
        List<HashMap<String,Object>> result = new ArrayList<HashMap<String, Object>>();
        //遍历设置key,value对应
        for (Object row : rows) {
            //每一行数据
            HashMap<String,Object> map = new HashMap<String,Object>();
            Object[] cell = (Object[]) row;
            map.put("id",ValidatorUtils.isEmpty(cell[0]) ? "" : cell[0]);
            map.put("applyCount",ValidatorUtils.isEmpty(cell[1]) ? "" : cell[1]);
            map.put("name",ValidatorUtils.isEmpty(cell[2]) ? "" : cell[2]);
            map.put("img",ValidatorUtils.isEmpty(cell[3]) ? "" : cell[3]);
            map.put("title",ValidatorUtils.isEmpty(cell[4]) ? "" : cell[4]);
            result.add(map);
        }
        return result;
        //执行查询存储过程返回信用卡列表
        //return creditDAO.list(bankId,useId,levelId,yearMoney,moneyType,organId,privilegeId,color,themeId,page,size);
    }

    @Override
    public CreditBean findById(Long pk) {
        return creditDAO.findOne(pk);
    }

}
