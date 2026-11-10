package com.example;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// Vérifier (exemple simplifié)
		if ("admin".equals(username) && "password".equals(password)) {
			HttpSession session = req.getSession();
			session.setAttribute("user", username);
			session.setMaxInactiveInterval(30 * 60); // 30 min
			resp.sendRedirect("/my-app/dashboard");
		} else {
			resp.sendRedirect("/my-app/login?erreur=1");
		}
	}
}