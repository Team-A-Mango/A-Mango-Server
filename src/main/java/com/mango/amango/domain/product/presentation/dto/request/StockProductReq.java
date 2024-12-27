package com.mango.amango.domain.product.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record StockProductReq(
        @NotNull
        @Range(min = 1, max = 2)
        Integer storageNumber
) {
}
