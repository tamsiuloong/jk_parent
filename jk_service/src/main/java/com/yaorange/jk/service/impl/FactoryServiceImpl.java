package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Factory;
import com.yaorange.jk.service.FactoryService;
import com.yaorange.jk.utils.Pagination;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("factoryService")
@Transactional
public
class FactoryServiceImpl implements FactoryService {

    @Autowired
    private BaseDao<Factory,String> factoryDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return factoryDao.pageByHql("from Factory",page.getPageNo(),page.getPageSize());
    }

    @Override
    public List<Factory> findAll() {
        return factoryDao.getListByHQL("from Factory");
    }

    @Override
    public void save(Factory model) {
        factoryDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Factory findById(String id) {
        return factoryDao.get(Factory.class,id);
    }

    @Override
    public void update(Factory model) {
        factoryDao.update(model);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            factoryDao.deleteById(Factory.class,id);
        }
    }

    @Override
    public List<Factory> findByCtype(String cType) {
        return factoryDao.getListByHQL("from Factory where ctype = ?",cType);
    }

    @Override
    public List<Object[]> findFactorySale() {
        List<Object[]> result = null;
        Session session  = sessionFactory.openSession();


        SQLQuery sqlQuery = session.createSQLQuery("select factory_name,sum(cnumber) sales from contract_product_c  group by factory_name");
        result=sqlQuery.list();

        session.close();
        return result;
    }

    @Override
    public List<Object[]> findProductSale() {
        List<Object[]> result = null;
        Session session  = sessionFactory.openSession();


        SQLQuery sqlQuery = session.createSQLQuery("select p2.product_no,p2.sales from (\n" +
                "select p.*,rownum r1 from (\n" +
                "select product_no,sum(cnumber) sales from contract_product_c group by product_no order by sum(cnumber) desc\n" +
                ") p \n" +
                ") p2 where p2.r1<11");
        result=sqlQuery.list();

        session.close();
        return result;
    }
}
