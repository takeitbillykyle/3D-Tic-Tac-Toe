import java.util.Vector;

public class TTT3D {
    int value;
    int initialPlayer;
    int otherPlayer;
    int basis = 4;
    static int[] Xpoints = {0,10,20,100,Integer.MAX_VALUE};
    static int[] Opoints = {0,10,20,100,Integer.MAX_VALUE};
    void setInitial(int p){
        this.initialPlayer = p;
        this.otherPlayer = p == 1 ? 2 : 1;
    }
    int alphaBeta(GameState state, int depth, double alpha, double beta, int player){
        Vector<GameState> nextStates = new Vector<>();
        state.findPossibleMoves(nextStates);
        //if(value>=9999999){return value;}
        if(depth == 0 || nextStates.isEmpty() || state.isEOG()){
            value = eval(state,player);  
            return value;
        }
        if(player == 1){
            value = Integer.MIN_VALUE;
            for(GameState child : nextStates){
                value = Math.max(value, alphaBeta(child,depth-1,alpha,beta,2));
                alpha = Math.max(alpha,value);
                if(beta<=alpha){
                    break;
                }
            }

        }
        else{
            value = Integer.MAX_VALUE;
            for(GameState child : nextStates){
                value = Math.min(value, alphaBeta(child,depth-1,alpha,beta,1));
                beta = Math.min(beta, value);
                if(beta<=alpha){
                    break;
                }
            }

        }
        return value;
    }
    int eval(GameState state,int player){
        value = 0;
        if(state.isXWin()) value = 1000;
        if(state.isOWin()) value = -1000;
    return value;
    }
    public static void main(String[] args){
        int x = 1<<0;
        int y = 1<<1;
        int z = 1<<2;
        System.err.println(x +", " + y + ", " + z);
    }
}
