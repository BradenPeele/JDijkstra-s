import java.awt.event.*;


class Controller {
    
    Board board;
    Pathfinder pathfinder;
    Data data;
    boolean running, complete;
    
    
    Controller(Board board, Pathfinder pathfinder, Data data) {
        this.board = board;
        this.pathfinder = pathfinder;
        this.data = data;
        this.board.addListener(new ControllerListener());
        pathfinder.setController(this);
        running = false;
    }
    
    
    void updateBoard(int row, int col) {
        board.updateBoard(row, col);
    }
    
    
    void drawPath(int row, int col) {
        board.drawPath(row, col);
    }
   
    
    class ControllerListener implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equals("Start") && !running) {
                running = true;
                pathfinder.setData(data);
                pathfinder.dijkstra();
            }
            
            if(e.getActionCommand().equals("ResetSearch")) {
                running = false;
                pathfinder.stopTimer();
                data.reset(false);
                board.resetSearch(data.walls);
            }
            
            if(e.getActionCommand().equals("ResetAll")) {
                running = false;
                pathfinder.stopTimer();
                data.reset(true);
                board.resetAll();
            }
            
            if(!running)
                for(int row = 0; row < Main.numRows; row++)
                    for(int col = 0; col < Main.numCols; col++)
                        if(e.getSource() == board.cells[row][col]) {
                            if(!data.walls[row][col])
                                data.walls[row][col] = true;
                            else
                                data.walls[row][col] = false;
                            
                            board.addWall(row, col);
                        }
        }
    }
}
