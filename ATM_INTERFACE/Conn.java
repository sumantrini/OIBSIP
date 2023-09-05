import java.sql.*;

public class Conn{

    Connection c;
    Statement s;
    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  //register the driver not needed, as the library picks up the driver by itself
            //connection statement
            c= DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "3Umbrellas@");
            s= c.createStatement();
        }

        catch(Exception e){
            System.out.println(e);
        }
    }
}