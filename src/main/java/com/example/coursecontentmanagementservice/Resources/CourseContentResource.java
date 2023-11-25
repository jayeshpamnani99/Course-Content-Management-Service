package com.example.coursecontentmanagementservice.Resources;

import com.example.coursecontentmanagementservice.CustomException;
import com.example.coursecontentmanagementservice.Model.CourseModule;
import com.example.coursecontentmanagementservice.Services.CourseContentService;
import jakarta.websocket.server.PathParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@RestController
public class CourseContentResource
{
    Logger logger = LoggerFactory.getLogger(CourseContentResource.class);

    @Autowired
    private CourseContentService courseContentService;

    @GetMapping("/")
    public String message(){
        logger.info("getting first log");
        return "Hello World!";
    }


    @GetMapping("/getCourseModuleDetails")
    public ResponseEntity<List> getCourseModuleDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathParam("courseId") int courseId) throws Exception {
        // code that uses the language variable
        List<CourseModule> allCourseModules = courseContentService.getCourseModuleDetails(authToken, courseId);

        return new ResponseEntity<List>(allCourseModules, HttpStatus.OK);
    }

    @GetMapping("/getModuleDetailsFull")
    public ResponseEntity<CourseModule> getModuleDetailsFull(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken, @PathParam("courseModuleId") int courseModuleId) throws Exception {
        // code that uses the language variable
        CourseModule courseModule = courseContentService.getModuleDetailsFull(authToken, courseModuleId);

        return new ResponseEntity<CourseModule>(courseModule, HttpStatus.OK);
    }

    @GetMapping("/getModuleDetailsById")
    public ResponseEntity<CourseModule> getModuleDetailsById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken,
                                                             @PathParam("courseModuleId") int courseModuleId,
                                                             @PathParam("courseId") int courseId) throws Exception {
        // code that uses the language variable
        CourseModule courseModule = courseContentService.getModuleDetailsById(authToken, courseModuleId,courseId);

        return new ResponseEntity<CourseModule>(courseModule, HttpStatus.OK);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleException(CustomException exception) {
        logger.info("inside exception handler: {}",exception);
        JSONObject obj = new JSONObject();
        obj.put("status",exception.getStatus());
        obj.put("message",exception.getE().getMessage());
        return ResponseEntity
                .status(exception.getStatus())
                .body(obj.toMap());
    }
}
