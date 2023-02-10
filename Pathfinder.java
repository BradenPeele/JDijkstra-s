import java.awt.*;
import java.awt.event.*;


class Pathfinder {
    
    Controller controller;
    Data data;
    Point current;
    boolean found;
    
    javax.swing.Timer timer;
    int row, col, currentDistance, rowIncrement, colIncrement;
    
    int tempRow;
    Point start;
   
    
    void setController(Controller controller) {
        this.controller = controller;
    }
    
    
    void setData(Data data) {
        this.data = data;
        current = new Point(Main.row, Main.startCol);
        found = false;
    }
    
    
    void stopTimer() {
        try {
            timer.stop();
        }
        catch (Exception e) {
        }
    }
    
    
    void dijkstra() {
        // set start node
        data.nodes[Main.row][Main.startCol] = new Node(0, current, new Point(Main.row, Main.startCol));
        
        // setup timer for dijkstra
        timer = new javax.swing.Timer(15, new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               dijkstraLoop();
           }
        });
       
        timer.start();
    }
    
    
   void dijkstraLoop() {
       
        // visit node
        row = (int)current.getX();
        col = (int)current.getY();
        currentDistance = data.nodes[(int)current.getX()][(int)current.getY()].distance;
       
       
        // set distance of all neighbors and add nodes to unvisited
        for(int n = 0; n < 4; n++) {
            
            if(n == 0) {
                rowIncrement = -1;
                colIncrement = 0;
            }
            
            if(n == 1) {
                rowIncrement = 0;
                colIncrement = 1;
            }
            
            if(n == 2) {
                rowIncrement = 1;
                colIncrement = 0;
            }
            
            if(n == 3) {
                rowIncrement = 0;
                colIncrement = -1;
            }
           
            
            try {
                if(data.walls[row+rowIncrement][col+colIncrement]) {
                    continue;
                }
               
                if(data.nodes[row+rowIncrement][col+colIncrement].distance > currentDistance+1) {
                    data.nodes[row+rowIncrement][col+colIncrement].distance = currentDistance+1;
                    data.nodes[row+rowIncrement][col+colIncrement].prev = current;
                }
            }
            catch(NullPointerException npe) {
               
                data.nodes[row+rowIncrement][col+colIncrement] = new Node(currentDistance+1, current, new Point(row+rowIncrement, col+colIncrement));
                data.pQueue.add(data.nodes[row+rowIncrement][col+colIncrement]);
                
                if(row+rowIncrement == Main.row && col+colIncrement == Main.endCol) {
                    found = true;
                    break;
                }
                
                controller.updateBoard(row+rowIncrement, col+colIncrement);
            }
            catch(ArrayIndexOutOfBoundsException be) {
            }
        }
       
       // end not found
        if(data.pQueue.isEmpty())
            timer.stop();
        
       // visit unvisited node with smallest distance
        if(!found) {
            current = data.pQueue.poll().location;
        }
       // end found
       else {
           timer.stop();
           start = new Point(Main.row, Main.startCol);
           timer = new javax.swing.Timer(30, new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                  backtrack();
              }
           });
           timer.start();
       }
    }
    
    
    void backtrack() {
        controller.drawPath(row, col);
        tempRow = row;
        row = (int)data.nodes[row][col].prev.getX();
        col = (int)data.nodes[tempRow][col].prev.getY();
        
        if(data.nodes[row][col].location.equals(start))
            timer.stop();
    }
}
