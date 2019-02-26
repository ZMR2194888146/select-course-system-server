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

    public boolean existTeacher(Long id){
        return teacherRepository.existsTeacherById(id);
    }


    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    /**
     * 获取所有的教师列表，以JSON格式返回
     * @return JSONObject
     */
    public JSONObject getAllTeacher(){
        List<Teacher> t = teacherRepository.findAll();
        JSONArray ja = new JSONArray();
        ja.add(JSONObject.parseObject(JSON.toJSONString(t)));
        return JSONObject.parseObject(ja.toJSONString());
    }

    public String updatePassword(Long id, String password) {
        if (existTeacher(id)){
            Teacher t = teacherRepository.findTeacherById(id);
            t.setPassword(password);
            teacherRepository.save(t);
            return FormatString.infoToJson("200","update successful", "");
        }
        return FormatString.infoToJson("200","update failed", "");
    }

    //根据id获取教师的身份信息
    public String getTeacherInfo(Long id){
        if (existTeacher(id)){
            Teacher t = teacherRepository.findTeacherById(id);
            return FormatString.userInfoToJson("200","query successful", JSON.toJSONString(t));
        }
        return FormatString.userInfoToJson("200","failed successful", "");
    }



}
