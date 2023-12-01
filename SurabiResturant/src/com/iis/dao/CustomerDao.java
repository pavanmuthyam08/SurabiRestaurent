package com.iis.dao;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.iis.entity.Customer;
import com.iis.entity.Item;
import com.iis.entity.OrderDetails;
import com.iis.menu.ResturantMenu;
import com.iis.service.CustomerService;
import com.iis.sessionfactory.SessionConnection;

import java.time.LocalDate;
import java.util.*;
public class CustomerDao {
	private SessionFactory sesfact;
	private Session ses;
	private Transaction tx;
	Customer custobj;
	private CustomerService custserv;
	private Scanner sc;
	private OrderDetails orderdets;
	private ResturantMenu menu;
	double totalamount=0;
	List<OrderDetails> list;
	public CustomerDao()
	{
		sesfact=SessionConnection.getConnection();
		custobj=new Customer();
		orderdets=new OrderDetails();
		sc=new Scanner(System.in);
		list=new ArrayList<>();
	}
    public void customerValidation(String username,String password)
    {
    	
    	custserv=new CustomerService();
    	ses=sesfact.openSession();
    	tx=ses.beginTransaction();
    	Query q=ses.createQuery("From Customer c where c.username=:uname and c.password=:pwd");
    	q.setParameter("uname", username);
    	q.setParameter("pwd", password);
    	List<Customer> userlist=q.list();
    	if(!userlist.isEmpty())
    	{
    		System.out.println("----Welcome to Customer Page------");
    		for(Customer id:userlist)
    		{
    		custserv.customerOperations(id);
    		}
    	}
    	else
    	{
    		System.out.println("You are Enter Wrong Credintials...");
    	}
    	
    }
    public void NewRegistraion(String username, String password)
    {
    	custserv=new CustomerService();
    	ses=sesfact.openSession();
    	tx=ses.beginTransaction();
    	Query q=ses.createQuery("From Customer c where c.username=:uname and c.password=:pwd");
    	q.setParameter("uname", username);
    	q.setParameter("pwd", password);
    	List<Customer> userlist=q.list();
    	if(userlist.isEmpty())
    	{
    		custobj.setUsername(username);
    		custobj.setPassword(password);
    		System.out.println("Enter The Customer id..");
    		custobj.setCustomerid(sc.nextInt());
    		System.out.println("Enter The Phone Nuber..");
    		custobj.setPhonenumber(sc.nextLong());
    		ses.save(custobj);
    		tx.commit();
    		System.out.println("---- Registration Done Successfully-----");
    		System.out.println("------Login as a Customer Now------");
    		menu=new ResturantMenu();
    		menu.dispalyMenu();	
    	}
    	else
    	{
    		System.out.println("You are Already Exist Plese Go and  Login As A Customer ");
    		menu=new ResturantMenu();
    		menu.dispalyMenu();
    	}
    		
    	
    }
	public void ItemList() {
		
		ses=sesfact.openSession();
		Query q=ses.createQuery("from Item");
		List<Item> itemlist=q.list();
		if(!itemlist.isEmpty())
		{
		for(Item item:itemlist)
		{
			System.out.println("The Menu Of Our Resturant ");
			System.out.println();
			System.out.println("The Item Code is :"+item.getItemcode());
			System.out.println("The Item Name is :"+item.getItemname());
			System.out.println("The Price Of Item is :"+item.getPrice());
			System.out.println();
		}
		}
		else
		{
			System.out.println("No Item Are there..");
		}
		
	}
	public void itemOrder(String name, Customer id) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Query q=ses.createQuery("select i from Item i where i.itemname=:name");
		q.setParameter("name", name);
		List<Item> item=q.list();
		if(item!=null)
		{
			for(Item it:item)
			{
				OrderDetails order=new OrderDetails();
			System.out.println("Enter How many Plates you want...");
			int noofplates=sc.nextInt();
			double price=it.getPrice()*noofplates;
			totalamount=totalamount+price;
			LocalDate d=LocalDate.now();
			order.setItemname(name);
			order.setNoofplates(noofplates);
			order.setTotalbill(price);
			order.setDate(d);
			order.setCustobj(id);
			list.add(order);
			ses.save(orderdets);
			System.out.println("Here is Your Food Have it.....");
			}
			
			tx.commit();
		}
		else
		{
			System.out.println("No Item Present with this name..");
		}
		
	}
	public void bill(Customer id)
	{
	   for(OrderDetails order:list)
	   {
		   System.out.println("Item name  :"+order.getItemname());
		   System.out.println("Number Of Plates :"+order.getNoofplates());
		   System.out.println("The item Bill is "+order.getTotalbill());
		   System.out.println();
	   }
	   System.out.println("Total Bill is :"+totalamount);
	}
	public void historyCustomer(Customer id) {
       
		ses=sesfact.openSession();
		Query q=ses.createQuery("select i from OrderDetails i where i.custobj=:name");
		q.setParameter("name", id);
		List<OrderDetails> list=q.list();
		if(!list.isEmpty())
		{
			System.out.println();
			System.out.println("The History of user....");
			System.out.println();
		   for(OrderDetails details:list)
		   {
			   System.out.println("Order id of Item :"+details.getOrderid());
			   System.out.println("Item name is :"+details.getItemname());
			   System.out.println("Date of Purchase "+details.getDate());
			   System.out.println("Bill of Order is "+details.getTotalbill());
			   System.out.println();
		   }
		}
		else
		{
		   System.out.println("You Are Not order Anything......");	
		}
	}
    
}
