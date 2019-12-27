package com.vickyproject.onlinemusicstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vickyproject.onlinemusicstore.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao 
{
	// Define a field for EntityManager
	private  EntityManager entityManager;
	
	// Constructor Injection
	ProductDaoImpl(EntityManager theEntityManager)
	{
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Product> findAll() {
		
		// Get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// Create a query
		Query<Product> theQuery = currentSession.createQuery("from Product", Product.class);
		
		// execute query and get the result list
		List<Product> products = theQuery.getResultList();
		
		return products;
	}

	@Override
	public Product findById(int theId) 
	{
		Session currentSession = entityManager.unwrap(Session.class);
		
		Product product = currentSession.get(Product.class,  theId);
		
		return product;
	}

	@Override
	public void save(Product theProduct) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee
		currentSession.saveOrUpdate(theProduct);
	}

	@Override
	public void deleteById(int theId) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete the object with primary key
		currentSession.createQuery("delete from Product where productId=:theId").setParameter("theId", theId).executeUpdate();
		
	}

}
