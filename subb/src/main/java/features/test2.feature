Test: testTakeOffBeforeFlight
Description: "let the drone take off before flight"
When: Time interval in [ seconds(2) , seconds(3)]
Then: Robot position is meter(0) meter(0) meter(2) within sphere with radius meter(1)
Then: Robot state is HOVER

Test: testFlyToLocation
Description: "test if the drone flies to a position and stabelise itself"
When: Time interval in [ seconds(20) , seconds(22)]
Then: Robot position is meter(5) meter(5) meter(5) within sphere with radius meter(1)
Then: Robot state is HOVER

Test: testCilinder
Description: "test if the drone stays within a certain circle"
When: Time is always
Then: Robot position is meter(0) meter(0) meter(0) within circle with radius meter(2)


