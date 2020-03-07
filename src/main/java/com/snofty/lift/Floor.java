package com.snofty.lift;

import com.snofty.model.People;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Floor {
    private List<People> peoples;
}
