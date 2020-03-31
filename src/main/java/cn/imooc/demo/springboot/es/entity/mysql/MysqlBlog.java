package cn.imooc.demo.springboot.es.entity.mysql;

import com.sun.org.apache.xpath.internal.objects.XString;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data

@Entity
@Table(name ="ask")
public class MysqlBlog {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer questionId;
    private String  questionType;
    private String  question;
    @Column(columnDefinition = "mediumtext")
    private String  answer;
    private String  answerDoctor;
    private Date submitDatetime;


}
