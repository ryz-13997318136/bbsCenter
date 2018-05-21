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

import com.mysql.jdbc.Statement;

import bean.Notice;
import bean.Person;
import bean.Topic;

public class TopicAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if (action.equals("check")) {

			session.removeAttribute("a");
			session.removeAttribute("b");
			try {
				Connection conn = getDataSource(request).getConnection();
				
				String sql1 = "select count(*) as total from topic";
				int t = (getTotal(sql1,conn)/5)+1;
				session.setAttribute("totle", t);
				session.setAttribute("n", 1);
				String sql = "select * from topic limit 0,5";
				java.sql.PreparedStatement perStmt = null;
				
				conn.setAutoCommit(false);
				
				perStmt = conn.prepareStatement(sql);				
				
				ResultSet r = perStmt.executeQuery();				
				conn.commit();
				System.out.println(r);
				List<Topic> list = new ArrayList<Topic>();
				while (r.next()) {
					Topic topic = new Topic();
					topic.setUser_id(r.getString("user_id"));
					topic.setUser_name(r.getString("user_name"));
					topic.setTopic_name(r.getString("topic_name"));
					topic.setTopic_id(r.getString("topic_id"));
					topic.setTopic_date(r.getString("topic_date"));
					topic.setTopic_content(r.getString("topic_content"));
					
					list.add(topic);
				}
				request.setAttribute("topic_list", list);
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
			String topic_id = request.getParameter("topic_id");
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "delete from topic where topic_id='"+topic_id+"'";
				java.sql.PreparedStatement perStmt = null;
				conn.setAutoCommit(false);
				perStmt = conn.prepareStatement(sql);

				int r = perStmt.executeUpdate();
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
			int b = 0;
			try{ 
				b = (int) session.getAttribute("b");
				session.setAttribute("n", b==0?b/5+1:b/5);
			if(b<5){
				b=0;
			}else{
			b=b-5;
			}
			}catch(Exception e){
				b=0;
				session.setAttribute("n", b/5+1);
			}
			try {
				Connection conn = getDataSource(request).getConnection();
				List<Topic> list = getList(b,conn);
				request.setAttribute("topic_list", list);
				session.setAttribute("b", b);
				
				return mapping.findForward("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equals("xia")){
			int b = 0;
			try{ 
				b = (int) session.getAttribute("b");
			    b=b+5;
			   }catch(Exception e){
				
			}
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql1 = "select count(*) as total from topic";
				int t = getTotal(sql1,conn);
				if(b>t){
					b=b-5;
				}
				List<?> list = getList(b,conn);
				request.setAttribute("topic_list", list);
				session.setAttribute("b", b);
				session.setAttribute("n", b/5+1);
				return mapping.findForward("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equals("search")){

			String topic_name =null;
			String user_name = null;
			String sql1 = "1=1 and ";
			topic_name = request.getParameter("topic_name");
			user_name = request.getParameter("topic_user");
			if (topic_name == null||topic_name.equals("")) {
				sql1 = sql1 + "1=1 and ";
			} else {
				sql1 = sql1 + "topic_name = '" + topic_name + "'  and ";
			}
			if (user_name == null|| user_name.equals("")) {
				sql1 = sql1 + "1=1";
			} else {
				sql1 = sql1 + "user_name = '" + user_name + "'";
			}
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql2 = "select * from topic where "+sql1+" ";
				conn.setAutoCommit(false);
				java.sql.Statement stmt = conn.createStatement();
				ResultSet r = stmt.executeQuery(sql2);
				conn.commit();		 
				List<Topic> list = new ArrayList<Topic>();
				while (r.next()) {
					Topic topic = new Topic();
					topic.setUser_id(r.getString("user_id"));
					topic.setUser_name(r.getString("user_name"));
					topic.setTopic_name(r.getString("topic_name"));
					topic.setTopic_id(r.getString("topic_id"));
					topic.setTopic_date(r.getString("topic_date"));
					topic.setTopic_content(r.getString("topic_content"));
					
					list.add(topic);
				}
				request.setAttribute("topic_list", list);
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				return mapping.findForward("success");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		
			
			
			
		}
		return mapping.findForward("success");
	}
	public List<Topic> getList(int c,Connection conn){
		String sql1 = "select count(*) as total from topic";
		int t = getTotal(sql1,conn);
		String sql="";
		
	if(t<c){
		sql = "select * from topic limit "+(c-5)+",5 ";
	}
	if(c<0){
		c=0;
		sql = "select * from topic limit "+c+",5 ";
		//session.setAttribute("b", b);
	}else{
		sql = "select * from topic limit "+c+",5 ";
	}
	
			
	
		try {
			
			java.sql.PreparedStatement perStmt = null;
			conn.setAutoCommit(false);			
			perStmt = conn.prepareStatement(sql);			
			ResultSet r = perStmt.executeQuery();
			conn.commit();		 
			List<Topic> list = new ArrayList<Topic>();
			while (r.next()) {
				Topic topic = new Topic();
				topic.setUser_id(r.getString("user_id"));
				topic.setUser_name(r.getString("user_name"));
				topic.setTopic_name(r.getString("topic_name"));
				topic.setTopic_id(r.getString("topic_id"));
				topic.setTopic_date(r.getString("topic_date"));
				topic.setTopic_content(r.getString("topic_content"));
				
				list.add(topic);
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
