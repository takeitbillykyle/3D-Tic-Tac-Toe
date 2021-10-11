//package TTT3D;

import java.util.Vector;
import java.util.ArrayList;

public class MinMax {
    int value = 0;
    int eval = 0;
    int xCell = 1;
    int oCell = 2;
    int initPlayer;
    int initDepth = 3;
    int base = 10;
    void setPlayer(int player) {
        this.initPlayer = player;
    }

    int alphaBeta(GameState state, int depth, double alpha, double beta, int player) {
        
        Vector < GameState > nextStates = new Vector < > ();
        state.findPossibleMoves(nextStates);
        if (depth == 0 || nextStates.isEmpty()) {
            value = eval(state);
            return value;

        }
        if (player == 1) {
            value = Integer.MIN_VALUE;
            for (GameState child: nextStates) {
                value = Math.max(value, alphaBeta(child, depth - 1, alpha, beta, 2));
                
                alpha = Math.max(alpha, value);
                if (beta <= alpha) {
                    return value;
                }
            }
        } else {
            value = Integer.MAX_VALUE;
            for (GameState child: nextStates) {
                value = Math.min(value, alphaBeta(child, depth - 1, alpha, beta, 1));
               
                beta = Math.min(beta, value);
                if (beta <= alpha) {
                    return value;
                }
            }

        }
        return value;
    }


