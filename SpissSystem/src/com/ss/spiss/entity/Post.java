package com.ss.spiss.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="post")
public class Post implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 342353432L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Generated(GenerationTime.ALWAYS)
	private int post_id;
	
	
	private String title;
	
	@Column(length=2000)
	private String content;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date launch_date = new Date();
	
	@Column(nullable=false,columnDefinition="int default 0")
	private int status;
	
	@OneToOne(targetEntity=UserMapping.class,fetch=FetchType.LAZY)
	@JoinColumn(name="last_userm_id",referencedColumnName="userm_id",unique=true)
	private UserMapping lastUserm;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastReplyDate;
	
	@ManyToOne(targetEntity=Plate.class,fetch=FetchType.LAZY)
	@JoinTable(name="plate_posts",
	joinColumns=@JoinColumn(name="post_id",referencedColumnName="post_id",unique=true),
	inverseJoinColumns=@JoinColumn(name="plate_id",referencedColumnName="plate_id"))
	private Plate posts_plate;
	
	@ManyToOne(targetEntity=UserMapping.class,fetch=FetchType.LAZY)
	@JoinTable(name="userm_posts",
	joinColumns=@JoinColumn(name="post_id",referencedColumnName="post_id",unique=true),
	inverseJoinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id"))
	private UserMapping posts_userm;
	
	
	@OneToMany(targetEntity=Reply.class,fetch=FetchType.LAZY)
	@JoinTable(name="post_replies",
	joinColumns=@JoinColumn(name="post_id",referencedColumnName="post_id"),
	inverseJoinColumns=@JoinColumn(name="reply_id",referencedColumnName="reply_id"))
	private Set<Reply> post_replies = new HashSet<>();


	public int getPost_id() {
		return post_id;
	}


	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getLaunch_date() {
		return launch_date;
	}


	public void setLaunch_date(Date launch_date) {
		this.launch_date = launch_date;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public UserMapping getLastUserm() {
		return lastUserm;
	}


	public void setLastUserm(UserMapping lastUserm) {
		this.lastUserm = lastUserm;
	}


	public Date getLastReplyDate() {
		return lastReplyDate;
	}


	public void setLastReplyDate(Date lastReplyDate) {
		this.lastReplyDate = lastReplyDate;
	}


	public Plate getPosts_plate() {
		return posts_plate;
	}


	public void setPosts_plate(Plate posts_plate) {
		this.posts_plate = posts_plate;
	}


	public UserMapping getPosts_userm() {
		return posts_userm;
	}


	public void setPosts_userm(UserMapping posts_userm) {
		this.posts_userm = posts_userm;
	}


	public Set<Reply> getPost_replies() {
		return post_replies;
	}


	public void setPost_replies(Set<Reply> post_replies) {
		this.post_replies = post_replies;
	}

	
	
	
	
	

}
