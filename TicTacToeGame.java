import java.awt.Color;

import java.util.Scanner;

/*
 *   A game of TicTacToe to be played between two human players.
 *   
 *   The board is a 2D array of Strings. 
 *   One player is the String literal "X".
 *   The second player is the String literal "O" (letter, not number).
 *   
 *   The game should continue to be played as long as either:
 *   1) No player has won the game.
 *   2) There is at least one open square left in the game.
 *    
 *   @author: INSERT FIRST TEAM MEMBER'S NAME HERE
 *   @author: INSERT SECOND TEAM MEMBER'S NAME HERE
 *
 */

public class TicTacToeGame
{
    /*
     * These fields are used to build the GUI.
     * You won't need to modiy them. 
     * They are declared as final because their value is final; it won't ever change.
     * The convention for final constants is all uppercase. Remember Math.PI?
     */
    private final int WINDOW_SIZE;
    private final int LINE_BUFFER;
    private final int TILE_SIZE;

    // These are the fields you'll be working with. 
    private boolean isXsTurn;
    private String[][] board;

    private boolean isLeftDiagonal;
    private boolean isRightDiagonal;
    private int rowNumber;
    private int columnNumber;
    public TicTacToeGame()
    {
        WINDOW_SIZE = 600;
        LINE_BUFFER = 25;
        TILE_SIZE = WINDOW_SIZE / 3;
        isXsTurn = true;

        Scanner s = new Scanner(System.in); //lets us analyze what someone puts into the S.o.p window

        Color c = new Color(0, 0, 0, 0);

        rowNumber = -1;
        columnNumber = -1;
    }

    private boolean isCellClaimed(int r, int c)
    {

        if(board[r][c] != null)
        {
            return true;
        }
        else 
        {
            return false;
        }

    }

    private boolean hasDiagonalsWon()
    {
        if(isCellClaimed(0,0) && isCellClaimed(1,1) && isCellClaimed(2,2))
        {
            if(board[0][0].equals("X") && board[1][1].equals("X") && board[2][2].equals("X"))
            {
                isRightDiagonal = true;
                return true;
            }
            else if(board[0][0].equals("O") && board[1][1].equals("O") && board[2][2].equals("O"))
            {
                isRightDiagonal = true;
                return true;
            }
        }

        else if (isCellClaimed(0, 2) && isCellClaimed(1,1) && isCellClaimed(2,0))
        {
            if(board[0][2].equals("X") && board[1][1].equals("X") && board[2][0].equals("X"))
            {
                isLeftDiagonal = true;
                return true;
            }
            else if(board[0][2].equals("O") && board[1][1].equals("O") && board[2][0].equals("O"))
            {
                isLeftDiagonal = true;
                return true;
            }
        }
        return false;
    }

    private boolean hasRowsWon()
    {
        if(isCellClaimed(0,0) && isCellClaimed(0,1) && isCellClaimed(0,2))
        {
            if(board[0][0].equals("X") && board[0][1].equals("X") && board[0][2].equals("X"))
            {
                rowNumber = 0;
                return true;
            }
            else if(board[0][0].equals("O") && board[0][1].equals("O") && board[0][2].equals("O"))
            {
                rowNumber = 0;
                return true;
            }
        }

        else if (isCellClaimed(1, 0) && isCellClaimed(1,1) && isCellClaimed(1,2))
        {
            if(board[1][0].equals("X") && board[1][1].equals("X") && board[1][2].equals("X"))
            {
                rowNumber = 1;
                return true;
            }
            else if(board[1][0].equals("O") && board[1][1].equals("O") && board[1][2].equals("O"))
            {
                rowNumber = 1;
                return true;
            }
        }

        else if (isCellClaimed(2,0) && isCellClaimed(2,1) && isCellClaimed(2,2))
        {
            if(board[2][0].equals("X") && board[2][1].equals("X") && board[2][2].equals("X"))
            {
                rowNumber = 2;
                return true;
            }
            else if(board[2][0].equals("O") && board[2][1].equals("O") && board[2][2].equals("O"))
            {
                rowNumber = 2;
                return true;
            }
        }
        return false;
    }

