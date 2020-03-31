package cn.imooc.demo.springboot.es.entity.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;
import javax.persistence.*;
import java.lang.annotation.Documented;

@Data
@Document(indexName = "blog",type = "ask",
        useServerConfiguration = true,createIndex = false)
public class EsBlog {

        @Id
        private Integer question_id;

        @Field(type = FieldType.Text,analyzer = "ik_max_word")
        private String  question_type;

        @Field(type = FieldType.Text,analyzer = "ik_max_word")
        private String  question;

        @Field(type = FieldType.Text,analyzer = "ik_max_word")
        private String  answer;
        @Field(type = FieldType.Text,analyzer = "ik_max_word")
        private String  answer_doctor;
        @Field(type = FieldType.Date,format = DateFormat.custom,
                pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
        private Date submit_datetime;


    }


