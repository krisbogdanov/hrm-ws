
package ws.services.Impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.2
 * Tue Feb 18 11:23:47 GMT 2014
 * Generated source version: 2.4.2
 */

@XmlRootElement(name = "removeGraduateTrainigByLocation", namespace = "http://Impl.services.ws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "removeGraduateTrainigByLocation", namespace = "http://Impl.services.ws/", propOrder = {"arg0", "arg1", "arg2"})

public class RemoveGraduateTrainigByLocation {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;
    @XmlElement(name = "arg1")
    private java.lang.String arg1;
    @XmlElement(name = "arg2")
    private boolean arg2;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

    public java.lang.String getArg1() {
        return this.arg1;
    }

    public void setArg1(java.lang.String newArg1)  {
        this.arg1 = newArg1;
    }

    public boolean getArg2() {
        return this.arg2;
    }

    public void setArg2(boolean newArg2)  {
        this.arg2 = newArg2;
    }

}

