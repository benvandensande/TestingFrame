Test: testTakeOffBeforeFlight
Description: "let the drone take off before flight"
When: Battery level is lower then percent(50)
Then: Robot state is LANDING

