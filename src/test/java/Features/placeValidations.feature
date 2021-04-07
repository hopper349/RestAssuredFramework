Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Validate if Place is being successfully added using AddPlace API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" using with "Post" http request
    Then API call is successful with status code as 200
    And "status" in response body is "OK"
    And Verify "place_id" created maps to "<name>" using getPlaceAPI

    Examples:
    |name           |language    |address           |
    |Dipanshu House | English    | 24th Jump Street |
    #   |Saini House    | Hindi      | 22nd Jump Street |

  @DeletePlace
  Scenario: Validate if place is successfully deleted using DeletePlace API
    Given DeletePlaceAPI Payload
    When User calls "deletePlaceAPI" using with "Delete" http request
    Then API call is successful with status code as 200
    And "status" in response body is "OK"