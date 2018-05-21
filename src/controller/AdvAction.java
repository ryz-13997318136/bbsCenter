package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import bean.Notice;
import bean.Person;
import bean.advertisement;

public class AdvAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action.equals("add")) {

			SmartUpload su = new SmartUpload();

			su.initialize(this.getServlet().getServletConfig(), request,
					response);

			try {
				su.upload();

				int count = su.save("/image");

				String address = (String) su.getRequest().getParameter(
						"address");
				// String imageid=(String)su.getRequest().getParameter("file");

				File file = su.getFiles().getFile(0);

				file.saveAs("/image/" + file.getFileName());

				file.saveAs("/image/" + file.getFileName(), su.SAVE_VIRTUAL);
				String url = file.getFileName();

				Connection conn;
				Date date = new Date();
				try {
					conn = getDataSource(request).getConnection();
					String sql = "insert advertisement(id,address,image_url) values('"
							+ date.getTime()
							+ "','"
							+ address
							+ "','"
							+ url
							+ "')";
					java.sql.PreparedStatement perStmt = null;
					conn.setAutoCommit(false);
					perStmt = conn.prepareStatement(sql);
					int r = perStmt.executeUpdate();
					conn.commit();
					String tip = "";
					if (r == 1) {
						// 插入成功
						perStmt.close();
						conn.close();
						tip = "发布成功！";
						request.setAttribute("tip", tip);
						return mapping.findForward("success");
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					String tip = "该管理员已存在，导致分配管理员失败，请更换名称。";
					request.setAttribute("tip", tip);
					return mapping.findForward("success");
				}

			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		if(action.equals("check")){
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "select * from advertisement";
				java.sql.PreparedStatement perStmt = null;
				conn.setAutoCommit(false);
				perStmt = conn.prepareStatement(sql);

				ResultSet r = perStmt.executeQuery();
				conn.commit();
				List<advertisement> list = new ArrayList<advertisement>();
				while (r.next()) {
					advertisement adv = new advertisement();
					adv.setId(r.getString("id"));
					adv.setAddress(r.getString("address"));
					adv.setImageUrl(r.getString("image_url"));
					list.add(adv);
				}
				request.setAttribute("adv_list", list);
				if (perStmt != null) {
					perStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				return mapping.findForward("showAdv");

		
	          }catch(Exception e){
	        	  
	          }
	
	
		}
		if(action.equals("delete")){
			String adv_id = request.getParameter("adv_id");
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "delete from advertisement where id='"+adv_id+"'";
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
				return mapping.findForward("showagain");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return mapping.findForward("success");
			}
		}
		return mapping.findForward("success");
				
   }
}