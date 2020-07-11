package DataStructure.Graph;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Edge<T extends Comparable <? super T>> {
    private Vertex<T> destVertex;
    private int edgeValue;

    public Edge(Vertex<T> destVertex, int edgeValue) {
        this.destVertex = destVertex;
        this.edgeValue = edgeValue;
    }

    public Vertex<T> getDestVertex() {
        return destVertex;
    }

    public int getEdgeValue() {
        return edgeValue;
    }

    public void setEdgeValue(int edgeValue) {
        this.edgeValue = edgeValue;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "destVertex=" + destVertex +
                ", edgeValue=" + edgeValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        return new EqualsBuilder()
                .append(getEdgeValue(), edge.getEdgeValue())
                .append(getDestVertex(), edge.getDestVertex())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getDestVertex())
                .append(getEdgeValue())
                .toHashCode();
    }
}
