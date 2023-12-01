package com.iis.dao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import com.iis.entity.Customer;
import com.iis.entity.Item;
import com.iis.entity.OrderDetails;
import com.iis.sessionfactory.SessionConnection;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.iis.service.*;
public class AdminDao {
	private SessionFactory sesfact;
   private Session ses;
    private Scanner sc;
    private AdminService adminserv;
    private Transaction tx;
	public AdminDao()
	{
		sesfact=SessionConnection.getConnection();
		sc=new Scanner(System.in);
		
	}
	public void adminValidation(String username, String password)
	{
		adminserv=new AdminService();
		
		if(username.equalsIgnoreCase("admin")&&password.equalsIgnoreCase("admin"))
		{
			System.out.println(".....Welcome To Admin Page......");
				adminserv.adminOperations();
		}
		else
		{
			System.out.println("OOPS!..You Enter The Wrong Username or Password");
		}
	}
   	 
    
	public void insertItem(List<Item> itemlist) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		for(Item item:itemlist)
		{
			Item item2 = new Item();
			item2.setItemcode(item.getItemcode());
			item2.setItemname(item.getItemname());
			item2.setPrice(item.getPrice());
			ses.save(item);	
		}
		tx.commit();
		System.out.println("Item Details saved");
		
	}
	public void retriveCustomers() {
		ses=sesfact.openSession();
		Query q=ses.createQuery("from Customer");
		List<Customer> custlist=q.list();
		if(!custlist.isEmpty())
		{
		for(Customer c:custlist)
		{
			System.out.println("The Customer Id : "+c.getCustomerid());
			System.out.println("The Phone Number of Customer :"+c.getPhonenumber());
			System.out.println("---------------------------------------------------------");
		}
		}
		else
		{
			System.out.println("There is No Customers Present");
		}
	}
	public void updateCutomerDetails(int id) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Customer cust=ses.find(Customer.class, id);
		if(cust!=null)
		{
			//Customer custmer=new Customer();
			System.out.println("Enter the Phone Number You Want to update");
			cust.setPhonenumber(sc.nextLong());
			ses.save(cust);
			System.out.println("Phone Number is Updates");
			tx.commit();
		}
		else
	    {
	    	System.out.println("No Customer Found....");
	    }
		 
		
	}
	public void deleteCutomer(int id) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Customer cust=ses.find(Customer.class, id);
		if(cust!=null)
		{
			ses.delete(cust);
			tx.commit();
		}
		else
		{
			System.out.println("No Customer Found..!");
		}
	}
	public void createCustomer(List<Customer> custlist) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		for(Customer list:custlist)
		{
			Customer cust=new Customer();
			cust.setCustomerid(list.getCustomerid());
			cust.setUsername(list.getUsername());
			cust.setPassword(list.getPassword());
			cust.setPhonenumber(list.getPhonenumber());
			ses.save(cust);
		}
		tx.commit();
		System.out.println("The Customers are Saved..");
		
	}
	public void customerOrderedlist(int id) {
		ses=sesfact.openSession();
		Customer cust=ses.find(Customer.class, id);
		if(cust!=null)
		{
			List<OrderDetails> list=cust.getListoforders();
			System.out.println("------ the Customer History ---------");
			for(OrderDetails det:list)
			{
				System.out.println("The Order Id is : "+det.getOrderid());
			System.out.println("The Item Name is : "+det.getItemname());
			System.out.println("The Number Of Plates are : "+det.getNoofplates());
			System.out.println("The Bill Of Order is : "+det.getTotalbill());
			System.out.println();
			}
		}
		else
		{
			System.out.println("The Customer Purchase Any Items .. ");
		}
		
	}
	public void updateItemDetails(int id) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Item item=ses.find(Item.class, id);
		if(item!=null)
		{
			System.out.println("Enter the Price Of Item You Want to update");
			item.setPrice(sc.nextDouble());
			ses.save(item);
			System.out.println("item Price is Updates");
			tx.commit();
		}
		else
	    {
	    	System.out.println("No Item Found....");
	    }	
	}
	public void deleteItemDetails(int id) {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Item item=ses.find(Item.class, id);
		if(item!=null)
		{
			ses.delete(item);
			tx.commit();
		}
		else
	    {
	    	System.out.println("No Item Found....");
	    }
		
	}
	public void itemList() {
		ses=sesfact.openSession();
		tx=ses.beginTransaction();
		Query q=ses.createQuery("from Item");
	   List<Item> item=q.list();
	   if(!item.isEmpty())
	   {
		   System.out.println("The List of Items ......");
		   System.out.println();
	   for(Item data:item)
	   {
		   System.out.println("The Item Code Is : "+data.getItemcode());
		   System.out.println("The Item Name Is : "+data.getItemname());
		   System.out.println("The Item Price Is : "+data.getPrice());
		   System.out.println();
	   }
	   }
	   else
	   {
		   System.out.println("No Items Present....");
	   }
	}
	public void billsToday() {
		double totalbill=0;
		int totalorder=0;
		LocalDate t=LocalDate.now();
		ses=sesfact.openSession();
		Query q=ses.createQuery("from OrderDetails o where o.date=:todaydate");
		q.setParameter("todaydate", t);
		List<OrderDetails> listorder=q.list();
		if(!listorder.isEmpty())
		{
		for(OrderDetails list:listorder)
		{
			totalbill=totalbill+list.getTotalbill();
			totalorder=totalorder+list.getNoofplates();
		}
		System.out.println();
		System.out.println("The Total Plates Served today is "+totalorder);
		System.out.println("Today Total Amount we get is "+totalbill);
		}
		else
		{
			System.out.println("No Item Purchased Today....");
		}
	}
	public void salesOfThisMonth() {
		int totalorders=0;
		LocalDate date=LocalDate.now();
		ses=sesfact.openSession();
		Query q=ses.createQuery("from OrderDetails o where month(o.date)=MONTH(CURRENT_DATE)");
		List<OrderDetails> order=q.list();
		if(!order.isEmpty())
		{
		for(OrderDetails list:order)
		{
			totalorders=totalorders+list.getNoofplates();
		}
		System.out.println("The Total Plates Saled in this Month is : "+totalorders);
		}
		else
		{
			System.out.println("No Item Saled In This Month ");
		}
		
	}

	
	
}
