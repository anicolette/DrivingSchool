# DrivingSchool
CSC 460 Final Project

To run:

1. Replace the directory tomcat/webapps/ROOT with the ROOT directory submitted
with this file.
2. Call tomcat/bin/startup.sh
3. Go to lectura.cs.arizona.edu:[YOUR PORT NUMBER]
4. From index, you can:
	a. Insert rows into tables here
		-Insert into any of the tables
	b. Delete tuples from tables here
		-Delete any tuples. Must know primary key for the table.
	c. Assign a manger to an office
		-Add a manager to an office (Must no OfficeID and EmployeeID)
	d. List all manager 
		-Retrieve a list of all of the managers
	e. List address of all Glasgow offices
		-Retrieve the address of all offices located in Glasgow
	f. Find all of the timestamps of appointments for an Employee
		-With an employee ID, you can see the start time of all of the
		appointments for that employee (appointments include Lessons, Interviews,
		Inspections, and DrivingTests that the Employee participates in)
	g. Find all the interviews for an Employee
		-With an employee ID, can find all of the Interviews the Employee has
		done (past and future)
	h. List average miles driven per hour lesson
		-Lists the average miles driven out of all of the lessons
		
Division of Work:
	CJ: 
		-Created html forms for inserting/deleting into tables and deleting from tables
		-Created .jsp files for inserting/deleting
		-Created the .jsp files and method in DatabaseController for find all appointments
		query and find all interview queries

	Daniel:
		-Created the .jsp file and method in DatabaseController for average miles per lesson
		-Started deletion methods in DatabaseController
		-Created ER Diagram and designed database
		-Performed normalization analysis

	Alejandro:
		-Debugged and completed deletion methods in DatabaseController
		-Created .jsp files and methods in DatabaseController for listManagers and listGlasgowOffices queries
		-Created all insertion methods in DatabaseController
		-Created all initial methods in DatabaseController
		-Created and organized directories
		-Planned out and organized back end
		-Adjusted table attributes as needed and created physical database