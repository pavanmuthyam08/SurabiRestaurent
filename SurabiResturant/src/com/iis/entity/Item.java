package com.iis.entity;
import javax.persistence.*;
@Entity
@Table(name="ItemDetails")
//InheritanceType.Joined strategy is used for table per sub class strategey
//@Inheritance(strategy=InheritanceType.JOINED)
public class Item {
    @Id
    @Column(name="ItemCode")
	private int itemcode;
    @Column(name="ItemName")
	private String itemname;
    @Column(name="Price")
	private double price;
	public int getItemcode() {
		return itemcode;
	}
	public void setItemcode(int itemcode) {
		this.itemcode = itemcode;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
