package zh.spring5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import zh.spring5.dao.UserDao;

import javax.annotation.Resource;

/*
* 在注解里面的value属性值可以不写
* 默认值是类名，首字母小写
* UserService —— userService
* */
//@Component(value = "userService")
@Service
public class UserService {
    /*
    * 定义到类型属性
    * 不需要添加set方法
    * 添加注入属性注解
    * */
//    @Autowired
//    @Qualifier(value = "userDaoImpl")
//    private UserDao userDao;

//    @Resource //根据类型注入
    @Resource(name = "userDaoImpl") //根据名称注入
    private UserDao userDao;

    @Value(value = "abc")
    private String name;

    public void add(){
        System.out.println("service add.........");
        userDao.add();
    }

    @Override
    public String toString() {
        return "UserService{" +
                "name='" + name + '\'' +
                '}';
    }
}
