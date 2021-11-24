import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		Scanner reader = new Scanner(System.in);
		File file = new File("C:\\Users\\gabri\\Downloads\\fln_pequena.net");
		
		Grafo_ND_P grafo = new Grafo_ND_P();
		
		
		grafo = grafo.ler(file);
		
		//System.out.println(grafo.V[1]);
		System.out.println(grafo.qtdVertices(grafo));
		System.out.println(grafo.qtdArestas(grafo));
		
		
		System.out.println(grafo.rotulo(grafo.V));
		
		System.out.println("Qual o aresta que você quer saber o peso?");
		int u = reader.nextInt();
		int v = reader.nextInt();
		System.out.println(grafo.peso(u, v,grafo));
		System.out.println(grafo.haAresta(u, v,grafo));
		System.out.println(grafo.vizinhos(v,grafo));
		System.out.println(grafo.grau(v,grafo));
		
	}

}
