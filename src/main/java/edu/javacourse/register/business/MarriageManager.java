package edu.javacourse.register.business;

// класс, исполняющий бизнес-логику
// к нему приходят запросы

import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;

public class MarriageManager {

    // при запуске MarriageManager, создается MarriageDao (оба в одном экземпляре)
    private MarriageDao dao;

    // с помощью данных, пришедших в request, необходимо проверить, зарегистрирован ли брак
    public MarriageResponse findMarriageSertificate(MarriageRequest request) {
        throw new UnsupportedOperationException("Unsupported");
    }

}
