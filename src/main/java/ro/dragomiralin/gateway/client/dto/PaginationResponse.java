package ro.dragomiralin.gateway.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PaginationResponse {
    private Object data;
    private int currentPage;
    private long totalItems;
    private int totalPages;
    private int pageSize;

}
