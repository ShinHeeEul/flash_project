package flash.flash.Data;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginForm {

    private String uid;
    private String upw;
}