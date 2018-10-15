package DataStructure.Graph;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class MatrixGraph<T extends Comparable <? super T>> implements Graph {
    protected int[][] adjacencyMatrix;
    protected int size = 0;
    public Set <Vertex <T>> vertices;

    public MatrixGraph(int size) {
        this.adjacencyMatrix = new int[size][size];
        this.size = size;
        this.vertices = new LinkedHashSet <Vertex <T>>();
    }

    public void addVertices(LinkedList <Vertex> vertices) {
        adjustMatrix(vertices.size());
        for (Vertex v : vertices) {
            addVertex(v);
        }
    }

    public void addVertex(Vertex vertex) {
        if (vertices.contains(vertex)) {
            throw new IllegalArgumentException("Vertex is already exists!");
        }
        vertices.add(vertex);
    }

    private void adjustMatrix(int addition) {
        int[][] largerMatrix = new int[this.size + addition][this.size + addition];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                largerMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        adjacencyMatrix = largerMatrix;
        this.size += addition;
    }

    public int verticsNum() {
        return vertices.size();
    }

    public int edgesNum() {
        return 0;
    }

    public void print() {

    }

    public void dfs() {

    }

    public void bfs() {

    }

}
