package me.cassayre.florian.MorpionGame;/*
 * Copyright © 2015 Florian Cassayre (florian.cassayre[at]gmail.com)
 *
 * This software is governed by the CeCILL license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

/**
 * @author Florian Cassayre
 * Represents a Morpion-game board.
 */
public class Board
{
    private Boolean[][] board = new Boolean[3][3];

    private boolean turn = false;

    /**
     * Default constructor.
     */
    public Board()
    {

    }

    /**
     * Clones a board.
     * @param clone the board to copy
     */
    public Board(Board clone)
    {
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
            {
                board[x][y] = clone.board[x][y];
            }
        turn = clone.turn;
    }

    /**
     * Checks if a move is legal.
     * @param x coordinate x in the board
     * @param y coordinate y in the board
     * @return true if the move is legal for the current player, false else
     */
    public boolean canPlay(int x, int y)
    {
        return !(x < 0 || y < 0 || x >= 3 || y >= 3) && board[x][y] == null;
    }

    /**
     * Plays for the current player to the given location on the board.
     * @param x coordinate x in the board
     * @param y coordinate y in the board
     */
    public void play(int x, int y)
    {
        board[x][y] = turn;
        turn = !turn;
    }

    /**
     * Counts the total of empty places.
     * @return the number of free spots
     */
    public int countAvaibleMoves()
    {
        int n = 0;

        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                if(board[x][y] == null)
                    n++;

        return n;
    }

    /**
     * Plays to the first n legal spot.
     * @param n the index
     */
    public void play(int n)
    {
        int i = 0;

        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                if(board[x][y] == null)
                {
                    if(i == n)
                    {
                        play(x, y);
                        return;
                    }
                    i++;
                }
    }

    /**
     * Returns the winner of the game.
     * If the game isn't finished yet, the value will be null.
     * @return who
     */
    public Boolean getWinner()
    {
        for(int i = 0; i < 3; i++)
        {
            if(board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2])
                return board[i][0];

            if(board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i])
                return board[0][i];
        }

        if(board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return board[0][0];

        if(board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return board[0][2];

        return null;
    }

    /**
     * Checks if the board is full ; no one can make a move.
     * @return true if the board is full, false else
     */
    public boolean isFull()
    {
        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                if(board[x][y] == null)
                    return false;
        return true;
    }

    /**
     * Sets the player's turn.
     * @param turn who
     */
    public void setTurn(boolean turn)
    {
        this.turn = turn;
    }

    /**
     * Gets the current player that have to play.
     * @return who
     */
    public boolean getTurn()
    {
        return turn;
    }

    /**
     * Returns a basic representation of the board, mostly for debugging purposes.
     * @return the board
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                builder.append(board[x][y] == null ? " " : (board[x][y] ? "X" : "O"));
                if(x != 2)
                    builder.append("|");
                else
                    builder.append("\n");
            }
            if(y != 2)
                builder.append("-----\n");
        }

        return builder.toString();
    }
}
