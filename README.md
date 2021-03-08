# spring5_test3
# Spring5框架—Bean管理（基于注解方式）
## IOC操作Bean管理（基于注解方式）
   ### 1、什么是注解
        （1）注解是代码中的特殊标记，格式：@注解名称(属性名称=属性值,属性名称=属性值,...)
        （2）使用注解，注解作用在类，方法，属性上面
        （3）使用注解的目的：简化xml配置
   ### 2、Spring针对Bean管理中创建对象提供注解
            （1）@Component（普通组件）
            （2）@Service（用于业务逻辑层）
            （3）@Controller（用于Web层）
            （4）@Repository（用于dao层）
            *上面的四个注解功能一样，都可以来创建bean实例
   ### 3、基于注解方式实现对象的创建
        第一步 引入依赖 spring-aop-5.2.6.RELEASE.jar
        第二步 开启组件扫描
            <!--开启组件扫描
                1.如果扫描多个包，多个包之间用逗号隔开
                2.扫描包的上层目录
            -->
            <context:component-scan base-package="zh.spring5"></context:component-scan>
        第三步 创建类，在类上面添加创建对象的注解
   ### 4、开启组件扫描细节配置
        <!--示例1
            use-default-filters="false" 表示不使用默认filter，自己设置filter
            标签context:include-filter，设置扫描哪些内容（白名单）
        -->
        <context:component-scan base-package="zh.spring5" use-default-filters="false">
            <context:include-filter type="annotation" expression="org.springframework.stereotype.Component"/>
        </context:component-scan>

        <!--示例2
            下面配置扫描包中所有内容
            context:exclude-filter，设置哪些内容不扫描（黑名单）
        -->
        <context:component-scan base-package="zh.spring5">
            <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        </context:component-scan>
   ### 5、基于注解方式实现属性的注入
        （1）@AutoWired：根据属性类型进行自动装配
            第一步 把service和dao对象创建，在service和dao类添加创建对象注解
            第二步 在service注入dao对象，在service类中添加dao类型属性，在属性上面使用注解
            @Service
            public class UserService {
                /*
                * 定义到类型属性
                * 不需要添加set方法
                * 添加注入属性注解
                * */
                @Autowired
                private UserDao userDao;
                public void add(){
                    System.out.println("service add.........");
                }
            }
        （2）@Qualifier：根据属性名称进行注入
            这个@Qualifier注解的使用，需要和上面的@AutoWired一起使用
                @Autowired
                @Qualifier(value = "userDaoImpl")
                private UserDao userDao;
        （3）@Resource：可以根据类型注入，也可以根据名称注入（不是spring中的注解，是javax中的）
                    @Resource //根据类型注入
                    @Resource(name = "userDaoImpl") //根据名称注入
                    private UserDao userDao;
        （4）@Value：注入普通类型属性
                @Value(value = "abc")
                private String name;
   ### 6、完全注解开发
        （1）创建配置类，替代xml配置文件
            @Configuration  //作为配置类，替代xml配置文件
            @ComponentScan(basePackages = {"zh.spring5"})
            public class SpringConfig {
            }
       （2）编写测试类
        @Test
            public void testService2(){
                ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
                UserService userService=context.getBean("userService", UserService.class);
                System.out.println(userService);
                userService.add();
            }
