package com.iis.entity;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name="OrderDetails")
public class OrderDetails{
	//@ManyToOne
	//@JoinColumn(name = "item_code", foreignKey = @ForeignKey(name = "ItemCode"))
	@Id
    @Column(name="Orderid")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderid;
    @Column(name="DateOfOrdered")
	private LocalDate date;
    @Column(name="TotalBill")
	private double totalbill;
	@ManyToOne
	@JoinColumn(name="CustomerCodeFk")
	private Customer custobj;
	private int noofplates;
	private String itemname;
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getNoofplates() {
		return noofplates;
	}
	public void setNoofplates(int noofplates) {
		this.noofplates = noofplates;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public LocalDate getDate() {
		return date;
	}
	
	public double getTotalbill() {
		return totalbill;
	}
	public void setTotalbill(double totalbill) {
		this.totalbill = totalbill;
	}
	public Customer getCustobj() {
		return custobj;
	}
	public void setCustobj(Customer custobj) {
		this.custobj = custobj;
	}
	
}
