import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BuscaEmLargura extends Grafo_ND_P{
	private int[] Distancia;
	private String[] Ancestral;
	Grafo_ND_P grafo = new Grafo_ND_P();
	
	//Work in progress
	//  - Algumas coisas devem ser arrumadas na classe pai para a relação funcionar corretamente
	
	public BuscaEmLargura(File fileGrafo, int raiz) throws IOException {
		super();
		//super(fileGrafo);
		grafo = grafo.ler(fileGrafo);
		executarBusca(raiz);
	}
	
	public BuscaEmLargura(Grafo_ND_P parent, int raiz) {
		super(parent.V, parent.E, parent.w);
		grafo = parent;
		executarBusca(raiz);
	}
	
	public void executarBusca(int raiz) {
		//Distancia = new int[super.V.length];
		Distancia = new int[grafo.V.length];
		for (int i = 0; i < Distancia.length; i++) {
			Distancia[i] = -1; //Usando -1 como representação de distância infinita
		}
		//Ancestral = new String[super.V.length];
		Ancestral = new String[grafo.V.length];
		boolean[] conhecido = new boolean[grafo.V.length];
				
		//Montando a árvore
		conhecido[raiz-1] = true;
		Distancia[raiz-1] = 0;
				
		Queue<Integer> q = new LinkedList<>();
		q.add(raiz);
		while(q.peek() != null) {
			int u = q.poll();
			//List<Integer> vizinhosU = super.vizinhos(u);
			List<Integer> vizinhosU = grafo.vizinhos(u);
			for (int i=0; i<vizinhosU.size(); i++) {
				if (conhecido[vizinhosU.get(i)-1] == false) {
					conhecido[vizinhosU.get(i)-1] = true;
					Distancia[vizinhosU.get(i)-1] = Distancia[u-1] + 1;
					//Ancestral[vizinhosU.get(i)-1] = super.rotulo(u-1);
					Ancestral[vizinhosU.get(i)-1] = grafo.rotulo(u);		
					q.add(vizinhosU.get(i));
				}
			}
		}
	}
	
	public void printArvore() {
		int galhoAtual = 0;
		while (galhoAtual >= 0) {
			boolean galhoExiste = false;
			for (int i=0; i<Distancia.length; i++) {
				if(Distancia[i] == galhoAtual) {
					if (!galhoExiste) {
						galhoExiste = true;
						System.out.print(Distancia[i]+": ");
					}
					System.out.print(grafo.rotulo(i+1)+", ");
				}
				
			}
			System.out.println();
			if (galhoExiste) {
				galhoAtual += 1;
			} else {
				galhoAtual = -1;
			}
		}
	}
}
