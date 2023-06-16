import javax.swing.*;

public class CountryInfo {
    private String name;
    private ImageIcon icon;
    private String region;
    private int population;
    private boolean forAgainst;

    public CountryInfo (String name, String region, int population, boolean forAgainst){
        this.name = name;
        this.icon = getIconByName(name);
        this.region = region;
        this.population = population;
        this.forAgainst = forAgainst;
    }

    public String getCountryName(){
        return name;
    }

    public String getRegion(){
        return region;
    }

    public ImageIcon getIcon(){
        return icon;
    }

    public int getPopulation(){
        return population;
    }

    public boolean getForAgainst(){
        return forAgainst;
    }

    public void setForAgainst(boolean value){
        forAgainst = value;
    }

    private ImageIcon getIconByName(String name) {
        String iconPath = "icons/" + name.toLowerCase() + ".png";
        return new ImageIcon(iconPath);
    }

}
