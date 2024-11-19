package by.it_academy.jd2.commonlib.page;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.domain.Page;
import java.util.List;
import static by.it_academy.jd2.commonlib.page.PageOf.Fields.*;

@Data
@FieldNameConstants
@JsonPropertyOrder({number, size, totalPages, totalElements, first, numberOfElements, last, content})
public class PageOf<T> {
    private final int number;

    private final int size;

    private final int totalPages;

    private final long totalElements;

    private final boolean first;

    private final int numberOfElements;

    private final boolean last;

    private final List<T> content;

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
