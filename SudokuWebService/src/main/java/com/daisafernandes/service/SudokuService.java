package com.daisafernandes.service;

import org.springframework.stereotype.Component;

import com.daisafernandes.utils.SudokuBoard;
import com.daisafernandes.utils.SudokuConstants;

/**
 * SudokuService Class contains the logic to validate moves
 * and to validate if the Sudoku bBrd is completed.
 * 
 * @author daisafernandes
 *
 */

@Component
public class SudokuService{

	/***
	 * Insert and validate values in sudoku board
	 * @param row
	 * @param column
	 * @param value
	 * @param board
	 * @return
	 */	
	public String insertValuesOnSudokuBoard(int row, int column, int value, int[][] board) {

		String validateInput = validateInput(row, column, value);

		if (validateInput != SudokuConstants.VALID) {
			return validateInput;

		} else {
			String msg;

			// validate the initial numbers, it can not change
			int [][] initalBoard = SudokuBoard.getInitialSudokuBoard();
			if(initalBoard[row][column] != 0) {
				msg = String.format("The Board Cell is already filled with number %d", board[row][column]);
				return msg;

			} else {			 
				if (isValid(row, column, value, board)) {						
					board[row][column] = value;

					if (isSudokuComplete(board)) {
						return "Congrats, Your SUDOKU Board is Complete :)!";
					} else {
						msg = String.format("Valid move, value %d, but not completed yet!", board[row][column]);
						return msg;
					}	
				} else {
					msg = String.format("The Board already contains the number %d", value);
					return msg;
				}
			}
		}
	}

	/**
	 * Validate the inputs, if they are a valid cenario
	 * @param row
	 * @param column
	 * @param value
	 * @return
	 */
	private String validateInput(int row, int column, int value){		

		String msg;
		if(row < 0 || row > 8){		 
			msg = String.format("Invalid input ROW %d", row);
			return msg;
		} else if (column < 0 || column > 8){
			msg = String.format("Invalid input COLUMN %d", column);
			return msg;
		} else if (value < 1 || value > 9){
			msg = String.format("Invalid input VALUE %d", value);
			return msg;
		} else {
			return SudokuConstants.VALID;
		}		
	}

	/**
	 * Verify if the sudoku is complete
	 */
	private boolean isSudokuComplete(int[][] board) {	
		int emptyCell = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int cell = board[i][j];
				if (cell == 0) {
					emptyCell++;
				}
			}
		}
		if (emptyCell == 0){
			return true;
		}
		return false;
	}

	/** 
	 * Check the value in board[i][j] is valid
	 */
	private boolean isValid(int row, int column, int value, int[][] board) {

		// Check COLUMNs
		for (int inRow = 0; inRow < 9; inRow++) {
			if (inRow != row && board[inRow][column] == value) {
				return false;
			}
		}

		// Check ROWs
		for (int inColumn = 0; inColumn < 9; inColumn++) {
			if (inColumn != column && board[row][inColumn] == value) {
				return false;
			}
		}
		return true;
	}

}
