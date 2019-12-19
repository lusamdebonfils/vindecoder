package com.playground.altimetrik.vindecoder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    private Integer makeID;
    private String makeName;
    private Integer modelID;
    private String modelName;
}
