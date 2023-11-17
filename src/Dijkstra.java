import java.util.*;

public class Dijkstra {
    private EdgeWeightedGraph graph;

    public Dijkstra(EdgeWeightedGraph graph) {
        this.graph = graph;
    }

    public double hidrogenioMinimo(String verticeInicial, String verticeFinal) {
        Map<String, Double> distancias = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distancia));

        for (String vertice : graph.getVerts()) {
            distancias.put(vertice, Double.POSITIVE_INFINITY);
        }

        distancias.put(verticeInicial, 0.0);
        priorityQueue.add(new Node(verticeInicial, 0));

        while (!priorityQueue.isEmpty()) {
            Node nodoAtual = priorityQueue.poll();

            if (nodoAtual.vertice.equals(verticeFinal)) {
                return distancias.get(verticeFinal);
            }

            for (Edge aresta : graph.getAdj(nodoAtual.vertice)) {
                String nodoVizinho = aresta.getW();
                double novaDistancia = distancias.get(nodoAtual.vertice) + aresta.getWeight();

                if (novaDistancia < distancias.get(nodoVizinho)) {
                    distancias.put(nodoVizinho, novaDistancia);
                    priorityQueue.add(new Node(nodoVizinho, novaDistancia));
                }
            }
        }

        return -1; // Se não houver caminho, retorne um valor indicando isso
    }

    private static class Node {
        private String vertice;
        private double distancia;

        public Node(String vertice, double distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }
    }
}
