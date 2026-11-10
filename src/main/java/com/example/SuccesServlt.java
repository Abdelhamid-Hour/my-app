package com.example;

import java.io.IOException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/succes")
public class SuccesServlt extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		String utilisateur = (String) session.getAttribute("utilisateur");

		if (utilisateur == null) {
			resp.sendRedirect("/my-app/inscription");
			return;
		}

		resp.setContentType("text/html");
		resp.getWriter().println("<h1>Bienvenue " + utilisateur + "!</h1>");
	}
}