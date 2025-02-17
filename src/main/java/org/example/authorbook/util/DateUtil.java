package org.example.authorbook.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class DateUtil {

//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("hello");
//    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("bye bye");
    }
}
