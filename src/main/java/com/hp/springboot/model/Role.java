package com.hp.springboot.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String name;
    private String code;
    private String parentCode;
    private String desc;
    private Date createTime;
    private Date updateTime;
}
