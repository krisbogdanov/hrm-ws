
package ws.services.Impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.2
 * Thu Nov 14 13:48:21 GMT 2013
 * Generated source version: 2.4.2
 */

@XmlRootElement(name = "addEmployee", namespace = "http://Impl.services.ws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addEmployee", namespace = "http://Impl.services.ws/", propOrder = {"arg0", "arg1", "arg2", "arg3", "arg4", "arg5"})

public class AddEmployee {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;
    @XmlElement(name = "arg1")
    private java.lang.String arg1;
    @XmlElement(name = "arg2")
    private java.lang.String arg2;
    @XmlElement(name = "arg3")
    private java.lang.String arg3;
    @XmlElement(name = "arg4")
    private java.lang.String arg4;
    @XmlElement(name = "arg5")
    private java.lang.String arg5;

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

    public java.lang.String getArg2() {
        return this.arg2;
    }

    public void setArg2(java.lang.String newArg2)  {
        this.arg2 = newArg2;
    }

    public java.lang.String getArg3() {
        return this.arg3;
    }

    public void setArg3(java.lang.String newArg3)  {
        this.arg3 = newArg3;
    }

    public java.lang.String getArg4() {
        return this.arg4;
    }

    public void setArg4(java.lang.String newArg4)  {
        this.arg4 = newArg4;
    }

    public java.lang.String getArg5() {
        return this.arg5;
    }

    public void setArg5(java.lang.String newArg5)  {
        this.arg5 = newArg5;
    }

}
