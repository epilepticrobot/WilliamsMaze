
package mazeproject;

public class MazeBuilder
{
    
    public void simpleBuild()
    {
        MazePiece emptySpaceN = new EmptySpace(); //create empty space north
        MazePiece emptySpaceS = new EmptySpace(); //create empty space soouth
        emptySpaceN.setSouthNeighbor(emptySpaceS);//connect empty space south with north
        emptySpaceS.setNorthNeighbor(emptySpaceN);
        //connect walls to north empty space
        emptySpaceN.setNorthNeighbor(new RunsEastWestWall());//connect north wall with north empty space
        emptySpaceN.setEastNeighbor(new RunsNorthSouthWall());//connet east wall to north empty space
        emptySpaceN.setWestNeighbor(new RunsNorthSouthWall());//connet west wall to north empty space
        //connect walls to south empty space
        emptySpaceS.setSouthNeighbor(new RunsEastWestWall());//connect south wall with south empty space
        emptySpaceS.setEastNeighbor(new RunsNorthSouthWall());//connect south walls with east empty space
        emptySpaceS.setWestNeighbor(new RunsNorthSouthWall());//connect south walls with west empty space
    }
}
