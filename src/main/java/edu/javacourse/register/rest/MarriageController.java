package edu.javacourse.register.rest;

// Класс, которы принимает REST-запросы (REST - сервис)
// имеет доступ к MarriageManager

import edu.javacourse.register.business.MarriageManager;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

// (специализация @Component) обычно используется если класс предостовляет какой-то бизнес-функционал
@Service("controller") // имя - controller, обычно имена нужны при наследовании от общего родителя
public class MarriageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);

    @Autowired // механизм автоматического связывания бинов (не нужны сеттеры)
    @Qualifier("marriageService") // реализация интерфейса (или выбор наследника класса) конкретным бином
    private MarriageManager marriageManager;

//    public void setMarriageManager(MarriageManager marriageManager) {
//        this.marriageManager = marriageManager;
//    }

    // метод для получения запроса
    public MarriageResponse findMarriageCertificate(MarriageRequest request){
        LOGGER.info("findMarriageCertificate called");
        // возвращение запроса
        return marriageManager.findMarriageCertificate(request);
    }
}
