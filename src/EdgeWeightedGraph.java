import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EdgeWeightedGraph {
  protected static final String NEWLINE = System.getProperty("line.separator");

  protected Map<String, List<Edge>> graph;

  public EdgeWeightedGraph() {
    graph = new HashMap<>();
  }

  public EdgeWeightedGraph(String filename) {
    this();
    In in = new In(filename);
    String line;
    while ((line = in.readLine()) != null) {
        String[] partes = line.split(" -> ");
        String[] verticesOrigem = partes[0].split(" ");
        String[] verticeAlvo = partes[1].split(" ");

        for (int i = 0; i < verticesOrigem.length-1; i+=2){
          addEdge(verticesOrigem[i+1], verticeAlvo[1], Long.parseLong(verticesOrigem[i]));
        }
    }
    in.close();
  }

  public void addEdge(String v, String w, long weight) {
    Edge e = new Edge(v, w, weight);
    addToList(v, e);
    addToList(w, e);
  }

  public Iterable<Edge> getAdj(String v) {
    return graph.get(v);
  }

  public Set<String> getVerts() {
    return graph.keySet();
  }

  public Iterable<Edge> getEdges() {
    Set<Edge> ed = new HashSet<>();
    for (String v : getVerts().stream().sorted().collect(Collectors.toList())){
      for (Edge e : getAdj(v)) {
        if (!ed.contains(e)) {
          ed.add(e);
        }
      }
    }
    return ed;
  }

  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("graph {" + NEWLINE);
    sb.append("rankdir = LR;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (Edge e : getEdges())
      sb.append(String.format("%s -- %s [label=\"%.0f\"]", e.getV(), e.getW(), e.getWeight()) + NEWLINE);
    sb.append("}" + NEWLINE);
    return sb.toString();
  }

  // Adiciona um vértice adjacente a outro, criando a lista
  // de adjacências caso ainda não exista no dicionário
  protected List<Edge> addToList(String v, Edge e) {
    List<Edge> list = graph.get(v);
    if (list == null)
      list = new LinkedList<>();
    list.add(e);
    graph.put(v, list);
    return list;
  }
}
