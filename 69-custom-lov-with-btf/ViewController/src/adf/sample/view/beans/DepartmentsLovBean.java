package adf.sample.view.beans;

import java.util.Map;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

public class DepartmentsLovBean {
    private RichTable employeeTable;

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
            //copy value into the pageFlowScope, which is returned in an task
            //flow param output
            ADFContext adfCtx = ADFContext.getCurrent();
            Map pageFlowScope = adfCtx.getPageFlowScope();
            pageFlowScope.put("departmentId", departmentIdValue);
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
