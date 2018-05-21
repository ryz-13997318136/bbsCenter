package controller;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import  org.apache.struts.action.Action;
import  org.apache.struts.action.ActionForward;
import  org.apache.struts.action.ActionForm;
import  org.apache.struts.action.ActionMapping;

import bean.User;

public class ComAction extends Action{
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
//		User user = (User) form;
//		if((isTokenValid(request, true))){
//		if(user.name.equals("123")){
//		request.setAttribute("name", user.name);
//		return mapping.findForward("success");
//		}
//		else{
//			return mapping.findForward("fail");
//		}
//		}else{
//			saveToken(request);
//			return mapping.findForward("fail");
//		}
//		
		//////////-----------------------------------
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		if(action.equals("login")){
		User user = (User) form;
			Connection conn;
			try {
				conn = getDataSource(request).getConnection();
				String sql = "select * from admin where name='"+user.name+"'and password='"+user.password+"'";
		        java.sql.PreparedStatement perStmt = null;
				conn.setAutoCommit(false);
				perStmt = conn.prepareStatement(sql);
				ResultSet r = perStmt.executeQuery();
				conn.commit();
				if(r.next()){
					session.setAttribute("admin_name", user.name);
					session.setAttribute("admin_pass_word", user.password);
					r.close();
					perStmt.close();
					conn.close();
					return mapping.findForward("success");
				}else{
					request.setAttribute("tip", "登录名或密码错误！");
					return mapping.findForward("fail");
				}				
			   }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return mapping.findForward("fail");
			    }
		}
			if(action.equals("check")){
				Connection conn;
				try {
					conn = getDataSource(request).getConnection();
					String sql = "select * from admin where name='"+session.getAttribute("admin_name")+"'and password='"+session.getAttribute("admin_pass_word")+"'";
			        java.sql.PreparedStatement perStmt = null;
					conn.setAutoCommit(false);
					perStmt = conn.prepareStatement(sql);
					ResultSet r = perStmt.executeQuery();
					conn.commit();
					List list = new LinkedList();
					if(r.next()){
						list.add(r.getString("name"));
						list.add(r.getString("password"));
						list.add(r.getString("age"));
						list.add(r.getString("sex"));
						session.setAttribute("admin_id", r.getString("id"));
					}
					request.setAttribute("list", list);
						r.close();
						perStmt.close();
						conn.close();
						
						return mapping.findForward("check");
									
				   }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return mapping.findForward("fail");
				    }
				
			}
			if(action.equals("save")){
				String name = request.getParameter("user_name");
				String password = request.getParameter("user_password");
				String age = request.getParameter("user_age");
				String sex = request.getParameter("user_sex");
				Connection conn;
				try {
					conn = getDataSource(request).getConnection();
					String sql = "update admin set name='"+name+"', password='"+password+"', sex='"+sex+"' ,age='"+age+"' where id='"+session.getAttribute("admin_id")+"'";
			        java.sql.PreparedStatement perStmt = null;
					conn.setAutoCommit(false);
					perStmt = conn.prepareStatement(sql);
					int r = perStmt.executeUpdate();
					conn.commit();
					if(r==1){
						//更新成功
						
						session.setAttribute("admin_name", name);
						session.setAttribute("admin_pass_word", password);
						perStmt.close();
						conn.close();
						
			
							return mapping.findForward("check1");	
					}
					else{
						//更新失败，返回当前页
						perStmt.close();
						conn.close();
						return mapping.findForward("check");
					}
					
				   }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return mapping.findForward("fail");
				    }
			}
			if(action.equals("add")){
				String name = request.getParameter("name");
				String password = request.getParameter("password_two");
				String age = request.getParameter("age");
				String sex = request.getParameter("sex");
				Connection conn;
				Date date= new Date();
				try {
					conn = getDataSource(request).getConnection();
					String sql = "insert admin(id,name,sex,age,password) values('"+date.getTime()+"','"+name+"','"+sex+"','"+age+"','"+password+"')";
			        java.sql.PreparedStatement perStmt = null;
					conn.setAutoCommit(false);
					perStmt = conn.prepareStatement(sql);
					int r = perStmt.executeUpdate();
					conn.commit();
					String tip="";
					if(r==1){
				    //插入成功
						perStmt.close();
						conn.close();
						tip="分配管理员成功！";
						request.setAttribute("tip", tip);
						return mapping.findForward("check2");	
					}
					
					
				   }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					String tip="该管理员已存在，导致分配管理员失败，请更换名称。";
					request.setAttribute("tip", tip);
					return mapping.findForward("check2");
				    }
			}
			return mapping.findForward("success");
		}

}

