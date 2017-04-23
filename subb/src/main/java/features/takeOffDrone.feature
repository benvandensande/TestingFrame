Test: takeoff
Description: "Let the drone take off"
Given: Robot position is 0 0 1
When: Robot state is TAKEOFF
Then: Robot position is 0.0 0.0 2.5 within sphere with radius meter(0.5)
Then: Robot state is HOVER
