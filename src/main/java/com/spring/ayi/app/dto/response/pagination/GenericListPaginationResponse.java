package com.spring.ayi.app.dto.response.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(
        value = "GenericListPaginationResponse",
        description = "Represents the data retrieved from the server as pages"
)
public class GenericListPaginationResponse<E> {

    @ApiModelProperty(position = 1, notes = "Page content")
    private List<E> pages;

    @JsonProperty("prev_page")
    @ApiModelProperty(position = 2, notes = "Previous page")
    private String prevPage;

    @JsonProperty("next_page")
    @ApiModelProperty(position = 3, notes = "Next page")
    private String nextPage;

    @ApiModelProperty(position = 4, notes = "Total pages")
    private Integer totalPages;

    @ApiModelProperty(position = 5, notes = "Current pages")
    private Integer currentPage;

    @ApiModelProperty(position = 6, notes = "Size")
    private Integer size;

    @ApiModelProperty(position = 5, notes = "Total de Elementos de la pagina")
    private Integer totalElements;
}
