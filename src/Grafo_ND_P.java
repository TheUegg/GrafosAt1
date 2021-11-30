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

	public Grafo_ND_P() {
		
	}
	
	/* Consertar ler(File file) para funcionar corretamente
	public Grafo_ND_P(File grafo) throws IOException {
		ler(grafo);
	}*/
	
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
	      e.printStackTrace();
	    }
	    return lines;
	}
	
	protected int qtdVertices(Grafo_ND_P grafo) {
		return V.length-1;
	}
	
	protected int qtdArestas() {
		return E.length;
	}
	
	protected int grau(int v) {
		int count = 0;
		for (int i = 0; i < E.length; i++) {
			if (E[i][0] == v) {
				count++;
			}
			if(E[i][1] == v ) {
				count++;
			}
		}
		
		return count;
	}
	
	protected String rotulo(int index) {
		return V[index-1];
	}
	
	protected List<Integer> vizinhos(int v) {
	    List<Integer> viz = new ArrayList<Integer>();
	    for (int i = 0; i < E.length; i++) {
			if (E[i][0] == v) {
				viz.add(E[i][1]);
			}
			if(E[i][1] == v ) {
				viz.add(E[i][0]);
			}
		}
		
		return viz;
	}
	
	protected boolean haAresta(int u, int v) {
		for (int i = 0; i < E.length; i++) {
			
			if ((E[i][0] == u && E[i][1] == v) || (E[i][0] == v && E[i][1] == u)) {
				return true;
			}

		}
		return false;
	}
	
	protected Float peso(int u, int v) {
		for (int i = 0; i < E.length; i++) {
			if ((E[i][0] == u && E[i][1] == v) || (E[i][0] == v && E[i][1] == u)) {
				return w[E[i][2]];
			}
		}
		return POSITIVE_INFINITY;
	}
	
	
	protected Grafo_ND_P ler(File file) throws IOException {
		 
		//List l = readFileInList("C:\\\\Users\\\\gabri\\\\Downloads\\\\fln_pequena.net");
		List l = readFileInList("C:\\Users\\User\\Downloads\\fln_pequena.net");
		
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
		System.out.println(calcAr);
		
		// Array for Pesos
		float[] Pesos = new float[calcAr];
		
		// MultArray for Arestas
		int[][] Arest = new int[calcAr][3];
		

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
			str1 = str1.substring(2,str1.length() - 1);
			Vert[i-1] = str1;
		}
		
		
		Grafo_ND_P grafo = new Grafo_ND_P(Vert,Arest,Pesos);
		
		return grafo;
		
	}
	
}


