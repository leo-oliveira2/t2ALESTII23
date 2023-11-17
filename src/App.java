public class App {
    public static void main(String[] args) throws Exception {
        EdgeWeightedGraph graph = new EdgeWeightedGraph("casoteste.txt"); 
        System.out.println(graph.toDot());
    }
}
