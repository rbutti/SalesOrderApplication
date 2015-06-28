package com.dev.frontend.panels.list;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dev.frontend.services.Services;

public class SalesOrderDataModel extends ListDataModel
{
    private static final long serialVersionUID = 7526529951747614655L;
    static final Logger logger = Logger.getLogger(SalesOrderDataModel.class);

    public SalesOrderDataModel()
    {
	super(new String[] { "Order Number", "Customer", "Total Price" }, 0);
    }

    @Override
    public int getObjectType() {
	return Services.TYPE_SALESORDER;
    }

    @Override
    public String[][] convertRecordsListToTableModel(List<Object> list)
    {
	logger.debug("convertRecordsListToTableModel() | SaleOrder Data: " + list.toString());
	JSONArray jsonList = (JSONArray) list;
	String[][] data = new String[jsonList.size()][3];
	for (int i = 0; i < jsonList.size(); i++) {
	    JSONObject jsonObject = (JSONObject) jsonList.get(i);
	    data[i][0] = jsonObject.get("orderNumber").toString();

	    JSONObject customer = new JSONObject();
	    customer = (JSONObject) jsonObject.get("customer");
	    data[i][1] = "(" + customer.get("code").toString() + ") " + customer.get("name");
	    data[i][2] = jsonObject.get("totalPrice").toString();
	}
	return data;
    }
}
