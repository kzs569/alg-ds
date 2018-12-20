package DataStructure.Graph;

import DataStructure.Tree.MinHeap;
import DataStructure.UnionFind;

import java.util.*;

public class AdjGraph<T extends Comparable<? super T>> implements Graph {
    private ArrayList<Vertex<T>> vertices;
    private boolean[] visited;
    HashMap<Vertex<T>, Dist> dist;

    public AdjGraph() {
        this.vertices = new ArrayList<Vertex<T>>();
    }

    public AdjGraph(LinkedList<Vertex<T>> vertics) {
        for (Vertex<T> v : vertics) {
            addVertex(v);
        }
    }

    public void addVertex(Vertex v) {
        if (v == null) return;
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
            return 0;
        }
        for (Vertex<T> v : vertices) {
            num += v.getOutDegree();
        }
        return num;
    }

    public LinkedList<Vertex<T>> zeroInDegreeVertices() {
        if (vertices == null || vertices.size() == 0) {
            return null;
        }
        LinkedList<Vertex<T>> zeroindegreevertices = new LinkedList<Vertex<T>>();
        for (Vertex<T> v : this.vertices) {
            if (v.getInDegree() == 0) {
                zeroindegreevertices.add(v);
            }
        }
        return zeroindegreevertices.size() != 0 ? zeroindegreevertices : null;
    }

    private void reset() {
        visited = new boolean[this.verticsNum()];

        for (Vertex<T> v :
                vertices) {
            v.setVisited(false);
        }

    }

    public void dfs() {
        reset();
        LinkedList<Vertex<T>> start = zeroInDegreeVertices();
        Stack<Vertex<T>> stack = new Stack<Vertex<T>>();

        if (start == null) {
            return;
        }

        for (Vertex<T> v : start) {
            visited[vertices.indexOf(v)] = true;
            stack.push(v);
            while (!stack.empty()) {
                Vertex<T> root = stack.pop();
                System.out.print(root.getValue().toString() + " ");
                List<Edge> edges = root.getEdges();
                for (Edge edge : edges) {
                    Vertex<T> toV = edge.getDestVertex();
                    if (!visited[vertices.indexOf(toV)]) {
                        visited[vertices.indexOf(toV)] = true;
                        stack.push(toV);
                    }
                }
            }
        }
        System.out.println();
    }

    public void bfs() {
        reset();
        Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
        LinkedList<Vertex<T>> start = zeroInDegreeVertices();

        if (start == null) {
            return;
        }

        for (Vertex<T> v : start) {
            queue.offer(v);
            visited[vertices.indexOf(v)] = true;
            while (!queue.isEmpty()) {
                Vertex root = queue.poll();
                System.out.print(root.getValue().toString() + " ");

                List<Edge> edges = root.getEdges();
                for (Edge e : edges) {
                    Vertex<T> toVertex = e.getDestVertex();
                    if (!visited[vertices.indexOf(toVertex)]) {
                        visited[vertices.indexOf(toVertex)] = true;
                        queue.offer(toVertex);
                    }
                }
            }
        }
        System.out.println();
    }

    public void topologicalSort() {

        //打印初始情况下所有节点的入度
        for (Vertex<T> vertex : vertices) {
            System.out.println(" vertex : " + vertex.getValue().toString() + ", initial indegree:" + vertex.getInDegree());
        }

        Queue<Vertex<T>> queue = zeroInDegreeVertices();

        while (!queue.isEmpty()) {
            Vertex<T> v = queue.poll();
            System.out.print(v.getValue().toString());
            for (Edge edge : v.getEdges()) {
                edge.getDestVertex().decInDegree();
                if (edge.getDestVertex().getInDegree() == 0) {
                    queue.add(edge.getDestVertex());
                }
            }
        }

        for (Vertex<T> vertex : vertices) {
            if (visited[vertices.indexOf(vertex)] == false) {
                System.out.println(" The graph has circle!!");
                break;
            }
        }

        System.out.println();
    }

    public void dijkstra(Vertex<T> vertex) {
        dist = new HashMap<Vertex<T>, Dist>();
        //初始化distHashMap,visited

//        for (Vertex<T> tmp : vertices) {
//            tmp.setVisited(false);
//        }

        for (Vertex v : vertices) {
            dist.put(v, new Dist(v, Integer.MAX_VALUE, null));
        }

        dist.put(vertex, new Dist(vertex, 0, null)); //源点到自身的路径长度设置为0

        MinHeap<Dist> heap = new MinHeap<Dist>(this.edgesNum());
        heap.push(dist.get(vertex));

        for (int i = 0; i < this.verticsNum(); i++) {
            while (!heap.isEmpty()) {
                Dist dist = heap.pop();
                relax(heap, dist.index);
            }
        }
        printDist(vertex);
    }

    private void printDist(Vertex<T> vertex) {
        Iterator iter = dist.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Vertex<T> key = (Vertex<T>) entry.getKey();
            Dist val = (Dist) entry.getValue();
            System.out.println("Vertex " + vertex.getValue().toString() + " to Vertex " + key.getValue().toString() + "'s length is " + val.length);
        }
    }

    private void relax(MinHeap<Dist> heap, Vertex<T> v) {
        for (Edge edge : v.getEdges()) {
            Vertex<T> destVertex = edge.getDestVertex();
            if (dist.get(destVertex).length > dist.get(v).length + edge.getEdgeValue()) {
                Dist tmp = new Dist(destVertex, dist.get(v).length + edge.getEdgeValue(), v);
                dist.put(destVertex, tmp);
                heap.push(tmp);
            }
        }
    }

    private class Dist implements Comparable<Dist> {
        Vertex<T> index;
        int length;
        Vertex<T> pre;

        public Dist(Vertex<T> index, int length, Vertex<T> pre) {
            this.index = index;
            this.length = length;
            this.pre = pre;
        }

        public int compareTo(Dist o) {
            if (this.length < o.length)
                return -1;
            if (this.length > o.length)
                return 1;
            return 0;
        }

        @Override
        public String toString() {
            return "Dist{" +
                    "index=" + index.getValue().toString() +
                    ", length=" + length +
//                    ", pre=" + pre +
                    '}';
        }
    }


    public void floyd() {
        HashMap<FloydVertex, Dist> d = new HashMap<FloydVertex, Dist>();
        //初始化
        for (Vertex v1 : vertices) {
            for (Vertex v2 : vertices) {
                if (v1 == v2) {
                    d.put(new FloydVertex(v1, v2), new Dist(v2, 0, v1));  //源点到自身的路径长度设置为0
                } else {
                    d.put(new FloydVertex(v1, v2), new Dist(v2, Integer.MAX_VALUE, null));
                }
            }
        }
        //将所有边的值更新
        for (Vertex<T> v : vertices) {
            for (Edge e : v.getEdges()) {
                d.put(new FloydVertex(v, e.getDestVertex()), new Dist(e.getDestVertex(), e.getEdgeValue(), v));
            }
        }
        //松弛操作
        for (Vertex<T> v1 : vertices) {
            for (Vertex<T> v2 : vertices) {
                for (Vertex<T> v3 : vertices) {
                    //解决溢出问题
                    if (Integer.valueOf(d.get(new FloydVertex(v2, v3)).length).longValue() >
                            Integer.valueOf(d.get(new FloydVertex(v2, v1)).length).longValue()
                                    + Integer.valueOf(d.get(new FloydVertex(v1, v3)).length).longValue()) {
                        d.put(new FloydVertex(v2, v3), new Dist(v3, d.get(new FloydVertex(v2, v1)).length + d.get(new FloydVertex(v1, v3)).length, v1));
                    }
                }
            }
        }
        printFloyd(d);

    }

    private void printFloyd(HashMap<FloydVertex, Dist> d) {
        Iterator iter = d.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            FloydVertex key = (FloydVertex) entry.getKey();
            Dist val = (Dist) entry.getValue();
            System.out.println(key.toString() + "'s length is " + val.length);
        }
    }

    private class FloydVertex {
        Vertex<T> from;
        Vertex<T> to;

        public FloydVertex(Vertex<T> from, Vertex<T> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FloydVertex that = (FloydVertex) o;
            return Objects.equals(from, that.from) &&
                    Objects.equals(to, that.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "FloydVertex{" +
                    "from=" + from.getValue().toString() +
                    ", to=" + to.getValue().toString() +
                    '}';
        }
    }

    public void prim(Vertex<T> start) {

        reset();
        LinkedList<MSTEdge> mstEdges = new LinkedList<>();
        dist = new HashMap<Vertex<T>, Dist>();
        for (Vertex v : vertices) {
            dist.put(v, new Dist(v, Integer.MAX_VALUE, null));
        }

        start.setVisited(true);
        dist.put(start, new Dist(start, 0, null));

        Vertex<T> v_1 = start;
        for (int i = 0; i < verticsNum() - 1; i++) {
            for (Edge edge : v_1.getEdges()) {
                if (!edge.getDestVertex().isVisited() && dist.get(edge.getDestVertex()).length > edge.getEdgeValue()) {
                    dist.get(edge.getDestVertex()).length = edge.getEdgeValue();
                    dist.get(edge.getDestVertex()).pre = v_1;
                }
            }
            Dist d = minVertex_(dist);
            v_1 = d.index;
            v_1.setVisited(true);
            mstEdges.add(new MSTEdge(d.pre, d.length, d.index));
        }

        for (MSTEdge edge : mstEdges) {
            System.out.println(edge.toString());
        }
    }

    private Dist minVertex_(HashMap<Vertex<T>, Dist> dist) {

        Dist start = new Dist(null, Integer.MAX_VALUE, null);

        Iterator iter = dist.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Vertex<T> key = (Vertex<T>) entry.getKey();
            Dist val = (Dist) entry.getValue();
            if (!key.isVisited()) {
                start = val;
                break;
            }
        }

        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Vertex<T> key = (Vertex<T>) entry.getKey();
            Dist val = (Dist) entry.getValue();
            if (!key.isVisited() && start.length > val.length) {
                start = val;
            }
        }
        return start;
    }

    private class MSTEdge implements Comparable<MSTEdge> {
        Vertex<T> from;
        int edgeValue;
        Vertex<T> to;

        public int compareTo(MSTEdge o) {
            if (this.edgeValue < o.edgeValue)
                return -1;
            if (this.edgeValue > o.edgeValue)
                return 1;
            return 0;
        }

        public MSTEdge(Vertex<T> from, int edgeValue, Vertex<T> to) {
            this.from = from;
            this.edgeValue = edgeValue;
            this.to = to;
        }

        public MSTEdge(Vertex<T> from, Edge<T> edge) {
            this.from = from;
            this.edgeValue = edge.getEdgeValue();
            this.to = edge.getDestVertex();
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MSTEdge mstEdge = (MSTEdge) o;
            return edgeValue == mstEdge.edgeValue &&
                    Objects.equals(from, mstEdge.from) &&
                    Objects.equals(to, mstEdge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, edgeValue, to);
        }

        @Override
        public String toString() {
            return "The MSTEdge " +
                    " Vertex : " + this.from.getValue().toString() +
                    " to Vertex : " + this.to.getValue().toString() +
                    " length is :" + this.edgeValue;
        }

    }


    public void kruskal() {
        reset();
        LinkedList<MSTEdge> mstEdges = new LinkedList<>();
        MinHeap<MSTEdge> heap = new MinHeap<MSTEdge>(this.edgesNum());
        UnionFind<Vertex<T>> uf = new UnionFind(this.vertices);

        for (Vertex<T> v : vertices) {
            for (Edge edge : v.getEdges()) {
                heap.push(new MSTEdge(v,edge.getEdgeValue(),edge.getDestVertex()));
            }
        }

        while(!heap.isEmpty() && mstEdges.size() < verticsNum() - 1){
            MSTEdge e = heap.pop();
            if(uf.connected(e.from,e.to)){
                continue;
            }
            uf.union(e.from,e.to);
            mstEdges.add(e);
        }

        for (MSTEdge edge :
                mstEdges) {
            System.out.println(edge.toString());
        }

    }


