package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.TeacherRepository;
import student.course.scsv.util.FormatString;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional      //添加事务管理
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private MenuService menuService;

    private boolean existTeacherById(Long id){
        return teacherRepository.existsTeacherById(id);
    }

    private boolean existTeacherByUserName(String userName){
        return teacherRepository.existsTeacherByUsername(userName);
    }


    /**
     * 获取所有的教师列表，以JSON格式返回
     * @return JSONObject
     */
    public JSONArray getAllTeacher(){
        List<Teacher> teachers = teacherRepository.findAll();
        JSONArray array = new JSONArray();
        for (Teacher teacher:   teachers ) {
            JSONObject json = JSONObject.parseObject(JSON.toJSONString(teacher));
            json.remove("password");
            array.add(json);
        }
        return array;
    }

    /**
     * 更新教师密码
     * @param id        需要更新密码的教师的id
     * @param nPass     新的密码
     * @param oPass     旧密码
     * @return JSON
     */
     public String updatePassword(Long id, String nPass, String oPass) {
         if (existTeacherById(id)){
             Teacher t = teacherRepository.findTeacherById(id);
             if (oPass.equals(t.getPassword())){
                 t.setPassword(nPass);
                 teacherRepository.save(t);
                 return FormatString.infoToJson("200","update successful");
             }
         }
         return FormatString.infoToJson("400","enter info have error !");
    }

    /**
     * 根据教师id获取教师信息
     * @param id    需要获取信息的教师的id
     * @return  JSON
     */
     public String getTeacherInfoById(Long id){
        if (existTeacherById(id)){
            Teacher t = teacherRepository.findTeacherById(id);
            JSONObject json = menuService.getMenuList("teacher");
            JSONObject teacher = JSONObject.parseObject(JSON.toJSONString(t));
            teacher.remove("password");
            Object userinfo = json.put("userinfo", teacher);
            return FormatString.infoToJson("200","query successful", json);
        }
        return FormatString.infoToJson("200","query failed");
    }

    /**
     * 添加一个教师用户
     * @param teacher   需要添加的教师实体
     * @return JSON
     */
    public String addTeacher(Teacher teacher){
         if (teacherRepository.save(teacher) != null){
             return FormatString.infoToJson("200", "update success");
         }
         return FormatString.infoToJson("400", "update failed");
    }

    /**
     * 通过教师id删除一位教师
     * @param id
     * @return
     */
    public String deleteTeacher(Long id){
        if (teacherRepository.existsTeacherById(id)){
            teacherRepository.deleteTeacherById(id);
            return FormatString.infoToJson("200", "update success");
        }
        return FormatString.infoToJson("400", "the man was not found");
    }

}
