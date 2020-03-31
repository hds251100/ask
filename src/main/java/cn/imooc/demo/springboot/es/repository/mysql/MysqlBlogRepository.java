package cn.imooc.demo.springboot.es.repository.mysql;

import cn.imooc.demo.springboot.es.entity.mysql.MysqlBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  MysqlBlogRepository extends JpaRepository< MysqlBlog,Integer> {

    @Query("select e from MysqlBlog e order by e.submitDatetime desc")
    List<MysqlBlog> queryAll();

    @Query("select e from MysqlBlog e where e.questionType "+"like concat('%',:keyword,'%')or e.question like concat('%',:keyword,'%')")
    List<MysqlBlog> queryBlogs( @Param  ( "keyword" )String keyword);
}
