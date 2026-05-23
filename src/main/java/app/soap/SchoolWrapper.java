package app.soap;

import app.model.School;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "schools")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchoolWrapper implements Serializable {

    public SchoolWrapper(){}

    public SchoolWrapper(List<School> school){
        this.school = school;
    }

    private List<School> school;

    public List<School> getSchool() {
        return school;
    }

    public void setSchool(List<School> school) {
        this.school = school;
    }
}
