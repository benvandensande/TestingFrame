Test: flyWithTunnel
Description: ""
Given: Robot position is 0.0 0.0 5.0  within sphere with radius meter(1)
When: Time interval in [ seconds(20), seconds(30)]
Then: Robot distance to 20 20 5 is lower then meter(1)
