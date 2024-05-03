package com.tjoeun.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MainItemDto {

    private Long id;
    private String itemNm;
    private String itemDetail;
    private String imgUrl;
    private Integer price;

  	// @QueryProjection : QMainItemDto 클래스를 생성하기 위한 QueryDSL Annotation
  	//                     ㄴ Entity 를 조회한 후 DTO 로 변환하는 과정이 자동으로 실행됨
  	@QueryProjection
    public MainItemDto(Long id, String itemNm, String itemDetail, String imgUrl,Integer price){
        this.id = id;
        this.itemNm = itemNm;
        this.itemDetail = itemDetail;
        this.imgUrl = imgUrl;
        this.price = price;
    }

}