    int eval(GameState state) {
        //for each row and column and diagonal (10 st) if there are both X's and O's, the score is zero
        //       
        //x=x, y=y, z=z
        eval = 0;
        //För alla yz-slices i genom kuben:
        for (int x = 0; x < 4; x++) {
            //kolla rader
            for (int y = 0; y < 4; y++) {
                int O = 0;
                int X = 0;
                for (int z = 0; z < 4; z++) {
                    if (state.at(x, y, z) == oCell) {
                        O++;
                    } else if (state.at(x, y, z) == xCell) {
                        X++;
                    }
                }

                if (X > 0 && O > 0) {
                    eval += 0; //inget händer med eval, det fanns x och o på raden
                } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                    if (initPlayer == 1) {
                        eval += 1;
                    } else {
                        eval -= 1;
                    }
                } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                    eval += Math.pow(base, X);
                } else if (X == 0 && O > 0) {
                    eval -= Math.pow(base, O);
                }
            }
            //kolla kolumner
            for (int z = 0; z < 4; z++) {
                int O = 0;
                int X = 0;
                for (int y = 0; y < 4; y++) {
                    if (state.at(x, y, z) == oCell) {
                        O++;
                    } else if (state.at(x, y, z) == xCell) {
                        X++;
                    }
                }
                if (X > 0 && O > 0) {
                    eval += 0; //inget händer med eval, det fanns x och o på raden
                } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                    if (initPlayer == 1) {
                        eval += 1;
                    } else {
                        eval -= 1;
                    }
                } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                    eval += Math.pow(base, X);
                } else if (X == 0 && O > 0) {
                    eval -= Math.pow(base, O);
                }
            }
            //kolla diagonal 1
            int O = 0;
            int X = 0;
            for (int y = 0; y < 4; y++) {
                if (state.at(x, y, y) == oCell) {
                    O++;
                } else if (state.at(x, y, y) == xCell) {
                    X++;
                }

            }
            if (X > 0 && O > 0) {
                eval += 0; //inget händer med eval, det fanns x och o på raden
            } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                if (initPlayer == 1) {
                    eval += 1;
                } else {
                    eval -= 1;
                }
            } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                eval += Math.pow(base, X);
            } else if (X == 0 && O > 0) {
                eval -= Math.pow(base, O);
            }

            O = 0;
            X = 0;
            //kolla andra diagonalen
            for (int y = 0; y < 4; y++) {
                if (state.at(x, y, 3 - y) == oCell) {
                    O++;
                } else if (state.at(x, y, 3 - y) == xCell) {
                    X++;

                }
            }
            if (X > 0 && O > 0) {
                eval += 0; //inget händer med eval, det fanns x och o på raden
            } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                if (initPlayer == 1) {
                    eval += 1;
                } else {
                    eval -= 1;
                }
            } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                eval += Math.pow(base, X);
            } else if (X == 0 && O > 0) {
                eval -= Math.pow(base, O);
            }
        }

        //för alla xz-slices genom kuben (kolumnerna från yz-slices har redan tagits)
        for (int y = 0; y < 4; y++) {
            //kolla rader
            for (int x = 0; x < 4; x++) {
                int O = 0;
                int X = 0;
                for (int z = 0; z < 4; z++) {
                    if (state.at(x, y, z) == oCell) {
                        O++;
                    } else if (state.at(x, y, z) == xCell) {
                        X++;
                    }
                }

                if (X > 0 && O > 0) {
                    eval += 0; //inget händer med eval, det fanns x och o på raden
                } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                    if (initPlayer == 1) {
                        eval += 1;
                    } else {
                        eval -= 1;
                    }
                } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                    eval += Math.pow(base, X);
                } else if (X == 0 && O > 0) {
                    eval -= Math.pow(base, O);
                    if(O == 3) eval-=1E6;
                }
            }

            //kolla diagonal 1
            int O = 0;
            int X = 0;
            for (int x = 0; x < 4; x++) {
                if (state.at(x, y, x) == oCell) {
                    O++;
                } else if (state.at(x, y, x) == xCell) {
                    X++;
                }

            }
            if (X > 0 && O > 0) {
                eval += 0; //inget händer med eval, det fanns x och o på raden
            } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                if (initPlayer == 1) {
                    eval += 1;
                } else {
                    eval -= 1;
                }
            } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                eval += Math.pow(base, X);
            } else if (X == 0 && O > 0) {
                eval -= Math.pow(base, O);
                if(O == 3) eval-=1E6;
            }

            O = 0;
            X = 0;
            //kolla andra diagonalen
            for (int x = 0; x < 4; x++) {
                if (state.at(x, y, 3 - x) == oCell) {
                    O++;
                } else if (state.at(x, y, 3 - x) == xCell) {
                    X++;

                }
            }
            if (X > 0 && O > 0) {
                eval += 0; //inget händer med eval, det fanns x och o på raden
            } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                if (initPlayer == 1) {
                    eval += 1;
                } else {
                    eval -= 1;
                }
            } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                eval += Math.pow(base, X);
            } else if (X == 0 && O > 0) {
                eval -= Math.pow(base, O);
                if(O == 3) eval-=1E6;
            }
        }

        //för alla xy-slices (rader och kolumner har redan tagits hand om ovan) bara diagonaler
        for (int z = 0; z < 4; z++) {
            //kolla rader
            //kolla diagonal 1
            int O = 0;
            int X = 0;
            for (int x = 0; x < 4; x++) {
                if (state.at(x, x, z) == oCell) {
                    O++;
                } else if (state.at(x, x, z) == xCell) {
                    X++;
                }

            }
            if (X > 0 && O > 0) {
                eval += 0; //inget händer med eval, det fanns x och o på raden
            } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                if (initPlayer == 1) {
                    eval += 1;
                } else {
                    eval -= 1;
                }
            } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                eval += Math.pow(base, X);
            } else if (X == 0 && O > 0) {
                eval -= Math.pow(base, O);
                if(O == 3) eval-=1E6;
            }

            O = 0;
            X = 0;
            //kolla andra diagonalen
            for (int x = 0; x < 4; x++) {
                if (state.at(x, 3 - x, z) == oCell) {
                    O++;
                } else if (state.at(x, 3 - x, z) == xCell) {
                    X++;

                }
            }
            if (X > 0 && O > 0) {
                eval += 0; //inget händer med eval, det fanns x och o på raden
            } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
                if (initPlayer == 1) {
                    eval += 1;
                } else {
                    eval -= 1;
                }
            } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
                eval += Math.pow(base, X);
            } else if (X == 0 && O > 0) {
                eval -= Math.pow(base, O);
                if(O == 3) eval-=1E6;
            }
        }

        //kolla de fyra huvud-diagonalerna
        //första
        int O = 0;
        int X = 0;
        for (int x = 0; x < 4; x++) {
            if (state.at(x, x, x) == oCell) {
                O++;
            }
        }
        if (X > 0 && O > 0) {
            eval += 0; //inget händer med eval, det fanns x och o på raden
        } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
            if (initPlayer == 1) {
                eval += 1;
            } else {
                eval -= 1;
            }
        } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
            eval += Math.pow(base, X);
        } else if (X == 0 && O > 0) {
            eval -= Math.pow(base, O);
            if(O == 3) eval-=1E6;
        }
        //andra
        O = 0;
        X = 0;
        for (int x = 0; x < 4; x++) {
            if (state.at(x, x, 3 - x) == oCell) {
                O++;
            }
        }
        if (X > 0 && O > 0) {
            eval += 0; //inget händer med eval, det fanns x och o på raden
        } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
            if (initPlayer == 1) {
                eval += 1;
            } else {
                eval -= 1;
            }
        } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
            eval += Math.pow(base, X);
        } else if (X == 0 && O > 0) {
            eval -= Math.pow(base, O);
            if(O == 3) eval-=1E6;
        }
        //tredje
        O = 0;
        X = 0;
        for (int x = 0; x < 4; x++) {
            if (state.at(x, 3 - x, x) == oCell) {
                O++;
            }
        }
        if (X > 0 && O > 0) {
            eval += 0; //inget händer med eval, det fanns x och o på raden
        } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
            if (initPlayer == 1) {
                eval += 1;
            } else {
                eval -= 1;
            }
        } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
            eval += Math.pow(base, X);
        } else if (X == 0 && O > 0) {
            eval -= Math.pow(base, O);
            if(O == 3) eval-=1E6;
        }

        //fjärde
        O = 0;
        X = 0;
        for (int x = 0; x < 4; x++) {
            if (state.at(3 - x, x, x) == oCell) {
                O++;
            }
        }
        if (X > 0 && O > 0) {
            eval += 0; //inget händer med eval, det fanns x och o på raden
        } else if (X == 0 && O == 0) { //eval får en poäng, raden helt tom
            if (initPlayer == 1) {
                eval += 1;
            } else {
                eval -= 1;
            }
        } else if (X > 0 && O == 0) { //om det finns x men inga o, kolla hur många x
            eval += Math.pow(base, X);
        } else if (X == 0 && O > 0) {
            eval -= Math.pow(base, O);
            if(O == 3) eval-=1E6;
        }
        if (initPlayer == 2) {
            eval = -eval;
        }

        return eval;
    }

}
