
Purpose: 
	- Create a parking system that maximizes the profit for the owner.

Scenario:
	- To reach the goal, I believe the system is required to not only work perfectly but also constantly update the availability of the 		lots in the parking.
	- Therefore, when a car leaves, the lot must be available for next car in the line right away. 
	- When a car enters, the lot must be marked as "Filled"
	- Whenever a car leaves or enters, the parking must display its availability either "Full" or "Available".
	- If the parking is full, no car shall pass. The gate must not open until a car leaves the exit gate.
	- If the parking is available, the gate will be able to open and close when a car passes.
	- The parking fee is $2 for the first 3 hours and $1 every extra hour.
	- The parking only charges when a car exits.

Assumption: 
	- 
	- There is only one entrance gate and one exit gate.
	- At the same time, there are only one car exits and one car enters. No two cars both either exits at the same time or enters at the same time.
	- It take 1 minute for the entrance/exit gate to open and close for a car to enter or exit.
	- Each car will have an estimate of parking time and this time is independent to exit and enter time.
	- The time tracking will keep increasing by 1 minute for each loop.

Classes:
	- Main: ask user to input the testcase number, then execute Parking Lot class.
	- Lot: hold information about the cartesian coordinates of its position in the parking lot, the available car
	- Car: hold information about the ticket (position of the lot), the parking time estimate, and the actual parking time.
	- Parking_Lot: this is the main class processing the system.
		+ Read the right testcase user chooses and store to the waiting queue
		+ Loop until the queue is empty or until the parking is empty if the queue is finished 
		+ The time increases by 1 minute and constantly check for car in and out each loop
