package com.card.api;

import java.io.Serializable;

public class User implements Serializable{
	/**  
	* (说明)
	* {@value}
	*/ 
	private static final long serialVersionUID = 42214706244211368L;
	private Long id; 
    private String name; 
    private Integer age;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	} 
    
}
