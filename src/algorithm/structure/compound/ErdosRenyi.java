package algorithm.structure.compound;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * 
 * @author Alex
 *
 */
public class ErdosRenyi {
	
	private static Random random = new Random();
	
	public static int count(int n) {
		// number of connections
		int edges = 0;
		// generates random pairs of integers between 0 and N-1
        UF uf = new UF(n);
		// calling connected() to determine if they are connected and then
		// union() if not
		//looping until all sites are connected
		while(uf.count() > 1){
			int p = random.nextInt(n);
			int q = random.nextInt(n);
			if(!uf.connected(p, q)) {
	        	uf.union(p, q);
	        	edges++;
	        }
		}
		//printing the number of connections generated
		return edges;
	}

	public static void main(String[] args) throws IOException {
		int n = 10; // number of vertices
		int trials = 10; // number of trials
		int[] edges = new int[trials];

		// repeat the experiment trials times
		for (int t = 0; t < trials; t++) {
			edges[t] = count(n);
		}
		IntStream.range(0, edges.length).forEach(i -> {
			System.out.print(edges[i] + " ");
		});
		System.out.println();
		double avg = Arrays.stream(edges).average().orElse(0.0);
		System.out.println(avg);
		   
	}
}
