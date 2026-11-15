package com.example.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProduitDAO {
	private final Map<Long, Produit> produits = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong();

	public ProduitDAO() {
		save(new Produit(null, "Laptop", "Dell XPS 13", new BigDecimal("9500.00"), 5));
		save(new Produit(null, "Souris", "Logitech MX Master", new BigDecimal("650.00"), 20));
	}

	public List<Produit> findAll() {
		List<Produit> resultat = new ArrayList<>(produits.values());
		Collections.sort(resultat, new Comparator<Produit>() {
			@Override
			public int compare(Produit premier, Produit second) {
				return premier.getId().compareTo(second.getId());
			}
		});
		return resultat;
	}

	public Produit findById(Long id) {
		return id == null ? null : produits.get(id);
	}

	public synchronized void save(Produit produit) {
		if (produit.getId() == null) {
			produit.setId(sequence.incrementAndGet());
		} else if (produit.getId() > sequence.get()) {
			sequence.set(produit.getId());
		}
		produits.put(produit.getId(), produit);
	}

	public void delete(Long id) {
		if (id != null) {
			produits.remove(id);
		}
	}
}
