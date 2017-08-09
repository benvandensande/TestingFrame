Test: testTakeOffBeforeFlight
Description: "let the UAV take off before flight"
When: Time interval in [ seconds(2) , seconds(3)]
Then: UAV position is meter(0) meter(0) meter(2) within sphere with radius meter(1)
Then: UAV state is HOVER

Test: testFlyToLocation
Description: "test if the drone flies to a position and stabelise itself, also the gps reading is tested"
When: Time interval in [ seconds(20) , seconds(22)]
Then: UAV position is meter(5) meter(5) meter(5) within sphere with radius meter(1)
Then: UAV state is HOVER
Then: UAV speed is lower than m/s(1,1,1)

Test: testCilinder
Description: "test if the drone stays within a certain circle, this test should fail"
When: Time is always
Then: UAV position is meter(0) meter(0) meter(0) within circle with radius meter(2)


