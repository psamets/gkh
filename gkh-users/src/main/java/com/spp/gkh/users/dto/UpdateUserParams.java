package com.spp.gkh.users.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserParams {
    private String name;
    private Integer age;
}
