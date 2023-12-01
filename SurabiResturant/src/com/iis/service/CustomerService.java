package com.iis.service;
import com.iis.dao.CustomerDao;
import com.iis.entity.Customer;

import java.util.*;
public class CustomerService {
   CustomerDao custdao;
   Scanner sc;
   String username;
   String password;
   public CustomerService()
   {
	   custdao=new CustomerDao();
	   sc=new Scanner(System.in);
   }
   public void checkCustomer()
   {
	   System.out.println("Enter Your Username");
	   username=sc.next();
	   System.out.println("Enter Your Password");
	   password=sc.next();
	   custdao.customerValidation(username, password);
   }
   public void registerCustomer()
   {
	   System.out.println("Enter Your Username");
	   username=sc.next();
	   System.out.println("Enter Your Password");
	   password=sc.next();
	   custdao.NewRegistraion(username,password);
   }
   public void customerOperations(Customer id)
   {
	   int choice;
	  	 String ch="y";
	  	 while(ch.equalsIgnoreCase("y"))
	  	 {
	  		 System.out.println();
	  		 System.out.println("------------Here is Your Operations List---------------");
	  		 System.out.println("1. Surabi Resturant Menu");
	  		 System.out.println("2. Order Item");
	  		 System.out.println("3. History of User");
	  		 System.out.println("4. Exit");
	  		 System.out.println();
	  		 System.out.println("Enter Your Choice");
	  		 choice=sc.nextInt();
	  		 switch(choice)
	  		 {
	  		 case 1:
	  			 listItems();
	  			 break;
	  		 case 2:
	  			 orderItem(id);
	  			 System.out.println();
	  			 bill(id);
	  			 System.exit(0);
	  		 case 3:
	  			 historyOfUser(id);
	  			 break;
	  		 case 4:
	  			 System.exit(0);
	  		 }
	  		 System.out.println("If You want to Continue Enter(Y) else Enter (N)");
	  		 ch=sc.next();
	  	 }
   }
   
   public void listItems()
   {
	   custdao.ItemList();
   }
   public void orderItem(Customer id)
   {
	   
	  	 String ch="y";
	  	 while(ch.equalsIgnoreCase("y"))
	  	 {   
	          System.out.println("Enter the Item You want to purchase..");
	          String name=sc.next();
	          custdao.itemOrder(name,id);
	          System.out.println("If You want to Continue Enter(Y) else Enter (N)");
		  	  ch=sc.next();
	  	 }
	  	 
   }
   public void bill(Customer id)
   {
	   custdao.bill(id);
   }
   public void historyOfUser(Customer id)
   {
	   custdao.historyCustomer(id);
   }
}
