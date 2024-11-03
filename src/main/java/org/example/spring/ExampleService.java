package org.example.spring;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ExampleService {

    public void test() {
        System.out.println("hello");
    }
}
