public class Model {


    private String name;
    private String icon;
    private String main;


    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getMain() {
        return main;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    private Double temp;
    private Double humidity;


}
