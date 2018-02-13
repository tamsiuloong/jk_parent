package com.yaorange.jk.service;

import com.yaorange.jk.entity.ContractProduct;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface ContractProductService {
    /**
     * 根据合同id查询对应货物list
     * @param page
     * @param contractId 合同id
     * @return
     */
    Pagination findByPage(Pagination page, String contractId);

    List<ContractProduct> findAll();

    void save(ContractProduct model);

    ContractProduct findById(String id);

    void update(ContractProduct model);

    void deleteByIds(String[] ids);

    /**
     * 根据船期获取出货表
     * @param inputDate
     * @return
     */
    List<ContractProduct> findOutProductList(String inputDate);
}
