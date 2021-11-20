import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
public class Grafo_ND_P {
	
	int[] V;
	int[] E;
	int[] w;

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
		 
		Iterator<String> itr = l.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		String str1 = (String) l.get(1);
		
		str1 = str1.substring(3,str1.length() - 1);
		
		
		System.out.println(str1);
		
	}
}


