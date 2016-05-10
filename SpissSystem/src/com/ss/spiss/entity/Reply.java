package com.ss.spiss.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity
@Table(name="reply")
public class Reply implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 435234235L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Generated(GenerationTime.ALWAYS)
	private int reply_id;
	
	@ManyToOne(targetEntity=Post.class,fetch=FetchType.EAGER)
	@JoinTable(name="post_replies",
	joinColumns=@JoinColumn(name="reply_id",referencedColumnName="reply_id",unique=true),
	inverseJoinColumns=@JoinColumn(name="post_id",referencedColumnName="post_id"))
	private Post replies_post;
	
	@ManyToOne(targetEntity=UserMapping.class,fetch=FetchType.EAGER)
	@JoinTable(name="userm_replies",
	joinColumns=@JoinColumn(name="reply_id",referencedColumnName="reply_id",unique=true),
	inverseJoinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id"))
	private UserMapping replies_userm;
	
	
	@Column(nullable=false)
	private String replyContent;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date replyDate = new Date();
	
	
	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Post getReplies_post() {
		return replies_post;
	}

	public void setReplies_post(Post replies_post) {
		this.replies_post = replies_post;
	}


	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public UserMapping getReplies_userm() {
		return replies_userm;
	}

	public void setReplies_userm(UserMapping replies_userm) {
		this.replies_userm = replies_userm;
	}

	
}
