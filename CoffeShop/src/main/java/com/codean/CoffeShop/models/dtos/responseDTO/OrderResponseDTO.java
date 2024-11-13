package com.codean.CoffeShop.models.dtos.responseDTO;

import com.codean.CoffeShop.models.dtos.OrderGetDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderResponseDTO {
    private List<OrderGetDTO> content;
    private Integer page_number;
    private Integer page_size;
    private Long total_element;
    private Integer total_page;
    private boolean last_page;
}
