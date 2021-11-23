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
	
	public int qtdVertices() {
		return V.length;
	}
	
	public int qtdArestas() {
		return E.length;
	}
	
	public int grau(int v) {
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
	
	public String rotulo(String[] V) {
		Scanner reader = new Scanner(System.in);
		int index = 0;
		System.out.print("Qual o número do rotulo?");
		index = reader.nextInt();
		return V[index];
	}
	
	public List<Integer> vizinhos(int v,Grafo_ND_P grafo) {
		
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
	
	public boolean haAresta(int u, int v) {
		boolean existe = false;
		boolean existe2 = false;
		for (int i = 0; i < E[0].length; i++) {
			for (int j = 0; j < 2; j++) {
				if (E[i][j] == u) {
					existe = true;
				}
				if(E[i][j] == v && existe == true) {
					existe2 = true;
				}
			}
			existe = false;
		}
		return existe2;
	}
	
	public Float peso(int u, int v) {
		boolean existe = false;
		for (int i = 0; i < E[0].length; i++) {
			for (int j = 0; j < 2; j++) {
				if (E[i][j] == u) {
					existe = true;
				}
				if(E[i][j] == v && existe == true) {
					System.out.print(w[E[i][2]]);
					return w[E[i][2]];
				}
			}
			existe = false;
		}
		
		return POSITIVE_INFINITY;
	}
	
	
	public Grafo_ND_P ler(File file) throws IOException {
		 
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

		// So LineEdges - 1, is the number of vertices lines
		System.out.println(lineEdges);
		
		// Array for Vertices
		String[] Vert = new String [lineEdges];
		
		
		// calc to see how many "Arestas"
		int calcAr = l.size() - lineEdges;
		System.out.println(calcAr);
		
		// Array for Pesos
		float[] Pesos = new float[calcAr];
		
		// MultArray for Arestas
		int[][] Arest = new int[calcAr][3];
		
		// Allocate the Vertice labbles in the graf
		for (int i = 1; i < lineEdges; i++) {
			String str1 = (String) l.get(i);
			str1 = str1.substring(3,str1.length() - 1);
			Vert[i-1] = str1;
			System.out.println(Vert[i-1]);
		}
		
		// Allocate the Peso values in the graf
		for (int i = lineEdges+1; i < l.size(); i++) {
			String str2 = (String) l.get(i);
			str2 = str2.substring(4);
			Pesos[i-calcAr] = Float.parseFloat(str2);
			System.out.println(Pesos[i-calcAr]);
		}
		
		// Alocate the Arestas index to Vertice and Peso in the graf
		for (int i = lineEdges+1; i < l.size(); i++) {
			String str3 = (String) l.get(i);
			String str4 = str3.substring(2,3);
			str3 = str3.substring(0,1);
			//System.out.print(str3+" "+str4);
			
			Arest[i-calcAr][0] = Integer.parseInt(str3);
			Arest[i-calcAr][1] = Integer.parseInt(str4);
			Arest[i-calcAr][2] = i-calcAr;
			//System.out.println(Arest[i-calcAr][0]);
			//System.out.println(Arest[i-calcAr][1]);
			//System.out.println(Arest[i-calcAr][2]);
		}
		
		
		Grafo_ND_P grafo = new Grafo_ND_P(Vert,Arest,Pesos);
		
		return grafo;
		
	}
}


