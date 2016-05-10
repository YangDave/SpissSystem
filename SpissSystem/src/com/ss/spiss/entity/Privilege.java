package com.ss.spiss.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="privilege")
public class Privilege implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1113L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer privilege_id;
	
	@Column(unique=true,nullable=false)
	private String privilege_name;
	private String privilege_comment;
	
	@ManyToMany(targetEntity=Role.class)
	@JoinTable(name="roles_privileges",
	joinColumns=@JoinColumn(name="privilege_id",referencedColumnName="privilege_id"),
	inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="role_id"))
	@Basic(fetch=FetchType.EAGER)
	private Set<Role> privilege_roles = new HashSet<>();
	
	
	
	
	public Set<Role> getPrivilege_roles() {
		return privilege_roles;
	}
	public void setPrivilege_roles(Set<Role> privilege_roles) {
		this.privilege_roles = privilege_roles;
	}
	public Integer getPrivilege_id() {
		return privilege_id;
	}
	public void setPrivilege_id(Integer privilege_id) {
		this.privilege_id = privilege_id;
	}
	public String getPrivilege_name() {
		return privilege_name;
	}
	public void setPrivilege_name(String privilege_name) {
		this.privilege_name = privilege_name;
	}
	public String getPrivilege_comment() {
		return privilege_comment;
	}
	public void setPrivilege_comment(String privilege_comment) {
		this.privilege_comment = privilege_comment;
	}
	
	

}
