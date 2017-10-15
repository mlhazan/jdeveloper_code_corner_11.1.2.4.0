package adf.sample;


import adf.sample.model.DepartmentsViewImpl;
import adf.sample.model.DepartmentsViewRowImpl;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.share.logging.ADFLogger;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.server.Entity;


public class RestoreBean {
    private static ADFLogger LOGGER =ADFLogger.createADFLogger(RestoreBean.class);
    public RestoreBean() {
    }

    public void onCancel(ActionEvent actionEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        LOGGER.finest(">>>>>fctx = "+fctx);
        ELContext elctx = fctx.getELContext();
        LOGGER.finest(">>>>>elctx = "+elctx);
        ExpressionFactory fact = fctx.getApplication().getExpressionFactory();
        LOGGER.finest(">>>>>fact = "+fact);
        ValueExpression ve = fact.createValueExpression(elctx,"#{bindings}",Object.class);
        LOGGER.finest(">>>>>ve = "+ve);
        DCBindingContainer bindings = (DCBindingContainer) ve.getValue(elctx);
        LOGGER.finest(">>>>>bindings = "+bindings);
        DCIteratorBinding iter = bindings.findIteratorBinding("DepartmentsView1Iterator");        
        Row rw = iter.getCurrentRow();
        LOGGER.finest(">>>>>rw = "+rw);
        OperationBinding getRowStatusBinding = bindings.getOperationBinding("getRowStatus");
        String rwStatus = (String)getRowStatusBinding.execute();
        LOGGER.finest(">>>>>rwStatus = "+rwStatus);
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
