package com.emailIntegration.entity.dtos;

import lombok.Data;

import java.util.List;
@Data
public class EmailDto
{
    private String from;
    private String[] to;

    private String subject;
    private String body;
}
