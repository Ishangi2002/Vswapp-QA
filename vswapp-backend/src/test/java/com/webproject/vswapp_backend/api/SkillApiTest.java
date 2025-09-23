package com.webproject.vswapp_backend.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SkillApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/api/skill";
    }

    // ------------------- POST Success -------------------
    @Test
    public void testCreateSkill_Success() {
        Map<String, Object> skill = new HashMap<>();
        skill.put("title", "TestRestTemplate Skill");
        skill.put("category", "Testing");
        skill.put("level", "Beginner");
        skill.put("about", "Testing API without REST Assured");
        skill.put("userId", 28);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(skill, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(getBaseUrl(), request, Map.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, Object> body = response.getBody();
        assertNotNull(body);
        assertEquals("TestRestTemplate Skill", body.get("title"));
        assertEquals("Testing", body.get("category"));
        assertEquals("Beginner", body.get("level"));
        assertEquals("Testing API without REST Assured", body.get("about"));
        assertEquals(28, ((Number) body.get("userId")).intValue());
    }

    // ------------------- POST Invalid Payload -------------------
    @Test
    public void testCreateSkill_InvalidPayload() {
        Map<String, Object> skill = new HashMap<>();
        skill.put("category", "Testing"); // missing title
        skill.put("level", "Beginner");
        skill.put("about", "Missing title");
        skill.put("userId", 28);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(skill, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl(), request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    // ------------------- GET Skills for Valid User -------------------
    @Test
    public void testGetSkillsByUser_Success() {
        ResponseEntity<Map[]> response = restTemplate.getForEntity(getBaseUrl() + "/user/28", Map[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map[] skills = response.getBody();
        assertNotNull(skills);
        assertTrue(skills.length >= 1);

        boolean found = false;
        for (Map skill : skills) {
            if ("TestRestTemplate Skill".equals(skill.get("title"))) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Created skill not found in GET response");
    }

    // ------------------- GET Skills for Non-Existent User -------------------
    @Test
    public void testGetSkillsByUser_NonExistentUser() {
        ResponseEntity<Map[]> response = restTemplate.getForEntity(getBaseUrl() + "/user/9999", Map[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode()); // API returns empty array
        Map[] skills = response.getBody();
        assertNotNull(skills);
        assertEquals(0, skills.length); // no skills
    }
}
