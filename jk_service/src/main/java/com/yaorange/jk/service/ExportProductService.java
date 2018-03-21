package com.yaorange.jk.service;

import com.yaorange.jk.entity.ExportProduct;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface ExportProductService {

    Pagination findByPage(Pagination page, String exportId);

    List<ExportProduct> findAll();

    void save(ExportProduct model);

    ExportProduct findById(String id);

    void update(ExportProduct model);

    void deleteByIds(String[] ids);


}
