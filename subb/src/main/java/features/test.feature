Test: testTakeOff
Description: "test the takeoff of the UAV"
When: Time interval in [ seconds(2) , seconds(3)]
Then: UAV position is meter(0) meter(0) meter(2) within sphere with radius meter(1)
Then: Barometer altitudereading is greater than meter(1)
Then: UAV state is HOVER

Test: testLanding
Description: "test the landing of the UAV"
When: Time interval in [ seconds(7) , seconds(10)]
Then: UAV position is meter(0) meter(0) meter(0.3) within sphere with radius meter(1)
Then: Barometer altitudereading is lower than meter(2)
Then: UAV state is HOVER

Test: testCilinder
Description: "test if the drone stays within a certain circle"
When: Time is always
Then: UAV position is meter(0) meter(0) meter(0) within circle with radius meter(2)


