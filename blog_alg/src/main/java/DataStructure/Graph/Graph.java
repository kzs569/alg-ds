package DataStructure.Graph;

import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public interface Graph{

    enum GraphType{
        DIRECTED,
        UNDIRECTED
    }

    void addVertex(Vertex _v);

    int verticsNum();

    int edgesNum();

}
