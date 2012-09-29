//copyright William Zulueta 2012
package mazeproject;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

public class MazeJPanel2 extends javax.swing.JPanel
{
    private MazeBuilder mazeBuilder = new MazeBuilder();
    private MazePiece startingPiece = mazeBuilder.startingMazePiece();
    private double centerX = 500;
    private double centerY = 500;
    private Set<MazePiece> drawnPieces = new HashSet();

    public MazeJPanel2()
    {
        initComponents();
//        centerX = this.getWidth()/2;
//        centerY = this.getHeight()/2;
    }

    public void drawMaze(MazePiece currentPiece, double x, double y, Graphics2D g2)
    {
        //draw current rectangle |__|
        if (!drawnPieces.contains(currentPiece))
        {
            drawnPieces.add(currentPiece);
            Rectangle r = new Rectangle((int) x, (int) y, (int) currentPiece.getWidth(), (int) currentPiece.getHeight());
            g2.setPaint(currentPiece.getColor());
            g2.fill(r);
            //stop when wall
            if (currentPiece instanceof EmptySpace)
            {
                //go east
                drawMaze(currentPiece.getEastNeighbor(), x + currentPiece.getWidth(), y, g2);
                //go north
                drawMaze(currentPiece.getNorthNeighbor(), x, y - currentPiece.getNorthNeighbor().getHeight(), g2);
                //west
                drawMaze(currentPiece.getWestNeighbor(), x - currentPiece.getWestNeighbor().getWidth(), y, g2);
                //south
                drawMaze(currentPiece.getSouthNeighbor(), x, y + currentPiece.getHeight(), g2);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setBackground(new java.awt.Color(204, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 800));
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                formMouseClicked(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter()
        {
            public void componentResized(java.awt.event.ComponentEvent evt)
            {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt)
            {
                formComponentShown(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1121, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 591, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_formMouseClicked
    {//GEN-HEADEREND:event_formMouseClicked
        drawnPieces.clear();
        drawMaze(startingPiece, centerX, centerY, (Graphics2D) this.getParent().getGraphics());
    }//GEN-LAST:event_formMouseClicked

    private void formComponentResized(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentResized
    {//GEN-HEADEREND:event_formComponentResized
        drawnPieces.clear();
        drawMaze(startingPiece, centerX, centerY, (Graphics2D) this.getParent().getGraphics());
    }//GEN-LAST:event_formComponentResized

    private void formComponentShown(java.awt.event.ComponentEvent evt)//GEN-FIRST:event_formComponentShown
    {//GEN-HEADEREND:event_formComponentShown
        drawnPieces.clear();
        drawMaze(startingPiece, centerX, centerY, (Graphics2D) this.getParent().getGraphics());
    }//GEN-LAST:event_formComponentShown
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
