package science.freeabyss.template.spa.model.dto;

import science.freeabyss.template.spa.model.FunctionEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author abyss
 * @date 2019-01-23 22:10
 */
@Data
public class FunctionDTO extends FunctionEntity {

    private List<FunctionDTO> children = new ArrayList<>();
}
