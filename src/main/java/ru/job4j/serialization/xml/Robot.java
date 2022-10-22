package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "robot")
@XmlAccessorType(XmlAccessType.FIELD)
public class Robot {
    @XmlAttribute
    private String modelName;
    private String origin;
    private boolean prepared;
    private float weight;
    private float height;
    private int strength;
    private Armor armor;
    @XmlElementWrapper(name = "weapons")
    @XmlElement(name = "weapon")
    private String[] weapons;

    public Robot() {
    }

    public Robot(String modelName, String origin, boolean prepared, float weight, float height, int strength, Armor armor, String... weapons) {

        this.modelName = modelName;
        this.origin = origin;
        this.prepared = prepared;
        this.weight = weight;
        this.height = height;
        this.strength = strength;
        this.armor = armor;
        this.weapons = weapons;
    }

    @Override
    public String toString() {
        return "Robot{"
                + "modelName='" + modelName + '\''
                + ", origin='" + origin + '\''
                + ", prepared=" + prepared
                + ", weight=" + weight
                + ", height=" + height
                + ", strength=" + strength
                + ", armor=" + armor
                + ", weapons=" + Arrays.toString(weapons)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {

        final Robot robot = new Robot("Striker Eureka", "Russia", true,  1.850f, 76.2f, 10, new Armor("full"), new String[]{"gun", "laser"});

        JAXBContext context = JAXBContext.newInstance(Robot.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(robot, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
