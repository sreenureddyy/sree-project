package com.sree.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.sree.service.IBaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class JPATestCustomer {

	@Autowired
	IBaseService baseService;

	@Before
	public void setUpTestDataWithinTransaction() {
		baseService.savePerson();
		baseService.savePerson();
	}

	@Test
	@Rollback(true)
	public void countTest() {
		
	}

	@Test
	@Rollback(true)
	public void findAllTest() {
	}

	public IBaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(IBaseService baseService) {
		this.baseService = baseService;
	}

}
