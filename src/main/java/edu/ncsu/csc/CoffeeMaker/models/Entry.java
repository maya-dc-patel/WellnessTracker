package edu.ncsu.csc.CoffeeMaker.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Entry extends DomainObject {

    @Id
    @GeneratedValue
    public Long    id;
    // @Enumerated ( EnumType.STRING )
    // private IngredientType ingredient;

    private String date;
    private String content;

    // TODO make a minimum value??


    public Entry ( final String ingredient, final int amount ) {
        super();
        this.date = date;
        this.content = content;
    }

    public Entry () {
        super();
    }

    @Override public Long getId () {
        return id;
    }

    public void setId ( final Long id ) {
        this.id = id;
    }

    public String getDate () {
        return date;
    }

    public void setDate ( final String date ) {
        this.date = date;
    }

    public String getContent () {
        return content;
    }

    public void setContent ( final String content ) {
        this.content = content;
    }

    @Override public boolean equals ( final Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        final Entry entry = (Entry) o;
        return Objects.equals( id, entry.id ) && Objects.equals( date, entry.date ) && Objects.equals( content,
                entry.content );
    }

    @Override public int hashCode () {
        return Objects.hash( id, date, content );
    }

    @Override public String toString () {
        return "Entry{ date='" + date + '\'' + ", content='" + content + '\'' + '}';
    }
}
