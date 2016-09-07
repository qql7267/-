package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import util.ConnFactory;
import vo.User;

public class UserDAO {

	public void addUser(User user) throws SQLException{
		Connection conn =  ConnFactory.getConn();
		String sql = "insert into myuser values(myuserseq.nextval,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		
		preparedStatement.executeUpdate();
		conn.close();
	}
	public User checkUser(User user) throws SQLException{
		User user2 = null;
		Connection conn =  ConnFactory.getConn();
		String sql = "select * from myuser where uname=? and upassword=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		ResultSet rSet = preparedStatement.executeQuery();
		if(rSet.next()){
			user2 = new User(rSet.getString(2),rSet.getString(3));
			
		}
		return user2;
	}
	
	
}
