package org.jboss.as.quickstarts.ws;

import org.jboss.as.quickstarts.dao.Mountain;
import org.jboss.as.quickstarts.dao.Summit;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MountainWS {

    @WebResult(name = "mountainFound")
    public Mountain nameExists(@WebParam(name = "mountainName")String mountainName);

    @WebResult(name = "IsCreated")
    public boolean createSummit(@WebParam(name = "mountainName")String mountainName,
                                @WebParam(name = "summitName")String summitName,
                                @WebParam(name="summitHeight") int summitHeight);

    @WebResult(name = "higher summit")
    public Summit findHigherSummit(@WebParam(name = "mountainName")String mountainName);

}
