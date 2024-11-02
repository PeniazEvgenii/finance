package by.it_academy.jd2.commonlib.page;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.domain.Page;

import java.util.List;

import static by.it_academy.jd2.commonlib.page.PageOf.Fields.*;

@FieldNameConstants
@JsonPropertyOrder({number, size, totalPages, totalElement, first, numberOfElements, last, content})
public class PageOf<T> {
    private final int number;

    private final int size;

    @JsonProperty(value = "total_pages")
    private final int totalPages;

    @JsonProperty(value = "total_elements")
    private final long totalElement;

    private final boolean first;

    @JsonProperty(value = "number_of_elements")

    private final int numberOfElements;

    private final boolean last;

    private final List<T> content;


    public PageOf(int number, int size, int totalPages, long totalElement,
                  boolean first, int numberOfElements, boolean last, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElement = totalElement;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public boolean isFirst() {
        return first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public List<T> getContent() {
        return content;
    }

    public static <T> PageOf<T> of(Page<T> page) {
        return new PageOf<T>(page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.getNumberOfElements(),
                page.isLast(),
                page.getContent());
    }
}
