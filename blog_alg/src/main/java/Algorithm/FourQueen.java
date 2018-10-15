package Algorithm;

public class FourQueen {
    private static int[][] checkBoard;
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public FourQueen(int checkBoardLength){
        checkBoard = new int[checkBoardLength][checkBoardLength];

        for (int i = 0; i < checkBoard.length; i++) {
            for (int j = 0; j < checkBoard.length; j++) {
                checkBoard[i][j] = 0;
            }
        }
    }

    private static void printCheckBoard(int[][] checkBoard){
        for (int i = 0; i < checkBoard.length; i++) {
            for (int j = 0; j < checkBoard.length; j++) {
                System.out.print(checkBoard[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    private static boolean isCorrect(int i, int j, int[][] checkBoard){
        int row, column;
        //判断行
        for (row = i, column = 0; column < checkBoard.length; column++) {
            if(checkBoard[row][column] == 1 && column != j){
                return false;
            }
        }
        //判断列
        for (column = j, row = 0; row < checkBoard.length; row++) {
            if(checkBoard[row][column] == 1 && row != i){
                return false;
            }
        }
        //判断左上方
        for(row = i - 1, column = j - 1; row >= 0 && column >= 0; row--, column--){
            if(checkBoard[row][column] == 1){
                return false;
            }
        }
        //判断左下方
        for(row = i - 1, column = j + 1; row >= 0 && column < checkBoard.length; row--, column++){
            if(checkBoard[row][column] == 1){
                return false;
            }
        }
        //判断右上方
        for(row = i + 1, column = j - 1; row < checkBoard.length && column >= 0; row++, column--){
            if(checkBoard[row][column] == 1){
                return false;
            }
        }
        //判断右下方
        for(row = i + 1, column = j + 1; row < checkBoard.length && column < checkBoard.length; row++, column++){
            if(checkBoard[row][column] == 1){
                return false;
            }
        }
        return true;
    }

    public static void fourQueen(int j, int[][] checkBoard){

        if(j==checkBoard.length){//递归结束条件
            printCheckBoard(checkBoard);
            count++;
            return ;
        }

        for(int i=0; i<checkBoard.length; i++){
            if(isCorrect(i, j, checkBoard)){//如果Q[i][j]可以放置皇后
                checkBoard[i][j]=1;//放置皇后
                fourQueen(j+1, checkBoard);//递归深度优先搜索解空间树
                checkBoard[i][j]=0;//这句代码就是实现回溯到上一层
            }
        }
    }

    public static void main(String[] args) {

        int queenNumber = 4;

        FourQueen fourQueen = new FourQueen(queenNumber);
        fourQueen.fourQueen(0,checkBoard);
        System.out.println("The solution about " + String.valueOf(queenNumber) +" queen question is : " + String.valueOf(getCount()));
    }
}
