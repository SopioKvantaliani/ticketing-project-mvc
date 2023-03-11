package com.cydeo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private Long id;
    private LocalDateTime insertDateTime;
    private Long insertUserId;
    private LocalDateTime lastUpdateTime;
    private Long lastUpdateUserId;

}

/*
In my user table I want to have all this information.
Whenever I create manager, I want to see that information.
User Entity is extended this class, because whenever we create new User, we need all the info to know from BaseEntity class.
 */