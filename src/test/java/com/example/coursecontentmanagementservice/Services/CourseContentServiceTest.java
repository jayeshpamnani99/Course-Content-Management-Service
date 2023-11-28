package com.example.coursecontentmanagementservice.Services;

import com.example.coursecontentmanagementservice.CustomException;
import com.example.coursecontentmanagementservice.Dao.CourseModuleDao;
import com.example.coursecontentmanagementservice.Dao.ModuleTypeDao;
import com.example.coursecontentmanagementservice.Model.CourseModule;
import com.example.coursecontentmanagementservice.Model.ModuleType;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CourseContentServiceTest {

    @Mock
    private CourseModuleDao courseModuleDao;

    @Mock
    private ModuleTypeDao moduleTypeDao;

    @Mock
    private EntityManager entityManager;

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CourseContentService courseContentService;

//    @Test
//    public void testGetModuleDetailsFull_success() throws Exception {
//        // Mock response from getUserDetailsFromToken
//        JSONObject userObj = new JSONObject();
//        userObj.put("id", 1);
//        when(courseContentService.getUserDetailsFromToken("authToken")).thenReturn(userObj);
//
//        // Mock response from checkIfQuizSubmissionIsPossible
//        JSONObject quizSubmissionResponse = new JSONObject();
//        quizSubmissionResponse.put("isQuizSubmissionPossible", true);
//        when(courseContentService.checkIfQuizSubmissionIsPossible(1, 1, 1)).thenReturn(quizSubmissionResponse);
//
//        // Call the method under test
//        CourseModule actualCourseModule = courseContentService.getModuleDetailsFull("authToken", 1);
//
//        // Verify the results
//        assertNotNull(actualCourseModule);
//        //assertTrue(actualCourseModule.isQuizSubmissionPossible());
//    }

    @Test
    public void testGetCourseModuleDetails_invalidCourseId() {
        int courseId = 0;
        String authToken = "dummyToken";

        assertThrows(CustomException.class, () -> courseContentService.getCourseModuleDetails(authToken, courseId));
    }


//    @Test
//    public void testGetCourseModuleDetails() throws Exception {
//
//        // Stub APIs
//        JSONObject user = new JSONObject();
//        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class)))
//                .thenReturn(new ResponseEntity(user.toString(), HttpStatus.OK));
//
//        List<CourseModule> modules = Arrays.asList(new CourseModule());
//        when(courseModuleDao.findByCourseId(anyInt())).thenReturn(modules);
//
//        ModuleType type = new ModuleType();
//        when(entityManager.find(eq(ModuleType.class), anyInt())).thenReturn(type);
//
//        // Call service method
//        List<CourseModule> result = courseContentService.getCourseModuleDetails("token", 1);
//
//        // Verify interactions & result
//        verify(courseModuleDao).findByCourseId(1);
//        verify(entityManager).find(ModuleType.class, modules.get(0).getModuleTypeId());
//        assertSame(modules, result);
//    }

    @Test
    public void testGetCourseModuleDetails_invalidUser() {
        int courseId = 1;
        String authToken = "dummyToken";

        Mockito.when(courseContentService.getUserDetailsFromToken(authToken)).thenReturn(null);


        assertThrows(CustomException.class, () -> courseContentService.getCourseModuleDetails(authToken, courseId));
    }

//    @Test
//    public void testGetCourseModuleDetails_validRequest() throws Exception {
//        int courseId = 1;
//        String authToken = "dummyToken";
//
//        List<CourseModule> expectedCourseModules = new ArrayList<>();
//        expectedCourseModules.add(new CourseModule());
//
//        Mockito.when(courseModuleDao.findByCourseId(courseId)).thenReturn(expectedCourseModules);
//        Mockito.when(courseContentService.getUserDetailsFromToken(authToken)).thenReturn(new JSONObject());
//
//        List<CourseModule> actualCourseModules = courseContentService.getCourseModuleDetails(authToken, courseId);
//
//        assertEquals(expectedCourseModules, actualCourseModules);
//    }

    @Test
    public void testGetModuleDetailsFull_invalidModuleId() {
        int courseModuleId = 0;
        String authToken = "dummyToken";

        assertThrows(CustomException.class, () -> courseContentService.getModuleDetailsFull(authToken, courseModuleId));
    }

//    @Test
//    public void testGetModuleDetailsFull_invalidUser() {
//        int courseModuleId = 1;
//        String authToken = "dummyToken";
//
//        Mockito.when(entityManager.find(CourseModule.class, courseModuleId)).thenReturn(null);
//        Mockito.when(courseContentService.getUserDetailsFromToken(authToken)).thenReturn(null);
//
//        assertThrows(CustomException.class, () -> courseContentService.getModuleDetailsFull(authToken, courseModuleId));
//    }

//    @Test
//    public void testGetModuleDetailsFull_validRequest() throws Exception {
//        int courseModuleId = 1;
//        int courseId = 1;
//        String authToken = "dummyToken";
//
//        CourseModule courseModule = new CourseModule();
//        ModuleType moduleType = new ModuleType();
//
//        Mockito.when(entityManager.find(CourseModule.class, courseModuleId)).thenReturn(courseModule);
//        Mockito.when(entityManager.find(ModuleType.class, courseModule.getModuleTypeId())).thenReturn(moduleType);
//        Mockito.when(courseContentService.getUserDetailsFromToken(authToken)).thenReturn(new JSONObject());
//
//        CourseModule actualCourseModule = courseContentService.getModuleDetailsFull(authToken, courseModuleId);
//
//        assertEquals(courseModule, actualCourseModule);
//    }

    @Test
    public void testGetModuleDetailsFull_unauthorized() throws CustomException {
        try {
            courseContentService.getModuleDetailsFull("authToken", 0);
            fail("Expected CustomException to be thrown");
        } catch (Exception e) {
            // Expected exception was thrown
        }
    }

    @Test
    public void testGetCourseModuleDetails_courseIdZero() {
        try {
            courseContentService.getCourseModuleDetails("authToken", 0);
            fail("Expected CustomException to be thrown");
        } catch (Exception e) {
            // Expected exception was thrown
        }
    }

    @Test
    public void testGetModuleDetailsById_courseModuleMismatch() {
        try {
            courseContentService.getModuleDetailsById("authToken", 1, 2);
            fail("Expected CustomException to be thrown");
        } catch (Exception e) {
            // Expected exception was thrown
        }
    }

    @Test
    public void testGetModuleDetailsFull_courseIdZero() {
        try {
            courseContentService.getModuleDetailsFull("authToken", 0);
            fail("Expected CustomException to be thrown");
        } catch (Exception e) {
            // Expected exception was thrown
        }
    }


}

