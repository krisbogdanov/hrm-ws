
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

@XmlRootElement(name = "getBankDetailsByEmployeeIdResponse", namespace = "http://Impl.services.ws/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBankDetailsByEmployeeIdResponse", namespace = "http://Impl.services.ws/")

public class GetBankDetailsByEmployeeIdResponse {

    @XmlElement(name = "return")
    private ws.dao.BankDetails _return;

    public ws.dao.BankDetails getReturn() {
        return this._return;
    }

    public void setReturn(ws.dao.BankDetails new_return)  {
        this._return = new_return;
    }

}
