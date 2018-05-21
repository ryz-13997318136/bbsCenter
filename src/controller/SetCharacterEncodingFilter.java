package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SetCharacterEncodingFilter implements Filter {

private String encoding;
private FilterConfig filterConfig = null;

public void destroy() {
  this.encoding = null;
  this.filterConfig = null;

}

public void doFilter(ServletRequest request, ServletResponse response,
  FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	String url = req.getRequestURI();
//	if(url.equals("/study/advertisement.do")){
//		chain.doFilter(request, response);
//	}
//	else{
  request.setCharacterEncoding(this.encoding);
	//}
  
  chain.doFilter(request, response);
}

public void init(FilterConfig filterConfig) throws ServletException {
  this.encoding = filterConfig.getInitParameter("encoding");
}

}

