KnightBoard has 3 public methods and a constructor, a private helper is needed as well.

@throws IllegalArgumentException when either parameter is negative.
public KnightBoard(int startingRows,int startingCols)

    Initialize the board to the correct size and make them all 0's


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
