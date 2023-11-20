public class App {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //DIJKSTRA
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph("casoteste.txt"); 
        System.out.println(graph.toDot());

        // Dijkstra d = new Dijkstra(graph);
        System.out.println(graph.contagemHidrogenio("ouro"));
        

        long fina = System.currentTimeMillis();
        double timeelapsed = (fina - start) / 1000.0;
        System.out.printf("Tempo em segundos:\n%.8f", timeelapsed);
       
    }
}