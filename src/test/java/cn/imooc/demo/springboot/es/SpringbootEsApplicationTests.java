package cn.imooc.demo.springboot.es;

import cn.imooc.demo.springboot.es.entity.es.EsBlog;
import cn.imooc.demo.springboot.es.repository.es.EsBlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

//import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootEsApplicationTests  {
    @Autowired
    EsBlogRepository esblogRepository;

    @Test
    public void testEs() {
        Iterable<EsBlog> all = esblogRepository.findAll();
        Iterator<EsBlog> esBlogIterator = all.iterator();
        for (EsBlog esBlog : all) {
            System.out.println(esBlog.toString());
        }
        EsBlog esBlog = esBlogIterator.next();
        System.out.println("++++++++++++++++++++++++++"+esBlog.getQuestion_type ());
        log.info("【es集成springboot】esBlog={}",esBlog);
    }
}

