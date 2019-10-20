
Purpouse: 
	- Create a parking system that maximizes the profit for the owner.

Scenario:
	- To reach the goal, I believe the system is required to not only work perfectly but also constantly update the availability of the lots in the parking.
	- Therefore, when a car leaves, the lot must be available for next car in the line right away. 
	- When a car enters, the lot must be marked as "Filled"
	- Whenever a car leaves or enters, the parking must display its availability either "Full" or "Available".
	- If the parking is full, no car shall pass. The gate must not open until a car leaves the exit gate.
	- If the parking is available, the gate will be able to open and close when a car passes.
	- The parking fee is $4 for the first 3 hours and $2 every extra hour.
	- The parking only charges when a car exits.

Assumption: 
	- The parking capacity is 3 rows, 4 cars per row.
	- There is only one entrance gate and one exit gate.
	- At the same time, there are only one car exits and one car enters. No two cars both either exits at the same time or enters at the same time.
	- It take 1 minute for the entrance/exit gate to open and close for a car to enter or exit.
	- Each car will have an estimate of parking time and this time is independent to exit and enter time.
	- The time tracking will keep increasing by 1 minute for each loop.
Input:
	- list of numbers which represent the time estimate for each car.

Classes:
	- Main: used for executing
	- Lot: hold information about the cartesian coordinates of a lot in the Parking_lot, and the available car
	- Car: hold information about the ticket received, parking time estimate, and actual parking time
	- Parking_Lot: this is the main class that process the system. 
		+ Read the file and save into a queue
		+ Run a while loop increasing time by 1 minute each loop
		+ Constantly checking for coming and leaving cars until the queue is empty
