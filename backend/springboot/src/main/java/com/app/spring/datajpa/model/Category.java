package com.app.spring.datajpa.model;

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

	public Category() {

	}

	public Category(String name_category, String photo) {
		this.name_category = name_category;
		this.photo = photo;
	}

	public long getId() {
		return id;
	}

	public String getName_mesa() {
		return name_category;
	}

	public void setName_mesa(String name_category) {
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
		return "Mesas [id=" + id + ", name_category=" + name_category + ", photo=" + photo + "]";
	}
}
