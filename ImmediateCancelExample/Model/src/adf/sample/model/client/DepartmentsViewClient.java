package adf.sample.model.client;

import adf.sample.model.common.DepartmentsView;

import oracle.jbo.Row;
import oracle.jbo.client.remote.ViewUsageImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Dec 23 14:14:41 CET 2008
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class DepartmentsViewClient extends ViewUsageImpl implements DepartmentsView {
    /**
     * This is the default constructor (do not remove).
     */
    public DepartmentsViewClient() {
    }

    public String getRowStatus(Row row) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"getRowStatus",new String [] {"oracle.jbo.Row"},new Object[] {row});
        return (String)_ret;
    }
}