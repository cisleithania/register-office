package edu.javacourse.register.dao;

// класс, для того, чтобы вытащить список персон

import edu.javacourse.register.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class PersonDao {

    // необходимо создать Entity Manager
    private EntityManager entityManager;

    public PersonDao() {
        // создание factory по имени persistence-unit в файле persistence.xml
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence");

        // создание EntityManager, который близок к объекту session у hibernate
        entityManager = factory.createEntityManager();
    }

    // получение списка персон
    public List<Person> findPersons() {
        // будет возвращен список объектов класса Person (используется язык JPAQL/HQL)
        // named query (именованные запросы), экономия времени на компиляцию
        Query query = entityManager.createNamedQuery("Person.findPersons");
        // имя параметра запроса
        query.setParameter("personId", 1L);
        return  query.getResultList();
    }

}
