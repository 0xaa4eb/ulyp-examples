package org.example.hibernate;

import org.example.hibernate.util.Configuration;
import org.example.hibernate.util.Person;
import org.example.hibernate.util.PersonStoreService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        PersonStoreService store = context.getBean(PersonStoreService.class);

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAge(42);

        store.save(person);
        System.out.println("Stored: " + person);

        Person foundPerson = store.find(person.getId());
        System.out.println("Found: " + foundPerson);
    }
}
