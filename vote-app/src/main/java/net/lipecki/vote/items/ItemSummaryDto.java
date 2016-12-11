package net.lipecki.vote.items;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemSummaryDto {

    private Integer id;

    private String title;

    private String type;

    private String details;

}
