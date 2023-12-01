package com.iis.menu;
import com.iis.service.*;
import java.util.*;
public class ResturantMenu {
     AdminService adminservice;
     CustomerService custservice;
     Scanner sc;
     public ResturantMenu()
     {
    	 adminservice=new AdminService();
    	 custservice=new CustomerService();
    	 sc=new Scanner(System.in);
     }
     public void dispalyMenu()
     {
    	 int choice;
    	 String ch="y";
    	 while(ch.equalsIgnoreCase("y"))
    	 {
    		 System.out.println("------------WelCome To Surabi Resturant---------------");
    		 System.out.println("1. Admin Login ");
    		 System.out.println("2. Customer Login");
    		 System.out.println("3. New Registration For Customer");
    		 System.out.println("4. Exit");
    		 System.out.println();
    		 System.out.println("Enter Your Choice");
    		 choice=sc.nextInt();
    		 switch(choice)
    		 {
    		 case 1:
    			 adminservice.checkAdmin();
    			 break;
    		 case 2:
    			 custservice.checkCustomer();
    			 break;
    		 case 3:
    			 custservice.registerCustomer();
    			 break;
    		 case 4:
    			 System.exit(0);
    			 break;
    		 }
    		 System.out.println("If You want to Continue Enter(Y) else Enter (N)");
    		 ch=sc.next();
    	 }
     }
}
