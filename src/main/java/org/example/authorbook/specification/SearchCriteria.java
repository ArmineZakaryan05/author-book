package org.example.authorbook.specification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authorbook.entity.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {

    private String name;
    private String surname;
    private String phone;
    private Gender gender;


}
