import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
 
public class DheerCipher
{
    public static void main(String[] args) throws IOException
    {
    	System.out.println("1.Register \n 2.Login\n3.View Users \n4.Show data base state(Encrypted data)");
    	Scanner in =new Scanner(System.in);
    	while(true){
    		System.out.println("Enter your Choice");
    		 int n=in.nextInt();
    		switch(n){
    		case 1: 
    			op1();
    		break;
    		case 2:
    	op2();	break;
    		case 3:
    			op3(); break;		
    		case 4:
    			op4(); break;		
    		}
    	}
    	}

    static void op1(){
    	try{
    	Class.forName("com.mysql.jdbc.Driver");  
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Kndheeraj@1997");
		  Statement st=con.createStatement();
		//  st.execute("create table login(User VARCHAR(20),Password VARCHAR(20))");
    	Scanner in =new Scanner(System.in);
    	System.out.println("Enter Username");
		String usr=in.next();
			
		System.out.println("Enter Password");
		String p=in.next();
		System.out.println("ReEnter Password");
		String rep=in.next();
		if(p.equals(rep)){
			String cipheruser = encrypt(usr);
			String cipherpass = encrypt(p);
		      st.executeUpdate("INSERT INTO login VALUES('"+cipheruser+"','"+cipherpass+"')");
		System.out.println("A/C created");}}catch(Exception e){
			System.out.println("Exception:"+e);
		}}
private static String encrypt(String usr) {
	byte[] encrypted = usr.getBytes();
  String enu=bytesToHex(encrypted);
  
		return enu;
	}
static void op2(){
	try{
    	Class.forName("com.mysql.jdbc.Driver");  
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Kndheeraj@1997");
		  Statement st=con.createStatement();
    	Scanner in =new Scanner(System.in);
    	System.out.println("Enter Username:");
		String ul=in.next();
		
		String cipheruser = encrypt(ul);
		st.executeQuery("select Password from login where User='"+cipheruser+"'");
		ResultSet rs = st.getResultSet();
		rs.next();
		String l=rs.getString(1);
		byte[] decryptedpv = decrypt(l);
		System.out.println("Enter Password:");
		String pl=in.next();
		if(pl.equals(new String(decryptedpv))){
		System.out.println("Login Successful");}
    	}catch(Exception e){
			System.out.println("Exception:"+e);
		}}
private static byte[] decrypt(String l) {
	 byte[] decrypted = hexStringToByteArray(l);
  	return decrypted;
}
static void op3(){
	try{
    	Class.forName("com.mysql.jdbc.Driver");  
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Kndheeraj@1997");
		  Statement st=con.createStatement();
    	Scanner in =new Scanner(System.in);
		st.executeQuery("select user from login");
		ResultSet ru = st.getResultSet();
		 while(ru.next())
  	       {
		String reus=ru.getString(1);
		byte[] decryptedusr = decrypt(reus);
		System.out.println("User: " + new String(decryptedusr));}
	}catch(Exception e){
		System.out.println("Exception:"+e);
	}}
static void op4(){
	try{
    	Class.forName("com.mysql.jdbc.Driver");  
    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","Kndheeraj@1997");
		  Statement st=con.createStatement();
	System.out.println("Database Content:");
	System.out.println("User	Password");
	st.executeQuery("select * from login");
	ResultSet rs = st.getResultSet();
	while(rs.next()){
		String usr=rs.getString(1);
		String pass=rs.getString(2);
	System.out.println(""+usr+"		"+pass);
}}catch(Exception e){
	System.out.println("Exception:"+e);
}}

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    }