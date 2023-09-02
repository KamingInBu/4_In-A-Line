/*
(ITP 3914 Assignment)
Program code:IT114105
Class：SE1A
Filename: Assignment.java
Programmer: <Lau Ka Ming Benjamin>
Student Number: 220014642
Description: Four-in-a-Line
*/

import java.util.Scanner;
public class Assignment {
    public static void main(String [] args) {
        //Create a Scanner object for console input
        Scanner sc = new Scanner(System.in);
        //Declare variable
        int round = 0;  //count the round the player has played
        int y = 5;  // row of 2D array
        int x ;   //column of 2d array
        int player ; //player1 or player2
        int [][] grid = new int[6][7]; //the array of grid

        drawGrid(grid);  // call drawGrid() grid as argument
        while (round < 42){// will not stop until 42 round

            if (round % 2 == 0) {   // find out whose turn
                player = 1;
            }else{
                player = 2;
            }

            System.out.print("Player "+player+" type a column (0-6) or 9 to quit current game:");
            x = sc.nextInt();

            if (x==9){  //if input 9 then quit the game
                System.out.println("Bye Bye");
                break;
            }

            if (getError(x,grid)) {  // call getError() with x and grid as argument
                continue;  //if input have error ask user input one more time
            }else{
                while (grid[y][x] == 1 || grid[y][x] == 2){    //if index of array already have data ascent
                    y--;
                }
                grid[y][x] = player;    //index of array input a data 1 or 2
                drawGrid(grid);      // call drawGrid() grid as argument
            }

            if (rowChecker(grid,y,x,player)  //call rowChecker() grid, y, x,player as argument
                    ||columnChecker(grid,y,x,player)  //call columnChecker() grid, y, x,player as argument
                    ||leftSlashChecker(grid,y,x,player)  //call leftSlashChecker() grid, y, x,player as argument
                    ||rightSlashChecker(grid,y,x,player)) {  //call rightSlashChecker() grid, y, x, player as argument

                System.out.print("Player "+player+" win this game!"); //if any checker return true that player win
                break;
            }
            y=5;  //the row of array descent
            round++;
            if(round==42){ //if round 42 but no one win then draw game
                System.out.print("Draw!!");
                break;
            }
        }
    }


    public static boolean getError(int x,int [][] grid ) {

        if (x < 0 || x > 6) { //if input <0 or > 6 ask the user input again and return true
            System.out.println("Range of column should be 0-6!");
            return true;
        }
        if (grid[0][x]==1 || grid[0][x]==2){  //if  column is full ask the user input again and return true
            System.out.println("Column "+x+" is full!");
            return true;
        }
        return false;
    }


    public static void drawGrid(int [][] grid) {

        int j=5;
        for (int i = 0;i<grid.length;i++){
            System.out.print(j+" |");
            j--;
            for (int k = 0; k<grid[0].length;k++){
                if(grid[i][k]==1)
                    System.out.print(" 1"); // print 1
                else if(grid[i][k]==2)
                    System.out.print(" 2"); // print 2
                else
                    System.out.print(" 0"); // print 0
            }
            System.out.println();
        }

        System.out.println("  +--------------");
        System.out.println("    0 1 2 3 4 5 6");
    }

    public static boolean rowChecker(int [][] grid,int y,int x,int player){
        int checkX = x;
        int check= 0 ; // check how many is connected

        while (checkX <= 6 && grid[y][checkX] == player && check < 4 ) { // if x is <or= 6 then check the right side is it the same until it is not the same.
            checkX++;
            check++;
        }

        checkX = x; //the index of checker reset

        if(check > 0 && check < 4)
            check--; // if it needs to check another side , check need to -1 because the first index is repeat

        while ( checkX >= 0 && grid[y][checkX] == player && check < 4 ) { //check the left side
            checkX--;
            check++;
        }
        return check == 4; //if it  has 4 connect return true
    }


    public static boolean columnChecker(int [][] grid,int y,int x,int player){
        int checkY = y;
        int check = 0; // check how many is connected

        while ( checkY <=5 && grid[checkY][x] == player && check < 4 ) { //if y is <or= 5 then check the bottom is it the same until it is not the same.
            checkY++; //if it is same check one more
            check++;
        }

        checkY = y; //the index of checker reset

        if(check>0&&check<4)
            check--; // if it needs to check another side , check need to -1 because the first index is repeat

        while (checkY >= 0 && grid[checkY][x] == player && check < 4) { //check the upside
            checkY--; //if it is same check one more
            check++;
        }
        return check ==4 ; //if it  has 4 connect return true
    }


    public static boolean leftSlashChecker(int [][] grid,int y,int x,int player){
        int checkX = x;
        int checkY = y;

        int check = 0 ; // check how many is connected
        while (checkX<=6 && checkY<=5 && grid[checkY][checkX] == player && check < 4 ) { // check the upper right is it the same until it is not the same.
            checkX++;//if it is same check one more
            checkY++;
            check++;
        }

        checkX = x; //the index of checker reset
        checkY = y;

        if(check>0&&check<4)
            check--; // if it needs to check another side , check need to -1 because the first index is repeat

        while ( checkX >= 0 && checkY >= 0 && grid[checkY][checkX] == player && check < 4 ) { //check the lower left
            checkX--; //if it is same check one more
            checkY--;
            check++;
        }
        return check == 4; //if it  has 4 connect return true
    }


    public static boolean rightSlashChecker(int [][] grid,int y,int x,int player){
        int checkX = x;
        int checkY = y;
        int check = 0 ; // check how many is connected

        while ( checkX>=0 && checkY<=5 && grid[checkY][checkX] == player && check < 4 ) { //check the upper left is it the same until it is not the same.
            checkX--; //if it is same check one more
            checkY++;
            check++;
        }

        checkX = x; //the index of checker reset
        checkY = y;

        if(check>0&&check<4)
            check--; // if it needs to check another side , check need to -1 because the first index is repeat

        while ( checkX<=6 && checkY>=0 && grid[checkY][checkX] == player && check < 4 ) { //check the lower right
            checkX++; //if it is same check one more
            checkY--;
            check++;

        }
        return check == 4; //if it  has 4 connect return true
    }
}