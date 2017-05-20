package com.github.sacredrelict.springbootangularsqlite.data.repository.impl;

import com.github.sacredrelict.springbootangularsqlite.data.entity.Biller;
import com.github.sacredrelict.springbootangularsqlite.data.repository.BillerDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Oleg on 14.05.2017.
 */
@Repository("billerDao")
public class BillerDaoImpl extends BaseDaoImpl<Biller> implements BillerDao {

    @Override
    protected String getEntityName() {
        return Biller.class.getSimpleName();
    }

    @Override
    public List<Biller> findBillers() {
        String queryString = "SELECT * FROM biller";
        final Query query = sessionNew().createSQLQuery(queryString).addEntity(Biller.class);
        return query.list();
    }

    @Override
    public void deleteBiller(Long id) {
        String queryString = "DELETE FROM biller WHERE id = :id";
        final Query query = sessionNew().createSQLQuery(queryString).addEntity(Biller.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateBiller(Long id, String name) {
        String queryString = "UPDATE biller SET name = :name WHERE id = :id";
        final Query query = sessionNew().createSQLQuery(queryString).addEntity(Biller.class);
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }
}
