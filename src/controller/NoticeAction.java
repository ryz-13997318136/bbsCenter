package controller;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.management.modelmbean.RequiredModelMBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.mysql.jdbc.PreparedStatement;

import bean.Notice;

public class NoticeAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String action = request.getParameter("action");
		if (action.equals("public")) {
			Notice notice = (Notice) form;
			// 设置notice的日期
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateNowStr = sdf.format(date).toString();
			notice.setNotice_date(dateNowStr);
			// 设置notice的随机id
			String random_id = Long.toString(date.getTime());
			notice.setNotice_id(random_id);

			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "insert into notice(notice_id,notice_content,notice_date,notice_name)values(?,?,?,?)";
				java.sql.PreparedStatement perStmt = null;
				conn.setAutoCommit(false);
				perStmt = conn.prepareStatement(sql);
				int index = 1;
				perStmt.setString(index++, notice.getNotice_id());
				perStmt.setString(index++, notice.getNotice_content());
				perStmt.setString(index++, notice.getNotice_date());
				perStmt.setString(index++, notice.getNotice_name());
				int r = perStmt.executeUpdate();
				perStmt.executeBatch();
				conn.commit();
				System.out.println(r);
				if (perStmt != null) {
					perStmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return mapping.findForward("fail");
			}

			return mapping.findForward("success");

		}
		if (action.equals("check")) {
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "select * from notice";
				java.sql.PreparedStatement perStmt = null;
				conn.setAutoCommit(false);
				perStmt = conn.prepareStatement(sql);

				ResultSet r = perStmt.executeQuery();
				conn.commit();
				System.out.println(r);
				List<Notice> list = new ArrayList<Notice>();
				while (r.next()) {
					Notice notice = new Notice();
					notice.setNotice_id(r.getString("notice_id"));
					notice.setNotice_name(r.getString("notice_name"));
					notice.setNotice_date(r.getString("notice_date"));
					notice.setNotice_content(r.getString("notice_content"));
					list.add(notice);
				}
				request.setAttribute("notice_list", list);
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
		if(action.equals("delete")){
			String notice_id = request.getParameter("notice_id");
			try {
				Connection conn = getDataSource(request).getConnection();
				String sql = "delete from notice where notice_id='"+notice_id+"'";
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
				return mapping.findForward("dshow");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				return mapping.findForward("success");
			}
		}
		return mapping.findForward("success");
	}
}
