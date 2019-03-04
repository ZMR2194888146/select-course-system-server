package student.course.scsv;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Notice implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("#########################################################");
        System.out.println("#                                                       #");
        System.out.println("#   程序启动后，会创建一个默认管理员admin（密码：88888888）  #");
        System.out.println("#                                                       #");
        System.out.println("#########################################################");
    }
}
