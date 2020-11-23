package ru.geekbrains.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "stock-client", url = "http://localhost:6080/stock")
public interface StockService {

    @GetMapping("/{id}")
    Stock getStockById(@PathVariable("id") Long id);
}
