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

    /**
     * 通过管理ID查询管理员的信息
     * @param id    管理员id
     * @return  JSON
     */
    public String getAdminUserInfo(Long id){
        Administrator a;
        if (administratorRepository.exists(id)){
            JSONObject json = menuService.getMenuList("admin");
            a = administratorRepository.findAdministratorById(id);
            JSONObject admin = JSONObject.parseObject(JSON.toJSONString(a));
            admin.remove("password");
            json.put("userinfo", admin);
            return FormatString.infoToJson("200","query successful", json);
        }
        return FormatString.infoToJson("200", "query failed");
    }

    /**
     * 添加一位管理员
     * @param administrator 包含管理员信息的实例
     * @return JSON
     */
    public String addAdministrator(Administrator administrator){
        if (administratorRepository.save(administrator) != null){
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("400", "have exception appear");
    }

    /**
     * 更新管理员密码
     * @param id    管理员id
     * @param nPass 新密码
     * @param oPass 旧密码
     * @return  JOSN
     */
    public String updatePassword(Long id, String nPass, String oPass){
        if (administratorRepository.exists(id)){
            Administrator a = administratorRepository.findAdministratorById(id);
            if (oPass.equals(a.getPassword())){
                a.setPassword(nPass);
                administratorRepository.save(a);
                return FormatString.infoToJson("200","update successful");
            }
        }
        return FormatString.infoToJson("400","enter info have error !");
    }

}
