package edu.javacourse.register.rest;

import edu.javacourse.register.business.MarriageManager;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Класс, которы принимает REST-запросы (REST - сервис)
// имеет доступ к MarriageManager

// ресурс-класс
// в аннотации Path указываем URL, на который класс будет реагировать

// (специализация @Component) обычно используется если класс предостовляет какой-то бизнес-функционал
@Service("controller") // имя - controller, обычно имена нужны при наследовании от общего родителя
@Path("/mc") // переход REST-сервиса MarriageController под управление jersey
public class MarriageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageController.class);

    @Autowired // механизм автоматического связывания бинов (не нужны сеттеры)
    @Qualifier("marriageService") // реализация интерфейса (или выбор наследника класса) конкретным бином
    private MarriageManager marriageManager;

//    public void setMarriageManager(MarriageManager marriageManager) {
//        this.marriageManager = marriageManager;
//    }

    // метод для получения запроса
    @GET
    // указываем, какой вариант преобразования объекта в строку (в данном случае используется json)
    @Produces(MediaType.APPLICATION_JSON) // производит контент(json)
    public MarriageResponse findMarriageCertificate(){
        LOGGER.info("findMarriageCertificate called");
        // возвращение запроса
        return marriageManager.findMarriageCertificate(null);
    }
}
