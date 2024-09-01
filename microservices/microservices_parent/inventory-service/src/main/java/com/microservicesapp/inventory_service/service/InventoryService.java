package com.microservicesapp.inventory_service.service;

import com.microservicesapp.inventory_service.dto.InventoryResponse;
import com.microservicesapp.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

//    //   Single skuCode
//    @Transactional(readOnly = true)
//    public boolean isInStock(String skuCode) {
//
//        return inventoryRepository.findBySkuCode(skuCode).isPresent();
//    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStockIn(List<String> skuCode) {

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                        .map(inventory ->
                            InventoryResponse.builder()
                                    .skuCode(inventory.getSkuCode())
                                    .isInStock(inventory.getQuantity() > 0)
                                    .build()
                ).toList();
    }


}
