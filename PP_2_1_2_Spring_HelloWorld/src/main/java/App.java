import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());

        HelloWorld bean1 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());

        Cat bean2 =
                (Cat) applicationContext.getBean("cat");
        Cat bean3 =
                (Cat) applicationContext.getBean("cat");
        System.out.println("Ссылки бинов равны?" + (bean == bean1));
        System.out.println("Ссылки бинов равны?" + (bean2 == bean3));

    }
}