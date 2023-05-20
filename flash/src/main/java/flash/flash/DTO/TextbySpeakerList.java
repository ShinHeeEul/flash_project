package flash.flash.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TextbySpeakerList {

    private List<String> nameA;
    private List<String> textA;
    private List<String> nameB;
    private List<String> textB;
    private String radio;
}
