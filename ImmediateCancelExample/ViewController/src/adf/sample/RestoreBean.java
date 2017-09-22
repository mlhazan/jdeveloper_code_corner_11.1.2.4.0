package adf.sample;


import adf.sample.model.DepartmentsViewRowImpl;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.server.Entity;


public class RestoreBean {

    public RestoreBean() {
    }

    public void onCancel(ActionEvent actionEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ELContext elctx = fctx.getELContext();
        ExpressionFactory fact = fctx.getApplication().getExpressionFactory();
        ValueExpression ve = fact.createValueExpression(elctx,"#{bindings}",Object.class);
        DCBindingContainer bindings = (DCBindingContainer) ve.getValue(elctx);
        DCIteratorBinding iter = bindings.findIteratorBinding("DepartmentsView1Iterator");        
        Row rw = iter.getCurrentRow();
        
        OperationBinding getRowStatusBinding = bindings.getOperationBinding("getRowStatus");
        String rwStatus = (String)getRowStatusBinding.execute();     
        if ("NEW".equalsIgnoreCase(rwStatus)){
           iter.removeCurrentRow();
           iter.refreshIfNeeded();
        }
        else{
            rw.refresh(Row.REFRESH_UNDO_CHANGES);   
        }
        fctx.renderResponse();        
    }
}
