
package ws.services.Impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.4.2
 * Tue Feb 18 11:22:53 GMT 2014
 * Generated source version: 2.4.2
 */

@XmlRootElement(name = "getEmployeePerformanceByEmployeeIdResponse", namespace = "http://Impl.services.ws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEmployeePerformanceByEmployeeIdResponse", namespace = "http://Impl.services.ws/")

public class GetEmployeePerformanceByEmployeeIdResponse {

    @XmlElement(name = "return")
    private java.util.List<ws.dao.EmployeePerformance> _return;

    public java.util.List<ws.dao.EmployeePerformance> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<ws.dao.EmployeePerformance> new_return)  {
        this._return = new_return;
    }

}

