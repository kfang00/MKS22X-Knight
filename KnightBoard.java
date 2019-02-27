import java.util.ArrayList;
import java.util.Arrays;

public class KnightBoard {
  private int[][]board;
  private int[][]moves;
  private int count;
  private ArrayList<Arrays> coor;

  //@throws IllegalArgumentException when either parameter is negative;
  public KnightBoard(int startingRows,int startingCols) {
    if ((startingRows <= 0) || (startingCols <= 0)) {
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    moves = new int[startingRows][startingCols];
    coor = new ArrayList<Arrays>(8);
    removeNull(board);
    removeNull(moves);
    outgoingMoves();
  }

  public void outgoingMoves() {
    moves[0][0] = 2; moves[0][moves[0].length - 1] = 2; moves[moves.length - 1][0] = 2; moves[moves.length - 1][moves[0].length - 1] = 2;
    moves[0][1] = 3; moves[0][moves[0].length - 2] = 3; moves[moves.length - 1][1] = 3; moves[moves.length - 1][moves[0].length - 2] = 3;
    moves[1][0] = 3; moves[1][moves[0].length - 1] = 3; moves[moves.length - 2][0] = 3; moves[moves.length - 2][moves[0].length - 1] = 3;
    moves[1][1] = 4; moves[1][moves[0].length - 2] = 4; moves[moves.length - 2][1] = 4; moves[moves.length - 2][moves[0].length - 2] = 4;
    for (int a = 0; a < moves.length; a++) {
      for (int b = 0; b < moves[0].length; b++) { 
        if (moves[a][b] == 0) {
	  if ((a == 0) || (a == (moves.length - 1)) || (b == 0) || (b == (moves[0].length - 1))) {
	    moves[a][b] = 4;
	  }
	  else if ((a == 1) || (a == (moves.length - 2)) || (b == 1) || (b == (moves[0].length - 2))) {
	    moves[a][b] = 6;
	  }
	  else {
	    moves[a][b] = 8;
	  }
   	}
      }
    }

  }

  public void removeNull(int[][] board) {
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board[0].length; b++) {
	       board[a][b] = 0;
      }
    }
  }

  public String toString(){
    String s = "";
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board[0].length; b++) {
        if (board[a][b] == 0) {
          s += "  _";
        }
        else if (board.length * board[0].length < 10) {
          s += "  " + board[a][b];
        }
        else {
          if ((board[a][b] + "").length() > 1) {
            s += " " + board[a][b];
          }
          else {
            s += "  " + board[a][b];
          }
        }
      }
      s += "\n";
    }
    return s;
  }

  public String toStringC(){
    String s = "";
    for (int a = 0; a < moves.length; a++) {
      for (int b = 0; b < moves[0].length; b++) {
        s += "  " + moves[a][b];
      }
      s += "\n";
    }
    return s;
  }

  private boolean checkZero() {
    for (int a = 0; a < board.length; a++) {
      for (int b = 0; b < board[0].length; b++) {
        if (board[a][b] != 0) {
          return false;
        }
      }
    }
    return true;
  }

  //pubilc void findCoor() {

//  }

  public boolean solve(int startingRow, int startingCol) {
    if (!checkZero()) {
      throw new IllegalStateException();
    }
    if ((startingRow < 0) || (startingCol < 0) || (startingRow >= board.length) || (startingCol >= board[0].length)) {
      throw new IllegalArgumentException();
    }
    return solveH(startingRow, startingCol, 1);
  }

  public boolean solveH(int staRow, int staCol, int level) {
    if (level == ((board.length * board[0].length) + 1)) {
      return true;
    }
    if ((staRow >= board.length) || (staCol >= board[0].length) || (staRow < 0) || (staCol < 0)) {
      return false;
    }
    if (placeKnight(staRow, staCol, level)) {
      if (solveH(staRow + 2, staCol + 1, level + 1)) {
	return true;
      }
      if (solveH(staRow + 2, staCol - 1, level + 1)) {
	return true;
      }
      if (solveH(staRow + 1, staCol + 2, level + 1)) {
	return true;
      }
      if (solveH(staRow - 1, staCol + 2, level + 1)) {
	return true;
      }
      if (solveH(staRow - 2, staCol + 1, level + 1)) {
	return true;
      }
      if (solveH(staRow - 2, staCol - 1, level + 1)) {
	return true;
      }
      if (solveH(staRow + 1, staCol - 2, level + 1)) {
	return true;
      }
      if (solveH(staRow - 1, staCol - 2, level + 1)) {
	return true;
      }
      removeKnight(staRow, staCol);
    }
    return false;
  }

  public boolean placeKnight(int staRow, int staCol, int level) {
    if (board[staRow][staCol] != 0) {
      return false;
    }
    board[staRow][staCol] = level;
    return true;
  }

  public boolean removeKnight(int staRow, int staCol) {
    if (board[staRow][staCol] != 0) {
      board[staRow][staCol] = 0;
      return true;
    }
    return false;
  }

  public int countSolutions(int startingRow, int startingCol) {
    if (!checkZero()) {
      throw new IllegalStateException();
    }
    if ((startingRow < 0) || (startingCol < 0) || (startingRow >= board.length) || (startingCol >= board[0].length)) {
      throw new IllegalArgumentException();
    }
    //if ((startingRow > board.length) || (startingCol > board[0].length)) {
    //  return countH(startingRow, startingCol, 1);
    //}
    //for (int a = startingRow; a < board.length; a++) {
    // for (int b = startingCol; b < board[0].length; b++) {
	//count = count + countSolutions(a, b);
      //}
    //}
    return countH(startingRow, startingCol, 1) / 8;
  }

  public int countH(int staRow, int staCol, int level) {
    if (level == ((board.length * board[0].length) + 1)) {
      return 1;
    }
    if ((staRow >= board.length) || (staCol >= board[0].length) || (staRow < 0) || (staCol < 0)) {
      return 0;
    }
    count = 0;
    if (placeKnight(staRow, staCol, level)) {
      count = count + countH(staRow + 2, staCol + 1, level + 1);
      count = count + countH(staRow + 2, staCol - 1, level + 1);
      count = count + countH(staRow + 1, staCol + 2, level + 1);
      count = count + countH(staRow - 1, staCol + 2, level + 1);
      count = count + countH(staRow - 2, staCol + 1, level + 1);
      count = count + countH(staRow - 2, staCol - 1, level + 1);
      count = count + countH(staRow + 1, staCol - 2, level + 1);
      count = count + countH(staRow - 1, staCol - 2, level + 1);
      removeKnight(staRow, staCol);
    }
    return count;
  }

  public static void main(String[] args) {
    KnightBoard k = new KnightBoard(5, 5);
    System.out.println(k.toStringC());
    System.out.println(k.solve(0, 0));
    System.out.println(k);
    KnightBoard a = new KnightBoard(6, 6);
    System.out.println(a.solve(0, 0));
    System.out.println(a);
    //KnightBoard ab = new KnightBoard(6, 6);
    //System.out.println(ab.countSolutions(0, 0));
    //System.out.println(ab);
  }


}
