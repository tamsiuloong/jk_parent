package com.yaorange.jk.web.controller.cargo;

import com.yaorange.jk.entity.ExportProduct;
import com.yaorange.jk.service.ExportProductService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
@RequestMapping("/cargo/exportProduct")
@RestController
public class ExportPorductCtrl {

    @Autowired
    private ExportProductService exportProductService;

    @GetMapping("/{exportId}")
    public Pagination list(Pagination page,@PathVariable("exportId")String exportId)
    {
        Pagination result = exportProductService.findByPage(page,exportId);
        return result;
    }




    @GetMapping("getAll")
    public List<ExportProduct> getAll(Pagination page)
    {
        List<ExportProduct> exportProductList = exportProductService.findAll();
        return exportProductList;
    }

    @DeleteMapping
    public String delete(String[] ids)
    {
        exportProductService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(@RequestBody ExportProduct exportProduct)
    {
        exportProductService.update(exportProduct);
        return "1";
    }

    @PostMapping
    public ExportProduct add(@RequestBody ExportProduct exportProduct)
    {
        exportProductService.save(exportProduct);
        return exportProduct;
    }
}
