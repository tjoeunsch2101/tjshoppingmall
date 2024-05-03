package com.tjoeun.shop.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.shop.constant.OrderStatus;
import com.tjoeun.shop.entity.Order;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderHistDto {
	
 	// 멤버변수 : 주문아이디, 주문날짜, 주문상태
	
  private Long orderId; 
  private String orderDate;
  private OrderStatus orderStatus; 
  
	// 생성자
  public OrderHistDto(Order order){
    this.orderId     = order.getId();
    this.orderDate   = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    this.orderStatus = order.getOrderStatus();
  }

  private List<OrderItemDto> orderItemDtoList = new ArrayList<>();

  // 주문 상품 리스트에 주문 상품을 추가함
  public void addOrderItemDto(OrderItemDto orderItemDto){
      orderItemDtoList.add(orderItemDto);
  }

}