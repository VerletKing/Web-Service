package org.verlet.site.bean;

import java.time.Instant;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement
public class Account {
	private long id;
	private Instant lasrModified;
	@NotBlank(message = "{validate.account.name}")
	private String name;
	@NotBlank(message = "{validate.account.billingAddress}")
	private String billingAddress;
	private String shippingAddress;
	@NotBlank(message = "{validate.account.phoneNumber}")
	private String phoneNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getLasrModified() {
		return lasrModified;
	}

	public void setLasrModified(Instant lasrModified) {
		this.lasrModified = lasrModified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
