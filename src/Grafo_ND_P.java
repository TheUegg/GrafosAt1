import java.io.*;
public class Grafo_ND_P {

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
	
	public void ler(File file) throws IOException {
		 
		       //Buffer Reader
		        BufferedReader br = new BufferedReader(new FileReader(file));
		 
		        // Declaring a string to take the content of the file
		        String st;
		        
		        // While has a character in the file
		        while ((st = br.readLine()) != null)
		 
		            // Print
		            System.out.println(st);
	}
}


