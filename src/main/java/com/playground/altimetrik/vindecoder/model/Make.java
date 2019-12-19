package com.playground.altimetrik.vindecoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Make {
    private Integer makeID;
    private String makeName;
    private Integer vehicleTypeID;
    private String vehicleTypeName;
}
