Feature: Web Automation Scenarios

#  Scenario: Authenticate Successfully
#    Given I am on the OrangeHRM login page
#    When I enter username and password for OrangeHRM
#    And I click the login button
#    Then I should see the dashboard
#
#
#  Scenario: My Info - Update Date of Birth
#    Given I am on the OrangeHRM login page
#    When I enter username and password for OrangeHRM
#    And I click the login button
#    And I navigate to My Info page
#    And I update the Date of Birth field and save
#    Then My Info page should be updated with the new Date of Birth

  Scenario: Log_out user
    Given I am on the OrangeHRM login page
    When I enter username and password for OrangeHRM
    And I click the login button
    And I navigate to My Info page
    Then I navigate to the login page after clicking on logout button