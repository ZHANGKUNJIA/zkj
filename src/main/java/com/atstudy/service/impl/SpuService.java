package com.atstudy.service.impl;

import com.alibaba.fastjson.JSON;
import com.atstudy.bean.bo.AddSpuBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.SpuSearchBo;
import com.atstudy.bean.bo.UpdateSpuBo;
import com.atstudy.bean.po.Spu;
import com.atstudy.bean.po.SpuAttrValueRelation;
import com.atstudy.bean.vo.SpuVo;
import com.atstudy.es.ESSpuRepository;
import com.atstudy.es.ESUtils;
import com.atstudy.mapper.CategoryMapper;
import com.atstudy.mapper.SpuAttrValueMapper;
import com.atstudy.mapper.SpuMapper;
import com.atstudy.service.ISpuService;
import com.atstudy.utils.ImgUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//业务层实现类-负责商品
@Service        //托管给spring框架
public class SpuService implements ISpuService {
    //成员属性
    @Autowired
    SpuMapper spuMapper;

    @Autowired
    SpuAttrValueMapper spuAttrValueMapper;

    @Autowired
    CategoryMapper categoryMapper;

    //增加一个es操作帮助类的，成员属性
    @Autowired
    ESUtils esUtils;

    //增加一个ESSpu数据层的成员属性+自动装载
    @Autowired
    ESSpuRepository esSpuRepository;

    @Autowired
    ImgUtils imgUtils;

    //es占用资源比较多，授课es完毕后，我们要把es要关闭+保证商品管理的可用性
    Boolean enableES = false;   //如果是true，就查询时访问es、增删改时也级联访问es
                                //如果是false，CRUD就不访问es

    @Override
    public List<Spu> getListWithBrand(SpuSearchBo spuSearchBo, PageBo pageBo) {

        if(this.enableES == false) {
            //第一步，得到符合条件的记录数
            Integer resultCount = spuMapper.getCount(spuSearchBo);

            //第二步，将记录数赋值给pageBo中的resultCount
            pageBo.setResultCount(resultCount);

            //第三步，返回分页查询list数据
            return spuMapper.getListWithBrand(spuSearchBo, pageBo);
        }

        //es开关开启，则 从 ElasticSearch 搜索引擎中 查询 满足条件的商品列表
        System.out.println( "===> spu_id = " + spuSearchBo.getSpu_id() );
        System.out.println( "===> spu_name = " + spuSearchBo.getSpu_name() );
        System.out.println( "===> spu_status = " + spuSearchBo.getSpu_status() );
        System.out.println( "===> brand_id = " + spuSearchBo.getSpu_brand_id() );
        System.out.println( "===> start_index = " + pageBo.getStartIndex() );
        System.out.println( "===> page_size = " + pageBo.getPageSize() );

        //前置步骤： 封装 query 条件（封装一个json的对象，递交给es进行查询）
        BoolQueryBuilder query = new BoolQueryBuilder();
        // 判断 查询条件中 是否包含 spu_id
        if( spuSearchBo.getSpu_id() != null ){
            query.must(QueryBuilders.termQuery("spu_id",spuSearchBo.getSpu_id() ));
        }
        // 判断 查询条件中 是否包含 spu_name（和mapper.xml中的动态标签if的写法，是类似的）
        if( spuSearchBo.getSpu_name() != null && spuSearchBo.getSpu_name().length() > 0 ){
            query.must( QueryBuilders.matchQuery("spu_name" , spuSearchBo.getSpu_name() ) );
        }
        // 判断 查询条件中 是否包含 spu_status
        if( spuSearchBo.getSpu_status() != null && spuSearchBo.getSpu_status() != -1){
            query.must( QueryBuilders.termQuery( "spu_status" , spuSearchBo.getSpu_status() == 1 ? true : false ) );
        }
        // 判断 查询条件中 是否包含 spu_brand_id
        if( spuSearchBo.getSpu_brand_id() != null && spuSearchBo.getSpu_brand_id().length() > 0 ){
            System.out.println("产生spu_brand_id条件");
            query.must( QueryBuilders.termQuery( "spu_brand_id" , spuSearchBo.getSpu_brand_id() ) );
        }

        //第一步：设置了分页条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder
                .query( query )
                .from( pageBo.getStartIndex().intValue() )
                .size( pageBo.getPageSize() );

        //第二步， 开始查询 并且 返回结果
        List<Spu> spuList =  esUtils.search("spu" , searchSourceBuilder , Spu.class );

        long  resultCount = esUtils.totalHits;

        //设置,返回的记录数resultCount
        pageBo.setResultCount((int) resultCount);

        return spuList;
    }


