Test: takeoff
Description: "Let the drone take off"
Given: Time interval in [ seconds(10) , seconds(12) ]
Then: Robot position is 0.0 0.0 2.0 within sphere with radius meter(2)
Then: Robot state is HOVER