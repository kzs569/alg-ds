package DataStructure.Graph;

import java.util.*;

public class AdjGraph<T extends Comparable <? super T>> implements Graph {
    private Set <Vertex <T>> vertices;

    public AdjGraph() {
        this.vertices = new LinkedHashSet <Vertex <T>>();
    }

    public AdjGraph(LinkedList <Vertex <T>> vertics) {
        for (Vertex <T> v : vertics) {
            addVertex(v);
        }
    }

    private void addVertex(Vertex <T> v) {
        //首先将顶点插入VertexSet
        vertices.add(v);
        //然后插入顶点的边
        if (v.hasEdges()) {
            for (int i = 0; i < v.getEdges().size(); i++) {
                Edge edge = (Edge) v.getEdges().get(i);
                if (!vertices.contains(edge.getDestVertex())) {
                    addVertex(edge.getDestVertex());
                }
                edge.getDestVertex().addInDegree();
            }
        }
    }

    public int verticsNum() {
        if (vertices == null) {
            return 0;
        }
        return vertices.size();
    }

    public int edgesNum() {
        int num = 0;
        if (vertices == null) {
            return num;
        } else {
            for (Vertex <T> v : vertices) {
                num += v.getOutDegree();
            }
        }
        return num;
    }

    public void print() {

    }

    private LinkedList <Vertex <T>> zeroInDegreeVertices() {
        if (vertices == null || vertices.size() == 0) {
            return null;
        }
        LinkedList <Vertex <T>> zeroindegreevertices = new LinkedList <Vertex <T>>();
        for (Vertex <T> v : this.vertices) {
            if (v.getInDegree() == 0) {
                zeroindegreevertices.add(v);
            }
        }
        return zeroindegreevertices.size() != 0 ? zeroindegreevertices : null;
    }

    private void reset() {
        for (Vertex v :
                vertices) {
            v.setVisited(false);
        }
    }

    public void dfs() {
        reset();
        LinkedList <Vertex <T>> start = zeroInDegreeVertices();
        Stack <Vertex <T>> stack = new Stack <Vertex <T>>();

        if (start == null) {
            return;
        }

        for (Vertex <T> v : start) {
            stack.push(v);
            while(!stack.empty()){
                Vertex<T> temp = stack.pop();
                System.out.println(temp.toString());
                temp.setVisited(true);
                List<Edge> edges = temp.getEdges();
                for (Edge edge : edges){
                    stack.push(edge.getDestVertex());
                }
            }
        }

    }

    public void bfs() {
        reset();
        Queue <Vertex <T>> queue = new LinkedList <Vertex <T>>();
        LinkedList <Vertex <T>> start = zeroInDegreeVertices();

        if (start == null) {
            return;
        }

        for (Vertex <T> v : start) {
            queue.offer(v);
            while (!queue.isEmpty()) {
                Vertex root = queue.poll();

                System.out.println(root.toString());

                List <Edge> edges = root.getEdges();
                for (Edge e : edges) {
                    queue.offer(e.getDestVertex());
                }
            }
        }
    }

    public void topologicalSort(){
        reset();
        Queue<Vertex<T>> queue = new LinkedList <Vertex <T>>();
        for (Vertex<T> v:vertices) {
            if(v.getInDegree() == 0){
                queue.add(v);
            }
        }
        while(!queue.isEmpty()){
            Vertex<T> v = queue.element();
            v.setVisited(true);
            for (Edge edge : v.getEdges()){
                edge.getDestVertex().decInDegree();
                if(edge.getDestVertex().getInDegree() == 0){
                    queue.add(edge.getDestVertex());
                }
            }
        }

        for (Vertex<T> vertex : vertices){
            if (vertex.isVisited() == false){
                System.out.println(" The graph has circle!!");
                break;
            }
        }
    }

    public void dijkstra(Vertex<T> vertex){

    }

    public static void main(String[] args) {

    }
}