    @Override
    public SpuVo getVoBySpuId(Long spu_id) {
        return spuMapper.getVo(spu_id);
    }

    //更新商品的业务
    @Transactional(rollbackFor = Exception.class)       //使用spring事务，出现exception异常，即可回滚事务
    @Override
    public boolean updateOne(UpdateSpuBo updateSpuBo) {
        boolean result = false; //初始是false

        //设置一下updatetime，java服务器的时间，要进行赋值
        updateSpuBo.setUpdatetime(new Date());

        //数据库访问，更新spu主表信息
        int row1 = spuMapper.updateOne(updateSpuBo);

        if(row1<=0){
            //更新失败，返回false
            return  result;
        }

        //数据库访问，删除spu-分类的信息
        int row2 = categoryMapper.deleteSpuCate(updateSpuBo.getSpu_id()) ;
        if(row2<=0){
            //更新失败，返回false
            //假设从产品+技术层面，之前设定了一个商品必须要关联1个分类，。。。意味着此时一定会有row2的删除动作受影响行数...throw异常
            //                  产品层面规定没有那么死/现实已经存放的数据层面，有些商品是未关联分类。。。。。。。。。。。return false
            return  result;
        }

        //数据库访问，批量添加spu-分类的信息
        int row3 = categoryMapper.addSpuCate(updateSpuBo) ;

        if(row3<=0){
            throw new RuntimeException("categoryMapper.addSpuCate出错，受影响行数-"+row3);
        }

        //数据库访问，删除spu-属性值的信息
        int row4 = spuAttrValueMapper.deleteSpuAttrValueRelationBySpuId(updateSpuBo.getSpu_id());

        if(row4<=0){
            return result;
        }

        //数据库访问，批量添加spu-筛选属性值的信
        int row5 = spuAttrValueMapper.addSpuAttrValueFilterRelation(updateSpuBo);
        if(row5<=0){
            throw new RuntimeException("spuAttrValueMapper.addSpuAttrValueFilterRelation出错，受影响行数-"+row5);
        }


        //遍历图片控件，上传图片内容。。。才会得到图片的路径，
        for (SpuAttrValueRelation relation:updateSpuBo.getSkuAttrValueList()) {
            //外层循环，得到当前商品的所有规格属性值的数据

            System.out.println("更新商品时，提交的规格属性值信息："+relation);

            //相册字符串数组;初始化需要初始化多个元素？..引用length属性即可
            String[] albums = new String[relation.getSpu_attr_image_list().length];
            int index = 0;
            for (MultipartFile img:
                 relation.getSpu_attr_image_list()) {//内层循环，得到当前规格属性值，上传的图片列表
                System.out.println("提交的相册："+img.getOriginalFilename());

                //执行上传图片.. 生成一个随机的图片名称...需要修改当前的规格属性值.spu_attr_imgs[外层循环中的成员属性]
                String filename = imgUtils.upload(img);

                //把保存好服务器文件名，存到一个String[]，需要引用相册的索引值
                albums[index] = filename;
                index++;
            }

            /*System.out.println("输出相册信息");
            for (String str:albums
                 ) {

                System.out.println(str);
            }*/

            //更新updateSpuBo.skuAttrValueList.spu_attr_imgs
            relation.setSpu_attr_imgs(
                    JSON.toJSONString( albums)          //set访问器需要一个json字符串，因此传递字符串数组+JSON.toJSONString的方法
            );
        }

        //数据库访问，批量添加spu-规格属性值的信息
        int row6 = spuAttrValueMapper.addSpuAttrValueSkuRelation(updateSpuBo);
        if(row6<=0){
            throw new RuntimeException("spuAttrValueMapper.addSpuAttrValueSkuRelation出错，受影响行数-"+row6);
        }

        if(this.enableES) {
            //增加一段对于es的操作逻辑（esUtils.insertOrUpdate的方法）
            esSpuRepository.update(updateSpuBo);
        }

        return true;//返回正确
    }

