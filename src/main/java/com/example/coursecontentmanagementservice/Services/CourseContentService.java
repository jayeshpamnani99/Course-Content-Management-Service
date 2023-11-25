package com.example.coursecontentmanagementservice.Services;

import com.example.coursecontentmanagementservice.CustomException;
import com.example.coursecontentmanagementservice.Dao.CourseModuleDao;
import com.example.coursecontentmanagementservice.Dao.ModuleTypeDao;
import com.example.coursecontentmanagementservice.Model.Constants;
import com.example.coursecontentmanagementservice.Model.CourseModule;
import com.example.coursecontentmanagementservice.Model.ModuleType;
import jakarta.persistence.EntityManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CourseContentService{

    Logger logger = LoggerFactory.getLogger(CourseContentService.class);

    @Autowired
    private CourseModuleDao courseModuleDao;

    @Autowired
    private ModuleTypeDao moduleTypeDao;

    @Autowired
    private EntityManager entityManager;

    public List<CourseModule> getCourseModuleDetails(String authToken, int courseId) throws Exception {
        if (courseId == 0) {
            throw new CustomException(new Exception("Course Id cannot be 0"), HttpStatus.BAD_REQUEST);
        }

        JSONObject userObj = getUserDetailsFromToken(authToken);
        if (null == userObj || userObj.isEmpty()){
            throw new CustomException(new Exception("User not Authorised"), HttpStatus.UNAUTHORIZED);
        }
        logger.info("user object : {}",userObj);

        List<CourseModule> allCourseModules = courseModuleDao.findByCourseId(courseId);

        allCourseModules.forEach(courseModule -> {
            ModuleType moduleType = entityManager.
                    find(ModuleType.class, courseModule.getModuleTypeId());
            courseModule.setModuleType(moduleType);
            courseModule.setContentUrl(null);
        });

        return allCourseModules;
    }

    public CourseModule getModuleDetailsFull(String authToken, int courseModuleId) throws Exception {
        if (courseModuleId == 0) {
            throw new CustomException(new Exception("courseModuleId cannot be 0"), HttpStatus.BAD_REQUEST);
        }

        JSONObject userObj = getUserDetailsFromToken(authToken);
        if (null == userObj || userObj.isEmpty()){
            throw new CustomException(new Exception("User not Authorised"), HttpStatus.UNAUTHORIZED);
        }
        logger.info("user object : {}",userObj);

        CourseModule courseModule = entityManager.find(CourseModule.class, courseModuleId);
        ModuleType moduleType = entityManager.
                find(ModuleType.class, courseModule.getModuleTypeId());
        courseModule.setModuleType(moduleType);

        return courseModule;
    }

    public JSONObject getUserDetailsFromToken(String token) {
        final String uri = "http://" + Constants.AWS_IP + ":8081/auth";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);


        try {
            ResponseEntity<String> res = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            String resBody = res.getBody();
            JSONObject jsonObject = new JSONObject(resBody);
            return jsonObject;
        } catch (Exception e) {
            logger.error("Error in API for auth!");
            return null;
        }
    }

}
