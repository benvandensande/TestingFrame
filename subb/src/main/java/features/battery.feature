Test: Title 
Description: "I want to use this template for my feature file"
Given: Time interval in [ seconds(30) , seconds(40) ]
Given: Robot position is 0.0 0.0 0.0 within sphere with radius meter(0.0)
When: Battery level is equal to percent(100.0)
When: Robot distance to 0 0 0 is lower then centimeter(0.0)
When: Mission status is ACTIVE
When: Robot position is 0.0 0.0 0.0 within sphere with radius meter(10)
Then: Robot state is LANDED

Test: Title2 
Description: "I want to use this template for my feature file"
Given: Robot position is 0.0 0.0 0.0 within sphere with radius meter(10)
When: Battery level is equal to percent(0.0)
When: Robot distance to 0 0 0 is lower then meter(0.0) with tolerance centimeter(0)
When: Robot position is 0.0 0.0 0.0 within sphere with radius meter(10)
Then: Robot state is LANDED

