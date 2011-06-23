package com.sree.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.richfaces.component.html.HtmlDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sree.domain.User;
import com.sree.drools.Employee;
import com.sree.drools.EmployeeVo;
import com.sree.drools.IRuleEngine;
import com.sree.service.IBaseService;

@SuppressWarnings( { "serial", "unused" })
@Component("home")
@Scope(value = "request")
public class Home extends BaseBean {
	private static Logger log = Logger.getLogger(Home.class);

	@Autowired
	private IRuleEngine ruleEngine;

	private int amount;

	public int getAmount() {

		KnowledgeBase kbase = ruleEngine.getRuleKnowledgeBaseMap().get(
				"Employee.xls");
		StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory
				.newFileLogger(ksession, "C:/drools-audit");
		// go !
		Employee employee = new Employee();
		employee.setAge(39);
		employee.setEmpName("Sree");
		employee.setGender("F");
		employee.setSubject("COMP");
		ksession.insert(employee);
		ksession.insert(new EmployeeVo());
		ksession.fireAllRules();
		System.out.println("Emp Salary is :: " + EmployeeVo.getEmpSalary());
		logger.close();
		amount = EmployeeVo.getEmpSalary();
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public IRuleEngine getRuleEngine() {
		return ruleEngine;
	}

	public void setRuleEngine(IRuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}
}
