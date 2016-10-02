package com.daisafernandes.utils;

/***
 * The Sudoku Board
 * @author daisafernandes
 *
 */
public class SudokuBoard {

	private static final int[][] BOARD = {			
		//    0  1  2    3  4  5    6  7  8
	        { 7, 0, 0,   0, 4, 0,   5, 3, 0 }, // 0
	        { 0, 0, 5,   0, 0, 8,   0, 1, 0 }, // 1
	        { 0, 0, 8,   5, 0, 9,   0, 4, 0 }, // 2
	        
	        { 5, 3, 9,   0, 6, 0,   0, 0, 1 }, // 3
	        { 0, 0, 0,   0, 1, 0,   0, 0, 5 }, // 4
	        { 8, 0, 0,   7, 2, 0,   9, 0, 0 }, // 5
	        
	        { 9, 0, 7,   4, 0, 0,   0, 0, 0 }, // 6
	        { 0, 0, 0,   0, 5, 7,   0, 0, 0 }, // 7
	        { 6, 0, 0,   0, 0, 0,   0, 5, 0 }  // 8
	    };
	
	
	public static int[][] getSudokuBoard() {
        return BOARD;
    }
	
}
