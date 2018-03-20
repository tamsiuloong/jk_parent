package com.yaorange.jk.web.controller.cargo;

import com.yaorange.jk.entity.Factory;
import com.yaorange.jk.service.FactoryService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
@RequestMapping("/cargo/factory")
@RestController
public class FactoryCtrl {

    @Autowired
    private FactoryService factoryService;

    @GetMapping
    public Pagination list(Pagination page)
    {
        Pagination result = factoryService.findByPage(page);
        return result;
    }


    @GetMapping("getAll")
    public List<Factory> getAll(String cType)
    {
        List<Factory> factoryList = factoryService.findByCtype(cType);
        return factoryList;
    }

    @DeleteMapping
    public String delete(@RequestBody String[] ids)
    {
        factoryService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(@RequestBody Factory factory)
    {
        factoryService.update(factory);
        return "1";
    }

    @PostMapping
    public Factory add(@RequestBody Factory factory)
    {
        factoryService.save(factory);
        return factory;
    }
}
