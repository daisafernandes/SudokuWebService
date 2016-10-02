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

        this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=2&column=b&value=5")
            .contentType(MediaType.TEXT_PLAIN))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

        ResponseEntity<String> response = this.sudokuController.validateValue("2", "b", "5");

        Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
        Assert.assertEquals(result, response.getBody());
    }
    
    @Test
    public void testControllerInsertInvalidNumber() throws Exception {
        final String result = "Invalid input COLUMN 11";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=2&column=11&value=5")
            .contentType(MediaType.TEXT_PLAIN))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

        ResponseEntity<String> response = this.sudokuController.validateValue("2", "11", "5");

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
    	final String result = "Valid move, value 3, but not completed yet!";

        this.mockMvc.perform(MockMvcRequestBuilders.put("/sudoku/moves?row=1&column=6&value=3")
            .contentType(MediaType.TEXT_PLAIN))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"));

        ResponseEntity<String> response = this.sudokuController.validateValue("8", "4", "3");

        Assert.assertTrue(response != null && response.hasBody() && !response.getBody().isEmpty());
        Assert.assertEquals(result, response.getBody());
    }
}
