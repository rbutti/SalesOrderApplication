package com.dev.frontend.panels.list;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dev.frontend.services.Services;

public class CustomerDataModel extends ListDataModel
{
    private static final long serialVersionUID = 7526529951747613655L;
    static final Logger logger = Logger.getLogger(CustomerDataModel.class);

    public CustomerDataModel()
    {
	super(new String[] { "Code", "Name", "Phone", "Current Credit" }, 0);
    }

    @Override
    public int getObjectType()
    {
	return Services.TYPE_CUSTOMER;
    }

    @Override
    public String[][] convertRecordsListToTableModel(List<Object> list)
    {
	logger.debug("convertRecordsListToTableModel() | Customer Data: " + list.toString());
	JSONArray jsonList = (JSONArray) list;
	String[][] data = new String[jsonList.size()][4];
	for (int i = 0; i < jsonList.size(); i++) {
	    data[i][0] = ((JSONObject) jsonList.get(i)).get("code").toString();
	    data[i][1] = ((JSONObject) jsonList.get(i)).get("name").toString();
	    data[i][2] = ((JSONObject) jsonList.get(i)).get("phone1").toString();
	    data[i][3] = ((JSONObject) jsonList.get(i)).get("currentCredit").toString();
	}
	return data;
    }
}
