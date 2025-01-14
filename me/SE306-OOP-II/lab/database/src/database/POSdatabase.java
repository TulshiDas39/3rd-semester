package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lab.Category;

public class POSdatabase {
	
	final private String dbName = "bsse08.db"; 
	final private String url = "jdbc:sqlite:db_file/"+dbName;
	private String tableName;// = "OOC2_Results";

    private Connection connect() {
        // SQLite connection string
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void createNewDatabase() {
   	 
      try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }   //database created so it can't be created twice
    
    
    public void createNewCustomerTable() {
       // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + "CustomerTable"+ " (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text,\n"
                + "	phone text,\n"
                +" balence real NOT NULL,\n"
                +"type text NOT NULL,\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table created sucessfully.");
            System.out.println(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createNewProductTable() {
        // SQL statement for creating a new table
    	/* private String id;
	private String name;
	private Category category;
	private double price;
	private double quantity;*/
     	this.tableName = "productName";
         String sql = "CREATE TABLE IF NOT EXISTS " + tableName+ " (\n"
                 + "	id integer PRIMARY KEY,\n"
                 + "	name text,\n"
                 + "	category text,\n"
                 +" price real NOT NULL,\n"
                 +"quantity text NOT NULL,\n"
                 + ");";
         
         try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
             // create a new table
             stmt.execute(sql);
             System.out.println("Table created sucessfully.");
             System.out.println(sql);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
     }
    
    public void createNewManagementTable() {
        // SQL statement for creating a new table
         String sql = "CREATE TABLE IF NOT EXISTS " + "ManagementTable"+ " (\n"
                 + "	CustomerId integer PRIMARY KEY,\n"
                 + "	ProductId integer,\n"
                 + "	TotalUnit integer,\n"
                 +" 	TotalPrice real NOT NULL,\n"
                 + ");";
         
         try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
             // create a new table
             stmt.execute(sql);
             System.out.println("Table created sucessfully.");
             System.out.println(sql);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
     }
    
    
    public void insert(int roll, String name, double lab,double mid,double Final,double Total) {
        String sql = "INSERT INTO " + tableName+ " (roll,name,lab,mid,Final,Total) VALUES(?,?)";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, roll);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void selectAll(){
        String sql = "SELECT id, name, points FROM " + tableName+ "";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getDouble("points"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void getpointsGreaterThan(double points){
        String sql = "SELECT id, name, points "
                   + "FROM " + tableName+ " WHERE points > ?";
 
		 try (Connection conn = this.connect();
		      PreparedStatement pstmt  = conn.prepareStatement(sql)){
		     
		     // set the value
			 pstmt.setDouble(1,points);
			 //
			 ResultSet rs  = pstmt.executeQuery();
			 
			 // loop through the result set
			 while (rs.next()) {
			     System.out.println(rs.getInt("id") +  "\t" + 
			                    rs.getString("name") + "\t" +
			                    rs.getDouble("points"));
			 }
		 } catch (SQLException e) {
		     System.out.println(e.getMessage());
		 }
    }
    
    public void update(int id, String name, double points) {
        String sql = "UPDATE " + tableName+ " SET name = ? , "
                + "points = ? "
                + "WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setString(1, name);
            pstmt.setDouble(2, points);
            pstmt.setInt(3, id);
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(int id) {
        String sql = "DELETE FROM " + tableName+ " WHERE id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
