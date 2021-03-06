package com.rudra.aks.test;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.rudra.aks.AppConfiguration;
import com.rudra.aks.bo.TestBO;

public class HibernateSearchTest {

	
	private static final Logger logger = Logger.getLogger(HibernateSearchTest.class);
	
	@Transactional //( propagation = Propagation.REQUIRED, readOnly = false)
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class);
	    SessionFactory	sessionFactory = ctx.getBean(SessionFactory.class);   
	    Session session = sessionFactory.openSession();
	     
	     
	    try {
	    	FullTextSession	fullTextSession = Search.getFullTextSession(session);
			fullTextSession.createIndexer().startAndWait();
			 
			// alternatively you can write the Lucene query using the Lucene query parser
			// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
			QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(TestBO.class).get();
			
			//partire  -- italian conjuction -- partite
			org.apache.lucene.search.Query query = qb
					.keyword()
					.onFields("name")
					.matching("This tokenizer creates  from strings of letters")
					.createQuery();
			// wrap Lucene query in a org.hibernate.Query
			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, TestBO.class);
			// execute search
			@SuppressWarnings("unchecked")
			List<TestBO> result = hibQuery.list();
			logger.info("----No of matching result : " + result.size());
			Iterator<TestBO> it = result.iterator();
			logger.info("==============================List of matched search resultl: ============================");
			while (it.hasNext()) {
				TestBO testbo1 = it.next();
				System.out.println(testbo1);
			}
		} catch (Exception e) {
			logger.info("Exception occurred!");
			e.printStackTrace();
		}
	    
	    
	    /*
	    TestBO sbo = (TestBO) session.load(TestBO.class, 6);
	    
	    Address fadd = new Address();
	    fadd.setAddress("banjara hills");
	    fadd.setCountry("india");
	    TestBO testBO = new TestBO();
	    testBO.setName("aksini");
	    testBO.setAge(24);
	    testBO.setDepartment("sales");
	    testBO.setEmailid("aks@rudra.com");
	    testBO.setSalary(2345);
	    testBO.setAddress("hyd");
	    //testBO.setFullAddress(fadd);
	    fadd.setTestbo(testBO);
	    //testBO.setFullAddress(fadd);
	    Transaction tx = session.beginTransaction();
	    int id = (int) session.save(testBO);
	    logger.info("saved.. id: " + id);
	    tx.commit();
	    logger.info(sbo);*/
	    
	    session.close();
	     
	     
	    ctx.close();
	}

}
