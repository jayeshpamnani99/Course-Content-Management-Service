package com.example.coursecontentmanagementservice.Resources;

import com.example.coursecontentmanagementservice.CustomException;
import com.example.coursecontentmanagementservice.Model.CourseModule;
import com.example.coursecontentmanagementservice.Resources.CourseContentResource;
import com.example.coursecontentmanagementservice.Services.CourseContentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourseContentResourceTest {

    @Mock
    private CourseContentService courseContentService;

    @InjectMocks
    private CourseContentResource courseContentResource;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCourseModuleDetails() throws Exception {
        // Mocked data
        String authToken = "Bearer Token";
        int courseId = 1;
        List<CourseModule> courseModules = new ArrayList<>();
        CourseModule courseModule = new CourseModule();
        courseModule.setId(1);
        courseModule.setTitle("Module 1");
        courseModules.add(courseModule);

        // Mocking service method
        when(courseContentService.getCourseModuleDetails(anyString(), anyInt())).thenReturn(courseModules);

        // Calling controller method
        ResponseEntity<List> responseEntity = courseContentResource.getCourseModuleDetails(authToken, courseId);

        // Validating response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals("Module 1", ((CourseModule) responseEntity.getBody().get(0)).getTitle());
    }

    // Add similar tests for other controller methods (getModuleDetailsFull, getModuleDetailsById)

    @Test
    public void testHandleException() {
        // Mocked CustomException
        CustomException customException = new CustomException(new Exception("Sample error"), HttpStatus.BAD_REQUEST);

        // Calling exception handler method
        ResponseEntity<Object> responseEntity = courseContentResource.handleException(customException);

        // Validating response
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(HttpStatus.BAD_REQUEST, ((List) responseEntity.getBody()).get("status"));
//        assertEquals("Sample error", ((List) responseEntity.getBody()).get("message"));
    }
}
