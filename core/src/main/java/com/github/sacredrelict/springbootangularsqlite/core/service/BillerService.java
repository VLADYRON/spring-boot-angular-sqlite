package com.github.sacredrelict.springbootangularsqlite.core.service;

import com.github.sacredrelict.springbootangularsqlite.core.dto.BillerDto;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Biller;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
public interface BillerService {

    List<Biller> getBillers();

    void addBillerFromDto(BillerDto billerDto);

    void removeBiller(Long id);

    void updateBillerFromDto(BillerDto billerDto);

}
