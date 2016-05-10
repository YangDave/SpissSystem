package com.ss.spiss.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="role")
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1112L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Generated(GenerationTime.ALWAYS)
	private Integer role_id;
	
	@Column(unique=true,nullable=false)
	private String role_name;
	private String role_comment;
	
	@ManyToMany(targetEntity=UserMapping.class)
	@JoinTable(name="userms_roles",
	joinColumns=@JoinColumn(name="role_id",referencedColumnName="role_id"),
	inverseJoinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id"))
	private Set<UserMapping> role_userms = new HashSet<>();
	
	@ManyToMany(targetEntity=Privilege.class,fetch=FetchType.EAGER)
	@JoinTable(name="roles_privileges",
	joinColumns=@JoinColumn(name="role_id",referencedColumnName="role_id"),
	inverseJoinColumns=@JoinColumn(name="privilege_id",referencedColumnName="privilege_id"))
	@Basic(fetch=FetchType.EAGER)
	private Set<Privilege> role_privileges = new HashSet<>();
	
	
	
	public Set<UserMapping> getRole_userms() {
		return role_userms;
	}
	public void setRole_userms(Set<UserMapping> role_userms) {
		this.role_userms = role_userms;
	}
	public Set<Privilege> getRole_privileges() {
		return role_privileges;
	}
	public void setRole_privileges(Set<Privilege> role_privileges) {
		this.role_privileges = role_privileges;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_comment() {
		return role_comment;
	}
	public void setRole_comment(String role_comment) {
		this.role_comment = role_comment;
	}
	
	

}
