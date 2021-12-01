import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		File file = new File("C:\\Users\\gabri\\Downloads\\fln_pequena.net");
		
		Grafo_ND_P grafo = new Grafo_ND_P();
		
		grafo.ler(file);
		
		//System.out.println(grafo.V[1]);
		System.out.println(grafo.qtdVertices());
		System.out.println(grafo.qtdArestas());
		
		
		
		System.out.println("Qual o aresta que você quer saber o peso?");
		int u = reader.nextInt();
		int v = reader.nextInt();
		System.out.println(grafo.rotulo(v));
		System.out.println(grafo.peso(u, v));
		//System.out.println("Qual o Vertice que você quer saber se tem aresta?");
		//u = reader.nextInt();
		//v = reader.nextInt();
		System.out.println(grafo.haAresta(u, v));
		//System.out.println("Qual o Vertice que você quer saber os vizinhos?");
		//v = reader.nextInt();
		System.out.println(grafo.vizinhos(v));
		//System.out.println("Qual o Vertice que você quer saber o grau?");
		//v = reader.nextInt();
		System.out.println(grafo.grau(v));
		
		int existe = grafo.existeCicloEuleriano(grafo);
		System.out.println(existe);
		if (existe == 1) {
			System.out.println(grafo.caminhoCicloEuleriano(grafo));
		}
		
	}

}
