package com.anuworks.kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Anudeep Madrampalli (Anuworks)
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String product;
    private int quantity;
}
