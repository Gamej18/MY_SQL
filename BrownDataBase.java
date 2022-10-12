import java.sql.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class BrownDataBase {
		public static void main(String[] args) {
			
		
		Scanner scan = new Scanner(System.in); // reads in a string using System.in
		String password; //username of user
		String username; // password of user
		int input = 0; // number that the user picks in while loop.
		
		System.out.print("Username: "); //prints out user name
		username = scan.nextLine(); // user types username
		System.out.print("Password: "); // prints out password
		password = scan.nextLine(); // user types password
		
		// connection to database on mysql connection server
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Tries to connect to the database system.
			try (Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/university?user=" + username + "&password=" + password);
					Statement stmt = conn.createStatement()) {
		// loops for user to select what they would like to do. until the user would like to quit.
		do {
			System.out.println("Please select what you would like to do");
			System.out.println("1.) Retrieve all info about departments");
			System.out.println("2.) Retrieve all info about courses");
			System.out.println("3.) Add a course");
			System.out.println("4.) Delete a course");
			System.out.println("5.) Update course title");
			System.out.println("6.) Retrieve all info about prerequistes ");
			System.out.println("7.) Add a prerequiste to the course");
			System.out.println("8.) Delete a prerequiste from the course");
			System.out.println("9.) Quit");
			
			input = scan.nextInt();
			
			/*
			 *  User Selects 1 if they want all information about departments.
			 */
			if(input == 1) // if statement that runs if user enters 1,2,3 etc...
			{
				ResultSet rset = stmt.executeQuery("select dept_name, building from department group by dept_name");
				System.out.print("\nDepartment info \n");
				while(rset.next()) {
					System.out.println(rset.getString("dept_name") + " | " + rset.getString("building"));
				}
			}
			/*
			 *  User Selects 2 if the want all information about courses.
			 */
			else if(input == 2)
			{
				ResultSet rset2 = stmt.executeQuery("select * from course group by course_id");
				System.out.println("\n Course_id - Title - Department - Credits \n-------------------------------\n");
				while (rset2.next()) {
					System.out.println(rset2.getString("course_id") + " | " + rset2.getString("title") + " | " + rset2.getString("dept_name") + " | " + (rset2.getString("credits")));
				}
			}
			/*
			 *  User Selects 3 if they would like to add a course.
			 */
			else if(input == 3)
			{
				System.out.print("Course_id: ");
				scan.nextLine();
				String newCourseID = scan.nextLine();
				System.out.println("Title: ");
				String addTitle = scan.nextLine();
				System.out.println("Dept_name: ");
				String addDeptName = scan.nextLine();
				System.out.println("Credits: ");
				int addCredits = scan.nextInt();
				// a try statement that throws an exception if the try fails to add funtion
				try { 
					AddCourse(newCourseID, addTitle, addDeptName, addCredits, conn); // AddCourseFunction to add a course.
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			
			else if(input == 4)
			{
				String deleteCourseID;
				System.out.print("Course_id: ");
				scan.nextLine();
				deleteCourseID = scan.nextLine();
				// a try statement that throws an exception if the try fails to add funtion
				try {
					DeleteCourse(deleteCourseID, conn);	// DeleteCourse function to delete a course.		
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
			
			else if(input == 5)
			{
				
				System.out.print("Course_ID");
				scan.nextLine();
				String updateCourseID = scan.nextLine();
				System.out.print("Updated Title");
				String updateTitle = scan.nextLine();
				// a try statement that throws an exception if the try fails to add funtion
				try {
					UpdatedCourseTitle(updateCourseID, updateTitle, conn); // Updatescoursetitle function to Update title of course. 
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				
			}
			
			else if (input == 6)
			{
				ResultSet rset3 = stmt.executeQuery("select * from prereq"); // executes the query.
				System.out.print("\n Course_id - Prereq_id \n");
				while (rset3.next()) {
					System.out.println(rset3.getString("course_id") + " | " + rset3.getString ("prereq_id"));		
				}
			}
			else if(input == 7)
			{
				String addPrereqCourse, addPrereq;
				System.out.print("Course_id: ");
				scan.nextLine();
				addPrereqCourse = scan.nextLine();
				System.out.print("Prereq_id: ");
				addPrereq = scan.nextLine();
				// a try statement that throws an exception if the try fails to add funtion
				try {
					AddPrerequiste(addPrereqCourse, addPrereq, conn); // AddPrerequiste function to add a prerequiste to prerequiste.
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}	
			}
			
			else if(input == 8)
			{
				String deletePrereq; // string delete prerequiste.
				System.out.print("Prerequiste ID: ");
				scan.nextLine();
				deletePrereq = scan.nextLine();
				// a try statement that throws an exception if the try fails to add funtion
				try {
					DeletePrerequiste(deletePrereq, conn); // DeletePrerequiste function to delete prereq.
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}			
			}
			
			else {
				exit(0); // call exit function to exit out of program.
			}
			
		}while (input != 9);
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void AddCourse(String courseID, String title, String dept_name, int credits, Connection conn) throws SQLException 
	{
		PreparedStatement pstmt = conn.prepareStatement("insert into course values ('" + courseID +"', '" + title + "', '" + dept_name + "', '" + credits + "')"); // database system compiles the query when its prepared.
		// tries to insert into course values
		try { 
			pstmt.executeUpdate();		
		} catch (Exception e) {
			System.out.println("Row could not be added");
		}
	}
	
	public static void DeleteCourse( String deleteCourseID, Connection conn) throws SQLException 
	{
		PreparedStatement pstmt = conn.prepareStatement("delete from course where course_id = '" + deleteCourseID + "'"); //database system compiles the query when its prepared.
		// tries to delete course ids
		try {
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Course could not be deleted");
		}
	}
	
	public static void UpdatedCourseTitle(String updateCourseID, String updateTitle, Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("update course set title = '" + updateTitle + "' Where course_id = '" + updateCourseID + "'"); //database system compiles the query when its prepared.
		//tries to update course title
		try {
			pstmt.executeUpdate();	
		} catch (Exception e) {
			System.out.println("Could not update course");
		}
	}	
	
	public static void AddPrerequiste(String addPrereqCourse, String addPrereq, Connection conn) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement("insert into prereq values ('" + addPrereqCourse + "', '" + addPrereq +"')"); //database system compiles the query when its prepared.
		// tries to  add prereq;
		try {
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not add prerequiste. Course must be in course table");
		}
	}
	
	public static void DeletePrerequiste(String deletePrereq, Connection conn) throws SQLException
	{
		PreparedStatement pstmt = conn.prepareStatement("delete from prereq where prereq_id = '" + deletePrereq + "'"); //database system compiles the query when its prepared.
		//tries to delete a prereq
		try {
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not delete course");
		}
	}	
}