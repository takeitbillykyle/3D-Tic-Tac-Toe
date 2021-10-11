//package TTT3D;

import java.util.*;

public class Player {
    int printminimax;
    /**
     * Performs a move
     *
     * @param gameState
     *            the current state of the board
     * @param deadline
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */
    public GameState play(final GameState gameState, final Deadline deadline) {
        Vector < GameState > nextStates = new Vector < GameState > ();
        gameState.findPossibleMoves(nextStates);

        if (nextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(gameState, new Move());
        }

        //alpha-beta pruning on the current gamState
        //alphaBeta returns the minimax of each possible next gamestate
        //take the nextPossibleGameState that gives the highest minimax value
        //jag skickar in endast ett gamestate i alpha-beta, så kör den rekursion på det statet.

        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        int best = -10000000;
        int argmax = 0;
        int depth = 0;
        TTT3D mm = new TTT3D();
        mm.setInitial(gameState.getNextPlayer());
        for (int state = 0; state < nextStates.size(); state++) {
            int minimax = 0;
            minimax = mm.alphaBeta(nextStates.elementAt(state), depth, Integer.MIN_VALUE, Integer.MAX_VALUE, gameState.getNextPlayer());
            if (minimax > best) {
                best = minimax;
                printminimax = minimax;
                argmax = state;
                if(minimax == 1000){return nextStates.elementAt(argmax);}
            }


        }

        return nextStates.elementAt(argmax);
    }
    /*
    public static void main(String[] args){
        TreeSet<Integer> test = new TreeSet<>();
        if(!test.isEmpty() && test.first() == 1){
            System.err.println(test.first());
        }
    }
*/
}
