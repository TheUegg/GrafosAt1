import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		System.out.println("============================================================");
		System.out.println("Exercício 1 | grafo: email.net");
		File emailFile = new File("./grafos/email.net");
		Grafo_ND_P email = new Grafo_ND_P(emailFile);
		System.out.println("Vértices: "+email.qtdVertices());
		System.out.println("Arestas: "+email.qtdArestas());
		System.out.println("Grau vértice 500: "+email.grau(500));
		System.out.println("Rótulo vértice 500: "+email.rotulo(500));
		System.out.print("Vizinhos vértice 500: [");
		List<Integer> vizinhos = email.vizinhos(500);
		for (int i=0; i<vizinhos.size(); i++) {
			System.out.print(" "+vizinhos.get(i));
		}
		System.out.println(" ]");
		System.out.println("Há aresta 500 61: "+email.haAresta(500, 61));
		System.out.println("Há aresta 500 62: "+email.haAresta(500, 62));
		System.out.println("Peso aresta 500 61: "+email.peso(500, 61));
		System.out.println();
		System.out.println("============================================================");
		System.out.println("Exercício 2 | grafo: karate.net raiz: 10");
		File karateFile = new File("./grafos/karate.net");
		BuscaEmLargura karateBL = new BuscaEmLargura(karateFile, 10);
		System.out.println("============================================================");
		System.out.println("Exercício 3.1 | grafo: ContemCicloEuleriano.net");
		File CCEfile = new File("./grafos/ContemCicloEuleriano.net");
		Grafo_ND_P CCE = new Grafo_ND_P(CCEfile);
		System.out.println("Contem ciclo Euleriano: "+CCE.existeCicloEuleriano());
		System.out.print("Caminho ciclo: [");
		List<Integer> ciclo = CCE.caminhoCicloEuleriano();
		for (int i=0; i<ciclo.size(); i++) {
			System.out.print(" "+ciclo.get(i));
		}
		System.out.println(" ]");
		System.out.println("Exercício 3.2 | grafo: SemCicloEuleriano.net");
		File SCEfile = new File("./grafos/SemCicloEuleriano.net");
		Grafo_ND_P SCE = new Grafo_ND_P(SCEfile);
		System.out.println("Contem ciclo Euleriano: "+SCE.existeCicloEuleriano());
		System.out.println();
		System.out.println("============================================================");
		System.out.println("Exercício 4 | grafo: fln_pequena.net raiz: 4");
		File flnPBFFile = new File("./grafos/fln_pequena.net");
		BellmanFord flnPBF = new BellmanFord(flnPBFFile, 4);
		System.out.println();
		System.out.println("============================================================");
	}

}
