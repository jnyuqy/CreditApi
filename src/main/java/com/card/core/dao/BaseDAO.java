package com.card.core.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * 该接口继承自JpaRepository<T, Long>,如需自定义CRUD函数，可自己在继承该接口的子接口内实现<br>
 * 如：<br>
 * @Query("from User u where u.name=:name")<br>
 * User findUser(@Param("name") String name);<br>
 * <br>
 * 项目名称：CreditCardCore<br>
 * 项目版本：V1.0 <br>
 * 类名称：IBaseDAO <br>
 * 创建人：yuqy <br>
 * 创建时间：2016年12月16日 上午10:03:57 <br>
 * 修改人：yuqy <br>
 * 修改时间：2016年12月16日 上午10:03:57 <br>
 * 修改备注：<br>
 */
public interface BaseDAO<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>,QueryDslPredicateExecutor<T>, Serializable {

}
