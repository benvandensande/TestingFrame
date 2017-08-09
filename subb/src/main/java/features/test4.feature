Test: testLandingBattery
Description: "Check if the UAV is landing if the battery is lower then 50 percent"
Given: Battery status is ACTIVE
When: Battery level is lower than percent(50)
Then: UAV state is LANDING

