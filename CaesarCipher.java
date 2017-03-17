import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner; 

public class CaesarCipher {

	public static void main(String[] args){
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
		String decryptedpv = decrypt(l);
		System.out.println("Enter Password:");
		String pl=in.next();
		if(pl.equals(decryptedpv)){
		System.out.println("Login Successful");}
    	}catch(Exception e){
			System.out.println("Exception:"+e);
		}}
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
		String decryptedusr = decrypt(reus);
		System.out.println("User: " + decryptedusr);}
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

public static String encrypt(String plainText) {
		char chars[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','!','@','#','$','%','^','&','(',')','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','+','-','*','/','.','[',']','{','}','=','<','>','?','_'};
	    String empty = "empty"; 
		     String cipher = null;
		    char[] plain = plainText.toCharArray(); 
		    
		    for(int i = 0;i<plain.length;i++){
		        for(int j = 0 ; j<85;j++){
		                if(plain[i]==chars[j]){
		                	 if(j<=80){
		                	plain[i] = chars[j+5];
		                    break;
		                }  
		            
		            else{
		                plain[i] = chars [j-80];
		            }
		                	 }
		         } 
		      } 
		    plainText = String.valueOf(plain);
			 return plainText;
		   }

	public static String decrypt(String cip) {
		char chars[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','!','@','#','$','%','^','&','(',')','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','+','-','*','/','.','[',']','{','}','=','<','>','?','_'};
	    String cipher = null;
	    String empty = "empty";
	    char[] cipher1 = cip.toCharArray();
	    if(cip .equals(empty)){
	    	System.out.println(" No text is Decrypted");
	    }
	    else{ 
                  for(int i = 0;i<cipher1.length;i++){
                        for(int j = 0 ; j<85;j++){
                            if(j>=5 && cipher1[i]==chars[j]){
                                cipher1[i] = chars[j-5];
                                break;
                            }
                                if(cipher1[i] == chars[j] && j<5){
                                    cipher1[i] = chars[81+j];
                                    break;
                      }
                } 
                  }
	    }  cipher = String.valueOf(cipher1);
			return cipher;
        }
}
