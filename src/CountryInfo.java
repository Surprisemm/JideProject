import javax.swing.*;


/**
 * Класс - хранит информацию о странах
 * Created by Nikita.Manzhukov on 16.06.2023
 */
public class CountryInfo implements Comparable<CountryInfo> {
    private final String name;
    private final ImageIcon icon;
    private final String region;
    private final int population;
    private boolean forAgainst;

    public CountryInfo (String name, String region, int population, boolean forAgainst){
        this.name = name;
        this.icon = getIconByName(name);
        this.region = region;
        this.population = population;
        this.forAgainst = forAgainst;
    }

    @Override
    public int compareTo(CountryInfo o) {
        return this.getCountryName().compareTo(o.getCountryName());
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

    /**
     * Иконка устанавливается по соответствию названия страны в таблице и имени файла картинки флага
     */
    private ImageIcon getIconByName(String name) {
        String iconPath = "icons/" + name.toLowerCase() + ".png";
        return new ImageIcon(iconPath);
    }

}
