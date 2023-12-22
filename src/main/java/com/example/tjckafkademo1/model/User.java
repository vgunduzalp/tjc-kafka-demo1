package com.example.tjckafkademo1.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String userName;
    private Integer age;
}
