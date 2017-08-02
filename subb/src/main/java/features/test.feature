Test: testTakeOff
Description: "test the synchronization of statements"
When: Time interval in [ seconds(2) , seconds(3)]
Then: Robot position is meter(0) meter(0) meter(2) within sphere with radius meter(1)
Then: Robot state is HOVER

Test: testLanding
Description: "test the synchronization of statements"
When: Time interval in [ seconds(7) , seconds(10)]
Then: Robot position is meter(0) meter(0) meter(0.3) within sphere with radius meter(1)
Then: Robot state is HOVER

Test: testCilinder
Description: "test if the drone stays within a certain circle"
When: Time is always
When: Robot position is meter(5) meter(5) meter(5) within circle with radius meter(2)
Then: Battery level is equal to percent(100)


