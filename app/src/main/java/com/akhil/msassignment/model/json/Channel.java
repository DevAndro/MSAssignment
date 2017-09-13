package com.akhil.msassignment.model.json;

public class Channel
{
    private Wind wind;

    private Location location;

    private String link;

    private Atmosphere atmosphere;

    private Image image;

    private String ttl;

    private Astronomy astronomy;

    private Units units;

    private String title;

    private String description;

    private Item item;

    private String lastBuildDate;

    private String language;

    public Wind getWind ()
    {
        return wind;
    }

    public void setWind (Wind wind)
    {
        this.wind = wind;
    }

    public Location getLocation ()
    {
        return location;
    }

    public void setLocation (Location location)
    {
        this.location = location;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public Atmosphere getAtmosphere ()
    {
        return atmosphere;
    }

    public void setAtmosphere (Atmosphere atmosphere)
    {
        this.atmosphere = atmosphere;
    }

    public Image getImage ()
    {
        return image;
    }

    public void setImage (Image image)
    {
        this.image = image;
    }

    public String getTtl ()
    {
        return ttl;
    }

    public void setTtl (String ttl)
    {
        this.ttl = ttl;
    }

    public Astronomy getAstronomy ()
    {
        return astronomy;
    }

    public void setAstronomy (Astronomy astronomy)
    {
        this.astronomy = astronomy;
    }

    public Units getUnits ()
    {
        return units;
    }

    public void setUnits (Units units)
    {
        this.units = units;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public Item getItem ()
    {
        return item;
    }

    public void setItem (Item item)
    {
        this.item = item;
    }

    public String getLastBuildDate ()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate (String lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [wind = "+wind+", location = "+location+", link = "+link+", atmosphere = "+atmosphere+", image = "+image+", ttl = "+ttl+", astronomy = "+astronomy+", units = "+units+", title = "+title+", description = "+description+", item = "+item+", lastBuildDate = "+lastBuildDate+", language = "+language+"]";
    }
}
