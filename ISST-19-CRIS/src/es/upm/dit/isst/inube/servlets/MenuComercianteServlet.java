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

@WebServlet("/MenuComercianteServlet")
public class MenuComercianteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" MenuComercianteServlet > doGet ");
		System.out.println(" ------------------------------------ ");
		
		// redirigir a MenuComercianteView.jsp
		getServletContext().getRequestDispatcher("/MenuComercianteView.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println(" ------------------------------------ ");
		System.out.println(" MenuComercianteServlet > doPost ");
		System.out.println(" ------------------------------------ ");
	
	}

}
