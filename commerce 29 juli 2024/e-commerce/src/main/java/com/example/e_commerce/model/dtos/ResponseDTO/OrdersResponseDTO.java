package com.example.e_commerce.model.dtos.ResponseDTO;

import com.example.e_commerce.model.dtos.OrdersGetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrdersResponseDTO {
    private List<OrdersGetDTO> content;
    private Integer page_number;
    private Integer page_size;
    private Long total_element;
    private Integer total_page;
    private boolean last_page;
}
