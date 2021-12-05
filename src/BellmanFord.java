import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class BellmanFord extends Grafo_ND_P{
	private float[]  Distancia;
	private int[] Ancestral;
	
	// Constroi um grafo apartir do arquivo e executa o algoritmo de Bellman-Ford com a raiz indicada
	public BellmanFord(File fileGrafo, int raiz) throws IOException {
		super(fileGrafo);
		executarBellmanFord(raiz);
	}
	
	// executa o algoritmo de Bellman-Ford com a raiz indicada apartir de um grafo ja existente
	public BellmanFord(Grafo_ND_P parent, int raiz) {
		super(parent.V, parent.E, parent.w);
		executarBellmanFord(raiz);
	}
	
	public void executarBellmanFord(int raiz) {
		// Inicialização
		Distancia = new float[super.V.length];
		for (int i=0; i<Distancia.length; i++) {
			Distancia[i] = Float.MAX_VALUE;   // Utilizando maior valor de float como infinito
		}
		Ancestral = new int[super.V.length];
		for (int i=0; i<Ancestral.length; i++) {
			Ancestral[i] = -1;                // Utiliando -1 para ancestral nulo
		}
		// Adicionando o valor da raiz
		Distancia[raiz-1] = 0;
		for (int i=1; i<=super.qtdVertices()-1; i++) {
			for (int j=0; j<super.qtdArestas(); j++) {
				// Aqui foram colocados 2 pois a verificação é feita através das arestas do grafo
				// o que gerou problemas já que o grafo é não-direcionado, foi adicionado mais um
				// 'if' para garantir que o menor caminho fosse encontrado independente da ordem
				// indicada no array das arestas.
				if (Distancia[super.E[j][1]-1] > Distancia[super.E[j][0]-1] + super.w[j]) {
					Distancia[super.E[j][1]-1] = Distancia[super.E[j][0]-1] + super.w[j];
					Ancestral[super.E[j][1]-1] = super.E[j][0]-1;
				}
				if (Distancia[super.E[j][0]-1] > Distancia[super.E[j][1]-1] + super.w[j]) {
					Distancia[super.E[j][0]-1] = Distancia[super.E[j][1]-1] + super.w[j];
					Ancestral[super.E[j][0]-1] = super.E[j][1]-1;
				} 
			}
		}
		printBellmanFord();
	}
	
	public void printBellmanFord() {
		// Garante o print para cada uma das arestas
		for (int i=0; i<super.V.length; i++) {
			// Pilha com o caminho mínimo
			Stack<Integer> caminho = buscaAncestrais(i);
			// printa o vértice atual
			System.out.print(i+": ");
			// printa toda a pilha do caminho
			while (!caminho.empty()) {
				// Aqui deixamos printando o rótulo do vértice, mas pode-se deixar printando
				// sua posição/número no array
				System.out.print(super.rotulo(caminho.pop()+1)+", ");
				//System.out.print((caminho.pop()+1)+" ");
			}
			// print da distância
			System.out.println(" d="+Distancia[i]);
		}
	}
	
	public Stack<Integer> buscaAncestrais(int vertice) {
		// Aqui monta-se uma pilha indicando o caminho
		Stack<Integer> caminho = new Stack<Integer>();
		// While que verifica se chegamos a raiz, que tem como antecessor -1 (nulo)
		while (vertice != -1) {
			// se adiciona o vértice a pilha e define o novo vértice como o ancestral dele
			caminho.push(vertice);
			vertice = Ancestral[vertice];
		}
		return caminho;
	}
}
