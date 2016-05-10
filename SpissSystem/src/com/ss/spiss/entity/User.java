package com.ss.spiss.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Indexes;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity
@Table(name="user",indexes={@Index(name="user_name",unique=true, columnList ="user_name")})
public class User implements Serializable{
	
	private static final long serialVersionUID = 1111L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Generated(GenerationTime.ALWAYS)
	private Integer user_id;
	
	@Column(unique=true,nullable=false)
	private String user_name; 
	
	@Column(nullable=false)
	private String password;
	
	private String nice_name;
	
	private String email;
	
	@Column(nullable=false,columnDefinition="int default 0")
	private int score;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date add_time = new Date();
	
	@OneToOne(targetEntity=UserMapping.class,mappedBy="user",cascade=CascadeType.ALL)
	private UserMapping userMapping;
	
	public UserMapping getUserMapping() {
		return userMapping;
	}
	public void setUserMapping(UserMapping userMapping) {
		this.userMapping = userMapping;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNice_name() {
		return nice_name;
	}
	public void setNice_name(String nice_name) {
		this.nice_name = nice_name;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Integer){
			if(this.user_id.equals(obj)){
				return true;
			}
		}
		if(obj instanceof String){
			if(this.user_name.equals(obj)){
				return true;
			}
		}
		if(obj instanceof User){
			User u = (User)obj;
			if(this.user_id.equals(u.getUser_id())){
				return true;
			}
		}
		
		
		return false;
	}
	
	
}
