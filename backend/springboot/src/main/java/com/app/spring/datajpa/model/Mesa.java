package com.app.spring.datajpa.model;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "mesas")
public class Mesa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name_mesa")
	private String name_mesa;

	@Column(name = "capacity")
	private Integer capacity;

	@Column(name = "is_active")
	private Integer is_active;

	@Column(name = "photo")
	private String photo;

	@ManyToMany(fetch = FetchType.LAZY, cascade = {
		CascadeType.PERSIST,
		CascadeType.MERGE
    })
    @JoinTable(name = "mesas_categories", joinColumns = { @JoinColumn(name = "mesa_id") }, inverseJoinColumns = {
            @JoinColumn(name = "category_id") })
    private Set<Category> categories = new HashSet<>();


	public Mesa() {

	}

	public Mesa(String name_mesa, Integer capacity, Integer is_active, String photo) {
		this.name_mesa = name_mesa;
		this.capacity = capacity;
		this.is_active = is_active;
		this.photo = photo;
	}

	public Set<Category> getCategories() {
        return this.categories;
    }

	public long getId() {
		return id;
	}

	public String getName_mesa() {
		return name_mesa;
	}

	public void setName_mesa(String name_mesa) {
		this.name_mesa = name_mesa;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getIs_active() {
		return is_active;
	}

	public void setIs_active(Integer is_active) {
		this.is_active = is_active;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Mesas [id=" + id + ", name_mesa=" + name_mesa + ", capacity=" + capacity + ", is_active=" + is_active + ", photo=" + photo + ", categories=" + categories + "]";
	}
}
