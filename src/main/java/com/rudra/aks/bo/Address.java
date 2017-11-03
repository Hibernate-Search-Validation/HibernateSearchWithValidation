package com.rudra.aks.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "test_add")
@Proxy(lazy = false)
public class Address {
	
	@Id
	@GeneratedValue//(generator="gen")
	@GenericGenerator(name="gen", strategy="foreign", parameters={@Parameter(name="property", value="id")})
	private int id;
	
	private String address;
	
	private String country;

	@OneToOne
	@PrimaryKeyJoinColumn()
	private TestBO	testbo;
	
	
	public TestBO getTestbo() {
		return testbo;
	}

	public void setTestbo(TestBO testbo) {
		this.testbo = testbo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Address() {
		super();
	}

/*	public Address(String address, String country) {
		super();
		this.address = address;
		this.country = country;
	}

	*/
}
