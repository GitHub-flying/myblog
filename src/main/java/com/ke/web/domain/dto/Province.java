package com.ke.web.domain.dto;

import com.ke.web.domain.dto.City;
import lombok.Data;

import java.util.List;

/**
 * @author ke
 * @ClassName Province
 * @Description TOOD
 * @Date 2019/11/15
 * @Version 1.0
 **/
@Data
public class Province {
    private String name;
    private String level;
    private String code;
    private List<City> cities;

}
