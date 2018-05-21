package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bean.Notice;
import bean.Person;
import bean.Topic;

public class PersonAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if (action.equals("check")) {

			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "select * from user limit 0,10";
				java.sql.PreparedStatement perStmt = null;
				conn.setAutoCommit(false);
				perStmt = conn.prepareStatement(sql);

				ResultSet r = perStmt.executeQuery();
				conn.commit();
				System.out.println(r);
				List<Person> list = new ArrayList<Person>();
				while (r.next()) {
					Person person = new Person();
					person.setUser_id(r.getString("user_id"));
					person.setUser_password(r.getString("user_password"));
					person.setUser_name(r.getString("user_name"));
					person.setUser_age(r.getString("user_age"));
					person.setUser_sex(r.getString("user_sex"));
					person.setUser_phonenumber(r.getString("user_phonenumber"));
					person.setUser_signature(r.getString("user_signature"));
					person.setUser_imageId(r.getString("user_imageId"));
					list.add(person);
				}
				request.setAttribute("person_list", list);
				if (perStmt != null) {
					perStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				return mapping.findForward("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return mapping.findForward("success");
			}

		}
		if(action.equals("delete")){
			String person_id = request.getParameter("person_id");
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "delete from user where user_id='"+person_id+"'";
				String sql1 = "delete from topic where user_id='"+person_id+"'";
				java.sql.PreparedStatement perStmt = null;
				conn.setAutoCommit(false);
				perStmt = conn.prepareStatement(sql);
				int r = perStmt.executeUpdate();
				conn.commit();
				perStmt = conn.prepareStatement(sql1);
				int r1 = perStmt.executeUpdate();
				conn.commit();
				System.out.println(r);
				
				if (perStmt != null) {
					perStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				return mapping.findForward("show");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return mapping.findForward("success");
			}
		}
		
		
		if(action.equals("shang")){
			int a = 0;
			try{ 
				a = (int) session.getAttribute("a");
			if(a<5){
				a=0;
			}else{
			a=a-5;
			}
			}catch(Exception e){
				a=0;
			}
			try {
				Connection conn = getDataSource(request).getConnection();
				List<Person> list = getList(a,conn);
				request.setAttribute("person_list", list);
				session.setAttribute("a", a);
				return mapping.findForward("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equals("xia")){
			int a = 0;
			try{ 
				a = (int) session.getAttribute("a");
			    a=a+5;
			   }catch(Exception e){
				
			}
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql1 = "select count(*) as total from user";
				int t = getTotal(sql1,conn);
				if(a>t){
					a=a-5;
				}
				List<?> list = getList(a,conn);
				request.setAttribute("person_list", list);
				session.setAttribute("a", a);
				return mapping.findForward("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equals("search")){
			String name =null;
			String sex = null;
			String age = null;
			String mobile = null;
			String sql1 = "1=1 and ";
			name = request.getParameter("name");
			age = request.getParameter("age");
			mobile = request.getParameter("mobile");
			sex = request.getParameter("sex");
			if (name == null||name.equals("")) {
				sql1 = sql1 + "1=1 and ";
			} else {
				sql1 = sql1 + "user_name = '" + name + "'  and ";
			}
			if (age == null|| age.equals("")) {
				sql1 = sql1 + "1=1 and ";
			} else {
				sql1 = sql1 + "user_age = '" + age + "'  and ";
			}
			if (mobile == null||mobile =="") {
				sql1 = sql1 + "1=1 and ";
			} else {
				sql1 = sql1 + "user_phonenumber = '" + mobile + "' and ";
			}
			if (sex == null||sex=="") {
				sql1 = sql1 + "1=1 ";
			} else {
				sql1 = sql1 + "user_sex = '" + sex + "'";
			}
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql2 = "select * from user where "+sql1+" ";
				conn.setAutoCommit(false);
				java.sql.Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql2);
				conn.commit();		 
				List<Person> list = new ArrayList<Person>();
				while (rs.next()) {
					Person person = new Person();
					person.setUser_id(rs.getString("user_id"));
					person.setUser_password(rs.getString("user_password"));
					person.setUser_name(rs.getString("user_name"));
					person.setUser_age(rs.getString("user_age"));
					person.setUser_sex(rs.getString("user_sex"));
					person.setUser_phonenumber(rs.getString("user_phonenumber"));
					person.setUser_signature(rs.getString("user_signature"));
					person.setUser_imageId(rs.getString("user_imageId"));
					list.add(person);
				}
			
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				request.setAttribute("person_list", list);
				return mapping.findForward("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		return mapping.findForward("success");
	}
	public List<Person> getList(int c,Connection conn){
		String sql1 = "select count(*) as total from user";
		int t = getTotal(sql1,conn);
		String sql="";
		
	if(t<c){
		sql = "select * from user limit "+(c-5)+",5 ";
	}
	if(c<0){
		c=0;
		sql = "select * from user limit "+c+",5 ";
		//session.setAttribute("b", b);
	}else{
		sql = "select * from user limit "+c+",5 ";
	}
	
			
	
		try {
			
			java.sql.PreparedStatement perStmt = null;
			conn.setAutoCommit(false);			
			perStmt = conn.prepareStatement(sql);			
			ResultSet r = perStmt.executeQuery();
			conn.commit();		 
			List<Person> list = new ArrayList<Person>();
			while (r.next()) {
				Person person = new Person();
				person.setUser_id(r.getString("user_id"));
				person.setUser_password(r.getString("user_password"));
				person.setUser_name(r.getString("user_name"));
				person.setUser_age(r.getString("user_age"));
				person.setUser_sex(r.getString("user_sex"));
				person.setUser_phonenumber(r.getString("user_phonenumber"));
				person.setUser_signature(r.getString("user_signature"));
				person.setUser_imageId(r.getString("user_imageId"));
				list.add(person);
			}
		
			if (perStmt != null) {
				perStmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		return list;
		}catch(SQLException e){
			
		}
		return null;
	}
	//返回数据库中的总数
	public int getTotal(String sql,Connection conn){
		@SuppressWarnings("unused")
		java.sql.PreparedStatement perStmt = null;		
		try {
			conn.setAutoCommit(false);
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//perStmt = conn1.prepareStatement(sql);		
			//ResultSet r1 = perStmt.executeQuery();
			//conn.commit();
			String r="";
			while(rs.next()){
			 r = rs.getString("total");
			}
			return Integer.parseInt(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
		
	}
				
}