//    public int distTo(Vertex<T> from, Vertex<T> to) {
//
//    }
//
//    public boolean hasPathTo(Vertex<T> from, Vertex<T> to) {
//
//    }
//
//    public Iterable<Edge<T>> pathTo(Vertex<T> from, Vertex<T> to) {
//
//    }


    public static void main(String[] args) {
        Vertex<Integer> v_1 = new Vertex(1);
        Vertex<Integer> v_2 = new Vertex(2);
        Vertex<Integer> v_3 = new Vertex(3);
        Vertex<Integer> v_4 = new Vertex(4);
        Vertex<Integer> v_5 = new Vertex(5);

        v_1.addEdge(v_2, 1);
        v_1.addEdge(v_3, 1);
        v_1.addEdge(v_4, 1);
        v_2.addEdge(v_3, 1);
        v_2.addEdge(v_4, 1);
        v_4.addEdge(v_5, 1);

        AdjGraph adj = new AdjGraph();

        adj.addVertex(v_1);
        adj.addVertex(v_2);
        adj.addVertex(v_3);
        adj.addVertex(v_4);
        adj.addVertex(v_5);

        LinkedList<Vertex<Integer>> l = adj.zeroInDegreeVertices();
        System.out.println("The size of zero degree vertices: " + l.size());
        for (Vertex<Integer> v : l) {
            System.out.println(v.toString());
        }

        System.out.println("the breadth-first search sequence is : ");
        adj.bfs();

        System.out.println("the breadth-first search sequence is : ");
        adj.dfs();

        System.out.println("the topological sequence : ");
        adj.topologicalSort();


        System.out.println("The dijsktra sequence : ");
        adj.dijkstra(v_1);

        System.out.println("The floyd sequence : ");
        adj.floyd();

        System.out.println("The prim sequence : ");
        adj.prim(v_1);

        System.out.println("The kruskal sequence : ");
        adj.kruskal();
    }
}
