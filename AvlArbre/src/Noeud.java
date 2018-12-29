
public class Noeud {
	int value;
	Noeud Left, Right;
	Noeud Parent;
	int equilibre; //difference de hauteur

	public Noeud(int value, Noeud left, Noeud right) {
		this.value = value;
		this.Left = left;
		this.Right = right;
	}

	public Noeud(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Noeud getLeft() {
		return Left;
	}

	public void setLeft(Noeud left) {
		Left = left;
	}

	public Noeud getRight() {
		return Right;
	}

	public void setRight(Noeud right) {
		Right = right;
	}
	
	public int getEquilibre() {
		return equilibre;
	}

	public void setEquilibre(int equilibre) {
		this.equilibre = equilibre;
	}


	public String toString() {
		return "" + this.value;
	}

}
