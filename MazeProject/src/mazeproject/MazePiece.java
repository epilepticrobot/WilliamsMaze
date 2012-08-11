
package mazeproject;

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
   
   
}
