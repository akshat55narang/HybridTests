Feature: Home Page

  @ui
  Scenario: Verify user is able to view gists on Home page
    Given I'm on the Home Page as anonymous user
    Then I should be able to see all gists

  @ui
  Scenario: Verify user is able to view gists on Home page
    Given I'm on the Home Page
    Then I should be able to see all gists

  @ui
  Scenario: Verify user is able to delete exist gists on Home page
    Given A gist with file name "delete_existing_gist_ui.txt"
    And I'm on the default user's profile page
    When I open the gist with name "delete_existing_gist_ui.txt"
    And I delete the gist the current open in the UI
    Then the gist with name "delete_existing_gist_ui" should be deleted




