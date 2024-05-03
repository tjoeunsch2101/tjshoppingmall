package com.tjoeun.shop.dto;

import com.tjoeun.shop.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDto {

	// 멤버변수 : 상품이름, 주문수량, 주문금액, 상품이미지경로
	private String itemNm;
	private int count;
	private int orderPrice;
	private String imgUrl;
	
	
	// 생성자               Entity
	public OrderItemDto(OrderItem orderItem, String imgUrl) {
		this.itemNm = orderItem.getItem().getItemNm();
		this.count = orderItem.getCount();
		this.orderPrice = orderItem.getOrderPrice();		
		this.imgUrl = imgUrl;
	}

}