import java.io.File;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\Users\\gabri\\Downloads\\ContemCicloEuleriano.net");
		
		Grafo_ND_P grafo = new Grafo_ND_P();
		
		
		grafo = grafo.ler(file);
		
		System.out.println(grafo.V[1]);
		System.out.println(grafo.qtdVertices());
		System.out.println(grafo.qtdArestas());
		
		System.out.println(grafo.rotulo(grafo.V));
	}

}
