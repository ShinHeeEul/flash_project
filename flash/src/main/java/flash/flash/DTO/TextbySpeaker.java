package flash.flash.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
public class TextbySpeaker {

    String name;
    ArrayList<String> text;

    public TextbySpeaker() {
        text= new ArrayList<>();
    }

    public TextbySpeaker(String name) {
        this.name = name;
        text = new ArrayList<>();
    }

    public void addText(String text) {
        this.text.add(text);
    }





}
