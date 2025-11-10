package practice.matrix;

/**
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board
 * position during the course of a valid tic-tac-toe game.
 *
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares ' '.
 * The first player always places 'X' characters, while the second player always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 */
public class ValidTicToeTac {
    public static void main(String args[]) {
        String[][] x1 = {{"0  "}, {"   "}, {"   "}};
        String[][] x2 = {{"XOX"}, {" X "}, {"   "}};
        String[][] x3 = {{"XOX"}, {"O O"}, {"XOX"}};
    }

    public static boolean isValid(String[][] x) {

        /**
         * The following rules must be taken care of
         * 1. if only o --> false
         * 2. if count(0) > count(X) --> false
         * 3. if count(X) -1 != count(0) --> false
         *
         * Now check win
         *  1. if any rows all 0 or X---> win
         *  2. if any cols all 0 or X---> win
         *  3. if any diagonal is all 0 or X ---> win
         *  
         *  4. if X has won and there are more 0 --> false
         *  5. if O has won and there are more X --> false
         */

        int count0 = 0, countX=0;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(x[i][j].equalsIgnoreCase("O")) {
                    count0++;
                } else {
                    countX++;
                }
            }
        }

        if((count0 > countX) || (countX != count0+1)) {
            return false;
        }
        
        if((hasWon("X") && countX -1  != count0)) {
            return false;
        }

        return !(hasWon("O") && countX != count0);
    }

    private static boolean hasWon(String x) {
        // check the condition of winning here
        return true;
    }


}
