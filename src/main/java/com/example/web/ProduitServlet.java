package com.example.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.model.Produit;
import com.example.model.ProduitDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitDAO dao;

	@Override
	public void init() {
		dao = (ProduitDAO) getServletContext().getAttribute("produitDAO");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("new".equals(action)) {
			req.setAttribute("produit", new Produit());
			forwardToForm(req, resp);
			return;
		}
		if ("edit".equals(action)) {
			Long id = parseId(req.getParameter("id"));
			Produit produit = dao.findById(id);
			if (produit == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			req.setAttribute("produit", produit);
			forwardToForm(req, resp);
			return;
		}
		req.setAttribute("produits", dao.findAll());
		req.getRequestDispatcher("/WEB-INF/views/produits/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("delete".equals(action)) {
			Long id = parseId(req.getParameter("id"));
			if (id == null) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			dao.delete(id);
			req.getSession().setAttribute("flash", "Produit supprimé.");
			resp.sendRedirect(req.getContextPath() + "/produits");
			return;
		}

		Map<String, String> erreurs = new HashMap<>();
		String nom = trim(req.getParameter("nom"));
		String description = trim(req.getParameter("description"));
		String prixText = trim(req.getParameter("prix"));
		String stockText = trim(req.getParameter("stock"));
		Long id = parseId(req.getParameter("id"));

		if (nom == null || nom.length() < 2 || nom.length() > 50) {
			erreurs.put("nom", "Le nom doit contenir entre 2 et 50 caractères.");
		}
		if (description != null && description.length() > 255) {
			erreurs.put("description", "La description ne doit pas dépasser 255 caractères.");
		}

		BigDecimal prix = null;
		try {
			if (prixText == null) {
				throw new NumberFormatException();
			}
			prix = new BigDecimal(prixText);
			if (prix.signum() <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			erreurs.put("prix", "Le prix doit être un nombre strictement positif.");
		}

		int stock = 0;
		if (stockText != null) {
			try {
				stock = Integer.parseInt(stockText);
				if (stock < 0) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException ex) {
				erreurs.put("stock", "Le stock doit être un entier positif ou nul.");
			}
		}

		Produit produit = new Produit(id, nom, description, prix, stock);
		if (!erreurs.isEmpty()) {
			req.setAttribute("erreurs", erreurs);
			req.setAttribute("produit", produit);
			forwardToForm(req, resp);
			return;
		}

		dao.save(produit);
		req.getSession().setAttribute("flash", id == null ? "Produit créé." : "Produit modifié.");
		resp.sendRedirect(req.getContextPath() + "/produits");
	}

	private void forwardToForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp").forward(req, resp);
	}

	private static String trim(String value) {
		if (value == null) {
			return null;
		}
		String result = value.trim();
		return result.isEmpty() ? null : result;
	}

	private static Long parseId(String value) {
		try {
			return value == null ? null : Long.valueOf(value);
		} catch (NumberFormatException ex) {
			return null;
		}
	}
}
