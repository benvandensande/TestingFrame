Test: flyWithTunnel
Description: ""
Given: Robot position is 0.0 0.0 0.5  within sphere with radius meter(1)
When: Time interval in [ seconds(5), seconds(6)]
Then: Robot position is 0.0 0.0 2.0  within sphere with radius centimeter(200)
