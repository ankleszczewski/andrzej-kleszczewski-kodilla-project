package com.crud.tasks.domain;

import com.crud.tasks.domain.badges.Badges;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCard {

    private String id;
    private String name;
    private Badges badges;
    private String shorturl;
}
