package com.abc.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Model {
	private String name;
	private String custid;
	private int accno;
	private String pwd;
	private int bal;
	private String email;
	private int raccno;
	private String recipient;
	private String subject;
	private String content;
	
	
	public ArrayList al=new ArrayList();
	public ArrayList sal=new ArrayList();
	public ArrayList ral=new ArrayList();
	public int getRaccno() {
		return raccno;
	}
	public void setRaccno(int raccno) {
		this.raccno = raccno;
	}
	private java.sql.Connection con;
	private java.sql.PreparedStatement pstmt;
	private ResultSet res;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getBal() {
		return bal;
	}
	public void setBal(int bal) {
		this.bal = bal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
  public Model() throws Exception
  {  Class.forName("com.mysql.jdbc.Driver");
     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BankApplication", "root", "system");
	  System.out.println("Loading the driver and establishing the connection is completed");
  }
  
  public boolean register()
  {
	  String s="insert into ABCBank values(?,?,?,?,?,?)";
	  try {
		pstmt=con.prepareStatement(s);
		pstmt.setString(1, name);
		pstmt.setString(2, custid);
		pstmt.setInt(3, accno);
		pstmt.setString(4, pwd);
		pstmt.setInt(5, bal);
		pstmt.setString(6, email);
		
		int x=pstmt.executeUpdate();
		 if(x>0)
		  {
			  return true;
		  }
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	 
	  return false;
  }
public boolean login() throws SQLException {
	String s="select * from ABCBank where custid=? and password=?";
	pstmt=con.prepareStatement(s);
	pstmt.setString(1, custid);
    pstmt.setString(2, pwd);	
    
    ResultSet res=pstmt.executeQuery();
    
    while(res.next()==true)
    {
    	accno=res.getInt("ACCNO");
    	email=res.getString("EMAIL");
    	return true;
    }
	return false;
}
public boolean checkBalance() throws SQLException
{
	String s="select balance from ABCBank where accno=?";
	pstmt=con.prepareStatement(s);
	pstmt.setInt(1, accno);
	ResultSet res = pstmt.executeQuery();
	
	while(res.next())
	{
		bal=res.getInt("balance");
		
		return true;
	}
	return false;
	
}
public boolean passwordChange() throws SQLException {
	// TODO Auto-generated method stub
	String s="update ABCBank set password=? where accno=?";
	pstmt=con.prepareStatement(s);
	pstmt.setInt(2, accno);
	pstmt.setString(1, pwd);
	int x=pstmt.executeUpdate();
	if(x>0)
	{
		return true;
	}
	return false;
}
public boolean transfer() throws SQLException  {
	// TODO Auto-generated method stub
	
	
	
	try {
		con.setAutoCommit(false);
		System.out.println("1");
		String s="select * from ABCBank where accno=?";
		System.out.println("s1");
		pstmt=con.prepareStatement(s);
		System.out.println("s2");
		pstmt.setInt(1, raccno);
		System.out.println("s3");
	ResultSet res = pstmt.executeQuery();
	System.out.println("s4");
	while(res.next())
	{System.out.println("2");
		
		String s1="update ABCBank set balance=balance-? where accno=?";
		pstmt=con.prepareStatement(s1);
		pstmt.setInt(1, bal);
		pstmt.setInt(2, accno);
		int x=pstmt.executeUpdate();
		if(x>0) {
			System.out.println("3");
			String s2="update ABCBank set balance=balance+? where accno=?";
			pstmt=con.prepareStatement(s2);
			pstmt.setInt(1, bal);
			pstmt.setInt(2, raccno);
			int y=pstmt.executeUpdate();
			if(y>0)
			{System.out.println("4");
				String s3="insert into GetStatement values(?,?,?)";
				pstmt=con.prepareStatement(s3);
				pstmt.setInt(1, accno);
				pstmt.setInt(2, raccno);
				pstmt.setInt(3, bal);
				int z=pstmt.executeUpdate();
				if(z>0)
				{System.out.println("5");
					con.setAutoCommit(true);
					return true;
				}
				
			}
		}
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		System.out.println("exception");
		con.rollback();
	}
	
	return false;
}
public ArrayList getStatement() throws SQLException {
	// TODO Auto-generated method stub
	String s="select * from getstatement where saccno=?";
	pstmt=con.prepareStatement(s);
	pstmt.setInt(1, accno);
	res=pstmt.executeQuery();
	while(res.next())
	{
		al.add(res.getInt("tamt"));
		sal.add(res.getInt("saccno"));
		ral.add(res.getInt("raccno"));
		
		
	}
	return al;
}
public boolean loan() throws SQLException {
	// TODO Auto-generated method stub
	String s="select * from abcbank where accno=?";
	pstmt=con.prepareStatement(s);
	pstmt.setInt(1, accno);
	res=pstmt.executeQuery();
	while(res.next())
	{
		name=res.getString("name");
		email=res.getString("email");
		return true;
	}
	return false;
}
public void sendEmail(String host, String port, String user, String pass) throws AddressException, MessagingException {
	// TODO Auto-generated method stub
	// sets SMTP server properties
			Properties properties = new Properties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			System.out.println(recipient);
			System.out.println(subject);
			System.out.println(content);
			
			

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, pass);
				}
			};

			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(user));
			InternetAddress[] toAddresses = { new InternetAddress(recipient) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			
			msg.setContent(content,"text/html");

			// sends the e-mail
			Transport.send(msg);

	
}
  
  
  
}
