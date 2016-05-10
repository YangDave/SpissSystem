package com.ss.spiss.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name="plate")
public class Plate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 24362452345L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Generated(GenerationTime.ALWAYS)
	private Integer plate_id;

	@Column(nullable=false,unique=true)
	private String plate_name;

	private String plate_introduction;

	@OneToMany(targetEntity=UserMapping.class,fetch=FetchType.LAZY)
	@JoinTable(name="userms_plate",
	joinColumns=@JoinColumn(name="plate_id",referencedColumnName="plate_id"),
	inverseJoinColumns=@JoinColumn(name="userm_id",referencedColumnName="userm_id",unique=true))
	private Set<UserMapping> plate_userms =new HashSet<>();
	
	@Column(nullable=false,columnDefinition="int default 0")
	private int click_count;
	
	@OneToMany(targetEntity=Post.class,fetch=FetchType.LAZY)
	@JoinTable(name="plate_posts",
	joinColumns=@JoinColumn(name="plate_id",referencedColumnName="plate_id"),
	inverseJoinColumns=@JoinColumn(name="post_id",referencedColumnName="post_id",unique=true))
	private Set<Post> plate_posts = new HashSet<>();
	
	public Set<UserMapping> getPlate_userms() {
		return plate_userms;
	}

	public void setPlate_userms(Set<UserMapping> plate_userms) {
		this.plate_userms = plate_userms;
	}

	public Integer getPlate_id() {
		return plate_id;
	}

	public void setPlate_id(Integer plate_id) {
		this.plate_id = plate_id;
	}

	public String getPlate_name() {
		return plate_name;
	}

	public void setPlate_name(String plate_name) {
		this.plate_name = plate_name;
	}

	
	public String getPlate_introduction() {
		return plate_introduction;
	}

	public void setPlate_introduction(String plate_introduction) {
		this.plate_introduction = plate_introduction;
	}

	public int getClick_count() {
		return click_count;
	}

	public void setClick_count(int click_count) {
		this.click_count = click_count;
	}

	public Set<Post> getPlate_posts() {
		return plate_posts;
	}

	public void setPlate_posts(Set<Post> plate_posts) {
		this.plate_posts = plate_posts;
	}

	
	
	
	
	
	

}
