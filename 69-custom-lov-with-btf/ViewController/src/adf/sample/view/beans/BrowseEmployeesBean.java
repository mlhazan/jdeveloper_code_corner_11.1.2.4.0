package adf.sample.view.beans;

import java.util.Map;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.ReturnEvent;

public class BrowseEmployeesBean {
    private RichInputText departmentIdToUpdate;

    public BrowseEmployeesBean() {
    }

    public void onDialogReturn(ReturnEvent returnEvent) {
        
        ADFContext adfCtx = ADFContext.getCurrent();
        Map pageFlowScope = adfCtx.getPageFlowScope();
        
        Object cancelFlag = pageFlowScope.get("submitOrCancelFlag");
        Object anyFlag = pageFlowScope.get("canBeAnything");
        //cancelFlag will not be null if either submit or cancel clicked
        if(cancelFlag!=null){
           if (((String)cancelFlag).equalsIgnoreCase("submit")) {
                Object departmentId = returnEvent.getReturnValue();
                departmentIdToUpdate.resetValue();
                departmentIdToUpdate.setValue(departmentId);
                AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
                adfFacesContext.addPartialTarget(departmentIdToUpdate);
                System.err.println("cancelFlag: "+cancelFlag + " "+"any : "+anyFlag);
            }
        }
        
    }

    public void setDepartmentIdToUpdate(RichInputText departmentIdToUpdate) {
        this.departmentIdToUpdate = departmentIdToUpdate;
    }

    public RichInputText getDepartmentIdToUpdate() {
        return departmentIdToUpdate;
    }
}