    private boolean hasColsWon()
    {
        if(isCellClaimed(0,0) && isCellClaimed(1,0) && isCellClaimed(2,0))
        {
            if(board[0][0].equals("X") && board[1][0].equals("X") && board[2][0].equals("X"))
            {
                columnNumber = 0;
                return true;
            }
            else if(board[0][0].equals("O") && board[1][0].equals("O") && board[2][0].equals("O"))
            {
                columnNumber = 0;
                return true;
            }
        }

        else if (isCellClaimed(0,1) && isCellClaimed(1,1) && isCellClaimed(2,1))
        {
            if(board[0][1].equals("X") && board[1][1].equals("X") && board[2][1].equals("X"))
            {
                columnNumber = 1;
                return true;
            }
            else if(board[0][1].equals("O") && board[1][1].equals("O") && board[2][1].equals("O"))
            {
                columnNumber = 1;
                return true;
            }
        }

        else if (isCellClaimed(0,2) && isCellClaimed(1,2) && isCellClaimed(2,2))
        {
            if(board[0][2].equals("X") && board[1][2].equals("X") && board[2][2].equals("X"))
            {
                columnNumber = 2;
                return true;
            }
            else if(board[0][2].equals("O") && board[1][2].equals("O") && board[2][2].equals("O"))
            {
                columnNumber = 2;
                return true;
            }
        }
        return false;
    }

