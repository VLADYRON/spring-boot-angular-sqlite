package com.github.sacredrelict.springbootangularsqlite.core.service.impl;

import com.github.sacredrelict.springbootangularsqlite.core.dto.BillerDto;
import com.github.sacredrelict.springbootangularsqlite.core.service.BillerService;
import com.github.sacredrelict.springbootangularsqlite.data.entity.Biller;
import com.github.sacredrelict.springbootangularsqlite.data.repository.BillerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
@Service("billerService")
public class BillerServiceImpl implements BillerService {

    @Autowired
    private BillerDao billerDao;

    @Override
    public List<Biller> getBillers() {
        return billerDao.findBillers();
    }

    @Override
    public void addBillerFromDto(BillerDto billerDto) {
        Biller biller = new Biller();
        biller.setName(billerDto.getName());
        billerDao.save(biller);
    }

    @Override
    public void removeBiller(Long id) {
        billerDao.deleteBiller(id);
    }

    @Override
    public void updateBillerFromDto(BillerDto billerDto) {
        billerDao.updateBiller(billerDto.getId(), billerDto.getName());
    }
}

