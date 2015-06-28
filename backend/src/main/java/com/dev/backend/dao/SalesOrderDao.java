package com.dev.backend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev.backend.model.OrderLine;
import com.dev.backend.model.SalesOrder;

@Repository
public class SalesOrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(SalesOrder salesOrder) {
	Session session = sessionFactory.getCurrentSession();
	session.saveOrUpdate(salesOrder);
    }

    public void update(SalesOrder salesOrder) {
	Session session = sessionFactory.getCurrentSession();
	session.update(salesOrder);
    }

    public SalesOrder findOne(Long salesOrderId) {
	Session session = sessionFactory.getCurrentSession();
	return (SalesOrder) session.load(SalesOrder.class, salesOrderId);

    }

    @SuppressWarnings("unchecked")
    public List<SalesOrder> findAll() {
	Session session = sessionFactory.getCurrentSession();
	return session.createQuery("from SalesOrder").list();
    }

    public SalesOrder findByOrderNumber(String orderNumber) {
	Session session = sessionFactory.getCurrentSession();
	return (SalesOrder) session.createQuery("from SalesOrder where orderNumber = :orderNumber")
		.setParameter("orderNumber", orderNumber)
		.uniqueResult();
    }

    public void delete(SalesOrder salesOrder) {

	Session session = sessionFactory.getCurrentSession();
	for (OrderLine orderLine : salesOrder.getOrderLines()) {
	    session.delete(orderLine);
	}

	session.delete(salesOrder);
    }
}
