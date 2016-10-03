package com.daisafernandes.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.daisafernandes.SudokuApplication;
import com.daisafernandes.utils.SudokuBoard;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SudokuApplication.class)
@WebAppConfiguration
public class SudokuControllerTest {

	@Autowired
	private SudokuController sudokuController;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.sudokuController).build();
	}

	@Test
	public void getBoard() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/sudoku/board"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testControllerInsertEmpty() throws Exception {
		final String result = "ROW_INVALID";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=&column=&value=")
				.contentType(MediaType.TEXT_PLAIN))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

		ResponseEntity<String> response = this.sudokuController.validateValue("", "", "");

		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		Assert.assertEquals(result, response.getBody());
	}

	@Test
	public void testControllerInsertLetter() throws Exception {
		final String result = "COLUMN_NOT_A_DIGIT";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=2&column=b&value=b")
				.contentType(MediaType.TEXT_PLAIN))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

		ResponseEntity<String> response = this.sudokuController.validateValue("2", "b", "b");

		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		Assert.assertEquals(result, response.getBody());
	}

	@Test
	public void testControllerInsertInvalidNumber() throws Exception {
		final String result = "Invalid input COLUMN 11";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=2&column=11&value=22")
				.contentType(MediaType.TEXT_PLAIN))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

		ResponseEntity<String> response = this.sudokuController.validateValue("2", "11", "22");

		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		Assert.assertEquals(result, response.getBody());
	}

	@Test
	public void testControllerInsertInvalidMove() throws Exception {
		final String result = "The Board already contains the number 6";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=3&column=3&value=6")
				.contentType(MediaType.TEXT_PLAIN).content("{ }"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

		ResponseEntity<String> response = this.sudokuController.validateValue("3", "3", "6");

		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		Assert.assertEquals(result, response.getBody());
	}


	@Test
	public void testControllerInsertMoveAlreadyFilled() throws Exception {
		final String result = "The Board Cell is already filled with number 7";

		this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=0&column=0&value=7")
				.contentType(MediaType.TEXT_PLAIN))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

		ResponseEntity<String> response = this.sudokuController.validateValue("0", "0", "7");

		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		Assert.assertEquals(result, response.getBody());
	}

	@Test
	public void testControllerInsertValidMove() throws Exception{
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=8&column=4&value=9")
				.contentType(MediaType.TEXT_PLAIN))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

	}
	
	@Test
	public void testControllerSudokuComplete() throws Exception{
		final String result = "Congrats, Your SUDOKU Board is Complete :)!";
		
		ResponseEntity<String> response;		
	
		response = this.sudokuController.validateValue("0","1","9");
		response = this.sudokuController.validateValue("0","2","2");
		response = this.sudokuController.validateValue("0","3","1");
		response = this.sudokuController.validateValue("0","5","6");
		response = this.sudokuController.validateValue("0","8","8");
		response = this.sudokuController.validateValue("1","0","4");
		response = this.sudokuController.validateValue("1","1","6");
		response = this.sudokuController.validateValue("1","3","2");
		response = this.sudokuController.validateValue("1","4","3");
		response = this.sudokuController.validateValue("1","6","7");
		response = this.sudokuController.validateValue("1","8","9");
		response = this.sudokuController.validateValue("2","0","3");
		response = this.sudokuController.validateValue("2","1","1");
		response = this.sudokuController.validateValue("2","4","7");
		response = this.sudokuController.validateValue("2","6","6");
		response = this.sudokuController.validateValue("2","8","2");
		response = this.sudokuController.validateValue("3","3","8");
		response = this.sudokuController.validateValue("3","5","4");
		response = this.sudokuController.validateValue("3","6","2");
		response = this.sudokuController.validateValue("3","7","7");
		response = this.sudokuController.validateValue("4","0","2");
		response = this.sudokuController.validateValue("4","1","7");
		response = this.sudokuController.validateValue("4","2","6");
		response = this.sudokuController.validateValue("4","3","9");
		response = this.sudokuController.validateValue("4","5","3");
		response = this.sudokuController.validateValue("4","6","4");
		response = this.sudokuController.validateValue("4","7","8");
		response = this.sudokuController.validateValue("5","1","4");
		response = this.sudokuController.validateValue("5","2","1");
		response = this.sudokuController.validateValue("5","5","5");
		response = this.sudokuController.validateValue("5","7","6");
		response = this.sudokuController.validateValue("5","8","3");		
		response = this.sudokuController.validateValue("6","1","5");
		response = this.sudokuController.validateValue("6","4","8");
		response = this.sudokuController.validateValue("6","5","1");
		response = this.sudokuController.validateValue("6","6","3");
		response = this.sudokuController.validateValue("6","7","2");
		response = this.sudokuController.validateValue("6","8","6");
		response = this.sudokuController.validateValue("7","0","1");
		response = this.sudokuController.validateValue("7","1","2");
		response = this.sudokuController.validateValue("7","2","3");
		response = this.sudokuController.validateValue("7","3","6");
		response = this.sudokuController.validateValue("7","6","8");
		response = this.sudokuController.validateValue("7","7","9");
		response = this.sudokuController.validateValue("7","8","4");
		response = this.sudokuController.validateValue("8","1","8");
		response = this.sudokuController.validateValue("8","2","4");
		response = this.sudokuController.validateValue("8","3","3");
		response = this.sudokuController.validateValue("8","4","9");
		response = this.sudokuController.validateValue("8","5","2");
		response = this.sudokuController.validateValue("8","6","1");
		response = this.sudokuController.validateValue("8","8","7");
	
		System.out.println(SudokuBoard.getSudokuBoard());
		
		Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
		Assert.assertEquals(result, response.getBody());
	}


}
