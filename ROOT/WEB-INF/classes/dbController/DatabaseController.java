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

  public boolean addEmployee(String fName, String mName, String lName, int salary) throws SQLException{
	
	String insertEmployee = "INSERT INTO anicolette.Employee(firstName, middleName, lastName, Salary) VALUES('" + fName + "', '" + mName + "', '" + lName + "', "  + salary + ")";

	try {
		ResultSet rs = statement_.executeQuery(insertEmployee);	
 	} catch (SQLException sqlex) {
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }

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

  public boolean addOffice(String city) throws SQLException{
	String addOffice = "INSERT INTO anicolette.Office(city) VALUES('" + city + "')";

	try{
		ResultSet rs = statement_.executeQuery(addOffice);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }

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

  public boolean addNeed(int client, int instructor, String description) throws SQLException{
	String addNeed = "INSERT INTO anicolette.Need(Client, Instructor, Description) VALUES(" + client + ", " + instructor + ", " + description + ")";
	
	try{
		ResultSet rs = statement_.executeQuery(addNeed);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }

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

  public boolean addNote(int lessonId, String note){
	String addNote = "INSERT INTO anicolette.Note(LessonId, Note) VALUES(" + lessonId + ", '" + note + "')";	

	try{
		ResultSet rs = statement_.executeQuery(addNote);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }

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

  public boolean addClient(String fName, String mName, String lName, int registered, String year, String month, String day, int provisionNum, int assignedInstructor, int requestedInstructor) throws SQLException{
	String addClient = "INSERT INTO anicolette.Client(firstName, middleName, lastName, registered, dob, provisionNum, assignedInstructor, requestedInstructor) VALUES(";
	addClient += "'" + fName + "', '" + mName + "', '" + lName + "', " + registered + ", TIMESTAMP '" + year + "-" + month + "-" + day + "', " + provisionNum + ", ";
	addClient += assignedInstructor + ", " + requestedInstructor + ")";
	
	try{
		ResultSet rs = statement_.executeQuery(addClient);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }

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

  public boolean addFault(String description) throws SQLException{
	String addFault = "INSERT INTO anicolette.Fault(Description) VALUES('" + description + "')";
	
	try{
		ResultSet rs = statement_.executeQuery(addFault);
	} catch (SQLException sqlex){
		sqlex.printStackTrace();
		throw sqlex;
	}
	return true;
  }

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

  public Vector<String> FindAllEmployees() {
    String sql_query = "SELECT * FROM mccann.employee";
    try {
      ResultSet rs  = statement_.executeQuery(sql_query);
      Vector<String> result_employees = new Vector<String>();
      while (rs.next()) {
         String temp_record = rs.getString("fname") + "##" + rs.getString("minit") +
             "##" + rs.getString("lname") + "##" + rs.getString("ssn") + "##" +
             rs.getString("bdate") + "##" + rs.getString("address") + "##" +
	     rs.getString("sex") + "##" + rs.getString("salary") +
	     "##" + rs.getString("superssn") + "##" + rs.getString("dno");
        result_employees.add(temp_record);
      }
      return result_employees;
    } catch (SQLException sqlex) {
      sqlex.printStackTrace();
    }
    return null;
  }
}
