import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
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
  protected Set<String> vertices;
  protected int totalVertices;
  protected int totalEdges;

  public EdgeWeightedGraph() {
    graph = new HashMap<>();
    vertices = new HashSet<>();
    totalVertices = totalEdges = 0;
  }

  public EdgeWeightedGraph(String filename) {
    this();
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line = "";
      while ((line = br.readLine()) != null) {
        String[] partes = line.split(" -> ");
        String[] verticesOrigem = partes[0].split(" ");
        String[] verticesAlvos = partes[1].split(" ");

        for (int i = 0; i < verticesOrigem.length - 1; i += 2) {
          addEdge(verticesOrigem[i + 1], verticesAlvos[1], new BigInteger(verticesOrigem[i]));
        }
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }

  public void addEdge(String v, String w, BigInteger weight) {
    Edge e = new Edge(v, w, weight);
    addToList(v, e);
    addToList(w, e);
    if (!vertices.contains(v)) {
      vertices.add(v);
      totalVertices++;
    }
    if (!vertices.contains(w)) {
      vertices.add(w);
      totalVertices++;
    }
    totalEdges += 2;
  }

  public Iterable<Edge> getAdj(String v) {
    List<Edge> res = graph.get(v);
    if (res == null)
      res = new LinkedList<>();
    return res;
  }

  public int getTotalVerts() {
    return totalVertices;
  }

  public int getTotalEdges() {
    return totalEdges;
  }

  public Set<String> getVerts() {
    return vertices;
  }

  public Iterable<Edge> getEdges() {
    Set<Edge> ed = new HashSet<>();
    for (String v : getVerts().stream().sorted().collect(Collectors.toList())) {
      for (Edge e : getAdj(v)) {
        if (!ed.contains(e)) {
          ed.add(e);
        }
      }
    }
    return ed;
  }

  public BigInteger contagemHidrogenio(String verticeAlvo) {
    if (verticeAlvo.equals("hidrogenio")) {
      return BigInteger.ONE;
    }

    BigInteger hidrogenioTotal = BigInteger.ZERO;
    for (Edge e : getEdges()) {
      if (e.getW().equals(verticeAlvo)){
        BigInteger peso = e.getWeight();
        BigInteger hidrogenio = contagemHidrogenio(e.getV());
        hidrogenioTotal = hidrogenioTotal.add(peso.multiply(hidrogenio));
      }
    }

    return hidrogenioTotal;
  }

  public String toDot() {
    StringBuilder sb = new StringBuilder();
    sb.append("graph {" + NEWLINE);
    sb.append("rankdir = LR;" + NEWLINE);
    sb.append("node [shape = circle];" + NEWLINE);
    for (Edge e : getEdges())
      sb.append(String.format("%s -- %s [label=\"%s\"]", e.getV(), e.getW(), e.getWeight().toString()) + NEWLINE);
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