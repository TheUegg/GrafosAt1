import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Grafo_ND_P {
	
	private static final Float POSITIVE_INFINITY = null;
	String[] V = null;
	int[][] E = null;
	float[] w = null;

	public Grafo_ND_P() {
		
	}
	
	public Grafo_ND_P(String[] Vertice,int[][] E_Aresta,float[] w_Peso) {
		V = Vertice;
		E = E_Aresta;
		w = w_Peso;
	}
	
	
	public static List<String> readFileInList(String fileName) {
		 
	    List<String> lines = Collections.emptyList();
	    try
	    {
	      lines =
	       Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
	    }
	 
	    catch (IOException e)
	    {
	 
	      // do something
	      e.printStackTrace();
	    }
	    return lines;
	}
	
	protected int qtdVertices(Grafo_ND_P grafo) {
		return grafo.V.length-1;
	}
	
	protected int qtdArestas(Grafo_ND_P grafo) {
		return grafo.E.length;
	}
	
	protected int grau(int v,Grafo_ND_P grafo) {
		int count = 0;
		for (int i = 0; i < grafo.E.length; i++) {
			if (grafo.E[i][0] == v) {
				count++;
			}
			if(grafo.E[i][1] == v ) {
				count++;
			}
		}
		
		return count;
	}
	
	// Com numeros maiores que 9, aparece "
	protected String rotulo(String[] V) {
		Scanner reader = new Scanner(System.in);
		int index = 0;
		System.out.print("Qual o número do rotulo?");
		index = reader.nextInt();
		return V[index-1];
	}
	
	protected List<Integer> vizinhos(int v,Grafo_ND_P grafo) {
		
        List<Integer> viz = new ArrayList<Integer>();
        for (int i = 0; i < grafo.E.length; i++) {
			if (grafo.E[i][0] == v) {
				viz.add(E[i][1]);
			}
			if(grafo.E[i][1] == v ) {
				viz.add(E[i][0]);
			}
		}
		
		return viz;
	}
	
	// Faltou o reverso (v u e u v são iguais)
	protected boolean haAresta(int u, int v, Grafo_ND_P grafo) {
		boolean existe = false;
		boolean existe2 = false;
		for (int i = 0; i < grafo.E.length; i++) {
			
			if ((grafo.E[i][0] == u && grafo.E[i][1] == v) || (grafo.E[i][0] == v && grafo.E[i][1] == u)) {
				return true;
			}

		}
		return false;
	}
	
	// Faltou o reverso (v u e u v são iguais)
	protected Float peso(int u, int v,Grafo_ND_P grafo) {
		boolean existe = false;
		for (int i = 0; i < grafo.E.length; i++) {
			if ((grafo.E[i][0] == u && grafo.E[i][1] == v) || (grafo.E[i][0] == v && grafo.E[i][1] == u)) {
				return w[E[i][2]];
			}
		}
		return POSITIVE_INFINITY;
	}
	
	
	protected Grafo_ND_P ler(File file) throws IOException {
		 
		List l = readFileInList("C:\\\\Users\\\\gabri\\\\Downloads\\\\ContemCicloEuleriano.net");
		
		int lineEdges = -1;
		// Just to see the number of Vertices in the file
		for (int i = 0; i < l.size(); i++) {
			
			System.out.println((String) l.get(i));
			String strFor = (String) l.get(i);
			if (strFor.equals("*edges")) {
				lineEdges = i;
			}
		}

		
		// Array for Vertices
		String[] Vert = new String [lineEdges];
		
		
		// calc to see how many "Arestas"
		int calcAr = l.size() - lineEdges-1;
		//System.out.println(calcAr);
		
		// Array for Pesos
		float[] Pesos = new float[calcAr];
		
		// MultArray for Arestas
		int[][] Arest = new int[calcAr][4];
		

		Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");
		List<Float> arestasPesosList = new ArrayList<Float>();
		
		for (int i = lineEdges+1; i < l.size(); i++) {	
			Matcher m = p.matcher((CharSequence) l.get(i));
			while (m.find()) {
				arestasPesosList.add(Float.parseFloat(m.group()));
			}
		}
		
		//System.out.println(arestasPesosList);
		// 0-2 1-5 2-8 3-11 4-14 5-17 6-20  Pesos
		//  +2  +4  +6  +8  +10   +12  +14
		// 0-0 1-3 2-6 3-9  4-12 5-15 6-18
		//  +0  +2  +4  +6  +8    +10  +12
		// 0-1 1-4 2-7 3-10 4-13 5-16 6-19
		//  +1  +3  +5  +7  +9    +11  +13
		for (int i = lineEdges+1; i < l.size(); i++) {	
		
			Pesos[i-lineEdges-1] = arestasPesosList.get((i-lineEdges-1)+2*(i-lineEdges));
			Arest[i-lineEdges-1][0] = Math.round(arestasPesosList.get((i-lineEdges-1)*3));
			Arest[i-lineEdges-1][1] = Math.round(arestasPesosList.get(((i-lineEdges-1)*3)+1));
			Arest[i-lineEdges-1][2] = (i-lineEdges-1);
			
		}

		
		// Allocate the Vertice labbles in the graf
		for (int i = 1; i < lineEdges; i++) {
			String str1 = (String) l.get(i);
			str1 = str1.substring(3,str1.length() - 1);
			Vert[i-1] = str1;
		}
		
		
		Grafo_ND_P grafo = new Grafo_ND_P(Vert,Arest,Pesos);
		
		return grafo;
		
	}
	
	protected int existeCicloEuleriano(Grafo_ND_P grafo) {
		
		//  Checking if all "graus" are even
		for (int i = 1; i <= grafo.V.length; i++) {
			if ((grafo.grau(i, grafo)) % 2 != 0) {
				return 0;
			}
		}
		return 1;
	}
	
	//  FindByNumber
	protected List<Integer> findByNumber(Grafo_ND_P grafo, int path, int v) {
		
		List<Integer> indexN2 = new ArrayList<Integer>();
		for (int i = 0; i < grafo.E.length; i++) {
			if (grafo.E[i][1] == path && grafo.E[i][0] == v && grafo.E[i][3] == 1) {
				indexN2.add(i);
				indexN2.add(grafo.E[i][1]);
				return indexN2;
			}
			if (grafo.E[i][0] == path && grafo.E[i][1] == v && grafo.E[i][3] == 1) {
				indexN2.add(i);
				indexN2.add(grafo.E[i][0]);
				return indexN2;
			}
		}
		return null;
	}
	
	protected List<Integer> caminhoCicloEuleriano(Grafo_ND_P grafo) {
			
		// E = '1' enable, if '0' disable
		// enabling "E"
		for (int i = 0; i < grafo.E.length; i++) {
			E[i][3] = 1;
		}
		// Final Path
		List<Integer> pathFinal = new ArrayList<Integer>();
		
		// List for index + the other vertice
		List<Integer> indexN2 = new ArrayList<Integer>();
		
		int v = 1;
		pathFinal.add(v);
		while (true) {
			// List for paths
			List<Integer> paths = grafo.vizinhos(v, grafo);
			paths.sort(null);
			for (int i = 0; i < paths.size(); i++) {
				indexN2 = grafo.findByNumber(grafo, paths.get(i), v);
				if (indexN2 != null) {
					pathFinal.add(indexN2.get(1));
					v = indexN2.get(1);
					grafo.E[indexN2.get(0)][3] = 0;
					break;
				}
			}
			if (v == 1) {
				break;
			}
		}
		
		return pathFinal;
	}
	
}


