public class App {
    public static void main(String[] args) throws Exception {

        //DIJKSTRA
        EdgeWeightedGraph graph = new EdgeWeightedGraph("casoteste.txt"); 
        System.out.println(graph.toDot());

        Dijkstra d = new Dijkstra(graph);

        double qtdHidrogenioMinima = d.hidrogenioMinimo("hidrogenio", "ouro");

        if(qtdHidrogenioMinima >= 0){
            System.out.println("A quantidade mínima de hidrogênio para produzir uma unidade de ouro é: " + qtdHidrogenioMinima);
        }else{
            System.out.println("Caminho não encontrado");
        }

        //BFS

        BFS bfs = new BFS(graph);
        String startVertex = "hidrogenio";
        String endVertex = "ouro";

        double minimumHydrogen = bfs.hidrogenioMinimo(startVertex, endVertex);

        if (minimumHydrogen >= 0) {
            System.out.println("BFS A quantidade mínima de hidrogênio para produzir ouro é: " + minimumHydrogen);
        } else {
            System.out.println("BFS Não há caminho para produzir ouro a partir do hidrogênio.");
        }
        
    }
} 