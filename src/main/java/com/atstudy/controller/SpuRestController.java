package com.atstudy.controller;

import com.atstudy.bean.bo.AddSpuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuSearchBo;
import com.atstudy.bean.bo.UpdateSpuBo;
import com.atstudy.bean.po.Spu;
import com.atstudy.service.ISpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//视图类-负责商品产品业务，使用restful风格的接口
@Api(tags="商品模块")                   //swagger注解，告诉swagger，当前视图类是什么模块？
@RestController
@RequestMapping("/spurest")
public class SpuRestController {
    //成员属性
    @Autowired
    ISpuService spuService;

    //查询一个产品，，，
    //用户访问/spurest/{spu_id} url的时候，需要定位到getOne的方法
    @ApiOperation(value="查询单个商品")
    @GetMapping("/{spu_id}")        //采用get请求类型
    public Object getOne(
            @PathVariable("spu_id")
            Long spu_id
    ){

        Spu spu = spuService.getOne(spu_id);

        Map<String ,Object> response = new HashMap<>();

        if(spu!=null) {//返回成功
            response.put("code", 200);       //restful协议，需要code成员属性
            response.put("status", "success");//restful协议，需要status成员属性
            response.put("data", spu);
            //restful协议的范畴，返回的数据存到data成员属性中的
        }
        else {//调用业务层失败
            response.put("code",500);       //500错误
            response.put("status","error");
        }
        return response;
    }

    //添加产品
    @ApiOperation(value="添加商品")
    @PostMapping("")
    public Object addOne(AddSpuBo addSpuBo){
        System.out.println("调用到了添加1个商品的视图方法:"+addSpuBo);


        //todo，调用业务层的添加1个商品的，业务方法

        Map<String ,Object> response = new HashMap<>();
        response.put("code",200);
        response.put("status","success");
        //todo,把添加的结果放到map的data成员属性中

        return response;
    }

    //全局修改产品
    @PutMapping("")
    @ApiOperation(value = "全局修改单个商品")
    public Object putOne(UpdateSpuBo updateSpuBo){
        System.out.println("调用到了全局修改1个商品的视图方法:"+updateSpuBo);


        //todo，调用业务层的全局修改1个商品的，业务方法

        Map<String ,Object> response = new HashMap<>();
        response.put("code",200);
        response.put("status","success");
        //todo,把修改的结果放到map的data成员属性中

        return response;
    }



    //局部修改产品
    @PatchMapping("")
    @ApiOperation(value = "局部修改单个商品")
    public Object updateOne(UpdateSpuBo updateSpuBo){
        System.out.println("调用到了局部修改1个商品的视图方法:"+updateSpuBo);


        //todo，调用业务层的局部修改1个商品的，业务方法

        Map<String ,Object> response = new HashMap<>();
        response.put("code",200);
        response.put("status","success");
        //todo,把修改的结果放到map的data成员属性中

        return response;
    }


    //单条删除
    // /spurest/{spu_id} ....pathvariable注解
    @ApiOperation(value="删除单个商品")
    @DeleteMapping("/{spu_id}")
    public Object deleteOne(
            @PathVariable("spu_id")
            Long spu_id
    ){
        //todo，调用业务层的删除单条商品的，业务方法

        Map<String ,Object> response = new HashMap<>();
        response.put("code",200);       //restful协议，需要code成员属性
        response.put("status","success");//restful协议，需要status成员属性
        //todo,把删除的结果放到map的data成员属性中

        return response;
    }


    //多条删除
    @ApiOperation(value="批量删除商品")
    @DeleteMapping("")
    public Object multiDel(String[] id_list){
        System.out.println("调用到了批量删除商品的视图方法,id_list"+id_list.length);

        //todo，调用业务层的批量删除商品的，业务方法

        Map<String ,Object> response = new HashMap<>();
        response.put("code",200);       //restful协议，需要code成员属性
        response.put("status","success");//restful协议，需要status成员属性
        //todo,把删除的结果放到map的data成员属性中

        return response;
    }

    //条件查询产品
    @ApiOperation(value="条件查询商品列表")
    @PostMapping(value = "/_search",produces = "application/json;charset=UTF-8")
    public Object getList(
            @RequestBody                //告诉spring框架，spuSearchBo的入参，需要从请求的数据中先进行json字符串->java对象的动作
            SpuSearchBo spuSearchBo
    ){
        System.out.println("调用到了条件查询商品的视图方法");

        System.out.println(spuSearchBo);
        //分页参数就丢了，具体后面调用service方法过程中，还是需要分页参数的

        //todo，调用业务层的条件查询商品的，业务方法

        Map<String ,Object> response = new HashMap<>();
        response.put("code",200);       //restful协议，需要code成员属性
        response.put("status","success");//restful协议，需要status成员属性
        //todo,把查询到的结果放到map的data成员属性中

        return response;

    }

}
