import java.io.File;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\Users\\gabri\\Downloads\\ContemCicloEuleriano.net");
		
		Grafo_ND_P grafo = new Grafo_ND_P();
		
		
		grafo = grafo.ler(file);
		
		System.out.print(grafo.V[1]);
	}

}
