package org.psawesome.springbootoauth2server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * package: org.psawesome.springbootoauth2server.entity
 * author: PS
 * DATE: 2020-05-20 수요일 01:20
 */
@Data
@AllArgsConstructor
@Table(name = "User")
public class MyUser {

    @Id
    Long id;

    @Column
    String name;
}
