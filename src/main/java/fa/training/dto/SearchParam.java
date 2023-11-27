package fa.training.dto;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchParam{

    private Integer numberValue;
    private String searchValue;
    private String filterValue;
}
