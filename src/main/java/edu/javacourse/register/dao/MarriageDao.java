package edu.javacourse.register.dao;

// класс, который вызывает EntityManager (класс, который работает непостредственно с БД)

import edu.javacourse.register.domain.MarriageCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // класс - хранилие данных (при использовании Spring Data)
// JpaRepository принимает два класса: сущность, с которой MarriageDao будет работать (MarriageCertificate)
// и тип идентификатора, который используется в dao (Long)
public interface MarriageDao extends JpaRepository<MarriageCertificate, Long> {

    // Spring Data сгенерирует выдачу, по правилам именования метода,
    // номер содержит какую-то подстроку
    List<MarriageCertificate> findByNumber(String number);

    // findByNum объявлен как NamedQuery, обращение к запросу в другом месте
    // в аннотации @Param указывается имя параметра из именованного запроса
    List<MarriageCertificate> findByNum(@Param("number") String number);

    // у метода имеется только Query, необходимо написать запрос
    @Query("SELECT mc FROM MarriageCertificate mc WHERE mc.number = :number")
    List<MarriageCertificate> findSomething(@Param("number") String number);

//    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageCertificate.class);
//
//    private EntityManager entityManager;
//    // пример инициализации какого-то поля
//    @Value("${test.value}") // установка значения поля, используя property файл
//    private String test;
//
//    public void setTest(String test) {
//        this.test = test;
//    }
//
//    public MarriageCertificate findMarriageCertificate(MarriageRequest request){
//        LOGGER.info("findMarriageCertificate called:{}", test);
//
//        return null;
//    }
}
