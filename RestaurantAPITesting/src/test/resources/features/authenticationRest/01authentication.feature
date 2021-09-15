Feature: Authentication rest controller

    Scenario Outline:User attempts to register with empty fields
        When Actor enters "<username>" "<password>" "<repeatedPassword>" "<name>" "<surname>" "<phoneNumber>" "<address>"
        Then Actor should see message about empty fields
        Examples:
        |username|password|repeatedPassword|name|surname|phoneNumber|address|
        |      |      |              |  |     |         |     |
        |   |P@ssword|P@ssword|TestName|TestSurname|070123456|TestAddress|
        |testUser@test.com| |P@ssword|TestName|TestSurname|070123456|TestAddress|
        |testUser@test.com|P@ssword|    |TestName|TestSurname|070123456|TestAddress|
        |testUser@test.com|P@ssword|P@ssword|   |TestSurname|070123456|TestAddress|
        |testUser@test.com|P@ssword|P@ssword|TestName|  |070123456|TestAddress|
        |testUser@test.com|P@ssword|P@ssword|TestName|TestSurname|  |TestAddress|
        |testUser@test.com|P@ssword|P@ssword|TestName|TestSurname|070123456|  |


    Scenario: User attempts to register with missing fields
        When Actor attempts to register with missing fields
        Then Actor should see message about missing fields

    Scenario Outline: User attempts to register with different passwords
        When Actor enters "<username>" "<password>" "<repeatedPassword>" "<name>" "<surname>" "<phoneNumber>" "<address>"
        Then Actor should see message that passwords do not match
        Examples:
        |username|password|repeatedPassword|name|surname|phoneNumber|address|
        |testUser@test.com|P@ssword1|P@ssword|TestName|TestSurname|070123456|TestAddress|
        |testUser@test.com|P@ssword|P@ssword1|TestName|TestSurname|070123456|TestAddress|


    Scenario: User attempts to register with existing username
        When Actor enters "admin@admin.com" "P@ssword" "P@ssword" "TestName" "TestSurname" "070123456" "TestAddress"
        Then Actor should see message that username exists

    Scenario: User registers successfully
        When Actor enters "testUser@test.com" "P@ssword" "P@ssword" "TestName" "TestSurname" "070123456" "TestAddress"
        Then Actor should see message about successful registration

    Scenario: Get info for user with invalid credentials
        When Actor tries to get info for user with username "test@admin.com"
        Then Actor should see message about invalid credentials

    Scenario: Get info for user with valid credentials
        When Actor tries to get info for user with username "admin@admin.com"
        Then Actor should see message about selected user

    Scenario: User attempts to leave a review with missing data
        When Actor attempts to leave a review with missing fields
        Then Actor should see message about missing fields

    Scenario Outline: User attempts to leave e review with empty data
        When Actor enters "<stars>" "<description>" "<username>"
        Then Actor should see message about empty fields
        Examples:
        |stars|description|username|
        |0    |Great      |admin@admin.com|
        |2    |           |admin@admin.com|
        |2    |Great      |               |

    Scenario: User attempts to leave a review with wrong username
        When Actor enters "3" "Great" "test@admin.com"
        Then Actor should see message about invalid credentials

    Scenario: User successfully leaves a review
        When Actor enters "3" "Great" "admin@admin.com"
        Then Actor should see message about successful leaving review