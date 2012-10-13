/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeproject;

import java.awt.Color;

/**
 *
 * @author william
 */
public class BeaconWall extends RunsEastWestWall
{
    RunsEastWestWall eww = new RunsEastWestWall();
    @Override
    public Color getColor()
    {
        return Color.GREEN;
    }
}
