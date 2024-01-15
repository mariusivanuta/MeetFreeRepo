package com.storage.dto;

import com.storage.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface ProductMapper {

    @Mapping(source = "productDescription", target = "description")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "productPrice", target = "unitPrice")
    @Mapping(target = "active", ignore = true)
    Product productDtoToProduct(ProductDto productDto);

    @Mapping(source="productName", target = "productName")
    @Mapping(source="description", target = "productDescription")
    @Mapping(source="unitPrice", target = "productPrice")
    @Mapping(target = "active", ignore = true)
    ProductDto productToProductDto(Product product);

    @InheritConfiguration
    Product updateProductFromProductDto(ProductDto productDto, @MappingTarget Product product);

    List<ProductDto> productListToProductDtoList(List<Product> product);
}
