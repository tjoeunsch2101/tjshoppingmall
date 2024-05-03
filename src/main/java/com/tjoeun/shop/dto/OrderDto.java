package com.tjoeun.shop.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/*
  상품 상세 페이지에서 주문하는 상품의 
  정보를 전달하는 역할을 함
*/

@Getter @Setter
public class OrderDto {

    @NotNull(message="상품 코드는 꼭 입력해 주세요")
    private Long itemId;

  	@Min(value = 1, message="최소 1 개 이상 주문해 주세요")
  	@Max(value = 999, message="최대 999 개까지만 주문해 주세요")
    private int count;

}