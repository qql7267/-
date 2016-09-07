package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnFactory;
import vo.Dept;
import vo.Emp;

public class EmpDAO {

	public List<Emp> getAll() throws SQLException{
		List<Emp> list = new ArrayList<Emp>();
		Connection connection = ConnFactory.getConn();
		String sql = "select e.*,d.* from emp e,dept d where e.deptno = d.deptno";
		PreparedStatement pStatement=connection.prepareStatement(sql);
		ResultSet rs = pStatement.executeQuery();
		while(rs.next()){
			Dept dept = new Dept(rs.getInt(9),rs.getString(10),rs.getString(11));
			Emp emp = new Emp(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),
					rs.getFloat(6),rs.getFloat(7),dept);
			list.add(emp);
		}
		return list;
		
	}
	
	public List<Dept> getAllDept() throws SQLException{
		List<Dept> list = new ArrayList<Dept>();
		Connection connection = ConnFactory.getConn();
		String sql = "select d.* from dept d";
		PreparedStatement pStatement=connection.prepareStatement(sql);
		ResultSet rs = pStatement.executeQuery();
		while(rs.next()){
			Dept dept = new Dept(rs.getInt(1),rs.getString(2),rs.getString(3));
			list.add(dept);
		}
		return list;
	}
	public void addEmp(Emp emp) throws SQLException{
		Connection connection = ConnFactory.getConn();
		String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
		PreparedStatement pStatement=connection.prepareStatement(sql);
		pStatement.setInt(1, emp.getEmpno());
		pStatement.setString(2, emp.getEname());
		pStatement.setString(3, emp.getJob());
		pStatement.setInt(4, emp.getMgr());
		pStatement.setDate(5, emp.getHireDate());
		pStatement.setFloat(6, emp.getSal());
		pStatement.setFloat(7, emp.getComm());
		pStatement.setInt(8, emp.getDept().getDeptno());
		
		pStatement.executeUpdate();
		
	}
	
	public void deleteEmp(int empno) throws SQLException{
		Connection connection = ConnFactory.getConn();
		String sql = "delete from emp where empno = ?";
		PreparedStatement pStatement=connection.prepareStatement(sql);
		pStatement.setInt(1,empno);
		pStatement.executeUpdate();
	}
	
	public Emp getEmpById(int empno) throws SQLException{
		Connection connection = ConnFactory.getConn();
		String sql = "select e.*,d.* from emp e,dept d where e.deptno = d.deptno and empno = ?";
		
		PreparedStatement pStatement=connection.prepareStatement(sql);
		pStatement.setInt(1, empno);
		ResultSet rs = pStatement.executeQuery();
		if(rs.next()){
			Dept dept = new Dept(rs.getInt(9),rs.getString(10),rs.getString(11));
			Emp emp = new Emp(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),
					rs.getFloat(6),rs.getFloat(7),dept);
			return emp;
		}
		return null;
	}
	
	public static void main(String args[]) throws SQLException{
		List<Dept> list = new EmpDAO().getAllDept();
		System.out.println(list);
	}
}
