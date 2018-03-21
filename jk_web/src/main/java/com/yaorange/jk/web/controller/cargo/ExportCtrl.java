package com.yaorange.jk.web.controller.cargo;

import com.yaorange.jk.entity.Export;
import com.yaorange.jk.service.ExportService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
@RequestMapping("/cargo/export")
@RestController
public class ExportCtrl {

    @Autowired
    private ExportService exportService;

    @GetMapping
    public Pagination list(Pagination page)
    {
        Pagination result = exportService.findByPage(page);
        return result;
    }

    @GetMapping("/{id}")
    public Export get(@PathVariable("id") String id)
    {
        Export result = exportService.findById(id);
        return result;
    }


    @DeleteMapping
    public String delete(@RequestBody String[] ids)
    {
        exportService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(@RequestBody Export export)
    {
        exportService.update(export);
        return "1";
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Export export)
    {
        try
        {
            exportService.save(export);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return new  ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new  ResponseEntity("1", HttpStatus.OK);
    }

    @PutMapping("/submit")
    public String submit(@RequestBody String[] ids)
    {
        exportService.updateState(ids,1);
        return "1";
    }

    @PutMapping("/cancel")
    public String cancel(@RequestBody String[] ids)
    {
        exportService.updateState(ids,0);
        return "1";
    }
}
