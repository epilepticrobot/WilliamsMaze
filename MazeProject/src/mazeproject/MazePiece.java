//copyright William Zulueta 2012
package mazeproject;

import java.awt.Color;

public interface MazePiece
{
   public MazePiece getNorthNeighbor();
   public void setNorthNeighbor(MazePiece mazePieceWMPN);
   public MazePiece getSouthNeighbor();
   public void setSouthNeighbor(MazePiece mazePieceWMPS);
   public MazePiece getEastNeighbor();
   public void setEastNeighbor(MazePiece mazePieceWMPE);
   public MazePiece getWestNeighbor();
   public void setWestNeighbor(MazePiece mazePieceWMPW);
   public double getWidth();
   public double getHeight();
   public Color getColor();
   public void changeColor(Color c);
  
   
   
   
}
