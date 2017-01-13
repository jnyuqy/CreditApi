package com.card.api.credit.dao;

import com.card.api.credit.bean.CreditBean;
import com.card.core.dao.BaseDAO;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：credirCardApi
 * 项目版本：V1.0
 * 包名称：com.card.api.credit.dao
 * 创建人：yuqy
 * 创建时间：2017/1/11 20:07
 * 修改人：yuqy
 * 修改时间：2017/1/11 20:07
 * 修改备注：
 */
public interface CreditDAO extends BaseDAO<CreditBean>
{

    //查询信用卡列表，调用存储过程
   /* @Query(
            value = "CALL proc_query_credit_list(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11)",
            nativeQuery = true
    )*/
    /*public List<HashMap<String,Object>> list
    (
            Long bankId,//银行编号
            Long useId,//用途
            Long levelId,//等级编号
            Long yearMoneyType,//年费类型
            String moneyType,//币种
            Long organId,//组织
            Long privilegeId,//特权
            Long color,//颜色
            Long themeId,//主题
            int page,//当前页码
            int size//每页条数
    );*/
}
