package com.yaorange.jk.service;

import com.yaorange.jk.entity.ExtCproduct;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface ExtCProductService {
    /**
     * 根据合同id查询对应货物list
     * @param page
     * @param contractId 合同id
     * @return
     */
    Pagination findByPage(Pagination page, String contractId);

    List<ExtCproduct> findAll();

    void save(ExtCproduct model);

    ExtCproduct findById(String id);

    void update(ExtCproduct model);

    void deleteByIds(String[] ids);
}