    @Override
    public Spu getOne(Long spu_id) {
        return spuMapper.getOne(spu_id);
    }


    // 添加商品Spu
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addOne(AddSpuBo addSpuBo) {

        Date now = new Date();
        // 给要添加的商品Spu 设置 创建时间和更新时间
        addSpuBo.setCreatetime( now );
        addSpuBo.setUpdatetime( now );

        UpdateSpuBo updateSpuBo = new UpdateSpuBo();
        updateSpuBo.setCategoryList(addSpuBo.getCategoryList());

        // 向 spu 数据表 添加数据
        int affectedRows1 = spuMapper.addOne( addSpuBo );
        updateSpuBo.setSpu_id(addSpuBo.getSpu_id());

        // 向 cate_spu 数据表 添加 商品和分类的关系数据
        int affectedRows2 = categoryMapper.addSpuCate( updateSpuBo );

        // 遍历 筛选属性
        for(  SpuAttrValueRelation r :addSpuBo.getFilterAttrValueList() ){
            r.setSpu_attr_imgs("[]");
        }

        // 处理商品规格属性 中的 上传的相册图片
        // 遍历 规格属性列表
        for(SpuAttrValueRelation r : addSpuBo.getSkuAttrValueList()){
            // 判断 当前遍历到的规格属性 是否 有相册
            if( r.getSpu_attr_image_list()[0].getSize() == 0 ){
                // 当前遍历到的规格属性 没有相册
                r.setSpu_attr_imgs("[]");
            }else{
                // 当前遍历到的规格属性 有相册
                // 准备 数组 存放 当前遍历到的规格属性的 相册
                String[] ablum = new String[ r.getSpu_attr_image_list().length ];
                // 循环 当前规格属性相册中的 每一个 上传图片
                for( int i = 0 ; i <= r.getSpu_attr_image_list().length - 1 ; i++ ){
                    //调用通用的文件上传工具类，完成上传工作
                    String filename = imgUtils.upload(r.getSpu_attr_image_list()[i]);
                    // 将 当前循环到的上传文件 的 文件名 添加到 相册数组中
                    ablum[i] = filename;
                }
                r.setSpu_attr_imgs(JSON.toJSONString( ablum ));
            }
        }
        updateSpuBo.setFilterAttrValueList(addSpuBo.getFilterAttrValueList());
        updateSpuBo.setSkuAttrValueList((addSpuBo.getSkuAttrValueList()));

        // 向 spu_attr_value_spus 数据表 添加 商品和属性的关系数据
        int affectedRows3 = spuAttrValueMapper.addSpuAttrValueFilterRelation( updateSpuBo );

        int affectedRow4 = spuAttrValueMapper.addSpuAttrValueSkuRelation(updateSpuBo);

        if(this.enableES) {
            //增加一段对于es的操作逻辑
            esSpuRepository.add(addSpuBo);
        }

        return affectedRows1 > 0 || affectedRows2 > 0 && affectedRows3 > 0;
    }

    @Override
    public List<Spu> getAll() {
        return spuMapper.getAll();
    }

}
