package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "armor")
public class Armor {
    @XmlAttribute
    private String armorType;

    public Armor() {
    }

    public Armor(String armorType) {
        this.armorType = armorType;
    }

    @Override
    public String toString() {
        return "Armor{"
                + "armorType='" + armorType + '\''
                + '}';
    }
}
