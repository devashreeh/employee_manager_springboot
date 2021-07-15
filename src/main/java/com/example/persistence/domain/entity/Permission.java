package com.example.persistence.domain.entity;

import javax.persistence.Entity;

import com.example.persistence.common.entity.BaseEntity;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = false)
public class Permission extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
