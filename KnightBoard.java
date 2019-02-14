public class KnightBoard {
  private int[][]board;

  @throws IllegalArgumentException when either parameter is negative.
  public KnightBoard(int startingRows,int startingCols) {
    board = new int[startingRows][startingCols];
    removeNull();
  }

  public void removeNull() {
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
        if (board.length * board[0].length >= 10) {
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

  public static void main(String[] args) {
    System.out.println();
  }



//KnightBoard has 3 public methods and a constructor, a private helper is needed as well.



public String toString()
see format for toString below
blank boards display 0's as underscores
you get a blank board if you never called solve or
when there is no solution

@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds.
public boolean solve(int startingRow, int startingCol)

@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds.
public int countSolutions(int startingRow, int startingCol)

Suggestion:
private boolean solveH(int row ,int col, int level)
// level is the # of the knight
