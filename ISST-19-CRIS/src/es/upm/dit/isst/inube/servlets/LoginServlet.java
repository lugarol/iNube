package es.upm.dit.isst.inube.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@WebServlet({ "/LoginServlet", "/" })
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" LoginServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// redirigir a LoginView.jsp
		getServletContext().getRequestDispatcher("/LoginView.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" LoginServlet > doPost ");
		System.out.println(" ------------------------------------ ");
		
		// recoger parámetros formulario
		String usuario = req.getParameter("usuario");
		String password = req.getParameter("password");
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(usuario, password);
			//token.setRememberMe(true);
			try {
				currentUser.login(token);
				if (currentUser.hasRole("admin")) {												// si el usuario es admin, redirigir a AdminServlet
					resp.sendRedirect(req.getContextPath() + "/AdminServlet");
				} else if (currentUser.hasRole("comerciante")) {								// si el usuario es comerciante, redirigir a MenuComercianteServlet
					resp.sendRedirect(req.getContextPath() + "/MenuComercianteServlet");
				} else {																		// redirigir a LoginServlet
					resp.sendRedirect(req.getContextPath() + "/LoginServlet");
				}
			} catch(Exception e) {																// en caso de error, redirigir a LoginServlet
				resp.sendRedirect(req.getContextPath() + "/LoginServlet");
			}
		} else {																				// en caso de error, redirigir a LoginServlet
			resp.sendRedirect(req.getContextPath() + "/LoginServlet");
		}
	
	}

}
