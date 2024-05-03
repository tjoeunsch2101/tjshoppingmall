package com.tjoeun.shop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.tjoeun.shop.constant.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter
@Table(name="orders")
@ToString(exclude={"member", "orderItems"})
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; 

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; 

  	// 주문하는 상품들을 모아두는 List
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    //주문할 때, 주문 상품(orderItem) 증가하기
  	// 주문하는 상품들을 모아두는 List 에 주문 상품(orderItem) 저장하기
  	// 마켓에 올려놓은 상품 중에서 장바구니에 담는 상품만
  	// orderItemList 에 추가하기	
    public void addOrderItem(OrderItem orderItem) {
    	  this.orderItems.add(orderItem);
        // order_item 테이블의 외래키인 order_id 컬럼에
    		// 현재 주문하는 주문테이블의 아이디(item_id) 값을 추가함
    	  // Order 클래스의 멤버변수 Order order 의 setter 를 호출함
        orderItem.setOrder(this);
    }

  	// Oder 객체를 생성해서 반환하기
  	// 주문할 때는 주문하는회원정보, 주문상품목록이 필요함
  	// 주문하는회원정보(Member member), 주문상품목록(List<OrderItem> orderItemList)
    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);

    		// 마켓에 올려놓은 상품 중에서 장바구니에 담는 상품만
    		// orderItemList 에 추가하기
        for(OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }

        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

  	// 총 주문 가격
  	// 주문한 모든 상품의 총가격
    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

  	// 주문 취소하기
    public void cancelOrder() {
        this.orderStatus = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

}