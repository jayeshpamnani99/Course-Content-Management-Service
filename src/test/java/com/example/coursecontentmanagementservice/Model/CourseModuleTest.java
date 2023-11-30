package com.example.coursecontentmanagementservice.Model;

import com.example.coursecontentmanagementservice.Model.CourseModule;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CourseModuleTest {

    @Test
    public void testCourseModuleCreation() {
        // Create a new CourseModule object
        CourseModule courseModule = new CourseModule();
        courseModule.setId(1);
        courseModule.setCourseId(101);
        courseModule.setSerialNumber(1);
        courseModule.setTitle("Module 1");
        courseModule.setDescription("Module description");
        courseModule.setContentUrl("https://example.com/module1");
        courseModule.setModuleTypeId(201);

        // Check if the CourseModule object is not null
        assertNotNull(courseModule);

        // Validate properties of the CourseModule object
        assertEquals(1, courseModule.getId());
        assertEquals(101, courseModule.getCourseId());
        assertEquals(1, courseModule.getSerialNumber());
        assertEquals("Module 1", courseModule.getTitle());
        assertEquals("Module description", courseModule.getDescription());
        assertEquals("https://example.com/module1", courseModule.getContentUrl());
        assertEquals(201, courseModule.getModuleTypeId());
    }

    @Test
    public void testSetQuizSubmissionDetails() {
        // Create a CourseModule object and set quiz submission details using JSONObject
        CourseModule courseModule = new CourseModule();
        JSONObject quizSubmissionDetails = new JSONObject();
        quizSubmissionDetails.put("questionCount", 10);
        quizSubmissionDetails.put("timeLimit", 60);
        courseModule.setQuizSubmissionDetails(quizSubmissionDetails);

        // Validate the conversion of JSONObject to Map in quizSubmissionDetails field
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("questionCount", 10);
        expectedMap.put("timeLimit", 60);

        assertEquals(expectedMap, courseModule.getQuizSubmissionDetails());
    }

    @Test
    public void testToString() {
        // Create a CourseModule object and check its toString() method
        CourseModule courseModule = new CourseModule();
        courseModule.setId(2);
        courseModule.setCourseId(102);
        courseModule.setSerialNumber(2);
        courseModule.setTitle("Module 2");
        courseModule.setDescription("Another module description");
        courseModule.setContentUrl("https://example.com/module2");
        courseModule.setModuleTypeId(202);

        String expectedToString = "CourseModule{id=2, courseId=102, serialNumber=2, title='Module 2', " +
                "description='Another module description', contentUrl='https://example.com/module2', " +
                "moduleTypeId=202, moduleType=null, isQuizSubmissionPossible=true, quizSubmissionDetails=null}";
        assertEquals(expectedToString, courseModule.toString());
    }
}