    private boolean isGameOver()
    {
        if(hasDiagonalsWon() || hasRowsWon() || hasColsWon())
        {
            return true;
        }
        else if (isBoardFull())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //This will require looping, so you may need to hold off on this
    private boolean isBoardFull()
    {
        for(String[] row : board)
        {
            for(String cell : row)
            {
                if(cell == null)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private void playAISimple()
    {
        if(board[0][0] == null)
        {
            board[0][0] = "O";
            drawShape("0", 1);
        }
        else if(board[0][1] == null)
        {
            board[0][1] = "O";
            drawShape("0", 2);
        }
        else if (board[0][2] == null)
        {
            board[0][2] = "O";
            drawShape("0", 3);
        }
        else if (board[1][0] == null)
        {
            board[1][0] = "O";
            drawShape("0", 4);
        }
        else if (board[1][1] == null)
        {
            board[1][1] = "O";
            drawShape("0", 5);
        }
        else if (board[1][2] == null)
        {
            board[1][2] = "O";
            drawShape("0", 6);
        }
        else if (board[2][0] == null)
        {
            board[2][0] = "O";
            drawShape("0", 7);
        }
        else if (board[2][1] == null)
        {
            board[2][1] = "O";
            drawShape("0", 8);
        }
        else if (board[2][2] == null)
        {
            board[2][2] = "O";
            drawShape("0", 9);
        }
        else
        {

        }
    } 
    /*IGNORE THIS IT GOES WITH A METHOD THAT DOESNT WORK
    private void playRandomAI()
    {
        int r = (int) (Math.random() * 3);
        int c = (int) (Math.random() * 3);
        
        if(board[r][c] != null)
        {
           r = (int) (Math.random() * 3);
           c = (int) (Math.random() * 3); 
        }
        else if(board[r][c] == board[0][0])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 1);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[0][1])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 2);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[0][2])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 3);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[1][0])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 4);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[1][1])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 5);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[1][2])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 6);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[2][0])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 7);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[2][1])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 8);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
        else if(board[r][c] == board[2][2])
        {
            if(board[r][c] == null)
            {
                board[r][c] = "O";
                drawShape("0", 9);
                r = (int) (Math.random() * 3);
                c = (int) (Math.random() * 3); 
            }
        }
    }
    */
    //////////////////////DO NOT MODIFY ANYTHING BELOW THIS COMMENT!!!!////////////////////////
    //////////////////////SERIOUSLY, DO NOT CHANGE ANYTHING AFTER THIS COMMENT.////////////////
    //////////////////////I DO NOT PITY THE FOOL THAT CHANGES THE CODE BELOW.///////////////////////
    //////////////////////DON'T BE ANNOYING LIKE HARRY POTTER AND IGNORE INSTRUCTIONS./////////
    public void playAGame()
    {
        drawBoard();
        while (!isGameOver())
        {
            if (mouseClicked())
            {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int square = determineSquare(x, y);
                int[] coords = getSquareRowColumn(square);
                if (!isCellClaimed(coords[0], coords[1]))
                {
                    if(isXsTurn)
                        drawShape("X", square);
                    else
                        drawShape("O", square);
                    isXsTurn = !isXsTurn;
                }
            }
        }

        if(isXsTurn)
        {
            Color c = new Color(255, 0, 0, 50);

            StdDraw.setPenColor(c);

            StdDraw.filledSquare(WINDOW_SIZE/2, WINDOW_SIZE/2, 300);
        }
        else
        {
            Color c = new Color(0, 0, 225, 50);

            StdDraw.setPenColor(c);

            StdDraw.filledSquare(WINDOW_SIZE/2, WINDOW_SIZE/2, 300);
        }

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.02);

        if(isLeftDiagonal == true || isRightDiagonal == true)
        {
            if(isLeftDiagonal == true)
            {
                StdDraw.line(550, 550, 50, 50);
            }
            else if(isRightDiagonal == true)
            {
                StdDraw.line(50, 550, 550, 50);
            }
        }

        if(hasRowsWon())
        {
            if(rowNumber == 0)
            {
                StdDraw.line(50, 500, 550, 500);
            }
            else if(rowNumber == 1)
            {
                StdDraw.line(50, 300, 550, 300);
            }
            else if(rowNumber == 2)
            {
                StdDraw.line(50, 100, 550, 100);
            }
        }

        if(hasColsWon())
        {
            if(columnNumber == 0)
            {
                StdDraw.line(100, 50, 100, 550);
            }
            else if(columnNumber == 1)
            {
                StdDraw.line(300, 50, 300, 550); 
            }
            else if(columnNumber == 2)
            {
                StdDraw.line(500, 50, 500, 550);
            } 
        }

        StdDraw.show();

        if(isXsTurn)
        {
            System.out.println("Good job player O! The game is over! Would you like to play again? Type 'YES' to play again. Type 'NO' to not play again.");
        }
        else
        {
            System.out.println("Good job player X! The game is over! Would you like to play again? Type 'YES' to play again. Type 'NO' to not play again.");
        }

        Scanner s = new Scanner(System.in); 

        String str = s.nextLine();

        if(str.equals("YES"))
        {
            isXsTurn = true;
            playAGame();
        }
    }

    public void playAgainstSimpleAI()
    {
        drawBoard();
        while (!isGameOver())
        {
            if (mouseClicked())
            {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int square = determineSquare(x, y);
                int[] coords = getSquareRowColumn(square);
                if (!isCellClaimed(coords[0], coords[1]))
                {
                    if(isXsTurn)
                        drawShape("X", square);
                    else
                        playAISimple();
                    isXsTurn = !isXsTurn;
                }
            }
        }

        if(isXsTurn)
        {
            Color c = new Color(255, 0, 0, 50);

            StdDraw.setPenColor(c);

            StdDraw.filledSquare(WINDOW_SIZE/2, WINDOW_SIZE/2, 300);
        }
        else
        {
            Color c = new Color(0, 0, 225, 50);

            StdDraw.setPenColor(c);

            StdDraw.filledSquare(WINDOW_SIZE/2, WINDOW_SIZE/2, 300);
        }

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.02);

        if(isLeftDiagonal == true || isRightDiagonal == true)
        {
            if(isLeftDiagonal == true)
            {
                StdDraw.line(550, 550, 50, 50);
            }
            else if(isRightDiagonal == true)
            {
                StdDraw.line(50, 550, 550, 50);
            }
        }

        if(hasRowsWon())
        {
            if(rowNumber == 0)
            {
                StdDraw.line(50, 500, 550, 500);
            }
            else if(rowNumber == 1)
            {
                StdDraw.line(50, 300, 550, 300);
            }
            else if(rowNumber == 2)
            {
                StdDraw.line(50, 100, 550, 100);
            }
        }

        if(hasColsWon())
        {
            if(columnNumber == 0)
            {
                StdDraw.line(100, 50, 100, 550);
            }
            else if(columnNumber == 1)
            {
                StdDraw.line(300, 50, 300, 550); 
            }
            else if(columnNumber == 2)
            {
                StdDraw.line(500, 50, 500, 550);
            } 
        }

        StdDraw.show();

        if(isXsTurn)
        {
            System.out.println("Good job player O! The game is over! Would you like to play again? Type 'YES' to play again. Type 'NO' to not play again.");
        }
        else
        {
            System.out.println("Good job player X! The game is over! Would you like to play again? Type 'YES' to play again. Type 'NO' to not play again.");
        }

        Scanner s = new Scanner(System.in); 

        String str = s.nextLine();

        if(str.equals("YES"))
        {
            isXsTurn = true;
            playAGame();
        }
    }
    /* IGNORE THIS IT DOESNT WORK
    public void playAdvancedAI()
    {
        drawBoard();
        while (!isGameOver())
        {
            if (mouseClicked())
            {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                int square = determineSquare(x, y);
                int[] coords = getSquareRowColumn(square);
                if (!isCellClaimed(coords[0], coords[1]))
                {
                    if(isXsTurn)
                        drawShape("X", square);
                    else
                        playRandomAI();
                    isXsTurn = !isXsTurn;
                }
            }
        }

        if(isXsTurn)
        {
            Color c = new Color(255, 0, 0, 50);

            StdDraw.setPenColor(c);

            StdDraw.filledSquare(WINDOW_SIZE/2, WINDOW_SIZE/2, 300);
        }
        else
        {
            Color c = new Color(0, 0, 225, 50);

            StdDraw.setPenColor(c);

            StdDraw.filledSquare(WINDOW_SIZE/2, WINDOW_SIZE/2, 300);
        }

        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.setPenRadius(0.02);

        if(isLeftDiagonal == true || isRightDiagonal == true)
        {
            if(isLeftDiagonal == true)
            {
                StdDraw.line(550, 550, 50, 50);
            }
            else if(isRightDiagonal == true)
            {
                StdDraw.line(50, 550, 550, 50);
            }
        }

        if(hasRowsWon())
        {
            if(rowNumber == 0)
            {
                StdDraw.line(50, 500, 550, 500);
            }
            else if(rowNumber == 1)
            {
                StdDraw.line(50, 300, 550, 300);
            }
            else if(rowNumber == 2)
            {
                StdDraw.line(50, 100, 550, 100);
            }
        }

        if(hasColsWon())
        {
            if(columnNumber == 0)
            {
                StdDraw.line(100, 50, 100, 550);
            }
            else if(columnNumber == 1)
            {
                StdDraw.line(300, 50, 300, 550); 
            }
            else if(columnNumber == 2)
            {
                StdDraw.line(500, 50, 500, 550);
            } 
        }

        StdDraw.show();

        if(isXsTurn)
        {
            System.out.println("Good job player O! The game is over! Would you like to play again? Type 'YES' to play again. Type 'NO' to not play again.");
        }
        else
        {
            System.out.println("Good job player X! The game is over! Would you like to play again? Type 'YES' to play again. Type 'NO' to not play again.");
        }

        Scanner s = new Scanner(System.in); 

        String str = s.nextLine();

        if(str.equals("YES"))
        {
            isXsTurn = true;
            playAGame();
        }
    }
    */
    private void drawBoard()
    {
        StdDraw.setCanvasSize(WINDOW_SIZE, WINDOW_SIZE);
        StdDraw.setScale(0, WINDOW_SIZE);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(WINDOW_SIZE / 3, LINE_BUFFER, WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER);
        StdDraw.line(2*WINDOW_SIZE / 3, LINE_BUFFER, 2*WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER);     
        StdDraw.line(LINE_BUFFER, WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER, WINDOW_SIZE / 3);
        StdDraw.line(LINE_BUFFER, 2*WINDOW_SIZE / 3, WINDOW_SIZE - LINE_BUFFER, 2*WINDOW_SIZE / 3);
        StdDraw.show();
        board = new String[3][3];
    }

    private int determineSquare(double x, double y)
    {
        if (y > 2*WINDOW_SIZE / 3)
        {
            if (x < WINDOW_SIZE / 3)
                return 1;
            else if (x < 2*WINDOW_SIZE / 3)
                return 2;
            else
                return 3;
        }
        else if (y > WINDOW_SIZE / 3)
        {
            if (x < WINDOW_SIZE / 3)
                return 4;
            else if (x < 2*WINDOW_SIZE / 3)
                return 5;
            else 
                return 6;
        }
        else
        {
            if (x < WINDOW_SIZE / 3)
                return 7;
            else if (x < 2*WINDOW_SIZE / 3)
                return 8;
            else
                return 9;
        }
    }

    private int[] getSquareRowColumn(int square)
    {
        int[] coords = new int[2];
        switch (square){
            case 1: 
            coords[0] = 0; coords[1] = 0; break;
            case 2:
            coords[0] = 0; coords[1] = 1; break;
            case 3: 
            coords[0] = 0; coords[1] = 2; break;
            case 4:
            coords[0] = 1; coords[1] = 0; break;
            case 5:
            coords[0] = 1; coords[1] = 1; break;
            case 6:
            coords[0] = 1; coords[1] = 2; break;
            case 7:
            coords[0] = 2; coords[1] = 0; break;
            case 8: 
            coords[0] = 2; coords[1] = 1; break;
            case 9: 
            coords[0] = 2; coords[1] = 2; break;
            default:
            break;
        }
        return coords;
    }

    private void drawShape(String letter, int square)
    {
        double x, y;
        if (square == 1 || square == 4 || square == 7)
            x = WINDOW_SIZE / 2 - TILE_SIZE;
        else if (square == 2 || square == 5 || square == 8)
            x = WINDOW_SIZE / 2;
        else
            x = WINDOW_SIZE / 2 + TILE_SIZE;
        if (square <= 3)
            y = WINDOW_SIZE - TILE_SIZE / 2;
        else if (square <= 6)
            y = WINDOW_SIZE / 2;
        else
            y = TILE_SIZE / 2;

        if (letter.equalsIgnoreCase("X"))
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x - TILE_SIZE / 2 + LINE_BUFFER, y - TILE_SIZE / 2 + LINE_BUFFER, x + TILE_SIZE / 2 - LINE_BUFFER, y + TILE_SIZE / 2 - LINE_BUFFER);
            StdDraw.line(x - TILE_SIZE / 2 + LINE_BUFFER, y + TILE_SIZE / 2 - LINE_BUFFER, x + TILE_SIZE / 2 - LINE_BUFFER, y - TILE_SIZE / 2 + LINE_BUFFER);
            addSymbolToBoard("X", square);
        }
        else
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(x, y, TILE_SIZE / 2 - LINE_BUFFER);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(x, y, TILE_SIZE / 2 - 1.2*LINE_BUFFER);
            addSymbolToBoard("O", square);
        }
        StdDraw.show();
    }

    private void addSymbolToBoard(String letter, int square)
    {
        switch (square){
            case 1: 
            board[0][0] = letter; break;
            case 2:
            board[0][1] = letter; break;
            case 3: 
            board[0][2] = letter; break;
            case 4:
            board[1][0] = letter; break;
            case 5:
            board[1][1] = letter; break;
            case 6:
            board[1][2] = letter; break;
            case 7:
            board[2][0] = letter; break;
            case 8: 
            board[2][1] = letter; break;
            case 9: 
            board[2][2] = letter; break;
            default:
            break;
        }
    }

    private void waitForStart()
    {
        while (!mouseClicked())
        {}
    }

    private boolean mouseClicked()
    {
        if (StdDraw.mousePressed())
        {
            while (StdDraw.mousePressed())
            {}
            return true;
        }
        return false;
    }
}