package DataStructure.Graph;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Vertex<T extends Comparable <? super T>> {
    private T value;
    private List <Edge> edges = null;
    private boolean visited;
    private int inDegree;
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
        this.visited = false;
        this.inDegree = 0;
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
        this.edges = edges;
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

        if (!(o instanceof Vertex)) return false;

        Vertex <?> vertex = (Vertex <?>) o;

        return new EqualsBuilder()
                .append(isVisited(), vertex.isVisited())
                .append(getInDegree(), vertex.getInDegree())
                .append(getValue(), vertex.getValue())
                .append(getEdges(), vertex.getEdges())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getValue())
                .append(getEdges())
                .append(isVisited())
                .append(getInDegree())
                .toHashCode();
    }

}
