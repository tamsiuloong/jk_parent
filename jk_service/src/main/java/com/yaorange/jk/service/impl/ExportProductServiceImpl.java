package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.ExportProduct;
import com.yaorange.jk.service.ExportProductService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("exprotProductService")
@Transactional
public class ExportProductServiceImpl implements ExportProductService {

    @Autowired
    private BaseDao<ExportProduct,String> exprotProductDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page, String exportId) {
        return exprotProductDao.pageByHql("from ExportProduct where export.id = ?",page.getPageNo(),page.getPageSize(),exportId);
    }

    @Override
    public List<ExportProduct> findAll() {
        return exprotProductDao.getListByHQL("from ExportProduct");
    }

    @Override
    public void save(ExportProduct model) {
        exprotProductDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public ExportProduct findById(String id) {
        return exprotProductDao.get(ExportProduct.class,id);
    }

    @Override
    public void update(ExportProduct model) {
        ExportProduct exportProduct = exprotProductDao.get(ExportProduct.class,model.getId());
        model.setExtEproducts(exportProduct.getExtEproducts());
        exprotProductDao.merge(model);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            exprotProductDao.deleteById(ExportProduct.class,id);
        }
    }
}
