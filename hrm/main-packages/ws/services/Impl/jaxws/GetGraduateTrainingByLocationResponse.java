
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

@XmlRootElement(name = "getGraduateTrainingByLocationResponse", namespace = "http://Impl.services.ws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGraduateTrainingByLocationResponse", namespace = "http://Impl.services.ws/")

public class GetGraduateTrainingByLocationResponse {

    @XmlElement(name = "return")
    private ws.dao.GraduateTraining _return;

    public ws.dao.GraduateTraining getReturn() {
        return this._return;
    }

    public void setReturn(ws.dao.GraduateTraining new_return)  {
        this._return = new_return;
    }

}

