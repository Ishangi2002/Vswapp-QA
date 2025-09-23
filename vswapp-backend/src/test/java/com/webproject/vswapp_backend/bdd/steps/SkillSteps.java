package com.webproject.vswapp_backend.bdd.steps;

import com.webproject.vswapp_backend.dto.SkillDto;
import com.webproject.vswapp_backend.service.SkillService;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration
public class SkillSteps {

    @Autowired
    private SkillService skillService;

    private SkillDto requestDto;
    private SkillDto responseDto;
    private Exception exception;
    private Long userId;

    @Given("a user with ID {long} exists")
    public void a_user_with_id_exists(Long id) {
        this.userId = id;
    }
    @When("the client creates a new skill with title {string}, level {string}, about {string}, and category {string}")
    public void the_client_creates_skill(String title, String level, String about, String category) {
        requestDto = new SkillDto();
        requestDto.setTitle(title);
        requestDto.setLevel(level);
        requestDto.setAbout(about);
        requestDto.setCategory(category);

        try {
            responseDto = skillService.createSkill(requestDto, userId);
        } catch (Exception e) {
            exception = e;
        }
    }
    @Then("the API should respond {int}")
    public void the_api_should_respond(int statusCode) {
        if (statusCode == 201) {
            assertNotNull(responseDto, "Expected a skill to be created, but response is null!");
        } else if (statusCode == 400) {
            assertNotNull(exception, "Expected an exception, but none occurred!");
            assertTrue(exception instanceof IllegalArgumentException);
        }
    }
    @Then("the response should contain a skill with title {string}")
    public void the_response_should_contain_title(String expectedTitle) {
        assertNotNull(responseDto, "Response skill is null!");
        assertEquals(expectedTitle, responseDto.getTitle());
    }
}
