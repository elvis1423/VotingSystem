package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import view.LoginStatus;

public class AuthorizationFilter extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		LoginStatus loginStatus = (LoginStatus) session.getAttribute("loginStatus");
		if (loginStatus == null) {
			String url = req.getServletPath();
			if (!url.equals("/login.jsp")) {
				resp.sendRedirect("http://" + req.getHeader("Host") + req.getContextPath() + "/login.jsp");
				return;
			}
		}
		filterChain.doFilter(req, resp);
	}

	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {  
    } 

}
