package com.example.coursecontentmanagementservice.Model;

import com.example.coursecontentmanagementservice.Model.ModuleType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ModuleTypeTest {

    @Test
    public void testModuleTypeCreation() {
        // Create a new ModuleType object
        ModuleType moduleType = new ModuleType();
        moduleType.setId(1);
        moduleType.setType("Lecture");

        // Check if the ModuleType object is not null
        assertNotNull(moduleType);

        // Validate properties of the ModuleType object
        assertEquals(1, moduleType.getId());
        assertEquals("Lecture", moduleType.getType());
    }

    @Test
    public void testModuleTypeSettersAndGetters() {
        // Create a new ModuleType object
        ModuleType moduleType = new ModuleType();
        moduleType.setId(2);
        moduleType.setType("Quiz");

        // Validate properties using getters
        assertEquals(2, moduleType.getId());
        assertEquals("Quiz", moduleType.getType());

        // Update properties using setters
        moduleType.setId(3);
        moduleType.setType("Assignment");

        // Validate updated properties using getters
        assertEquals(3, moduleType.getId());
        assertEquals("Assignment", moduleType.getType());
    }
}

