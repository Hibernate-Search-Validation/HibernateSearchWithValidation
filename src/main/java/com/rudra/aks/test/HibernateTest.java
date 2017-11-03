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

public class HibernateTest {

	
	private static final Logger logger = Logger.getLogger(HibernateSearchTest.class);
	
	@Transactional //( propagation = Propagation.REQUIRED, readOnly = false)
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfiguration.class, TestBO.class);
		
	    SessionFactory	sessionFactory = ctx.getBean(SessionFactory.class);   
	    Session session = sessionFactory.openSession();
	     
	     
	    try {
	    	FullTextSession	fullTextSession = Search.getFullTextSession(session);
			fullTextSession.createIndexer().startAndWait();
			 
			//Transaction tx = fullTextSession.beginTransaction();
			QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity( TestBO.class ).get();
			org.apache.lucene.search.Query query = qb.keyword().onField("name").matching("ankush").createQuery();
			// wrap Lucene query in a org.hibernate.Query
			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(query, TestBO.class);
			// execute search
			List<TestBO> result = hibQuery.list();
			Iterator<TestBO> it = result.iterator();
			logger.info("List of matched search resultl: ");
			while (it.hasNext()) {
				TestBO testbo1 = it.next();
				System.out.println(testbo1);
			}
		} catch (Exception e) {
			logger.info("Exception occurred!");
			e.printStackTrace();
		}
	    

	    TestBO sbo = (TestBO) session.load(TestBO.class, 6);
	    logger.info(sbo);
	    session.close();
	     
	     
	    ctx.close();
	}

}
