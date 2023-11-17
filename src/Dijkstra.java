import java.math.BigInteger;
import java.util.*;

public class Dijkstra {
    private EdgeWeightedGraph grafo;

    public Dijkstra(EdgeWeightedGraph grafo) {
        this.grafo = grafo;
    }

    public BigInteger hidrogenioMinimo(String verticeInicial, String verticeFinal) {
        Map<String, BigInteger> distancias = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.distancia));

        for (String vertex : grafo.getVerts()){
            distancias.put(vertex, BigInteger.valueOf(Integer.MAX_VALUE));
        }

        distancias.put(verticeInicial, BigInteger.ZERO);
        pq.add(new Node(verticeInicial, 0));

        while (!pq.isEmpty()) {
            Node nodoAtual = pq.poll();

            if (nodoAtual.vertice.equals(verticeFinal)) {
                return distancias.get(verticeFinal);
            }

            for (Edge arestaVizinha : grafo.getAdj(nodoAtual.vertice)) {
                String vizinho = arestaVizinha.getW();
                BigInteger newDistance = distancias.get(nodoAtual.vertice)
                        .add(BigInteger.valueOf((long) arestaVizinha.getWeight()))
                        .multiply(BigInteger.valueOf((long) getHydrogenMultiplier(arestaVizinha)));
                if (newDistance.compareTo(distancias.get(vizinho)) < 0){
                    distancias.put(vizinho, newDistance);
                    pq.add(new Node(vizinho, newDistance.doubleValue()));
                }
            }
        }

        return BigInteger.valueOf(-1); // Se não houver caminho, retorne um valor indicando isso
    }

    private double getHydrogenMultiplier(Edge edge) {
        // Lógica para obter o multiplicador de hidrogênio
        // Neste exemplo, assumimos que o multiplicador é o custo do próximo elemento
        // Substitua essa lógica com base nos requisitos específicos do seu problema
        return edge.getWeight();
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
