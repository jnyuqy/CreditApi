package com.card.core.naming;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class TableNameCaseNaming extends ImprovedNamingStrategy {

	/**  
	* (说明)
	* {@value}
	*/ 
	private static final long serialVersionUID = -7764135656923372043L;
	
	/**
	 * 
	 */
	@Override
	public String tableName(String tableName) {
		return tableName.toUpperCase();
	}
}
