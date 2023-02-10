import java.awt.*;


class Node {
    
    int distance;
    Point prev;
    Point location;
    
    
    Node(int distance, Point prev, Point location) {
        this.distance = distance;
        this.prev = prev;
        this.location = location;
    }
    
    
    void updateDistance(int distance) {
        this.distance = distance;
    }
    
}
