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

import java.util.*;

/**
 * @author Florian Cassayre
 * The morpion AI core.
 */
public class MorpionAI
{
    private final Random random;

    /**
     * Default constructor.
     */
    public MorpionAI()
    {
        this(new Random());
    }

    /**
     * Initializes the AI with a Random object.
     * @param random
     */
    public MorpionAI(Random random)
    {
        this.random = new Random();
    }

    /**
     * Plays with the perfect AI, using random values when multiple exact moves are available.
     * The parameter is intact, the returned object only has been modified.
     * @param board the board to analyze
     * @return a board with the best move
     */
    public Board playBest(Board board)
    {
        List<BoardValue> moves = new ArrayList<BoardValue>();

        for(int i = 0; i < board.countAvaibleMoves(); i++)
        {
            Board clone = new Board(board);
            clone.play(i);
            moves.add(new BoardValue(clone, getVal(clone)));
        }

        List<BoardValue> same = new ArrayList<BoardValue>();

        for(BoardValue boardValue : moves)
        {
            if(same.size() == 0)
            {
                same.add(boardValue);
                continue;
            }

            if(boardValue.getValue() > same.get(0).getValue())
            {
                same.clear();
                same.add(boardValue);
                continue;
            }

            if(boardValue.getValue() == same.get(0).getValue())
            {
                same.add(boardValue);
            }
        }

        return same.size() == 0 ? board : (same.get(random.nextInt(same.size())).getBoard());
    }

    /**
     * Gets the power of a move.
     * @param board the board to analyze
     * @return the power of this move
     */
    public int getVal(Board board)
    {
        if(board.getWinner() != null)
        {
            if(board.getWinner())
                return 5000;
            else
                return -5000;
        }
        if(board.isFull())
            return 0;

        int sum = -1000;

        for(int i = 0; i < board.countAvaibleMoves(); i++)
        {
            Board clone = new Board(board);
            clone.play(i);
            sum += getVal(clone);
        }

        return (sum / board.countAvaibleMoves());
    }

    /**
     * A pair class which contains a board and its power.
     */
    public class BoardValue
    {
        private final Board board;
        private final int value;

        public BoardValue(Board board, int val)
        {
            this.board = board;
            this.value = val;
        }

        public Board getBoard()
        {
            return board;
        }

        public int getValue()
        {
            return value;
        }
    }
}
