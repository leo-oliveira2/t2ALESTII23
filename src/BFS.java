import java.util.*;

public class BFS {
    private EdgeWeightedGraph graph;

    public BFS(EdgeWeightedGraph graph) {
        this.graph = graph;
    }

    public double hidrogenioMinimo(String verticeInicial, String verticeFinal) {
        Queue<String> q = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        Map<String, Double> hidrogenioMap = new HashMap<>();

        q.add(verticeInicial);
        visitados.add(verticeInicial);
        hidrogenioMap.put(verticeInicial, 0.0);

        while (!q.isEmpty()){
            String nodoAtual = q.poll();

            for (Edge neighborEdge : graph.getAdj(nodoAtual)) {
                String neighbor = neighborEdge.getW();
                double hydrogenRequired = hidrogenioMap.get(nodoAtual) + neighborEdge.getWeight();

                if (!visitados.contains(neighbor)) {
                    q.add(neighbor);
                    visitados.add(neighbor);
                    hidrogenioMap.put(neighbor, hydrogenRequired);

                    if (neighbor.equals(verticeFinal)) {
                        return hydrogenRequired;
                    }
                }
            }
        }

        return -1; // Se não houver caminho, retorne um valor indicando isso
    }
}
