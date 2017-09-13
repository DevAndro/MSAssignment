package com.akhil.msassignment.model.json;

public class Guid
{
    private String isPermaLink;

    public String getIsPermaLink ()
    {
        return isPermaLink;
    }

    public void setIsPermaLink (String isPermaLink)
    {
        this.isPermaLink = isPermaLink;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isPermaLink = "+isPermaLink+"]";
    }
}
