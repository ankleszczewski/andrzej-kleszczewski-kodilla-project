package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CompanyConfig {

    @Value("${company.name}")
    private String companyName;

    @Value("&{company.gual}")
    private String companyGoal;
}
