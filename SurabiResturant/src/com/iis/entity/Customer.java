package com.iis.entity;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(name="CustomerDetails")
public class Customer {
     @Id
     @Column(name="CustomerId")
     //@GeneratedValue(strategy=GenerationType.AUTO)
	private int customerid;
     @Column(name="Password")
	private String password;
     @Column(name="UserName")
	private String username;
     @OneToMany(mappedBy="custobj",cascade=CascadeType.ALL)
     private List<OrderDetails> listoforders;
     @Column(name="PhoneNumber")
	private long phonenumber;
	public long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public List<OrderDetails> getListoforders() {
		return listoforders;
	}
	public void setListoforders(List<OrderDetails> listoforders) {
		this.listoforders = listoforders;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
