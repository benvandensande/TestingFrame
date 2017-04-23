Test: Position
Description: "Drone has to always know his position"
Given: Robot position is 0.0 0.0 0.3 within sphere with radius meter(10)
When: Robot GPS fails 
Then: Robot state is HOVER
    
		
		