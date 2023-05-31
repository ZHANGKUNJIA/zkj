package com.atstudy.test;


import com.atstudy.Application;
import com.atstudy.bean.bo.AddCategoryBo;
import com.atstudy.bean.bo.CategorySearchBo;
import com.atstudy.bean.bo.PageBo;
import com.atstudy.bean.bo.UpdateCategoryBo;
import com.atstudy.bean.po.Category;
import com.atstudy.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;


@SpringBootTest(classes = Application.class)    //标注一个测试类的注解，需要指定主启动类
public class CategoryMapperTest {/*新建一个测试类，需要服务于mapper包中的CategoryMapper*/
    //成员属性（测试类是上层、CategoryMapper接口类型是下层）
    @Autowired
    CategoryMapper categoryMapper;//因为有了@Autowired注解，所以这个成员属性在启动后，会由spring框架的上下文进行自动实例化


    //增加一些测试方法
    @Test               //需要增加@Test注解
    public void getOne(){//查询的测试+单条记录的查询（java类型对象实例）
        //需要通过成员属性，要去调用getOne()方法，根据返回值要确定到底调用成功还是失败
        int cate_id = 1;
        Category category = categoryMapper.getOne(cate_id);

        if(category!=null){
            System.out.println("cate_id="+cate_id+",返回结果："+category);
        }
    }

    //增删改的测试？
    @Test
    public void addOne(){
        //准备数据
        AddCategoryBo addCategoryBo=new AddCategoryBo();
        addCategoryBo.setCate_name("测试分类");
        addCategoryBo.setCate_sort(1);

        //因为创建时间和修改时间是timestamp类型，所以需要先实例化一个ts
        Timestamp d = new Timestamp(System.currentTimeMillis());
        addCategoryBo.setCreatetime(d );
        addCategoryBo.setUpdatetime(d);
        addCategoryBo.setCate_parentid(1);

        //调用数据访问层，updateOne方法
        int affectedRows = categoryMapper.addOne(addCategoryBo);

        //两条规则：判断
        //——受影响的行数是大于0，代表成功
        //——添加业务，还需要多判断一下，插入之后返回的主键id的数据是否是符合预期的
        if(affectedRows>0 && addCategoryBo.getCate_id()>0){
            System.out.println("调用添加成功，affectedRows="+affectedRows+",主键id="+addCategoryBo.getCate_id());
        }else {
            System.out.println("调用失败");
        }

    }

    @Test
    public void updateOne(){
        //准备数据
        UpdateCategoryBo updateCategoryBo = new UpdateCategoryBo();
        updateCategoryBo.setCate_id(1923);
        updateCategoryBo.setCate_name("测试分类修改");
        Timestamp d = new Timestamp(System.currentTimeMillis());
        updateCategoryBo.setUpdatetime(d);
        updateCategoryBo.setCate_sort(2);
        updateCategoryBo.setCate_parentid(2);

        //调用访问层
        int result = categoryMapper.updateOne(updateCategoryBo);

        //判断
        if(result>0){
            System.out.println("调用成功");
        }
        else{
            System.out.println("调用失败：result="+result);
        }
    }

    @Test
    public void deleteOne(){
        //准备数据
        Integer cate_id = 1923;

        //调用访问层方法
        int affectedRows = categoryMapper.deleteOne(cate_id);

        //判断
        if(affectedRows>0){
            System.out.println("调用成功");
        }
        else{
            System.out.println("调用失败：affectedRows="+affectedRows);
        }
    }


    //查询列表的数据List<T>？
    @Test
    public void getListWithParent(){
        //准备数据
        CategorySearchBo categorySearchBo=new CategorySearchBo();
        categorySearchBo.setCate_parentid(1);

        //调用访问层方法
        List<Category > list = categoryMapper.getListWithParent(categorySearchBo,new PageBo());

        //判断
        //——关注sql写的对还是不对，有没有返回数据！。。。。。。list.size()>0
        //——再输出第一个元素的信息。。。。。。。。。。。。。。。list.get(0)在控制台上输出
        if(list!=null && list.size()>0){
            System.out.println("测试成功,count="+list.size());
            System.out.println(list.get(0));
        }
        else{
            System.out.println("测试失败");             //背后的逻辑：这种返回场景其实不能很充分的说明你的sql是写的对的；
        }
    }
}
