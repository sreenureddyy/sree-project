/**
 * 
 */
package com.sree.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 * @author srinivasr
 *
 */
public class EmployeeSalaries {
	public static void main(String args[]){
		try {
			// load up the knowledge base
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "C:/drools-audit");
			// go !
			Employee employee = new Employee();
			employee.setAge(39);
			employee.setEmpName("Sree");
			employee.setGender("F");
			employee.setSubject("COMP");
			ksession.insert(employee);
			ksession.insert(new EmployeeVo());
			ksession.fireAllRules();
			System.out.println("Emp Salary is :: "+EmployeeVo.getEmpSalary());
			logger.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		DecisionTableConfiguration config = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		config.setInputType(DecisionTableInputType.XLS);
		kbuilder.add(ResourceFactory.newFileResource("C://Employee.xls"), ResourceType.DTABLE, config);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error: errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}
}
