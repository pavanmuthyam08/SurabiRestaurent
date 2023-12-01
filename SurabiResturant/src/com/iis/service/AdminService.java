package com.iis.service;
import com.iis.entity.Item;
import com.iis.dao.AdminDao;
import com.iis.entity.Customer;
import java.util.*;
public class AdminService {
   AdminDao admindao;
   Scanner sc;
   List<Item> itemlist;
   List<Customer> custlist;
   public AdminService()
   {
	   admindao=new AdminDao();
	   sc=new Scanner(System.in);
	   itemlist=new ArrayList<>();
	   custlist=new ArrayList<>();
   }
   public void adminOperations()
   {
	   int choice;
	  	 String ch="y";
	  	 while(ch.equalsIgnoreCase("y"))
	  	 {
	  		 System.out.println();
	  		 System.out.println("------------Here is Your Operations List---------------");
	  		 System.out.println("1. CRUD Operations On Item");
	  		 System.out.println("2. CRUD Operations On Customer");
	  		 System.out.println("3. Find List Of Order Done By Customer Id");
	  		 System.out.println("4. TotalBills In This Day");
	  		 System.out.println("5.  Total Sales of This Month");
	  		 System.out.println("6. Exit");
	  		 System.out.println();
	  		 System.out.println("Enter Your Choice");
	  		 choice=sc.nextInt();
	  		 switch(choice)
	  		 {
	  		 case 1:
	  			crudOperationItem();
	  			 break;
	  		 case 2:
	  			crudoperationCustomer();
	  			 break;
	  		 case 3:
	  			ordersOfCustomer();
	  			 break;
	  		 case 4:
	  			totalBillToday();
	  			 break;
	  		 case 5:
	  			totalSalesOfMonth();
	  			 break;
	  		 case 6:
	  			 System.exit(0);
	  			 break;
	  		 }
	  		 System.out.println("If You want to Continue Enter(Y) else Enter (N)");
	  		 ch=sc.next();
	  	 }
	
   }
   public void crudOperationItem()
   {
	   int choice;
	  	 String ch="y";
	  	 while(ch.equalsIgnoreCase("y"))
	  	 {
	  		 System.out.println();
	  		 System.out.println("------------Here is Your Operations List---------------");
	  		 System.out.println("1. Insert Items");
	  		 System.out.println("2. List Of Items");
	  		 System.out.println("3. update Item");
	  		 System.out.println("4. Delete Item");
	  		System.out.println("Enter Your Choice");
	  		 choice=sc.nextInt();
	  		 switch(choice)
	  		 {
	  		 case 1:
	  			 insertItem();
	  			 break;
	  		 case 2:
	  			listOfItems();
	  			 break;
	  		 case 3:
	  			updateItem();
	  			 break;
	  		 case 4:
	  			deleteItem();
	  			 break;
	  		 }
	  		 System.out.println("If You want to Continue Enter(Y) else Enter (N)");
	  		 ch=sc.next();
	  	 }
	  		
   }
   public void crudoperationCustomer()
   {
	   int choice;
	  	 String ch="y";
	  	 while(ch.equalsIgnoreCase("y"))
	  	 {
	  		 System.out.println();
	  		 System.out.println("------------Here is Your Operations List---------------");
	  		 System.out.println("1. Add New Customers");
	  		 System.out.println("2. Delete the Customer");
	  		 System.out.println("3. View All The Customers");
	  		 System.out.println("4. Update Customer Details");
	  		System.out.println("Enter Your Choice");
	  		 choice=sc.nextInt();
	  		 switch(choice)
	  		 {
	  		 case 1:
	  			createCustomer();
	  			 break;
	  		 case 2:
	  			deleteCustomer();
	  			 break;
	  		 case 3:
	  			retriveCustomer();
	  			 break;
	  		 case 4:
	  			updateCustomer();
	  			 break;
	  		 }
	  		 System.out.println("If You want to Continue Enter(Y) else Enter (N)");
	  		 ch=sc.next();
	  	 }
   }
   public void checkAdmin()
   {
	   System.out.println("Enter the UserName");
	   String username=sc.next();
	   System.out.println("Enter Password");
	   String password=sc.next();
	   admindao.adminValidation(username,password);
   }
   
   public void insertItem()
   {
	   System.out.println("Enter How Many Items You Want to insert..");
	   int noofitem=sc.nextInt();
	   for(int x=0;x<noofitem;x++)
	   {
		   Item item=new Item();
		   System.out.println("Enter The Item Code");
		   item.setItemcode(sc.nextInt());
		   System.out.println("Enter The Item Name ");
		   item.setItemname(sc.next());
		   System.out.println("Enter the Price of Item");
		   item.setPrice(sc.nextDouble());
		   itemlist.add(item);
	   }
	   admindao.insertItem(itemlist);
   }
   public void retriveCustomer()
   {
	   admindao.retriveCustomers();
   }
   public void updateCustomer()
   {
	   System.out.println("Enter Customer Id you want to update");
	   int id=sc.nextInt();
	   admindao.updateCutomerDetails(id);
   }
   public void deleteCustomer()
   {
	   System.out.println("Enter Customer Id you want to Delete");
	   int id=sc.nextInt();
	   admindao.deleteCutomer(id);
   }
   public void createCustomer()
   {
	   System.out.println("Enter How Many Customer Do You Want to Insert...");
	   int noofcust=sc.nextInt();
	   for(int x=0;x<noofcust;x++)
	   {
		   Customer cust=new Customer();
		   System.out.println("Enter The Customer Id");
		   cust.setCustomerid(sc.nextInt());
		   System.out.println("Enter customer username");
		   cust.setUsername(sc.next());
		   System.out.println("Enter password of Customer");
		   cust.setPassword(sc.next());
		   System.out.println("Enter The Phone Number of Customer");
		   cust.setPhonenumber(sc.nextLong());
		   custlist.add(cust);
	   }
	   admindao.createCustomer(custlist);
   }
   public void ordersOfCustomer()
   {
	   System.out.println("Enter the Customer id You want to search");
	   int id=sc.nextInt();
	   admindao.customerOrderedlist(id);
   }
   public void updateItem()
   {
	   System.out.println("Enter Item Id you want to update");
	   int id=sc.nextInt();
	   admindao.updateItemDetails(id);   
   }
   public void deleteItem()
   {
	   System.out.println("Enter Item Id you want to update");
	   int id=sc.nextInt();
	   admindao.deleteItemDetails(id); 
   }
   public void listOfItems()
   {
	   admindao.itemList();
   }
   public void totalBillToday()
   {
	   admindao.billsToday();
   }
   public void totalSalesOfMonth()
   {
	   admindao.salesOfThisMonth();
   }
}
