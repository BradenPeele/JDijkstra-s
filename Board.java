import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;


class Board extends JFrame {
    
    JPanel cellPanel;
    JButton startButton, resetSearchButton, resetAllButton;
    JButton[][] cells;
    
    
    Board() {
        setupButtonArray();
        setupButtonPanel();
        setupFrame();
    }
    
    
    void setupButtonArray() {
        
        cellPanel = new JPanel();
        cellPanel.setLayout(new GridLayout(Main.numRows, Main.numCols));
        
        cells = new JButton[Main.numRows][Main.numCols];
        
        for(int row = 0; row < Main.numRows; row++)
            for(int col = 0; col < Main.numCols; col++) {
                cells[row][col] = new JButton();
                cells[row][col].setBorder(new LineBorder(Color.BLACK));
                cellPanel.add(cells[row][col]);
            }
        
        
        cells[Main.row][Main.startCol].setBackground(Color.GREEN);
        cells[Main.row][Main.startCol].setOpaque(true);
        cells[Main.row][Main.startCol].setEnabled(false);
        cells[Main.row][Main.endCol].setBackground(Color.RED);
        cells[Main.row][Main.endCol].setOpaque(true);
        cells[Main.row][Main.endCol].setEnabled(false);
        
        
        add(cellPanel, BorderLayout.CENTER);
    }
    
    
    void setupButtonPanel() {
        
        JPanel buttonPanel = new JPanel();
        
        startButton = new JButton("Start");
        startButton.setActionCommand("Start");
        buttonPanel.add(startButton);
        
        resetSearchButton = new JButton("Reset Search");
        resetSearchButton.setActionCommand("ResetSearch");
        buttonPanel.add(resetSearchButton);
        
        resetAllButton = new JButton("Reset All (including walls)");
        resetAllButton.setActionCommand("ResetAll");
        buttonPanel.add(resetAllButton);
        
        add(buttonPanel, BorderLayout.NORTH);
    }
    
    
    void setupFrame() {
        
        Toolkit tk;
        Dimension d;
        
        tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        
        double width = d.width / 1.5;
        double height = d.height / 1.5;
        
        setSize((int)width, (int)height);
        setLocation(d.width / 4, d.height / 4);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
        setResizable(false);
    }
    
    
    void addListener(ActionListener listener) {
        
        startButton.addActionListener(listener);
        resetSearchButton.addActionListener(listener);
        resetAllButton.addActionListener(listener);
        
        for(int row = 0; row < Main.numRows; row++)
            for(int col = 0; col < Main.numCols; col++)
                cells[row][col].addActionListener(listener);
    }
    
    
    void addWall(int row, int col) {
        if(cells[row][col].getBackground() == Color.BLACK) {
            cells[row][col].setBackground(Color.WHITE);
            cells[row][col].setOpaque(true);
        }
        else {
            cells[row][col].setBackground(Color.BLACK);
            cells[row][col].setOpaque(true);
        }
    }
    
    
    void resetSearch(boolean[][] wall) {
        for(int row = 0; row < Main.numRows; row++)
            for(int col = 0; col < Main.numCols; col++)
                if(!wall[row][col]) {
                    cells[row][col].setBackground(Color.WHITE);
                    cells[row][col].setOpaque(true);
                }
        
        cells[Main.row][Main.startCol].setBackground(Color.GREEN);
        cells[Main.row][Main.startCol].setOpaque(true);
        cells[Main.row][Main.endCol].setBackground(Color.RED);
        cells[Main.row][Main.endCol].setOpaque(true);
        
        repaint();
    }
    
    
    void resetAll() {
        for(int row = 0; row < Main.numRows; row++)
            for(int col = 0; col < Main.numCols; col++) {
                cells[row][col].setBackground(Color.WHITE);
                cells[row][col].setOpaque(true);
            }
        
        cells[Main.row][Main.startCol].setBackground(Color.GREEN);
        cells[Main.row][Main.startCol].setOpaque(true);
        cells[Main.row][Main.endCol].setBackground(Color.RED);
        cells[Main.row][Main.endCol].setOpaque(true);
        
        repaint();
    }
    
    
    void updateBoard(int row, int col) {
        cells[row][col].setBackground(Color.YELLOW);
        cells[row][col].setOpaque(true);
    }
    
    
    void drawPath(int row, int col) {
        cells[row][col].setBackground(Color.BLUE);
        cells[row][col].setOpaque(true);
    }
}
