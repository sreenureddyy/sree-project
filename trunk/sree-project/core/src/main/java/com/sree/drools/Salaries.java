package com.sree.drools;

import java.io.FileInputStream;
import java.io.StringReader;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.WorkingMemory;
import org.drools.compiler.PackageBuilder;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.rule.Package;



public class Salaries {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
        try {

        //load up the rulebase
            RuleBase ruleBase = readDecisionTable();
            WorkingMemory workingMemory =  ruleBase.newStatefulSession();

            //WorkingMemoryFileLogger logger = new WorkingMemoryFileLogger(workingMemory);
            //logger.setFileName("C:/drools-audit");

        	EmployeeVo empvo = new EmployeeVo();
			Employee employee = new Employee();
			employee.setAge(35);
			employee.setEmpName("Sree");
			employee.setGender("F");
			employee.setSubject("COMP");
            workingMemory.insert(employee);
            workingMemory.insert(empvo);
            //workingMemory.setGlobal("empvo", empvo);

            workingMemory.fireAllRules();
            //logger.writeToDisk();

            System.out.println("Salary :: "+empvo.getEmpSalary());

        } catch (Throwable t) {
            t.printStackTrace();
        }
	}

	private static RuleBase readDecisionTable() throws Exception {
		//read in the source
        final SpreadsheetCompiler converter = new SpreadsheetCompiler();
        FileInputStream is = new FileInputStream("C:\\Employee.xls");
        final String drl = converter.compile( is, InputType.XLS ); 
        //final String drl = converter.compile( "C:\\Employee.xls", InputType.XLS );
        System.out.println(drl);
		PackageBuilder builder = new PackageBuilder();
		builder.addPackageFromDrl( new StringReader( drl ) );
		Package pkg = builder.getPackage();
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage( pkg );
		return ruleBase;
	}

}
