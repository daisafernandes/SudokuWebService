package com.daisafernandes.utils;

/***
 * 
 * @author daisafernandes
 *
 */
public class SudokuBoard {

	private static final int[][] BOARD = {			
		//    1  2  3  4  5  6  7  8  9
	        { 7, 0, 0, 0, 4, 0, 5, 3, 0 }, // 1
	        { 0, 0, 5, 0, 0, 8, 0, 1, 0 }, // 2
	        { 0, 0, 8, 5, 0, 9, 0, 4, 0 }, // 3
	        { 5, 3, 9, 0, 6, 0, 0, 0, 1 }, // 4
	        { 0, 0, 0, 0, 1, 0, 0, 0, 5 }, // 5
	        { 8, 0, 0, 7, 2, 0, 9, 0, 0 }, // 6
	        { 9, 0, 7, 4, 0, 0, 0, 0, 0 }, // 7
	        { 0, 0, 0, 0, 5, 7, 0, 0, 0 }, // 8
	        { 6, 0, 0, 0, 0, 0, 0, 5, 0 }  // 9
	    };
	
	public static int[][] getSudokuBoard() {
        return BOARD;
    }
	
}
