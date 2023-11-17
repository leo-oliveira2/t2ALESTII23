import java.math.BigInteger;
import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        EdgeWeightedGraph graph = new EdgeWeightedGraph("casoteste.txt"); 
        System.out.println(graph.toDot());

        Dijkstra d = new Dijkstra(graph);

        BigInteger qtdHidrogenioMinima = d.hidrogenioMinimo("hidrogenio", "ouro");

        if(qtdHidrogenioMinima.compareTo(BigInteger.valueOf(-1)) != 0){
            System.out.println("A quantidade mínima de hidrogênio para produzir uma unidade de ouro é: " + qtdHidrogenioMinima);
        }else{
            System.out.println("Caminho não encontrado");
        }
    }
}