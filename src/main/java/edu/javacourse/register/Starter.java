package edu.javacourse.register;

import edu.javacourse.register.rest.MarriageController;
import edu.javacourse.register.view.MarriageRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {
    public static void main(String[] args) {
        // создание контекста
        // интерфейс верхнего уровня ApplicationContext
        // принимает на вход список файлов с описанием контекста
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"springContext.xml"}
        );

//        // получение бина, с указанием конкретного класса
//        MarriageController controller = context.getBean(MarriageController.class);
////        MarriageController controller = context.getBean("controller", MarriageController.class);
//        // передача реквеста (пока пустой)
//        controller.findMarriageCertificate(new MarriageRequest());
    }
}
