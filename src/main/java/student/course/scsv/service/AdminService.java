package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Administrator;
import student.course.scsv.repository.AdministratorRepository;
import student.course.scsv.util.FormatString;

@Service
public class AdminService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private MenuService menuService;

    public String getAdminUserInfo(Long id){
        Administrator a;
        if (administratorRepository.exists(id)){
            JSONObject json = menuService.getMenuList("admin");
            a = administratorRepository.findAdministratorById(id);
            JSONObject admin = JSONObject.parseObject(JSON.toJSONString(a));
            admin.remove("password");
            return FormatString.infoToJson("200","query successfule", admin);
        }
        return FormatString.infoToJson("200", "query failed");
    }
}
