package com.github.sacredrelict.springbootangularsqlite.data.repository;

import com.github.sacredrelict.springbootangularsqlite.data.entity.Biller;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
public interface BillerDao extends BaseDao<Biller> {

    List<Biller> findBillers();

    void deleteBiller(Long id);

    void updateBiller(Long id, String name);

}
