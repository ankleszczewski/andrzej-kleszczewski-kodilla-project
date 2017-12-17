package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.badges.Trello;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TrelloValidatorTest {

    @Test
    public void testValidateTrelloBoards() {
        //Given
        TrelloValidator trelloValidator = new TrelloValidator();

        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("1", "test", trelloLists);
        TrelloBoard board2 = new TrelloBoard("2", "name", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(board1);
        trelloBoards.add(board2);
        //When
        List<TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        //Then
        assertEquals(1, validatedTrelloBoards.size());
        assertFalse(validatedTrelloBoards.contains(board1));
        assertTrue(validatedTrelloBoards.contains(board2));
    }
}
