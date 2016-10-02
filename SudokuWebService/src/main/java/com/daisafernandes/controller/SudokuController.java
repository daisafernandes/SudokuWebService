package com.daisafernandes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daisafernandes.service.SudokuService;
import com.daisafernandes.utils.SudokuBoard;
import com.daisafernandes.utils.SudokuConstants;

/***
 *  SudokuController is a class used to process HTTP 
 *  (Requests and Responses) to handle teh following requests
 *  GET - /sudoku/board , to request thee board
 *  PUT - /sudoku/moves (row, column, values) to put value into Board
 *  
 * @author daisafernandes
 *
 */
@RestController
@RequestMapping("/sudoku")
public class SudokuController {
	
	@Autowired
	SudokuService sudokuService;
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
    public int[][] getBoard() {
		return SudokuBoard.getSudokuBoard();
    }
	
	@RequestMapping(value = "/moves")
	public ResponseEntity<String> validateValue (@RequestParam(value = "row", required = true) String row,
			@RequestParam(value = "column", required = true) String column,
			@RequestParam(value = "value", required = true) String value){
		
		String returnValidateNull = validateNullOrEmpty(row, column, value); 
		
		if (returnValidateNull != SudokuConstants.VALID){
			return new ResponseEntity<String>(returnValidateNull, HttpStatus.NOT_FOUND);
			
		} else {			
			String returnValidateDigit = validateDigit(row, column, value);
			if(returnValidateDigit != SudokuConstants.VALID){
				return new ResponseEntity<String>(returnValidateDigit, HttpStatus.NOT_FOUND);
				
			} else {			
				String result = insertValue(Integer.valueOf(row), Integer.valueOf(column), Integer.valueOf(value));
				return new ResponseEntity<String>(result, HttpStatus.OK);
			}
		}	
	}

	private String validateNullOrEmpty(String row, String column, String value) {
		if(row == null || row.isEmpty()){
			return SudokuConstants.ROW_INVALID;
			
		} else if (column == null || column.isEmpty()){
			return SudokuConstants.COLUMN_INVALID;
			
		} else if (value == null || value.isEmpty()){
			return SudokuConstants.VALUE_INVALID;
			
		} else {
			return SudokuConstants.VALID;
		}
	}
	
	public String validateDigit(String row, String column, String value) {	
		
		if(!Character.isDigit(row.toCharArray()[0])) {
			return SudokuConstants.ROW_NOT_A_DIGIT;
			
		} else if (!Character.isDigit(column.toCharArray()[0])){
			return SudokuConstants.COLUMN_NOT_A_DIGIT;
			
		} else if (!Character.isDigit(value.toCharArray()[0])){
			return SudokuConstants.VALUE_NOT_A_DIGIT;
			
		} else {
			return SudokuConstants.VALID;
		}
	}
	

    private String insertValue(int row, int column, int value) {
    	
    	int [][] board = SudokuBoard.getSudokuBoard();    			
    	return sudokuService.insertValuesOnSudokuBoard(row, column, value, board);
    }

}
