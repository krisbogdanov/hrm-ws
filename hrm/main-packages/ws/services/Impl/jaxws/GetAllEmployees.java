
package ws.services.Impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.2
 * Tue Feb 18 11:14:51 GMT 2014
 * Generated source version: 2.4.2
 */

@XmlRootElement(name = "getAllEmployees", namespace = "http://Impl.services.ws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllEmployees", namespace = "http://Impl.services.ws/", propOrder = {"arg0", "arg1"})

public class GetAllEmployees {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;
    @XmlElement(name = "arg1")
    private boolean arg1;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

    public boolean getArg1() {
        return this.arg1;
    }

    public void setArg1(boolean newArg1)  {
        this.arg1 = newArg1;
    }

}

