package flash.flash.DTO;

public enum FILETYPE {

    MP3("audio/mpeg"),
    M4A("audio/x-m4a"),
    MP4("audio/mp4");

    private String type;
    FILETYPE(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
