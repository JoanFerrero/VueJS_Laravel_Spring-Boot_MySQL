package com.app.spring.datajpa.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name_category")
	private String name_category;

	@Column(name = "photo")
	private String photo;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
		CascadeType.PERSIST,
		CascadeType.MERGE
    }, mappedBy = "categories")
    @JsonIgnore
    private Set<Mesa> mesas = new HashSet<>();


	public Category() {

	}

	public Category(String name_category, String photo) {
		this.name_category = name_category;
		this.photo = photo;
	}

	public long getId() {
		return id;
	}

	public String getName_category() {
		return name_category;
	}

	public void setName_category(String name_category) {
		this.name_category = name_category;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		//return "Category [id=" + id + ", name_category=" + name_category + ", photo=" + photo + "]";
		return "Category [id=" + id + ", name_category=" + name_category + ",  photo=" + photo + "]";
	}
}
