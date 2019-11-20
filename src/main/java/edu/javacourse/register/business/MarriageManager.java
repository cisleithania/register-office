package edu.javacourse.register.business;

// класс, исполняющий бизнес-логику
// Вызывается классом MarriageController, вызывает MarriageDao

import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.dao.PersonDao;
import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.domain.Person;
import edu.javacourse.register.domain.PersonFemale;
import edu.javacourse.register.domain.PersonMale;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service("marriageService") //(специализация @Component) обычно используется если класс предостовляет какой-то бизнес-функционал
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) // область видимости бинов (singleton или prototype)
public class MarriageManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);

    // при запуске MarriageManager, создается MarriageDao и PersonDao (оба в одном экземпляре)
    @Autowired // механизм автоматического связывания бинов
    private MarriageDao marriageDao;
    @Autowired // механизм автоматического связывания бинов
    private PersonDao personDao;


    // с помощью данных, пришедших в request, необходимо проверить, зарегистрирован ли брак
    @Transactional // Определение области действия одной транзакции БД (метод findMarriageCertificate)
    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("findMarriageCertificate called");

        // добавление 3 персон
        personDao.addPerson(getPerson(1));
        personDao.addPerson(getPerson(2));

        // получение свидетельства о браке
        MarriageCertificate mc = getMarriageCertificate();

        // сохранить полученное свидетельство о браке
        marriageDao.saveAndFlush(mc);

        // найти все сертификаты
//        marriageDao.findAll();

        // найти сертификаты по Id
//        marriageDao.findById(1L);

        // поиск по номеру, используя findByNumber
        List<MarriageCertificate> list = marriageDao.findByNumber("12345");
        // показать на экране, с помощью лямбда
        list.forEach(m -> LOGGER.info("MC:{}", m.getMarriageCertificateId()));

        LOGGER.info("----------------");

        // поиск по номеру, используя findByNum
        List<MarriageCertificate> list2 = marriageDao.findByNum("98765");
        list2.forEach(m -> LOGGER.info("MC:{}", m.getMarriageCertificateId()));

        LOGGER.info("----------------");

        // поиск по номеру, используя findSomething
        List<MarriageCertificate> list3 = marriageDao.findSomething("01928");
        list3.forEach(m -> LOGGER.info("MC:{}", m.getMarriageCertificateId()));

        return new MarriageResponse();
    }

    // создание MarriageCertificate
    private MarriageCertificate getMarriageCertificate() {

        MarriageCertificate mc = new MarriageCertificate();
        mc.setIssueDate(LocalDate.now());
        mc.setNumber("98765");
        mc.setActive(true);

        // нужно найти всех персон
        List<Person> persons = personDao.findPersons();

        for(Person person : persons) {
            if (person instanceof PersonMale) {
                mc.setHusband((PersonMale)person);
            } else {
                mc.setWife((PersonFemale)person);
            }
        }

        return mc;
    }

    // создание новой персоны
    private Person getPerson(int sex) {
        Person m = sex == 1 ? new PersonMale() : new PersonFemale();
        m.setFirstName("1_" + sex);
        m.setLastName("2_" + sex);
        m.setPatronymic("3_" + sex);
        m.setDateOfBirth(LocalDate.of(1991, 3, 12));
        return m;
    }

}
