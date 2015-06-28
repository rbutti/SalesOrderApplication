package com.dev.backend.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev.backend.model.OrderLine;

@Repository
public class OrderLineDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(OrderLine orderLine) {
	Session session = sessionFactory.getCurrentSession();
	session.saveOrUpdate(orderLine);
    }

    public void update(OrderLine orderLine) {
	Session session = sessionFactory.getCurrentSession();
	session.update(orderLine);
    }

    public void delete(OrderLine orderLine) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(orderLine);
    }

    public OrderLine findOne(Long orderLineId) {
	Session session = sessionFactory.getCurrentSession();
	return (OrderLine) session.load(OrderLine.class, orderLineId);

    }

    @SuppressWarnings("unchecked")
    public List<OrderLine> findAll() {
	Session session = sessionFactory.getCurrentSession();
	return session.createQuery("from OrderLineWrapper").list();
    }

}
