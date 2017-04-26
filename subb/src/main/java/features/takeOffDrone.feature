Test: takeoff
Description: "Let the drone take off"
When: Time interval in [ seconds(10), seconds(50)]
Then: Robot position is 0.0 0.0 2.5 within sphere with radius meter(2)
Then: Robot state is HOVER
