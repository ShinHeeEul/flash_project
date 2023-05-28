package flash.flash.DTO;


import lombok.*;

import java.util.ArrayList;



@Data
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
