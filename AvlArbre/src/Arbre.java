
public class Arbre {
	// La racine
	private Noeud racine;
	//
	Noeud[] nonEquilibre;
	int nbrNonEquilibre = 0;

	// Les constructeurs
	public Arbre() {
	}

	public Arbre(Noeud n) {
		this.racine = n;
	}

	public Arbre(int n) {
		Noeud noeud = new Noeud(n);
		this.racine = noeud;
	}

	public Arbre(int n, Noeud g, Noeud d) {
		Noeud noeud = new Noeud(n, g, d);
		this.racine = noeud;
	}

	public Noeud getRacine() {
		return racine;
	}

	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

	void rotationSG(Noeud n) {
		Noeud tmp = n;
		n = n.getRight();
		n.setLeft(tmp);
		tmp.setRight(n.getLeft());
	}

	void rotationSD(Noeud n) {
		Noeud tmp = n;
		n = n.getLeft();
		n.setRight(tmp);
		tmp.setLeft(n.getRight());
	}

	/*
	 * public void insertRight(Noeud n, Noeud nv) { n.setRight(nv); }
	 * 
	 * public void insertLeft(Noeud n, Noeud nv) { n.setLeft(nv); }
	 */

	// Calcul de l'equilibre au niveau de chaque noeud
	public void differenceHauteur(Noeud n) {
		if (n != null) {
			if (n.getRight() != null && n.getLeft() != null)
				n.setEquilibre(Hauteur(n.getRight()) - Hauteur(n.getLeft()));
			else if (n.getRight() == null)
				n.setEquilibre((-1) * Hauteur(n.getLeft()));
			else if (n.getLeft() == null)
				n.setEquilibre(Hauteur(n.getRight()));
			else
				n.setEquilibre(0);
			differenceHauteur(n.getRight());
			differenceHauteur(n.getLeft());
		}
	}

	// Inserer sans equilibrage
	public void Inserer(int nv) {
		Noeud noeud = new Noeud(nv);
		if (racine == null) {
			racine = noeud;
		} else {
			Noeud p = racine, q = racine;
			while (p != null) {
				if (nv > p.getValue()) {
					q = p;
					p = p.getRight();
				} else if (nv < p.getValue()) {
					q = p;
					p = p.getLeft();
				} else
					break; // valeur existe deja
			}
			if (p == null) {// valeur n existe pas
				if (nv > q.getValue())
					q.setRight(noeud);
				else
					q.setLeft(noeud);
			}
		}
	}

	public void nonEquilibre(Noeud r) {
		if (r != null) {
			if (r.getEquilibre() == 2 || r.getEquilibre() == -2) {
				nonEquilibre[nbrNonEquilibre] = r;
				nbrNonEquilibre++;

				nonEquilibre(r.getLeft());
				nonEquilibre(r.getRight());
			}
		}
	}

	// Inserer et équilibrer
	public void InsererAvl(int nv) {
		// Insertion
		Inserer(nv);
		// Reinitialiser le tableau des noeuds a equilibrer
		nbrNonEquilibre = 0;
		nonEquilibre = new Noeud[this.nombreNoeuds(this.racine)];

		/// Calcul des differences des hauteurs pour chaque noeud
		differenceHauteur(this.racine);
		// Chercher les noeud non equilibrés
		nonEquilibre(racine);
		// on equilibre le dernier noeud non equilibré
		Noeud eq = nonEquilibre[nbrNonEquilibre];
		// Equilibrage
		if (eq != null) {
			// Rotation simple gauche
			if (eq.getEquilibre() == 2 && eq.getRight().getEquilibre() == 1)
				rotationSG(eq);

			// Rotation simple droit
			else if (eq.getEquilibre() == -2 && eq.getLeft().getEquilibre() == -1)
				rotationSD(eq);

			// Rotation double gauche droite
			if (eq.getEquilibre() == -2 && eq.getLeft().getEquilibre() == 1) {
				rotationSG(eq.getLeft());
				rotationSD(eq);
			}

			// Rotation double droite gauche
			if (eq.getEquilibre() == 2 && eq.getRight().getEquilibre() == -1) {
				rotationSD(eq.getRight());
				rotationSG(eq);
			}
		}
	}

	// Parcours réccursif
	public void infixe(Noeud r) {
		if (r != null) {
			infixe(r.Left);
			System.out.print("  " + r.toString() + "  ");
			infixe(r.Right);
		}
	}

	public void prefixe(Noeud r) {
		if (r != null) {
			System.out.print("  " + r.toString() + "  ");
			prefixe(r.Left);
			prefixe(r.Right);
		}
	}

	public void postfixe(Noeud r) {
		if (r != null) {
			postfixe(r.Left);
			postfixe(r.Right);
			System.out.print("  " + r.toString() + "  ");
		}
	}

	// nbr d'éléments
	public int nombreNoeuds(Noeud r) {
		int nb = 0;
		if (r != null)
			nb = 1;
		if (r.Left != null)
			nb += nombreNoeuds(r.Left);
		if (r.Right != null)
			nb += nombreNoeuds(r.Right);
		return nb;
	}

	// nbr de feuilles
	public int nombreFeuilles(Noeud r) {
		int nb = 0;
		if (r.Left == null && r.Right == null)
			nb = 1;

		if (r.Left != null)
			nb += nombreFeuilles(r.Left);
		if (r.Right != null)
			nb += nombreFeuilles(r.Right);
		return nb;
	}

	public int max(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	// Hauteur de d'un noeud
	public int Hauteur(Noeud r) {
		if (r == null)
			return 0;
		else
			return 1 + max(Hauteur(r.Left), Hauteur(r.Right));
	}

	// Cherche un noeud par sa valeur
	public Noeud Rechercher(Noeud r, int n) {
		if (r == null || r.getValue() == n)
			return r;

		if ( n > r.getValue())
			return Rechercher(r.Right, n);
		
		//if ( n < r.getValue())
		return 	Rechercher(r.Left, n);
	}

	// Cherche un noeud
	public Noeud Rechercher(Noeud r, Noeud n) {
		if (r.getValue() == n.getValue()) {
			// System.out.println("Noeud " + n + " trouvé");
			return n;
		}
		if (r.Right != null)
			Rechercher(r.Right, n);
		if (r.Left != null)
			Rechercher(r.Left, n);
		return null;
	}

}
