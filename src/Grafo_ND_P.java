import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grafo_ND_P {
	
	private static final Float POSITIVE_INFINITY = null;
	String[] V = null;
	int[][]  E = null;
	float[]  w = null;

	// Construtor vazio
	public Grafo_ND_P() {
		
	}
	
	// Construtor com parâmetros
	public Grafo_ND_P(String[] Vertice,int[][] E_Aresta,float[] w_Peso) {
		// Array de vertices
		V = Vertice;
		// Array de Arestas, sendo N por 3, [N][0] = recebe primeiro elemento da aresta,
		// [N][1] = recebe segundo elemento da aresta, [N][2] = recebe o index desta aresta
		// [N][3] = Marcação, usado para saber o estado da aresta "fechado" ou "aberto"
		E = E_Aresta;
		// Array de pesos
		w = w_Peso;
	}
	
	// Leitura de um arquivo e inserção do mesmo em uma lista, sendo que cada indice contém uma linha completa
	public static List<String> readFileInList(String fileName) {
	    List<String> lines = Collections.emptyList();
	    try
	    {
	      lines =
	       Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return lines;
	}
	
	// Verifica a quantidade de vertices por meio do tamanho do array V
	protected int qtdVertices() {
		return V.length;
	}
	
	// Verifica a quantidade de Arestas por meio do tamanho do array E
	protected int qtdArestas() {
		return E.length;
	}
	
	// Verifica o grau de uma vertice por meio de um contador de arestas
	protected int grau(int v) {
		// Contador utilizado para contagem de grau
		int count = 0;
		// Percorre todas as arestas
		for (int i = 0; i < E.length; i++) {
			// Verifica se a aresta possue o vertice do parâmetro no primeiro elemento
			if (E[i][0] == v) {
				// Incrementa contador
				count++;
			}
			// Verifica se a aresta possue o vertice do parâmetro no segundo elemento
			if(E[i][1] == v ) {
				// Incrementa contador
				count++;
			}
		}
		// Retorna o contador
		return count;
	}
	
	// Retorna o conteudo do Array de Vertices(Rótulo)
	protected String rotulo(int index) {
		return V[index-1];
	}
	
	// Verifica nas arestas quais ligações o vertice possue, e retorna o seus vizinhos
	protected List<Integer> vizinhos(int v) {
		// Lista para armazenar os vizinhos
	    List<Integer> viz = new ArrayList<Integer>();
	    // Percorre todas as arestas
	    for (int i = 0; i < E.length; i++) {
	    	// Verifica se a aresta possue o vertice do parâmetro no primeiro elemento
	    	if (E[i][0] == v) {
	    		// Adiciona o segundo elemento como vizinho
	    		viz.add(E[i][1]);
			}
	    	// Verifica se a aresta possue o vertice do parâmetro no segundo elemento
			if(E[i][1] == v ) {
				// Adiciona o primeiro elemento como vizinho
				viz.add(E[i][0]);
			}
		}
	    // Retorna lista de vizinhos
		return viz;
	}
	
	// Verifica existência de aresta entre u e v
	protected boolean haAresta(int u, int v) {
		// Percorre todas as arestas
		for (int i = 0; i < E.length; i++) {
			// Verifica se u é o primeiro elemento da aresta e v é o segundo elemento, além de verificar o caso inverso
			if ((E[i][0] == u && E[i][1] == v) || (E[i][0] == v && E[i][1] == u)) {
				// Retorna verdadeiro
				return true;
			}
		}
		// Retorna false por default
		return false;
	}
	
	// Verifica o peso da aresta entre u e v
	protected Float peso(int u, int v) {
		// Percorre todas as arestas
		for (int i = 0; i < E.length; i++) {
			// Verifica se u é o primeiro elemento da aresta e v é o segundo elemento, além de verificar o caso inverso
			if ((E[i][0] == u && E[i][1] == v) || (E[i][0] == v && E[i][1] == u)) {
				// Retorna seu peso
				return w[E[i][2]];
			}
		}
		// Retorna peso infinito positivo caso não exista areta entre os dois
		return POSITIVE_INFINITY;
	}
	
	// Leitura do arquivo e inserção nos atributos do Grafo
	protected void ler(File file) throws IOException {
		// Chama o método para colocar o arquivo em uma lista
		List l = readFileInList(file.getPath());
		
		// Variavel para conta de Vertices(numero de linhas até chegar nas arestas - 1)
		int lineEdges = -1;
		
		// Coloca a primeira linha em uma string
		String strFor1 = (String) l.get(0);
		// Manipula a string para pegar o valor de Vertices
		lineEdges = Integer.parseInt(strFor1.substring(10));
		
		// Array para as Vertices
		String[] Vert = new String [lineEdges];
		
		// Calculo para verificar o numero de Arestas
		int calcAr = l.size() - lineEdges-2;
		
		// Array para os Pesos
		float[] Pesos = new float[calcAr];
		
		// MultArray para as Arestas
		int[][] Arest = new int[calcAr][4];
		
		// Padrão utilizado para pegar os valores das arestas e pesos
		Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");
		// Lista que recebe os valores dos pesos e arestas
		List<Float> arestasPesosList = new ArrayList<Float>();
		
		// Percorre as linhas do arquivo que possuem as arestas e pesos
		for (int i = lineEdges+1; i < l.size(); i++) {
			// Verifica o pardrao com o texto
			Matcher m = p.matcher((CharSequence) l.get(i));
			// Onde o padrão bater com o descrito
			while (m.find()) {
				// Adiciona a lista os valores
				arestasPesosList.add(Float.parseFloat(m.group()));
			}
		}
		
		// Percorre o numero de vertices vezes
		for (int i = 0; i < lineEdges; i++) {
			// Como a lista de valores possue tanto pesos quanto arestas, utiliza lógica de PA
			// Para inserir no array de Pesos
			// Index do Peso - Index da arestasPesoList
			// 0-2 1-5 2-8 3-11 4-14 5-17 6-20
			Pesos[i] = arestasPesosList.get((i)+2*(i+1));	
			// Como a lista de valores possue tanto pesos quanto arestas, utiliza lógica de PA
			// Para inserir no array de Arestas
			// Index da Aresta[i][0] - Index da arestasPesoList
			// 0-0 1-3 2-6 3-9  4-12 5-15 6-18
			Arest[i][0] = Math.round(arestasPesosList.get((i)*3));
			// Como a lista de valores possue tanto pesos quanto arestas, utiliza lógica de PA
			// Para inserir no array de Arestas
			// Index do da Aresta[i][1] - Index da arestasPesoList
			// 0-1 1-4 2-7 3-10 4-13 5-16 6-19		
			Arest[i][1] = Math.round(arestasPesosList.get(((i)*3)+1));
			// Recebe o index
			Arest[i][2] = (i);
			// str1 definida para pegar todas as Vertices
			String str1 = (String) l.get(i+1);
			// Retira '"' da string
			str1 = str1.replace("\"", "");
			// Separa a string em partes
			String[] parts = str1.split(" ");
			// Retira o numero do vertice da string
			parts[0] = "";
			// Junta todas as partes
			str1 = String.join(" ",parts);
			// Retira espaço em branco no inicio da string
			str1 = str1.substring(1);
			// Coloca o rotulo da vertice no array
			Vert[i] = str1;
		}
		
		// Percorre desde o numero de vertices até o numero de arestas
		for (int i = lineEdges; i < calcAr; i++) {
			// Como a lista de valores possue tanto pesos quanto arestas, utiliza lógica de PA
			// Para inserir no array de Pesos
			// Index do Peso - Index da arestasPesoList
			// 0-2 1-5 2-8 3-11 4-14 5-17 6-20
			Pesos[i] = arestasPesosList.get((i)+2*(i+1));	
			// Como a lista de valores possue tanto pesos quanto arestas, utiliza lógica de PA
			// Para inserir no array de Arestas
			// Index da Aresta[i][0] - Index da arestasPesoList
			// 0-0 1-3 2-6 3-9  4-12 5-15 6-18
			Arest[i][0] = Math.round(arestasPesosList.get((i)*3));
			// Como a lista de valores possue tanto pesos quanto arestas, utiliza lógica de PA
			// Para inserir no array de Arestas
			// Index do da Aresta[i][1] - Index da arestasPesoList
			// 0-1 1-4 2-7 3-10 4-13 5-16 6-19		
			Arest[i][1] = Math.round(arestasPesosList.get(((i)*3)+1));
			// Recebe o index
			Arest[i][2] = (i);
		}		
		// w recebe os Pesos
		w = Pesos;
		// E recebe as Arestas
		E = Arest;
		// V recebe os Vertices
		V = Vert;	
	}
	
	// Verifica se todos os graus são par, caso não sejam, o ciclo Euleriano não existe
	protected int existeCicloEuleriano(Grafo_ND_P grafo) {	
		// Percorre o numero de Vertices
		for (int i = 1; i <= grafo.V.length; i++) {
			// Chama o método que pega o grau e verifica se é par
			if ((grafo.grau(i)) % 2 != 0) {
				// Caso não par retorna 0
				return 0;
			}
		}
		// Caso todos sejam pares retorna 1 
		return 1;
	}
	
	// Encontra o index e outro vertice
	protected List<Integer> findByNumber(Grafo_ND_P grafo, int path, int v) {
		// Lista para index e Vertice
		List<Integer> indexN2 = new ArrayList<Integer>();
		// Percorre todas as Arestas
		for (int i = 0; i < grafo.E.length; i++) {
			// Caso aresta esteja disponivel, e valores das arestas estejam corretas
			if (grafo.E[i][1] == path && grafo.E[i][0] == v && grafo.E[i][3] == 1) {
				// Adiciona index
				indexN2.add(i);
				// Adiciona Vertice da aresta
				indexN2.add(grafo.E[i][1]);
				// retorna Lista
				return indexN2;
			}
			// Caso aresta esteja disponivel, e valores das arestas estejam corretas
			if (grafo.E[i][0] == path && grafo.E[i][1] == v && grafo.E[i][3] == 1) {
				// Adiciona index
				indexN2.add(i);
				// Adiciona Vertice da aresta
				indexN2.add(grafo.E[i][0]);
				// retorna Lista
				return indexN2;
			}
		}
		// Retorna null, caso n tenha
		return null;
	}
	
	// Busca o caminho euleriano por meio do primeiro Vertice
	protected List<Integer> caminhoCicloEuleriano(Grafo_ND_P grafo) {
		// Arestas podem estar "abertas" ou "fechadas", caso abertas o caminho pode passar por elas, caso fechadas
		// não passa
		
		// Deixa todas as Arestas como abertas
		for (int i = 0; i < grafo.E.length; i++) {
			E[i][3] = 1;
		}
		
		// Lista do Caminho
		List<Integer> pathFinal = new ArrayList<Integer>();
		
		// Lista para o index e o outro vertice
		List<Integer> indexN2 = new ArrayList<Integer>();
		
		// Vertice usado para o caminho
		int v = 1;
		// Adiciona vertice ao caminho
		pathFinal.add(v);
		while (true) {
			// Lista de vizinhos para verificar os caminhos
			List<Integer> paths = grafo.vizinhos(v);
			// Sort para sempre buscar o caminho com menor indice dos Vertices disponiveis
			paths.sort(null);
			// Percorre o tamanho dos vizinhos
			for (int i = 0; i < paths.size(); i++) {
				// Busca o vizinho que possui disponibilidade
				indexN2 = grafo.findByNumber(grafo, paths.get(i), v);
				// Se está aresta está disponível
				if (indexN2 != null) {
					// Adiciona vertice ao caminho
					pathFinal.add(indexN2.get(1));
					// v recebe novo valor
					v = indexN2.get(1);
					// Fecha a aresta
					grafo.E[indexN2.get(0)][3] = 0;
					// "Pula" o for
					break;
				}
			}
			// Caso v volte a ser 1, encerra o while
			if (v == 1) {
				// "Pula" o while
				break;
			}
		}
		// Retorna o caminho
		return pathFinal;
	}
	
}