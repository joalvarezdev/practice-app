package com.joalvarez.example.service.interfaces;

import com.joalvarez.example.data.dto.ProductDTO;
import com.joalvarez.example.service.generals.IBaseService;

public interface IProductService extends IBaseService<ProductDTO, Long> {
    ProductDTO findBySKU(String sku);
}
