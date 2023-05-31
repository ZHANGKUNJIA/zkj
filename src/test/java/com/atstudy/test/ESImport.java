package com.atstudy.test;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.tran.DataStream;
import org.frameworkset.tran.db.input.es.DB2ESImportBuilder;

//职责：从mysql,atstudy_mall数据库spu数据表，把400+条的数据，导入es的spu仓库
public class ESImport {
    public static void main(String[] args) {
        DB2ESImportBuilder importBuilder = DB2ESImportBuilder.newInstance();

        // 第一步：删除原有的 Indice（es中的文档、数据表）
        //如果保留第一步，数据导入过程中会采用es自适应数据类型的方式来自动匹配数据类型
        //会带来一个问题——手动创建好的spu -es中的文档/数据表结构就没了。。商品名、标题、介绍失去了指定了分词器规则，后面执行条件查询时有些需求就无法实现了
//        try {
//
//            ElasticSearchHelper.getRestClientUtil().dropIndice("spu");
//        }
//        catch (Exception e){}


        //第二步， JDBC 配置
        importBuilder.setDbName("atstudy_mall")
                .setDbDriver("com.mysql.cj.jdbc.Driver")
                .setDbUrl("jdbc:mysql://127.0.0.1:3306/atstudy_mall?useUnicode=true&characterEncoding=utf8&useCursorFetch=true")
                .setDbUser("root")
                .setDbPassword("")
                .setUsePool(false);//是否使用连接池

        // 将查询结果导入 ElasticSearch
        importBuilder.setSql("select * from `spu`");


        //第三步， ElasticSearch 配置（往es中导入数据）
        importBuilder
                .setIndex("spu")            //指定spu的文档（es中的spu）
                .setIndexType("_doc")
                .setRefreshOption(null)
                .setUseJavaName(false)
                .setBatchSize(5000)
                .setJdbcFetchSize(10000);
        // 开始导入数据
        DataStream dataStream = importBuilder.builder();
        dataStream.execute();
    }
}