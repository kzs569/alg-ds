package DataStructure.Graph;

import java.io.IOException;
import java.util.Iterator;

public interface Graph {

    enum GraphType{
        DIRECTED,
        UNDIRECTED
    }

    int verticsNum();
    int edgesNum();

    float INFINITY = 9999;

    void print();

    void dfs();
    void bfs();
//    void dijkstra(int s,int t);
//    void floyd();
//    void prim();
//    void kruskal();
}
