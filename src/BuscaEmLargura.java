import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BuscaEmLargura extends Grafo_ND_P{
	private int[] Distancia;
	private String[] Ancestral;
	
	
	// Constroi um grafo apartir do arquivo e executa a busca em largura com a raiz indicada
	public BuscaEmLargura(File fileGrafo, int raiz) throws IOException {
		super(fileGrafo);
		executarBusca(raiz);
	}
	
	// executa a busca em largura com a raiz indicada apartir de um grafo ja existente
	public BuscaEmLargura(Grafo_ND_P parent, int raiz) {
		super(parent.V, parent.E, parent.w);
		executarBusca(raiz);
	}
	
	public void executarBusca(int raiz) {
		//Definindo estruturas que serão utilizadas
		Distancia = new int[super.V.length];
		for (int i = 0; i < Distancia.length; i++) {
			Distancia[i] = -1; 							    //Usando -1 como representação de distância infinita
		}
		Ancestral = new String[super.V.length];             // não precisa ser inicializada pois java inicia como vazi0
		boolean[] conhecido = new boolean[super.V.length];  // não precisa ser inicializada pois java inicia como false
				
		// Definindo valores da raiz
		conhecido[raiz-1] = true;
		Distancia[raiz-1] = 0;
		
		// Cria lista de vértices a visitar e adiciona raiz
		Queue<Integer> q = new LinkedList<>();
		q.add(raiz);
		while(q.peek() != null) {
			// Retira primeiro da lista
			int u = q.poll();
			// Encontra os vizinhos do vértice atual
			List<Integer> vizinhosU = super.vizinhos(u);
			// Iteração por todos os vizinhos
			for (int i=0; i<vizinhosU.size(); i++) {
				// Se o vizinho não foi visitado, defina que ele foi visitado, defina sua distância como seu antecessor +1
				// e defina seu antecessor e adiciona vizinho na fila
				if (conhecido[vizinhosU.get(i)-1] == false) {
					conhecido[vizinhosU.get(i)-1] = true;
					Distancia[vizinhosU.get(i)-1] = Distancia[u-1] + 1;
					Ancestral[vizinhosU.get(i)-1] = super.rotulo(u);		
					q.add(vizinhosU.get(i));
				}
			}
		}
		printArvore();
	}
	
	public void printArvore() {
		// Define o galho atual
		int galhoAtual = 0;
		// Quando um galho não existe o galho atual é definido como -1
		while (galhoAtual >= 0) {
			// Galho existe quando existe um vértice com distância igual ao galho
			boolean galhoExiste = false;
			// Iteração pelo array de Distancia
			for (int i=0; i<Distancia.length; i++) {
				// Caso a Distancia do galho seja igual ao galho atual
				if(Distancia[i] == galhoAtual) {
					// caso o galho ainda não exista print o número do galho e dois pontos, e defina o galho como existente
					if (!galhoExiste) {
						galhoExiste = true;
						System.out.print(Distancia[i]+": ");
					}
					// Printe o rótulo
					System.out.print(super.rotulo(i+1)+", ");
				}
				
			}
			// Enter para próximo galho
			System.out.println();
			// Se existiu galho nessa iteração vá para o próximo galho caso contrário vá para o galho -1 e finalize o while
			if (galhoExiste) {
				galhoAtual += 1;
			} else {
				galhoAtual = -1;
			}
		}
	}
}
