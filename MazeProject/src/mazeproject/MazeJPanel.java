//copyright William Zulueta 2012
package mazeproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class MazeJPanel extends JPanel
{
    private MazeBuilder mazeBuilder = new MazeBuilder();
    private MazePiece startingPiece;
    private double x;
    private double y;

    protected void paintComponet(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        startingPiece = mazeBuilder.startingMazePiece();
        drawMaze(startingPiece, x, y, g2);
    }
    
    public void drawMaze(MazePiece startingPiece, double x, double y, Graphics2D g2)
    {
        Rectangle r = new Rectangle(10,20,30,40);
        g2.setPaint(Color.BLUE);
        g2.fill(r);
    }
}
