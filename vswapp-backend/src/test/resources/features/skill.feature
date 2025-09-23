Feature: Manage skills
  As a user, I want to add a new skill so that my profile shows my strengths.

  Scenario: Create a new skill successfully
    Given a user with ID 28 exists
    When the client creates a new skill with title "React", level "Intermediate", about "I am currently working as a frontend developer", and category "Programming"
    Then the API should respond 201
    And the response should contain a skill with title "React"

  Scenario: Fail to create skill with empty title
    Given a user with ID 28 exists
    When the client creates a new skill with title "", level "Intermediate", about "Something", and category "Programming"
    Then the API should respond 400
