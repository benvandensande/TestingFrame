Test: testTakeOffBeforeFlight
Description: "let the drone take off before flight"
When: Time interval in [ seconds(2) , seconds(3)]
Then: UAV position is meter(0) meter(0) meter(2) within sphere with radius meter(1)
Then: UAV state is HOVER

Test: testFlyToLocation
Description: "test if the drone flies to a position and stabelise itself"
When: Time interval in [ seconds(30) , seconds(32)]
Then: UAV position is meter(0) meter(0) meter(2) within sphere with radius meter(1)
Then: UAV state is HOVER

Test: testCilinder
Description: "test if the drone stays within a certain circle"
When: Time is always
Then: UAV position is meter(1) meter(1) meter(1) within circle with radius meter(3)

Test: testObstacles
Description: "test if the drone stays within a certain circle"
When: Time is never
Then: UAV distance to obstacles is lower than meter(2)


