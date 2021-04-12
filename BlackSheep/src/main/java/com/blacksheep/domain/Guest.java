package com.blacksheep.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;

@Entity
public class Guest {
	private Long id;
	private String fullName;
	private String email;
    private Set<Order> orders;
    
	public Guest() {}
    
	public Guest(Long id, String fullName, String email, String streetAddress, String city, String state,
			String postalCode, Set<Order> orders) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.orders = orders;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", fullName=" + fullName + ", email=" + email + ", orders=" + orders + "]";
	}
	
	

}
