package dataBaseProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataTest {
	WebDriver driver = new ChromeDriver();
	Random rand =new Random();
	Random rand2 =new Random();
	int randomNumber = rand.nextInt(888)* rand2.nextInt(887);

	Connection con;
	Statement stmt;
	ResultSet rs;
@BeforeTest
public void mySetup() throws SQLException {
con= DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1996_Batool");
	
}
@Test(priority = 1, invocationCount = 50)
public void addToTheDB() throws SQLException {
	int randomNumber = rand.nextInt(500);
    System.out.println(randomNumber);

	String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES ("+randomNumber+", 'ABC Corporation', 'Smith', 'John', '123-456-7890', '123 Elm Street', 'Suite 500', 'New York', 'NY', '10001', 'USA', 1501, 50000.00)";
	stmt= con.createStatement();
     int rowIsertment = stmt.executeUpdate(query);
     System.out.println(rowIsertment);

}

@Test(priority = 2)
public void updateData() throws SQLException {

	String query = "update customers set contactFirstName ='nooralhuda' where customerNumber = "+randomNumber;

	stmt = con.createStatement();

	int NumberOfRowUpdated = stmt.executeUpdate(query);

	System.out.println(NumberOfRowUpdated);
     }

@Test(priority = 3)
public void myFirstToGetData() throws SQLException {
	stmt= con.createStatement();
	rs= stmt.executeQuery("SELECT * FROM customers where customerNumber =" + randomNumber);
	while(rs.next()){
		int custmerNumberInTheDB = rs.getInt("customerNumber");
		String custmerNameInTheDB = rs.getString("customerName");
        System.out.println(custmerNumberInTheDB);
        System.out.println(custmerNameInTheDB);
        String firstName = rs.getString("contactFirstName");
        String lastName = rs.getString("contactLastName");
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
        WebElement firstInpputField= driver.findElement(By.id("firstname"));
        WebElement lastInpputField= driver.findElement(By.id("lastname"));
        firstInpputField.sendKeys(firstName);
        lastInpputField.sendKeys(lastName);


	}

}


@Test(priority = 4, enabled = false)
public void deleteData() throws SQLException {

	String query = "delete from customers where customerNumber= "+ randomNumber;
	stmt= con.createStatement();
     int rowIsertmentDeleted = stmt.executeUpdate(query);
     System.out.println(rowIsertmentDeleted);
     }

}
