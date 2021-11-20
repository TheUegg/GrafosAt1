import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class Grafo_ND_P {
	
	String[] V = null;
	int[] E = null;
	int[] w = null;

	public Grafo_ND_P() {
		
	}
	
	public Grafo_ND_P(String[] Vertice,int[] E_Aresta,int[] w_Peso) {
		V = Vertice;
		E = E_Aresta;
		w = w_Peso;
	}
	
	public int qtdVertices() {
		return 1;
	}
	
	public int qtdArestas() {
		return 1;
	}
	
	public int grau() {
		return 1;
	}
	
	public int rotulo() {
		return 1;
	}
	
	public int vizinhos() {
		return 1;
	}
	
	public boolean haAresta() {
		return true;
	}
	
	public int peso() {
		return 1;
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
	
	public void ler(File file) throws IOException {
		 
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
		
		// Allocatint the Vertice labbles in the graf
		for (int i = 1; i < lineEdges; i++) {
			String str1 = (String) l.get(i);
			str1 = str1.substring(3,str1.length() - 1);
			Vert[i-1] = str1;
			System.out.println(Vert[i-1]);
		}
		
		
		// Checking to see if works
		//System.out.print(V[].toString());
	}
}


