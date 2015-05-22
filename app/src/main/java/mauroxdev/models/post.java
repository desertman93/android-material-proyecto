package mauroxdev.models;

/**
 * Created by Mauricio on 21-05-2015.
 */
public class post {
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String titulo;
    private String url;
    private String img;
}
