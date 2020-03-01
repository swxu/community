package dev.willsnow.community.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author will
 */

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;

    /*current page*/
    private Integer page;
    private List<Integer> pages;
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        totalPage = (totalCount % size == 0) ? (totalCount / size) : ((totalCount / size) + 1);
        this.page = page;
        pages = new LinkedList<>();
        pages.add(page);
        for (int i = 1; i <= 3; ++i) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 是否展示上一页
        showPrevious = page != 1;

        // 是否展示下一页
        showNext = !page.equals(totalPage);

        // 是否展示第一页
        showFirstPage = !pages.contains(1);

        // 是否展示最后一页
        showEndPage = !pages.contains(totalPage);
    }
}
