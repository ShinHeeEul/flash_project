package flash.flash.Model;

public enum FILETYPE {

    MP3("audio/mpeg"),
    M4A("audio/x-m4a");

    private String type;
    FILETYPE(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
