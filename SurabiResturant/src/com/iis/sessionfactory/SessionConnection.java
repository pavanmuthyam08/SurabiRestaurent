package com.iis.sessionfactory;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.iis.entity.Customer;
import com.iis.entity.Item;
import com.iis.entity.OrderDetails;

public class SessionConnection {
	private static SessionFactory sesfact;

	private SessionConnection() {
		sesfact = new Configuration().configure("hiber.cfg.xml").addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Item.class).addAnnotatedClass(OrderDetails.class).buildSessionFactory();
	}

	public static SessionFactory getConnection() {
		if (sesfact == null) {
			SessionConnection sescon = new SessionConnection();
			return sesfact;
		}

		else {

			return sesfact;
		}

	}
}
