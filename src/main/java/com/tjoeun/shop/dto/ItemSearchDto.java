package com.tjoeun.shop.dto;

import com.tjoeun.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {
	
	/*
    searchDateType    : 검색 기준일
    searchSellStatus  : 판매 상태
    searchBy          : 검색 조건
    searchQuery       : 검색어
  */

  private String searchDateType;

  private ItemSellStatus searchSellStatus;

  private String searchBy;

  private String searchQuery = "";

}