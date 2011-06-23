/*package com.junit;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sree.dao.PersonService;
import com.sree.domain.Person;

public class PersonServiceTest {
    private static PersonService personService;

    @BeforeClass
    // This method is when unit testing PersonServiceTest instance is build out is performed after  
    // You can do it in this way some initialization operations  
    public static void setUpBeforeClass() throws Exception {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
            personService = (PersonService)applicationContext.getBean("personService");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave() {
        personService.save(new Person(" Xiao Zhang  "));
    }

    @Test
    public void testUpdate() {
        Person person = personService.getPerson(1);
        //....
        person.setName(" Hsiao Li  ");
        personService.update(person);
    }

    @Test
    public void testGetPerson() {
        Person person = personService.getPerson(2);
        System.out.println(person.getName());
        try {
            System.out.println(" Please close the database  ");
            Thread.sleep(18*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" The second start getting  ");
        person = personService.getPerson(2);
        System.out.println(person.getName());
    }

    @Test
    public void testDelete() {
        personService.delete(1);
    }

    @Test
    public void testGetPersons() {
        List<Person> persons = personService.getPersons();
        for(Person person : persons){
            System.out.println(person.getName());
        }
    }

}
*/