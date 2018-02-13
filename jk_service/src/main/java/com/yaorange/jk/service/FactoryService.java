package com.yaorange.jk.service;

import com.yaorange.jk.entity.Factory;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface FactoryService {
    Pagination findByPage(Pagination page);

    List<Factory> findAll();

    void save(Factory model);

    Factory findById(String id);

    void update(Factory model);

    void deleteByIds(String[] ids);

    /**
     * 根据厂家类型查询list
     * @param cType 获取/附件
     * @return
     */
    List<Factory> findByCtype(String cType);

    /**
     * 获取生产厂家销售情况
     * @return
     */
    List<Object[]> findFactorySale();

    /**
     * 获取产品销售情况
     * @return
     */
    List<Object[]> findProductSale();
}
