package com.example.e_commerce.model.dtos.ResponseDTO;

import com.example.e_commerce.model.dtos.OrderItemsAddDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsResponseDTO {
    private List<OrderItemsAddDTO> content;
    private Integer page_number;
    private Integer page_size;
    private Long total_element;
    private Integer total_page;
    private boolean last_page;
}
