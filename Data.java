import java.util.*;


class Data {
    
    Node[][] nodes;
    PriorityQueue<Node> pQueue;
    boolean[][] walls;
 
    
    Data() {
        nodes = new Node[Main.numRows][Main.numCols];
        pQueue = new PriorityQueue<Node>(5, new NodeComparator());
        walls = new boolean[Main.numRows][Main.numCols];
    }
    
    void reset(boolean resetWalls) {
        nodes = new Node[Main.numRows][Main.numCols];
        pQueue = new PriorityQueue<Node>(5, new NodeComparator());
        
        if(resetWalls)
            walls = new boolean[Main.numRows][Main.numCols];
    }
}
