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

import java.util.Random;
import java.util.Scanner;

/**
 * @author Florian Cassayre
 * Basic game implementation class.
 */
public class Game
{
    private final Random random = new Random();

    private final MorpionAI ai = new MorpionAI(random);

    /**
     * Default constructor.
     * Starts instantly the game.
     */
    public Game()
    {
        Board board = new Board();

        System.out.println("Jeu de Morpion, AI intégrée.");

        board.setTurn(random.nextBoolean());

        while(!board.isFull() && board.getWinner() == null)
        {
            System.out.println();
            System.out.println("C'est au tour de : " + (board.getTurn() ? "X" : "O"));

            System.out.println(board.toString());

            if(board.getTurn())
                board = computerTurn(board);
            else
                board = playerTurn(board);
        }

        System.out.println(board.toString());

        if(board.getWinner() != null)
        {
            System.out.println("Le gagnant est : " + (board.getWinner() ? "X" : "O"));
        }
        else
        {
            System.out.println("La partie se termine en une égalité.");
        }
    }

    /**
     * Returns the board after a player move.
     * @param board initial board
     * @return the played board
     */
    public Board playerTurn(Board board)
    {
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("X : ");
            int x = scanner.nextInt();
            System.out.println("Y : ");
            int y = scanner.nextInt();

            if(!board.canPlay(x, y))
            {
                System.out.println("Impossible de jouer ici.");
                continue;
            }

            board.play(x, y);
            return board;
        }
    }

    /**
     * Returns the board after a computer move.
     * @param board initial board
     * @return the played board
     */
    public Board computerTurn(Board board)
    {
        return ai.playBest(board);
    }

    public static void main(String[] args)
    {
        new Game();
    }
}
