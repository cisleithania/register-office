package edu.javacourse.register.dao;

// класс, для того, чтобы вытащить список персон

import edu.javacourse.register.domain.Person;

import javax.persistence.*;
import java.util.List;

public class PersonDao {

    // необходимо создать Entity Manager
    @PersistenceContext // автоматическое связывание менеджера сущностей с бином (прописано в springContext.xml)
    private EntityManager entityManager;

    // получение списка персон
    public List<Person> findPersons() {
        // будет возвращен список объектов класса Person (используется язык JPAQL/HQL)
        // named query (именованные запросы), экономия времени на компиляцию
        Query query = entityManager.createNamedQuery("Person.findPersons");
        return  query.getResultList();
    }

    // добавление персоны в БД
    public Long addPerson(Person person){

//        // получение транзакции
//        EntityTransaction tr = entityManager.getTransaction();
//        // начало транзакции
//        tr.begin();
//        try{

            // сохраняем персону в БД
            entityManager.persist(person);
            // запись сохраненных данных непосредственно в БД
            entityManager.flush();

//            // завершение транзакции
//            tr.commit();
//        } catch (Exception ex) {
//            // отмена транзакции
//            tr.rollback();
//            throw new RuntimeException(ex);
//        }

        // возвращается Id персоны
        return person.getPersonId();
    }

}
