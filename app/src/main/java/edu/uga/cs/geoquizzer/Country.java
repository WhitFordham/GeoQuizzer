package edu.uga.cs.geoquizzer;


/**
 * Class to create a country object.
 */
public class Country {
    private long id;
    private String countryName;
    private String countryContinent;

    /**
     * Default country constructor.
     */
    public Country() {
        this.id = -1;
        this.countryName = null;
        this.countryContinent = null;
    }

    /**
     * Country constructor
     * @param name name of the country
     * @param continent continent of the country
     */
    public Country(String name, String continent) {
        this.id = -1;
        this.countryName = name;
        this.countryContinent = continent;
    }

    /**
     * Returns the ID of the country.
     * @return
     */
    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String name) {
        this.countryName = name;
    }

    public String getCountryContinent() {
        return this.countryContinent;
    }

    public void setCountryContinent(String continent) {
        this.countryContinent = continent;
    }

    public String toString() {
        return id + ": " + countryName + " " + countryContinent;
    }
}
