package cn.imooc.demo.springboot.es.controller;


import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import cn.imooc.demo.springboot.es.entity.mysql.MysqlBlog;
import cn.imooc.demo.springboot.es.repository.es.EsBlogRepository;
import cn.imooc.demo.springboot.es.repository.mysql.MysqlBlogRepository;
import lombok.Data;
import org.elasticsearch.common.StopWatch;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class DataController {
    @Autowired
    MysqlBlogRepository mysqlBlogRepository;
    @Autowired
    EsBlogRepository esBlogRepository;
    @GetMapping("/blogs")
    public Object blog(){
        List< MysqlBlog > mysqlBlogs=mysqlBlogRepository.queryAll ();
        return mysqlBlogs;
    }
    @PostMapping("/search")
    public Object search( @RequestBody Param param ){
        HashMap<String,Object> map=new HashMap <> (  );
        //统计耗时
        StopWatch watch=new StopWatch (  );
        watch.start ();

        String type=param.getType ();
        if(type.equalsIgnoreCase ( "mysql" )){
            //mysql
            List<MysqlBlog> mysqlBlogs=mysqlBlogRepository.queryBlogs ( param.getKeyword () );
            map.put ( "list",mysqlBlogs );
        }else if (type.equalsIgnoreCase ( "es" )){
            //es
/*            POST /blog/_search
            {
                "query": {
                "bool": {
                    "should": [
                    {
                        "match_phrase": {
                        "question": "springboot"
                    }
                    },
                    {
                        "match_phrase": {
                        "answer": "123"
                    }
                    }
                    ]
                }
            }
            }*/
            BoolQueryBuilder builder= QueryBuilders.boolQuery ();
            builder.should (QueryBuilders.matchPhraseQuery ( "question",param.getKeyword () ));
            builder.should (QueryBuilders.matchPhraseQuery ( "answer",param.getKeyword () ));
            String s=builder.toString ();

            System.out.println ( s );
            Page < EsBlog > search= (Page < EsBlog >) esBlogRepository.search ( builder );
            List< EsBlog > content=search.getContent ();
            map.put ( "list" ,content);

        }else {
            return "我有些不明白你说什么，请你换个思路说。";
        }
        watch.stop ();
        TimeValue totalTimeMillis=watch.totalTime ();
        map.put ( "duration" ,totalTimeMillis);

        return map;
    }


    @Data
    public  static class Param{
        //查询方式
        private String type;
        private String keyword;

        public Param () {
        }
    }
}