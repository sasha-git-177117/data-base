package model;

import lombok.Data;

@Data
public class Author {
    private Integer id;
    private String name;
    private String login;
    private String email;
}
