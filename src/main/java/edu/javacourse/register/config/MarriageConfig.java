package edu.javacourse.register.config;

import edu.javacourse.register.dao.PersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // класс - источник определения бинов (аналог конфигурации в xml)
// необходимо прописать property файл, для его загрузки. Обычно в Configuration классе
@PropertySource(value = {"classpath:/register.properties"})
public class MarriageConfig {

    @Bean // метод создает, настраивает и инициализирует новый объект, управляемый Spring IoC контейнером
    public PersonDao buildPersonDao(){

        System.out.println("PersonDao is created");

        return new PersonDao();
    }
}
