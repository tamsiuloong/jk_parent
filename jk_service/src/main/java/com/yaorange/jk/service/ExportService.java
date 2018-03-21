package com.yaorange.jk.service;

import com.yaorange.jk.entity.Export;
import com.yaorange.jk.entity.ExportProduct;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface ExportService {
    Pagination findByPage(Pagination page);

    List<Export> findAll();

    void save(Export model);

    Export findById(String id);

    void update(Export model);

    void deleteByIds(String[] ids);

    /**
     * 根据报运单id货物对应货物list
     * @param id
     * @return
     */
    List<ExportProduct> findEPListByEId(String id);

    /**
     * 更新保运货物
     * @param ep
     */
    void updateEP(ExportProduct ep);

    void updateState(String[] ids, int state);
}
