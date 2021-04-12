package com.blacksheep.security;

import java.util.Set;


import org.springframework.security.core.userdetails.UserDetails;

import com.blacksheep.domain.Customer;

public class CustomSecurity extends Customer implements UserDetails{

	private static final long serialVersionUID = -7557407588987378717L;
	
	public CustomSecurity() {}

	public CustomSecurity(Customer customer) {		
		this.setId(customer.getId());
		this.setFirstName(customer.getFirstName());
		this.setLastName(customer.getLastName());
		this.setEmail(customer.getEmail());
		this.setPassword(customer.getPassword());
		this.setPhone(customer.getPhone());
		this.setStreetAddress(customer.getStreetAddress());
		this.setCity(customer.getCity());
		this.setState(customer.getState());
		this.setPostalCode(customer.getPostalCode());
		this.setAuthorities(customer.getAuthorities());
		this.setOrders(customer.getOrders());
	}

	@Override
	public Set<Authority> getAuthorities() {
		return super.getAuthorities();
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
