// A implementação utiliza divisão e conquista para preencher a tabela. Inicialmente a tabela é dividida em
//.. quatro quadrantes, então é visto em qual quadrante o buraco se encontra. O quadrante com o buraco é o
//.. primeiro a ser preenchido. Após o preenchimento do quadrante com o buraco, é inserida uma peça no meio
//.. da tabela, servindo como "buraco" para os outros quadrantes, que então são preenchidos recursivamente.

import java.util.*;

public class Trimino{
	
	private int[][] t;
	private int n;
	
	public Trimino(int tam, int x, int y) {

		int ta = 1;
		while (ta < tam) ta*=2;

		t = new int[ta][ta];
		n = 1;
		
		for (int i=0; i<ta; i++) {
			for (int j=0; j<ta; j++) {
				t[i][j] = 0;
			}
		}

		t[x][y] = -1;
	}
	
	public int getn()){
		return this.n;
	}
	
	public void jogoRec(int tam, int topx, int topy) {
		
		if (tam == 2) {
		
			for (int i=0; i<tam; i++) 
				for (int j=0; j<tam; j++)
					if (t[topx+i][topy+j] == 0)
						t[topx+i][topy+j] = n;
		
			n++;
		}
		
		else {
			
			int salvaX=topx, salvaY=topy;
			
			for (int x=topx; x<topx+tam; x++) 
				for (int y=topy; y<topy+tam; y++)
					if (t[x][y] != 0) {
						salvaX = x;
						salvaY = y;
					}
				
			if (salvaX < topx + tam/2 && salvaY < topy + tam/2) {
				
				jogoRec(tam/2, topx, topy);
				
				t[topx+tam/2][topy+tam/2-1] = n;
				t[topx+tam/2][topy+tam/2] = n;
				t[topx+tam/2-1][topy+tam/2] = n;
				
				n++;
				
				jogoRec(tam/2, topx, topy+tam/2);
				jogoRec(tam/2, topx+tam/2, topy);
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
				
			}
			
			else if (salvaX < topx + tam/2 && salvaY >= topy + tam/2) {
				
				jogoRec(tam/2, topx, topy+tam/2);
				
				t[topx+tam/2][topy+tam/2-1] = n;
				t[topx+tam/2][topy+tam/2] = n;
				t[topx+tam/2-1][topy+tam/2-1] = n;
				
				n++;
				
				jogoRec(tam/2, topx, topy);
				jogoRec(tam/2, topx+tam/2, topy);
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
				
			}
			
			else if (salvaX >= topx + tam/2 && salvaY < topy + tam/2) {
				
				jogoRec(tam/2, topx+tam/2, topy);
				
				t[topx+tam/2-1][topy+tam/2] = n;
				t[topx+tam/2][topy+tam/2] = n;
				t[topx+tam/2-1][topy+tam/2-1] = n;
				
				n++;
				
				jogoRec(tam/2, topx, topy);
				jogoRec(tam/2, topx, topy+tam/2);
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
			}
			else {
				
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
				
				t[topx+tam/2-1][topy+tam/2] = n;
				t[topx+tam/2][topy+tam/2-1] = n;
				t[topx+tam/2-1][topy+tam/2-1] = n;
				
				n++;
				
				jogoRec(tam/2, topx+tam/2, topy);
				jogoRec(tam/2, topx, topy+tam/2);
				jogoRec(tam/2, topx, topy);
			}
			
		} 
	} 
	
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		System.out.println("Qual o tamanho da tabela? Deve ser potência de 2");
		int tam = stdin.nextInt();
		
		System.out.println("Onde está o buraco? (coordenadas x e y, separadas por espaço)");
		int x = stdin.nextInt();
		int y = stdin.nextInt();
		
		Trimino objeto = new Trimino(tam, x, y);
		objeto.jogoRec(t.length, 0, 0);
		
		System.out.println("Tabela final com os triminós:\n");

		for (int i=0; i<t.length; i++) {
			for (int j=0; j<t[i].length; j++)
				System.out.print(t[i][j] + "\t");
			System.out.println();
		}

		System.out.printf("Quantidade de peças utilizadas: "+(objeto.getn()-1));
		
	}
}
