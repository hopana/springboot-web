package com.hp.springboot.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sms {
    private Long id;
    private String mobile;
    private String content;
    private Date createTime;
    private Date updateTime;
}
