package DataStructure.Graph;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex<T extends Comparable <? super T>> implements Comparable<Vertex>{
    private T value;
    private List <Edge> edges = null;
    private int inDegree;
    private boolean visited;
    //出度直接用edges的size表示
    //前驱结点...

    public void addInDegree(){
        this.inDegree += 1;
    }

    public void addInDegrees(int num){
        this.inDegree += num;
    }

    public void decInDegree(){
        this.inDegree -= 1;
    }

    public int getInDegree() {
        return inDegree;
    }

    public int getOutDegree() {
        return edges.size();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex(T value) {
        this.value = value;
        this.edges = new ArrayList <Edge>();
        this.inDegree = 0;
        this.visited = false;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public final boolean hasEdges() {
        return this.edges != null && this.edges.isEmpty();
    }

    public List <Edge> getEdges() {
        return edges;
    }

    public void setEdges(List <Edge> edges) {
        for(Edge edge : edges){
            Vertex<T> toVertex = edge.getDestVertex();
            toVertex.addInDegree();
            this.edges.add(edge);
        }
    }

    //顶点的出度
    public final int getNumEdges() {
        return this.hasEdges() ? this.getEdges().size() : 0;
    }

    public final void addEdge(Vertex v, int edgeValue) {
        if (this.edges == null) {
            this.edges = new ArrayList <Edge>(1);
        }
        this.edges.add(new Edge(v, edgeValue));
        v.addInDegree();
    }

    public final List <Edge> removeEdges(Vertex v) {
        List <Edge> removeEdges = new ArrayList <Edge>();
        if (this.edges != null){
            Iterator i$ = this.edges.iterator();

            while(i$.hasNext()){
                Edge edge = (Edge)i$.next();
                if(edge.getDestVertex().equals(v)){
                    removeEdges.add(edge);
                }
            }
            this.edges.removeAll(removeEdges);
        }
        return removeEdges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return inDegree == vertex.inDegree &&
                visited == vertex.visited &&
                value.equals(vertex.value) &&
                edges.equals(vertex.edges);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "value=" + value +
                ", edges=" + edges +
                ", inDegree=" + inDegree +
                ", visited=" + visited +
                '}';
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getValue())
                .append(getEdges())
                .append(getInDegree())
                .append(isVisited())
                .toHashCode();
    }

    @Override
    public int compareTo(Vertex o) {
        return 0;
    }


}
