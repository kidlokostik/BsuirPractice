package com.example.dto;

import lombok.Data;

@Data
public class OrderDTO {
    Long order_id;
    Long user_id;
    String address;
    String price;
}