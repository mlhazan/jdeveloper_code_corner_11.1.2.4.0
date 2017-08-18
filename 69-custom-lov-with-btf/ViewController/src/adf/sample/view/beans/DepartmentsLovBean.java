package adf.sample.view.beans;

import com.bea.security.xacml.Logger;

import java.util.Map;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

public class DepartmentsLovBean {
    private RichTable employeeTable;
    private static final ADFLogger Logger = ADFLogger.createADFLogger(DepartmentsLovBean.class);
    public DepartmentsLovBean() {
        super();
    }

    public String onValueSelect() {
        //CollectionModel extends the Faces DataModel class and adds on support for rowKeys and sorting
        CollectionModel model = (CollectionModel) employeeTable.getValue();
        JUCtrlHierBinding tableBinding = (JUCtrlHierBinding) model.getWrappedData();
        //table synchronizes row selection with current binding row
        DCIteratorBinding tableIterator = tableBinding.getDCIteratorBinding();
        if (tableIterator.getCurrentRow() != null) {
            Object departmentIdValue =
                tableIterator.getCurrentRow().getAttribute("DepartmentId");
                Logger.log(ADFLogger.TRACE,">>>>>", departmentIdValue);
                System.err.println(">>>>>" + departmentIdValue);
            //copy value into the pageFlowScope, which is returned in an task
            //flow param output
            ADFContext adfCtx = ADFContext.getCurrent();
            Map pageFlowScope = adfCtx.getPageFlowScope();
            pageFlowScope.put("departmentId", departmentIdValue);
            pageFlowScope.put("canBeAnything", "CAN BE ANY THING");
        }
        return "return";
    }

    public void setEmployeeTable(RichTable employeeTable) {
        this.employeeTable = employeeTable;
    }

    public RichTable getEmployeeTable() {
        return employeeTable;
    }
}
