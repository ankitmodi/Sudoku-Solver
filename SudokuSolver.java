import java.util.*;
public class SudokuSolver 
{

	public static void main(String[] args) 
	{
		int[][] inputSudoku = 
				{{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
		
		int[][] outputSudoku = inputSudoku;
		if(sudokuSolver(outputSudoku))
		{
			System.out.println("Yay! Solved it :-)\n");
			print(outputSudoku);
		}
		else
		{
			System.out.println("What sorcery is this !!\nThis sudoku is not solvable.");
		}

	}

	
	
	private static boolean sudokuSolver(int[][] outputSudoku)
	{
		int row=0, col =0;
		
		String tmp = FindNextEmptyCell(outputSudoku);
		if(tmp.isEmpty())
		{
			return true;
		}
		else
		{
			String[] parts = tmp.split("and");
			row = Integer.parseInt(parts[0]);
			col = Integer.parseInt(parts[1]);
			
			for(int num=1; num<=9; num++)
			{
				if(isValid(outputSudoku, row, col, num))
				{
					outputSudoku[row][col] = num;
					if(sudokuSolver(outputSudoku))
					{
						return true;
					}
					outputSudoku[row][col] = num;
				}
			}
			
			return false;
		}
	}



	private static String FindNextEmptyCell(int[][] outputSudoku) 
	{
		String result = "";
		
		for(int row=0; row< outputSudoku.length; row++)
		{
			for(int col=0; col< outputSudoku[0].length; col++)
			{
				if(outputSudoku[row][col]==0)
				{
					result += String.valueOf(row) + "and" + String.valueOf(col);
					return result;
				}
			}
		}
		
		return result;
	}
	
	
	
	private static boolean isValid(int[][] outputSudoku, int row, int col, int num) 
	{
		return !AlreadyInRow(outputSudoku, row, num) ||
				!AlreadyInCol(outputSudoku, col, num) ||
				!AlreadyInBox(outputSudoku, row, col, num);
	}



	private static boolean AlreadyInRow(int[][] outputSudoku, int row, int num)
	{
		for(int col=0; col<outputSudoku[0].length; col++)
		{
			if(outputSudoku[row][col] == num)
			{
				return true;
			}
		}
		
		return false;
	}



	private static boolean AlreadyInCol(int[][] outputSudoku, int col, int num) 
	{
		for(int row=0; row<outputSudoku.length; row++)
		{
			if(outputSudoku[row][col] == num)
			{
				return true;
			}
		}
		
		return false;
	}



	private static boolean AlreadyInBox(int[][] outputSudoku, int row, int col, int num) 
	{
		int rowStart = row - row%3;
		int colStart = col - col%3;
		
		for(int i=0; i< 3; i++)
		{
			for(int j=0; j< 3; j++)
			{
				if(outputSudoku[rowStart + i][colStart + j] == num)
				{
					return true;
				}
			}
		}
		
		return false;
	}



	private static void print(int[][] outputSudoku)
	{
		for(int i=0; i< outputSudoku.length; i++)
		{
			for(int j=0; j< outputSudoku[0].length; j++)
			{
				System.out.print(outputSudoku[i][j] + " ");
			}
			System.out.println("");
		}
		
	}

}