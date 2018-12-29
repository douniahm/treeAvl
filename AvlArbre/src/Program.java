import java.util.Scanner;

/**
 * Dounia Ait Hammi
 * Elève ingénieure en Génie du logiciel et systèmes informatiques distribués
 *  GLSID 2
 */

public class Program {
	public static void main(String[] args) {
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		int i = 0; // l'élément à inserer
		Arbre ar = new Arbre();
		System.out.println("-------Insertion---------");

		System.out.println("enter les nombres a inserer ");
		System.out.println("taper -1 pour arreter");
		i = sc.nextInt();
		sc.nextLine();
		while (i != -1) {

			ar.InsererAvl(i);
			i = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("-------Fin d'insertion---------");
		System.out.println("-------Affichage---------");
	

		System.out.println("\nParcours infixé");
		ar.infixe(ar.getRacine());

		System.out.println("\nParcours postefixe");
		ar.postfixe(ar.getRacine());

		System.out.println("\nParcours prefixé");
		ar.prefixe(ar.getRacine());
		
		System.out.println("\nVoulez-vous chercher un nombre? si oui taper 1");
		choix = sc.nextInt();

		if(choix==1) {
			System.out.println("Entrer le nombre à chercher");
			i = sc.nextInt();
			Noeud trouve = ar.Rechercher(ar.getRacine(), i);
			if(trouve==null) {
				System.out.println("Nombre introuvable");
			}
			else System.out.println("Nombre: "+ trouve.getValue() +" a été trouvé dans l'arbre");
			
		}
		 System.out.println("Fin du programme");
		
	}

}
