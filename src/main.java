import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		File file = new File("C:\\Users\\gabri\\Downloads\\ContemCicloEuleriano.net");
		
		Grafo_ND_P grafo = new Grafo_ND_P();
		
		
		grafo = grafo.ler(file);
		
		System.out.println(grafo.V[1]);
		System.out.println(grafo.qtdVertices());
		System.out.println(grafo.qtdArestas());
		
		System.out.println(grafo.E[0][0]);
		System.out.println(grafo.E[0][1]);
		
		//System.out.println(grafo.rotulo(grafo.V));
		
		System.out.println("Qual o aresta que você quer saber o peso?");
		//int u = reader.nextInt();
		int v = reader.nextInt();
		//System.out.println(grafo.peso(u, v));
		//System.out.println(grafo.haAresta(u, v));
		System.out.println(grafo.vizinhos(v,grafo));
		System.out.println(grafo.grau(v));
		
	}

}
