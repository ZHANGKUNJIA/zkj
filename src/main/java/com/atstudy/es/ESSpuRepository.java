package com.atstudy.es;

import com.atstudy.bean.bo.AddSpuBo;
import com.atstudy.bean.bo.UpdateSpuBo;
import com.atstudy.bean.po.ESSpu;
import org.apache.ibatis.annotations.Update;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

///需要访问es中的 spu 文档，的数据访问层实现类
@Component              //作为下层，它的上层应该是SpuService业务层实现类，供上层调用
public class ESSpuRepository {

    @Resource
    private ESUtils esUtils;

    // 添加 商品 Spu
    public void add(AddSpuBo addSpuBo){

        // 第一部分， 将 AddSpuBo 转化成 ESSpuPo对象
        // 需要在bean的pakcage中，po的package中，要创建一个ESSpu的类型(需要和es中的spu文档，要对应起来)
        ESSpu spu = new ESSpu();
        spu.setSpu_id(  addSpuBo.getSpu_id() );
        spu.setSpu_name( addSpuBo.getSpu_name() );
        spu.setSpu_title( addSpuBo.getSpu_title() );
        spu.setSpu_introduction( addSpuBo.getSpu_introduction() );
        spu.setSpu_status( addSpuBo.getSpu_status() == 1 );
        spu.setSpu_brand_id( addSpuBo.getSpu_brand_id() );
        spu.setCreatetime( addSpuBo.getCreatetime() );
        spu.setUpdatetime( addSpuBo.getUpdatetime() );
        spu.setSpu_unit( addSpuBo.getSpu_unit() );

        // 创建 ES 实体对象
        EsEntity<ESSpu> esEntity = new EsEntity<>(spu.getSpu_id().toString(),spu);
        try{
            // 通过es访问操作的帮助类，添加 ES 实体对象
            IndexResponse response = esUtils.insertOrUpdateOne("spu",esEntity);
            System.out.println("==> 添加数据到 ElasticSearch 搜索引擎成功！");
        }catch (Exception e){
            System.out.println("==> 添加数据到 ElasticSearch 搜索引擎失败！");
            /* 封装 发送消息的 载荷 数据
            Map<String,Object> payload = new HashMap<>();
            payload.put("index","spu");
            payload.put("entity",esEntity);
            // 将 要添加的数据 发送到 消息队列中间件 使用异步任务实现重新添加
            rabbitTemplate.convertAndSend(
                    "mall-es",      // Exchange 交换机的名称
                    "mall.es.add.readd",    // RoutingKey 路由映射规则
                    JSON.toJSONString( payload )    // 消息内容
            );
            System.out.println("==> 向 消息队列中间件 发送消息！");*/
        }
    }

    //修改ESSpu
    public void update(UpdateSpuBo updateSpuBo){

        // 第一部分， 将 UpdateSpuBo 转化成 ESSpuPo对象
        // 需要在bean的pakcage中，po的package中，要创建一个ESSpu的类型(需要和es中的spu文档，要对应起来)
        ESSpu spu = new ESSpu();
        spu.setSpu_id(  updateSpuBo.getSpu_id() );
        spu.setSpu_name( updateSpuBo.getSpu_name() );
        spu.setSpu_title( updateSpuBo.getSpu_title() );
        spu.setSpu_introduction( updateSpuBo.getSpu_introduction() );
        spu.setSpu_status( updateSpuBo.getSpu_status() == 1 );
        spu.setSpu_brand_id( updateSpuBo.getSpu_brand_id() );
        spu.setCreatetime( updateSpuBo.getCreatetime() );
        spu.setUpdatetime( updateSpuBo.getUpdatetime() );
        spu.setSpu_unit( updateSpuBo.getSpu_unit() );

        // 创建 ES 实体对象
        EsEntity<ESSpu> esEntity = new EsEntity<>(spu.getSpu_id().toString(),spu);
        try{
            // 通过es访问操作的帮助类，添加 ES 实体对象
            IndexResponse response = esUtils.insertOrUpdateOne("spu",esEntity);
            System.out.println("==> 添加数据到 ElasticSearch 搜索引擎成功！");
        }catch (Exception e){
            System.out.println("==> 添加数据到 ElasticSearch 搜索引擎失败！");
            /* 封装 发送消息的 载荷 数据
            Map<String,Object> payload = new HashMap<>();
            payload.put("index","spu");
            payload.put("entity",esEntity);
            // 将 要添加的数据 发送到 消息队列中间件 使用异步任务实现重新添加
            rabbitTemplate.convertAndSend(
                    "mall-es",      // Exchange 交换机的名称
                    "mall.es.add.readd",    // RoutingKey 路由映射规则
                    JSON.toJSONString( payload )    // 消息内容
            );
            System.out.println("==> 向 消息队列中间件 发送消息！");*/
        }
    }
}
