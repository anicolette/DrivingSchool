package dbController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.ResultSet;
import java.util.*;

/**
 * Servlet implementation class for Servlet: DatabaseController
 *
 */
public class DatabaseController {
  static final long serialVersionUID = 1L;
  /**
   * A handle to the connection to the DBMS.
   */
  protected Connection connection_;
  /**
   * A handle to the statement.
   */
  protected Statement statement_;
  /**
   * The connect string to specify the location of DBMS
   */
  protected String connect_string_ = null;
  /**
   * The password that is used to connect to the DBMS.
   */
  protected String password = null;
  /**
   * The username that is used to connect to the DBMS.
   */
  protected String username = null;


  public DatabaseController() {
    // your cs login name
    username = "anicolette"; 
    // your Oracle password, NNNN is the last four digits of your CSID
    password = "a0374";
    connect_string_ = "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
  }


  /**
   * Closes the DBMS connection that was opened by the open call.
   */
  public void Close() {
    try {
      statement_.close();
      connection_.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    connection_ = null;
  }


  /**
   * Commits all update operations made to the dbms.
   * If auto-commit is on, which is by default, it is not necessary to call
   * this method.
   */
  public void Commit() {
    try {
      if (connection_ != null && !connection_.isClosed())
        connection_.commit();
    } catch (SQLException e) {
      System.err.println("Commit failed");
      e.printStackTrace();
    }
  }


  public void Open() {
    boolean opened = false;
    while (!opened) {
      try {
        Class.forName("oracle.jdbc.OracleDriver");
        connection_ = DriverManager.getConnection(
            connect_string_, username, password);
        statement_ = connection_.createStatement();
        opened = true;
        return;
      } catch (SQLException sqlex) {
        sqlex.printStackTrace();
        opened  = false;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        System.exit(1); //programemer/dbsm error
      } catch (Exception ex) {
        ex.printStackTrace();
        System.exit(2);
      }
    }
  }

       /*---------------------------------------------------------------------
        |  Method addEmployee
        |
        |  Purpose:  Adds a new employee to the Employee table of the database
        |
        |  Pre-condition:  The Employee table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new Employee will have been inserted into the 
        |                  Employee table with the given attribute values
        |
        |  Parameters:
        |      String fName -- the employee's first name
        |      String mName -- the employee's middle name
        |      String lName -- the employee's last name
        |      int salary -- the employee's salary
        |      String phoneNum -- the employee's phone number
        |      char sex -- the sex of the employee
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addEmployee(String fName, String mName, String lName, int salary, String phoneNum, char sex) throws SQLException{
	
	String insertEmployee = "INSERT INTO anicolette.Employee(firstName, middleName, lastName, Salary, sex, PhoneNum) VALUES('" + fName + "', '" + mName + "', '" + lName + "', "  + salary + ", '" + sex + "', '" + phoneNum  + "')";

	try {
		ResultSet rs = statement_.executeQuery(insertEmployee);	
 	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addRole
        |
        |  Purpose:  Adds a new role to the Role table of the database
        |
        |  Pre-condition:  The Role table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new Role will have been inserted into the 
        |                  Role table with the given attribute values
        |
        |  Parameters:
        |      int empId -- the employee's id number
        |      String role -- the name of the employee's role
        |      int officeNo -- the id number of the employee's office
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addRole(int empId, String role, int officeNo) throws SQLException{
	String addRole = "INSERT INTO anicolette.Role(empId, role, OfficeNo) VALUES(" + empId + ", '" + role + "', " + officeNo  + ")"; 	

	try {
		ResultSet rs = statement_.executeQuery(addRole);	
 	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addOffice
        |
        |  Purpose:  Adds a new office to the Office table of the database
        |
        |  Pre-condition:  The Office table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new office will have been inserted into the 
        |                  Office table with the given attribute values
        |
        |  Parameters:
        |      String city -- the city the office is in
        |      String address -- the address of the office
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addOffice(String city, String address) throws SQLException{
	String addOffice = "INSERT INTO anicolette.Office(city, address) VALUES('" + city + "', '" + address  + "')";

	try{
		ResultSet rs = statement_.executeQuery(addOffice);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addCar
        |
        |  Purpose:  Adds a new car to the Car table of the database
        |
        |  Pre-condition:  The Car table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new car will have been inserted into the 
        |                  Car table with the given attribute values
        |
        |  Parameters:
        |      String lPlate -- the license plate of the car
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addCar(String lPlate) throws SQLException{
	String addCar = "INSERT INTO anicolette.Car(LPlate) VALUES('" + lPlate  + "')";
	
	try{
		ResultSet rs = statement_.executeQuery(addCar);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addNeed
        |
        |  Purpose:  Adds a new need to the Need table of the database
        |
        |  Pre-condition:  The Need table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new need will have been inserted into the 
        |                  Need table with the given attribute values
        |
        |  Parameters:
        |      int client -- the id number of the client whose need this is
        |      int instructor -- the id number of the instructor who conducted the
        |                        interview in which this need was discovered
        |      String description -- the description of the need
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addNeed(int client, int instructor, String description) throws SQLException{
	String addNeed = "INSERT INTO anicolette.Need(Client, Instructor, Description) VALUES(" + client + ", " + instructor + ", '" + description + "')";
	
	try{
		ResultSet rs = statement_.executeQuery(addNeed);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addLesson
        |
        |  Purpose:  Adds a new lesson to the Lesson table of the database
        |
        |  Pre-condition:  The Lesson table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new lesson will have been inserted into the 
        |                  Lesson table with the given attribute values
        |
        |  Parameters:
        |      int client -- the id number of the client taking the lesson
        |      int instructor -- the id number of the instructor giving the lesson
        |      String year -- the year in which the lesson is scheduled
        |      String month -- the month in which the lesson is scheduled
        |      String day -- the day on which the lesson is scheduled
        |      String hour -- the hour on which the lesson is scheduled
        |      String minute -- the minute on which the lesson is scheduled
        |      boolean isBlocked -- determines whether this lesson was booked
        |                           in a block of lessons
        |      int milesDriven --  the number of miles driven in the lesson
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addLesson(int client, int instructor, String year, String month, String day, String hour, String minute, boolean isBlocked, int milesDriven) throws SQLException{
	String block = "F";
	if(isBlocked){
		block = "T";
	}
	String addLesson = "INSERT INTO anicolette.Lesson(Client, Instructor, time, isBlocked, milesDriven) VALUES(" + client + ", " + instructor + ", TIMESTAMP '" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00', '" + block + "', " + milesDriven + ")";

	try{
		ResultSet rs = statement_.executeQuery(addLesson);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }



       /*---------------------------------------------------------------------
        |  Method addInterview
        |
        |  Purpose:  Adds a new interview to the Interview table of the database
        |
        |  Pre-condition:  The Interview table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new interview will have been inserted into the 
        |                  Interview table with the given attribute values
        |
        |  Parameters:
        |      int interviewer -- the id number of the employee giving the interview
        |      String year -- the year in which the interview is scheduled
        |      String month -- the month in which the interview is scheduled
        |      String day -- the day on which the interview is scheduled
        |      String hour -- the hour on which the interview is scheduled
        |      String minute -- the minute on which the interview is scheduled
        |      int client --  the id number of the client being interviewed
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addInterview(int interviewer, String year, String month, String day, String hour, String minute, int client) throws SQLException{
	String addInterview = "INSERT INTO anicolette.Interview(Interviewer, time, Client) VALUES(" + interviewer + ", TIMESTAMP '" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00', " + client + ")";
	
	try{
		ResultSet rs = statement_.executeQuery(addInterview);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addNote
        |
        |  Purpose:  Adds a new note to the Note table of the database
        |
        |  Pre-condition:  The Note table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new office will have been inserted into the 
        |                  Office table with the given attribute values
        |
        |  Parameters:
        |      int lessonId -- the id number of the lesson in which the note was made
        |      String note -- the content of the note
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addNote(int lessonId, String note) throws SQLException{
	String addNote = "INSERT INTO anicolette.Note(LessonId, Note) VALUES(" + lessonId + ", '" + note + "')";	

	try{
		ResultSet rs = statement_.executeQuery(addNote);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addDrivingTest
        |
        |  Purpose:  Adds a new driving test to the DrivingTest table of the database
        |
        |  Pre-condition:  The DrivingTest table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new driving test will have been inserted into the 
        |                  DrivingTest table with the given attribute values
        |
        |  Parameters:
        |      int instructor -- the id number of the instructor driving the 
        |                        client to their test
        |      String year -- the year in which the test is scheduled
        |      String month -- the month in which the test is scheduled
        |      String day -- the day on which the test is scheduled
        |      String hour -- the hour on which the test is scheduled
        |      String minute -- the minute on which the test is scheduled
        |      char testType -- determines whether the test is written or practical
        |      boolean pass -- true if the test was passed, false if it was failed
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addDrivingTest(int instructor, int client, String year, String month, String day, String hour, String minute, char testType, boolean pass) throws SQLException{
	String passed = "T";
	if(!pass){
		passed = "F";
	}

	String addDrivingTest = "INSERT INTO anicolette.DrivingTest(InstructorId, Client, time, testType, pass) VALUES(" + instructor + ", " + client + ", TIMESTAMP '" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00', '" + testType + "', '" + passed + "')";

	try{
		ResultSet rs = statement_.executeQuery(addDrivingTest);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addClient
        |
        |  Purpose:  Adds a new client to the Client table of the database
        |
        |  Pre-condition:  The Client table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new client will have been inserted into the 
        |                  Client table with the given attribute values
        |
        |  Parameters:
        |      String fName -- the client's first name
        |      String mName -- the client's middle name
        |      String lName -- the client's last name
        |      int registered -- the office at which the client registered
        |      String year -- the client's year of birth
        |      String month -- the client's month of birth
        |      String day -- the client's day of birth
        |      int provisionNum -- the client's provisional license number
        |      int assignedInstructor -- the id number of the instructor assigned 
        |                                to the client
        |      int requestedInstructor -- the id number of the instructor requested
        |                                 by the client
        |      char sex -- the sex of the client
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addClient(String fName, String mName, String lName, int registered, String year, String month, String day, int provisionNum, int assignedInstructor, int requestedInstructor, char sex) throws SQLException{
	String addClient = "INSERT INTO anicolette.Client(firstName, middleName, lastName, registered, dob, sex, provisionNum, assignedInstructor, requestedInstructor) VALUES(";
	addClient += "'" + fName + "', '" + mName + "', '" + lName + "', " + registered + ", DATE '" + year + "-" + month + "-" + day + "', '" + sex + "', " + provisionNum + ", ";
	addClient += assignedInstructor + ", " + requestedInstructor + ")";
	
	try{
		ResultSet rs = statement_.executeQuery(addClient);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
	
  }


       /*---------------------------------------------------------------------
        |  Method addFailure
        |
        |  Purpose:  Adds a new failure to the Failure table of the database
        |
        |  Pre-condition:  The Failure table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new failure will have been inserted into the 
        |                  Failure table with the given attribute values
        |
        |  Parameters:
        |      int test -- the id number of the test which was failed
        |      String description -- the description of the failure
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addFailure(int test, String description) throws SQLException{
	String addFailure = "INSERT INTO anicolette.Failure(TestId, Description) VALUES(" + test + ", '" + description + "')"; 
	
	try{
		ResultSet rs = statement_.executeQuery(addFailure);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method addFault
        |
        |  Purpose:  Adds a new fault to the Fault table of the database
        |
        |  Pre-condition:  The Fault table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new fault will have been inserted into the 
        |                  Fault table with the given attribute values
        |
        |  Parameters:
        |      int inspectionId -- the id number of the inspection in which the fault
        |                          was discovered
        |      String description -- the description of the fault
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addFault(int inspectionId, String description) throws SQLException{
	String addFault = "INSERT INTO anicolette.Fault(InsId, Description) VALUES(" + inspectionId  + ", '" + description + "')";
	
	try{
		ResultSet rs = statement_.executeQuery(addFault);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }


       /*---------------------------------------------------------------------
        |  Method Inspection
        |
        |  Purpose:  Adds a new  inspection to the Inspection table of the database
        |
        |  Pre-condition:  The Inspection table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: A new inspection will have been inserted into the 
        |                  Inspection table with the given attribute values
        |
        |  Parameters:
        |      int instructor -- the id number of the instructor  the 
        |                        client to their inspection
        |      String year -- the year in which the inspection is scheduled
        |      String month -- the month in which the inspection is scheduled
        |      String day -- the day on which the inspection is scheduled
        |      String hour -- the hour on which the inspection is scheduled
        |      String minute -- the minute on which the inspection is scheduled
        |      int inspectorId -- id number of the employee performing the inspection
        |      int carId -- the id number of the car being inspected
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
  public boolean addInspection(String year, String month, String day, String hour, String minute, int inspectorId, int carId) throws SQLException{
	String addInspection = "INSERT INTO anicolette.Inspection(instDate, Inspector, CarId) VALUES(TIMESTAMP '" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00', " + inspectorId + ", " + carId + ")";
	
	try{
		ResultSet rs = statement_.executeQuery(addInspection);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }

       /*---------------------------------------------------------------------
        |  Method deleteEmployee
        |
        |  Purpose:  Deletes an employee from the Employee table
        |
        |  Pre-condition:  The Employee table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified employee will have been removed from 
        |                  the Employee table 
        |
        |  Parameters:
        |      int ID -- the id number of the employee to be deleted 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/  
  public boolean deleteEmployee(int ID) throws SQLException
{
	String deleteEmployee = "DELETE FROM anicolette.Employee WHERE IdNo=" + ID;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteEmployee);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteOffice
        |
        |  Purpose:  Deletes an office from the Office table
        |
        |  Pre-condition:  The Office table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified office will have been removed from 
        |                  the Office table 
        |
        |  Parameters:
        |      int ID -- the id number of the office to be deleted 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteOffice(int ID) throws SQLException
{
	String deleteOffice = "DELETE FROM anicolette.Office WHERE OfficeNo=" + ID;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteOffice);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteCar
        |
        |  Purpose:  Deletes a car from the Car table
        |
        |  Pre-condition:  The Car table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified car will have been removed from 
        |                  the Car table 
        |
        |  Parameters:
        |      int ID -- the id number of the car to be deleted 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteCar(int ID) throws SQLException
{
	String deleteCar = "DELETE FROM anicolette.Car WHERE CarId=" + ID;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteCar);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteInspection
        |
        |  Purpose:  Deletes a inspection from the Inspection table
        |
        |  Pre-condition:  The Inspection table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified inspection will have been removed from 
        |                  the Inspection table 
        |
        |  Parameters:
        |      int ID -- the id number of the inspection to be deleted 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteInspection(int ID) throws SQLException
{
	String deleteInspection = "DELETE FROM anicolette.Inspection WHERE InsId=" + ID;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteInspection);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteLesson
        |
        |  Purpose:  Deletes a lesson from the Lesson table
        |
        |  Pre-condition:  The Lesson table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified lesson will have been removed from 
        |                  the Lesson table 
        |
        |  Parameters:
        |      int ID -- the id number of the lesson to be deleted 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteLesson(int ID) throws SQLException
{
	String deleteLesson = "DELETE FROM anicolette.Lesson WHERE LessonId=" + ID;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteLesson);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteClient
        |
        |  Purpose:  Deletes a client from the Client table
        |
        |  Pre-condition:  The Client table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified client will have been removed from 
        |                  the Client table 
        |
        |  Parameters:
        |      int ID -- the id number of the client to be deleted 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteClient(int ID) throws SQLException
{
	String deleteClient = "DELETE FROM anicolette.Client WHERE ClientId=" + ID;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteClient);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteDrivingTest
        |
        |  Purpose:  Deletes a driving test from the DrivingTest table
        |
        |  Pre-condition:  The DrivingTest table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified driving test will have been removed from 
        |                  the DrivingTest table 
        |
        |  Parameters:
        |      int ID -- the id number of the driving test to be deleted 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteDrivingTest(int ID) throws SQLException
{
	String deleteDrivingTest = "DELETE FROM anicolette.DrivingTest WHERE TestId=" + ID;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteDrivingTest);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteInterview
        |
        |  Purpose:  Deletes an interview from the Interview table
        |
        |  Pre-condition:  The Interview table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified interview will have been removed from 
        |                  the Interview table 
        |
        |  Parameters:
        |      String year -- the year in which the interview was scheduled
        |      String month -- the month in which the interview was scheduled
        |      String day -- the day on which the interview was scheduled
        |      String hour -- the hour on which the interview was scheduled
        |      String minute -- the minute on which the interview was scheduled
        |      int interviewerID -- the id number of the interviewer who conducted
        |                           the interview 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteInterview(String year, String month, String day, String hour, String minute, int interviewerID) throws SQLException
{
	String time = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00";
	String deleteInterview = "DELETE FROM anicolette.Interview WHERE Interviewer=" + interviewerID + " AND time=TIMESTAMP '" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":00'";
	try
	{
		ResultSet rs = statement_.executeQuery(deleteInterview);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteRole
        |
        |  Purpose:  Deletes a role from the Role table
        |
        |  Pre-condition:  The Role table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified role will have been removed from 
        |                  the Role table 
        |
        |  Parameters:
        |      int empID -- the id number of the employee filling the role
        |      String role -- the name of the role
        |      int officeNo -- the id number of the office in which the role exists 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteRole(int empID, String role, int officeNo) throws SQLException
{
	String deleteRole = "DELETE FROM anicolette.Role WHERE empId=" + empID + " AND role='" + role + "' AND OfficeNo=" + officeNo;
	try
	{
		ResultSet rs = statement_.executeQuery(deleteRole);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteNeed
        |
        |  Purpose:  Deletes a need from the Need table
        |
        |  Pre-condition:  The Need table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified need will have been removed from 
        |                  the need table 
        |
        |  Parameters:
        |      int ID -- the id number of the need to be deleted
        |      int instructorID -- the id number of the employee who recorded the need
        |      String description -- the need's description 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteNeed(int clientID, int instructorID, String description) throws SQLException
{
	String deleteNeed = "DELETE FROM anicolette.Need WHERE Client=" + clientID + " AND Instructor=" + instructorID + " AND Description='" + description + "'";
	try
	{
		ResultSet rs = statement_.executeQuery(deleteNeed);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteNote
        |
        |  Purpose:  Deletes a note from the Note table
        |
        |  Pre-condition:  The Note table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified note will have been removed from 
        |                  the Note table 
        |
        |  Parameters:
        |      int lessonID -- the id number of the lesson from which the note
        |                      was taken
        |      String note -- the content of the note 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteNote(int lessonID, String note) throws SQLException
{
	String deleteNote = "DELETE FROM anicolette.Note WHERE LessonId=" + lessonID + " AND Note='" + note + "'";
	try
	{
		ResultSet rs = statement_.executeQuery(deleteNote);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteFault
        |
        |  Purpose:  Deletes a fault from the Fault table
        |
        |  Pre-condition:  The Fault table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified fault will have been removed from 
        |                  the Fault table 
        |
        |  Parameters:
        |      int inspectionID -- the id number of the inspection in which the
        |                          fault was discovered
        |      String description -- the description of the fault 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteFault(int inspectionID, String description) throws SQLException
{
	String deleteFault = "DELETE FROM anicolette.Fault WHERE InsId=" + inspectionID + " AND Description='" + description + "'";
	try
	{
		ResultSet rs = statement_.executeQuery(deleteFault);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method deleteFailure
        |
        |  Purpose:  Deletes a failure from the Failure table
        |
        |  Pre-condition:  The Failure table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The specified failure will have been removed from 
        |                  the Failure table 
        |
        |  Parameters:
        |      int testID -- the id number of the test which was failed
        |      String description -- the description of the failure 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
public boolean deleteFailure(int testID, String description) throws SQLException
{
	String deleteFailure = "DELETE FROM anicolette.Failure WHERE TestId=" + testID + " AND Description='" + description + "'";
	try
	{
		ResultSet rs = statement_.executeQuery(deleteFailure);
	}
	catch(SQLException sqlex)
	{
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
}


       /*---------------------------------------------------------------------
        |  Method assignManager
        |
        |  Purpose:  assigns a manager to an office
        |
        |  Pre-condition:  The given employee and office must both exist
        |
        |  Post-condition: The specified employee will be registered as the manager
        |                  of the specified office 
        |
        |  Parameters:
        |      int officeId -- the id number of the office to which the manager will 
        |                      be assigned
        |      int empId -- the employee to be assigned as a manager 
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
	public boolean assignManager(int officeId, int empId) throws SQLException{
		String assignManager = "UPDATE anicolette.Office SET mgrid=" + empId  + " WHERE OfficeNo=" + officeId;

		try{
			ResultSet rs = statement_.executeQuery(assignManager);
		} catch (SQLException sqlex){
			sqlex.printStackTrace();
			throw sqlex;
		}
		return true;
	}


       /*---------------------------------------------------------------------
        |  Method listManagers
        |
        |  Purpose:  Lists the names and phone numbers of all managers, and the 
        |            cities and office numbers of the offices they manage.
        |
        |  Pre-condition:  The Office and Employee tables must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The managers' information will have been returned 
        |
        |  Parameters: None
        |
        |  Returns:  a Vector containing the information about all the managers
        *-------------------------------------------------------------------*/
	public Vector<String> listManagers() throws SQLException{
		String mgrList = "SELECT FIRSTNAME, MIDDLENAME, LASTNAME, PHONENUM, CITY, OFFICENO from anicolette.Employee,anicolette.Office WHERE anicolette.Employee.IdNo=anicolette.Office.mgrId ORDER BY anicolette.Office.city,anicolette.Office.OfficeNo";

		try{
			ResultSet rs = statement_.executeQuery(mgrList);
			Vector<String> resultMgrs = new Vector<String>();
			while(rs.next()){
				String tempRec = rs.getString("FIRSTNAME") + "##" + rs.getString("MIDDLENAME") + "##" + rs.getString("LASTNAME") + "##" + rs.getString("PHONENUM") + "##" + rs.getString("CITY") + "##" + rs.getString("OFFICENO");

				resultMgrs.add(tempRec);
			}
			return resultMgrs;
		} catch (SQLException sqlex){
			throw sqlex;
		}
	} 


       /*---------------------------------------------------------------------
        |  Method listGlasgowOffices
        |
        |  Purpose:  Lists the addresses and office numbers of all offices in Glasgow
        |
        |  Pre-condition:  The Office table must exist in the database and
        |                  JDBC must be connected to the database.
        |
        |  Post-condition: The offices' information will have been returned 
        |
        |  Parameters: None
        |
        |  Returns:  a boolean true value if the SQL statement was successful,
        |            throws an exception if it was not
        *-------------------------------------------------------------------*/
	public Vector<String> listGlasgowOffices() throws SQLException{
		String glasOffices = "SELECT ADDRESS,OFFICENO FROM anicolette.Office WHERE anicolette.Office.city='Glasgow' ORDER BY OfficeNo";
		try{
			ResultSet rs = statement_.executeQuery(glasOffices);
			Vector<String> resultOffices = new Vector<String>();
			while(rs.next()){
				String tempRec = rs.getString("ADDRESS") + " ## " + rs.getString("OFFICENO");

				resultOffices.add(tempRec);
			}
			return resultOffices;
		} catch (SQLException sqlex){
			throw sqlex;
		}
		
	}

	/**
		Method: findAppointment(empID)
		Description: takes in an employee ID number, and finds all of the appointments that employee is scheduled for
		Input: An employee ID number
		Output: Vector<String> objects with the timeStamps written in them.
	
	**/
	public Vector<String> findAppointment(int empID) throws SQLException {
		String query = "SELECT instDate FROM anicolette.Inspection WHERE anicolette.Inspection.Inspector=" + empID + " AND anicolette.Inspection.instDate > SYSDATE AND anicolette.Inspection.instDate < (SYSDATE + 7) UNION "
						+ "SELECT time FROM anicolette.Interview WHERE anicolette.Interview.Interviewer=" + empID + " AND anicolette.Interview.time > SYSDATE AND anicolette.Interview.time < (SYSDATE + 7) UNION "
						+ "SELECT time FROM anicolette.Lesson WHERE anicolette.Lesson.Instructor=" + empID + " AND anicolette.Lesson.time > SYSDATE AND anicolette.Lesson.time < (SYSDATE + 7) UNION "
						+ "SELECT time FROM anicolette.DrivingTest WHERE anicolette.DrivingTest.InstructorID=" + empID + " AND anicolette.DrivingTest.time > SYSDATE AND anicolette.DrivingTest.time < (SYSDATE + 7)";
		try {
			ResultSet rs = statement_.executeQuery(query);
			Vector<String> resultQuery = new Vector<String>();
			while(rs.next()) {
				String tempRec = rs.getString("INSTDATE");
				resultQuery.add(tempRec);
			}
			return resultQuery;
		} catch(SQLException sqlex) {
			throw sqlex;
		}
	}
	
	/*
	 * Method: findInterview(empID)
	 * Description: takes in an employee ID number, and finds all of the interviews that employee has completed or is scheduled for
	 * Input: An employee ID number
	 * Output: Vector<String> objects with the Interview details written in them.
	 */
	public Vector<String> findInterview(int empID) throws SQLException {
		String query = "SELECT * FROM anicolette.Interview WHERE anicolette.Interview.Interviewer=" + empID;
		
		try {
			ResultSet rs = statement_.executeQuery(query);
			Vector<String> resultQuery = new Vector<String>();
			while(rs.next()) {
				String tempRec = rs.getString("INTERVIEWER") + " ## " + rs.getString("time") + " ## " + rs.getString("ClIENT");
				resultQuery.add(tempRec);
			}
			return resultQuery;
		} catch(SQLException sqlex) {
			throw sqlex;
		}
	}
	
	/*
	 * Method: getAverageMiles()
	 * Description: Uses the MilesDriven attribute from the Lesson table to 
	 * calculate the average miles driven per hour lesson and 
	 * returns the resulting Vector<String>
	 * Input: None
	 * Output: Vector<String> containing the average miles driven per hour lesson
	 */

	public Vector<String> getAverageMiles() throws SQLException
	{
		String average = "SELECT AVG(MILESDRIVEN) AVERAGE_MILES FROM anicolette.Lesson";
		try
		{
			ResultSet rs = statement_.executeQuery(average);
			Vector<String> resultAvg = new Vector<String>();
			while(rs.next())
			{
				String tempRec = rs.getString("AVERAGE_MILES");

				resultAvg.add(tempRec);
			}
			return resultAvg;
		} 
		catch (SQLException sqlex)
		{
			throw sqlex;
		}
		
	}
}
