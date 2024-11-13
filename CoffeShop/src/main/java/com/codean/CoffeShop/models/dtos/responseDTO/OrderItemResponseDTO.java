package com.codean.CoffeShop.models.dtos.responseDTO;

import com.codean.CoffeShop.models.dtos.OrderGetDTO;
import com.codean.CoffeShop.models.dtos.OrderItemGetDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderItemResponseDTO {
    private List<OrderItemGetDTO> content;
    private Integer page_number;
    private Integer page_size;
    private Long total_element;
    private Integer total_page;
    private boolean last_page;
}
