package com.hp.springboot.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private int age;
    private String idCard;
    private Integer gender;
    private String mobile;
    private String address;
    private Date createTime;
    private Date updateTime;
}
