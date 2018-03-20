package com.yaorange.jk.web.controller.cargo;

import com.yaorange.jk.entity.ExtCproduct;
import com.yaorange.jk.service.ExtCProductService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
@RequestMapping("/cargo/extCproduct")
@RestController
public class ExtCproductCtrl {

    @Autowired
    private ExtCProductService extCproductService;

    @GetMapping
    public Pagination list(Pagination page,String contractProductId)
    {
        Pagination result = extCproductService.findByPage(page,contractProductId);
        return result;
    }


    @GetMapping("getAll")
    public List<ExtCproduct> getAll(Pagination page)
    {
        List<ExtCproduct> extCproductList = extCproductService.findAll();
        return extCproductList;
    }

    @DeleteMapping
    public String delete(@RequestBody String[] ids)
    {
        extCproductService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(@RequestBody ExtCproduct extCproduct)
    {
        extCproductService.update(extCproduct);
        return "1";
    }

    @PostMapping
    public ExtCproduct add(@RequestBody ExtCproduct extCproduct)
    {
        extCproductService.save(extCproduct);
        return extCproduct;
    }
}
