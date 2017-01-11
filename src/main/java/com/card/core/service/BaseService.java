package com.card.core.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.card.core.bean.BaseBean;
import com.card.core.bean.PagerBean;
import com.card.core.constants.SysConstants;
import com.card.core.constants.SysMsgConstants;
import com.card.core.dao.BaseDAO;
import com.card.core.exception.LogicException;
import com.card.core.utils.LogUtils;
import com.card.core.utils.ValidatorUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * <p>
 * 该类为所有业务逻辑类提供常用的工具以及常量，该类实现SysMsgConstants, Serializable, SysConstants等接口<br>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：BaseService <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月16日 上午10:19:09 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月16日 上午10:19:09 <br>
 * 修改备注：
 * <p>
 */
public abstract class BaseService<T extends BaseBean> implements SysMsgConstants, Serializable, SysConstants {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8893393967315482761L;
    
    private BaseDAO<T> DAO;


    //复查查询对象
    @PersistenceContext
    protected EntityManager entityManager;
    
    /**
     * <p>
     * 日志对象，可用来打印日志消息
     * <p>
     */
    protected LogUtils logUtils = new LogUtils(this.getClass());
    
    /**
     * 设置dao对象完成查询分页<br>
    * 2016年12月26日 下午4:07:10
    * setDAO
    * @param baseDAO
    * @return void
     */
    protected void setDAO(BaseDAO<T> baseDAO)
    {
    	this.DAO = baseDAO;
    }
    
    /**
     * 分页查询数据库数据，需要设置传入实体的:<br>
     * sorter : 排序类型,SORT_DESC,SORT_ASC两种。默认为SORT_ASC<br>
     * sort_column : 排序字段名称，默认为id<br>
     * page_index : 分页页码<br>
     * page_size : 分页每页查询条数<br>
     * 
     * <br>2016年12月16日 下午3:01:12<br>
     * findByPage
     * 
     * @param t 查询实体<br>
     * @return List 结果集<T>
     */
    @SuppressWarnings("unchecked")
	@Cacheable(value = "demo")
    protected PagerBean findByPage(T t) throws Exception{
    	if(ValidatorUtils.isEmpty(DAO))
    	{
    		throw new LogicException(SysMsgConstants.XX_ERROR,"未调用setDAO方法设置dao对象","分页");
    	}
        //所有查询实体都继承自BaseBean
        BaseBean baseBean = (BaseBean) t;
        //分页参数对象
        Pageable pageable = null; 
        if(!ValidatorUtils.isEmpty(baseBean.getSidx())){
	        //获取排序对象
			Direction sort_direction = Direction.ASC.toString().equalsIgnoreCase(baseBean.getSord()) ? Direction.ASC : Direction.DESC;
	        //设置排序对象参数
	        Sort sort = new Sort(sort_direction, baseBean.getSidx());
	        //设置分页对象参数
	        pageable = new PageRequest(baseBean.getPage() - 1, baseBean.getSize(), sort);
        }
        else
        {
        	//设置分页对象参数
	        pageable = new PageRequest(baseBean.getPage() - 1, baseBean.getSize());
        }
       
        //执行分页查询
        Page<T> page = (Page<T>) DAO.findAll(pageable);
        
        logUtils.info("分页查询参数 ：", t, "分页查询返回结果 ：", page.getContent(),"分页返回总条数：",page.getSize());
        //自定义分页对象
        PagerBean pager = new PagerBean();
        //设置分页查询出的条内容
        pager.setRows((List<BaseBean>)page.getContent());
        //设置总条数
        pager.setRecords(page.getTotalElements());
        //设置当前页码
        pager.setPage(baseBean.getPage());
        //设置总页数
        pager.setTotal(page.getTotalPages());
        //返回查询列表
        return pager;
    }
    
    /**
     * 子类必须实现该函数，用于操作日志以及单条数据读取
    * 2016年12月29日 下午6:29:03
    * findById
    * @param pk
    * @return
    * @return T
     */
    public abstract T findById(Long pk);
}
