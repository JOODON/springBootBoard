package com.example.springBootBoard.entity;


import com.example.springBootBoard.dto.CommentDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20,nullable = false)
    private String commentWriter;

    @Column
    private String commentContests;

    //Board Comment 관계는 1 대 N 한개의 개시글에 여러개의 댓글이 달릴수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDto commentDto,BoardEntity boardEntity) {
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setCommentWriter(commentDto.getCommentWriter());
        commentEntity.setCommentContests(commentDto.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);

        return commentEntity;
    }

}
