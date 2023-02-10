class Main {
    
    static final int numRows = 21;
    static final int numCols = 41;
    static final int row = 10;
    static final int startCol = 5;
    static final int endCol = 35;
    
    
    public static void main(String[] args) {
        new Controller(new Board(), new Pathfinder(), new Data());
    }
}
