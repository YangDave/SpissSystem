package com.ss.spiss.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="usermapping")
public class UserMapping implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3242352342L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Generated(GenerationTime.ALWAYS)
	private Integer userm_id;
	
    @ManyToMany(targetEntity=Role.class,fetch=FetchType.EAGER)
    @JoinTable(name="userms_roles",
    joinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id"),
    inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="role_id"))
	private Set<Role> userm_roles = new HashSet<>();
	
	
	@ManyToOne(targetEntity=Plate.class,fetch=FetchType.EAGER)
	@JoinTable(name="userms_plate",
	joinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id",unique=true),
	inverseJoinColumns=@JoinColumn(name="plate_id",referencedColumnName="plate_id"))
	private Plate userms_plate;
	
	@OneToMany(targetEntity=Post.class,fetch=FetchType.EAGER)
	@JoinTable(name="userm_posts",
	joinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id"),
	inverseJoinColumns=@JoinColumn(name="post_id",referencedColumnName="post_id",unique=true))
	private Set<Post> userm_posts = new HashSet<>();
	
	
	@OneToOne(targetEntity=User.class,fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName="user_id",unique=true)
	private User user;
	
	@OneToMany(targetEntity=Reply.class,fetch=FetchType.EAGER)
	@JoinTable(name="userm_replies",
	joinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id"),
	inverseJoinColumns=@JoinColumn(name="reply_id",referencedColumnName="reply_id",unique=true))
	private Set<Reply> userm_replies = new HashSet<>();

	
	public Set<Reply> getUserm_replies() {
		return userm_replies;
	}

	public void setUserm_replies(Set<Reply> userm_replies) {
		this.userm_replies = userm_replies;
	}


	public Integer getUserm_id() {
		return userm_id;
	}


	public void setUserm_id(Integer userm_id) {
		this.userm_id = userm_id;
	}


	public Set<Role> getUserm_roles() {
		return userm_roles;
	}


	public void setUserm_roles(Set<Role> userm_roles) {
		this.userm_roles = userm_roles;
	}

	



	public Plate getUserms_plate() {
		return userms_plate;
	}


	public void setUserms_plate(Plate userms_plate) {
		this.userms_plate = userms_plate;
	}


	public Set<Post> getUserm_posts() {
		return userm_posts;
	}


	public void setUserm_posts(Set<Post> userm_posts) {
		this.userm_posts = userm_posts;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
