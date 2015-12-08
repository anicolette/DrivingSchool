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
	String addNeed = "INSERT INTO anicolette.Need(Client, Instructor, Description) VALUES(" + client + ", " + instructor + ", '" + description + "')";
	
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
		String query = "SELECT instDate FROM cjkarl.Inspection WHERE cjkarl.Inspection.Inspector=" + empID + " AND cjkarl.Inspection.instDate > SYSDATE AND cjkarl.Inspection.instDate < (SYSDATE + 7) UNION "
						+ "SELECT time FROM cjkarl.Interview WHERE cjkarl.Interview.Interviewer=" + empID + " AND cjkarl.Interview.time > SYSDATE AND cjkarl.Interview.time < (SYSDATE + 7) UNION "
						+ "SELECT time FROM cjkarl.Lesson WHERE cjkarl.Lesson.Instructor=" + empID + " AND cjkarl.Lesson.time > SYSDATE AND cjkarl.Lesson.time < (SYSDATE + 7) UNION "
						+ "SELECT time FROM cjkarl.DrivingTest WHERE cjkarl.DrivingTest.InstructorID=" + empID + " AND cjkarl.DrivingTest.time > SYSDATE AND cjkarl.DrivingTest.time < (SYSDATE + 7)";
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
		String query = "SELECT * FROM cjkarl.Interview WHERE cjkarl.Interview.Interviewer=" + empID;
		
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
				String tempRec = rs.getString("MILESDRIVEN");

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
