package zh.spring5.testdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zh.spring5.config.SpringConfig;
import zh.spring5.service.UserService;

public class TestSpring5_Demo1 {
 /*   @Test
    public void testService1(){
        ApplicationContext context=new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService=context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }*/
    @Test
    public void testService2(){
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService=context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

}
