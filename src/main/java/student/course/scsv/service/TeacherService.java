package student.course.scsv.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import student.course.scsv.entity.Teacher;
import student.course.scsv.repository.TeacherRepository;
import student.course.scsv.util.FormatString;

import java.util.List;

@Service
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
            array.add(JSONObject.parseObject(JSON.toJSONString(teacher)));
        }
        return array;
    }

    /**
     * 更新教师密码
     * @param id        需要更新密码的教师的id
     * @param password  新的密码
     * @return JSON
     */
     public String updatePassword(Long id, String password) {
        if (existTeacherById(id)){
            Teacher t = teacherRepository.findTeacherById(id);
            t.setPassword(password);
            teacherRepository.save(t);
            return FormatString.infoToJson("200","update successful");
        }
        return FormatString.infoToJson("200","update failed");
    }

    /**
     * 根据获取教师信息
     * @param id    需要获取信息的教师的id
     * @return  JSON
     */
     public String getTeacherInfoById(Long id){
        if (existTeacherById(id)){
            Teacher t = teacherRepository.findTeacherById(id);
            return FormatString.infoToJson("200","query successful", t);
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

}
