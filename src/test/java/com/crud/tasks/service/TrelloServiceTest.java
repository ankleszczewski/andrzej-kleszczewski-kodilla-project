package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.domain.badges.AttachmentsByType;
import com.crud.tasks.domain.badges.Badges;
import com.crud.tasks.domain.badges.Trello;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("1", "name", false));

        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(new TrelloBoardDto("1", "BoardName", trelloListDtos));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> testTrelloBoardDto = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(1, testTrelloBoardDto.size());
        assertEquals("BoardName", testTrelloBoardDto.get(0).getName());
    }

    @Test
    public void testCreateTrelloCard() {
        Badges badge = new Badges(1, new AttachmentsByType(new Trello(1, 1)));
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "1", "name", "url.com", badge);
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "name", "desc", "pos", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        //when
        CreatedTrelloCardDto testCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertEquals("1", testCreatedTrelloCardDto.getId());
        assertEquals("name", testCreatedTrelloCardDto.getName());
        assertEquals("url.com", testCreatedTrelloCardDto.getShortUrl());
        assertEquals(badge, testCreatedTrelloCardDto.getBadges());
    }
}
