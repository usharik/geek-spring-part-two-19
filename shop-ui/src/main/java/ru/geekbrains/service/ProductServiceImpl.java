package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.geekbrains.aspect.TrackTime;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persist.repo.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @TrackTime
    public List<ProductRepr> findAll() {
        logger.info("Product findAll call");

        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }
}
