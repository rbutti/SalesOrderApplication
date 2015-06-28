package com.dev.frontend.panels.list;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dev.frontend.services.Services;

public class ProductDataModel extends ListDataModel
{
    private static final long serialVersionUID = 7526529951747614655L;
    static final Logger logger = Logger.getLogger(ProductDataModel.class);

    public ProductDataModel()
    {
	super(new String[] { "Code", "Description", "Price", "Quantity" }, 0);
    }

    @Override
    public int getObjectType() {
	return Services.TYPE_PRODUCT;
    }

    @Override
    public String[][] convertRecordsListToTableModel(List<Object> list)
    {
	logger.debug("convertRecordsListToTableModel() | Product Data: " + list.toString());
	JSONArray jsonList = (JSONArray) list;
	String[][] data = new String[jsonList.size()][4];
	for (int i = 0; i < jsonList.size(); i++) {
	    data[i][0] = ((JSONObject) jsonList.get(i)).get("code").toString();
	    data[i][1] = ((JSONObject) jsonList.get(i)).get("description").toString();
	    data[i][2] = ((JSONObject) jsonList.get(i)).get("price").toString();
	    data[i][3] = ((JSONObject) jsonList.get(i)).get("quantity").toString();
	}
	return data;
    }
}
