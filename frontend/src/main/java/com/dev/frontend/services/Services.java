package com.dev.frontend.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dev.frontend.panels.ComboBoxItem;
import com.dev.frontend.services.facade.RestfulClientFacade;

public class Services
{
    public static final int TYPE_PRODUCT = 1;
    public static final int TYPE_CUSTOMER = 2;
    public static final int TYPE_SALESORDER = 3;
    static final Logger logger = Logger.getLogger(RestfulClientFacade.class);

    private static final Map<Integer, String> urlMap;
    static
    {
	urlMap = new HashMap<>();
	urlMap.put(TYPE_PRODUCT, RestfulClientFacade.BASEURL + "backend/products");
	urlMap.put(TYPE_CUSTOMER, RestfulClientFacade.BASEURL + "backend/customers");
	urlMap.put(TYPE_SALESORDER, RestfulClientFacade.BASEURL + "backend/salesorders");
    }

    public static Object save(Object object, int objectType)
    {
	logger.debug("save() | Persistent Object: " + object.toString());
	return RestfulClientFacade.postRequest(
		Services.urlMap.get(new Integer(objectType)),
		object.toString());
    }

    public static Object readRecordByCode(String code, int objectType)
    {
	logger.debug("readRecordByCode() | Read object key: " + code + " Object Type: " + objectType);
	return RestfulClientFacade.getRequest(
		Services.urlMap.get(new Integer(objectType)) + "/" + code);
    }

    public static boolean deleteRecordByCode(String code, int objectType)
    {
	logger.debug("deleteRecordByCode() | Delete object key: " + code + " Object Type: " + objectType);
	RestfulClientFacade.deleteRequest(
		Services.urlMap.get(new Integer(objectType)) + "/" + code);

	return true;
    }

    public static List<Object> listCurrentRecords(int objectType)
    {
	logger.debug("listCurrentRecords() | List Object Type: " + objectType);
	return RestfulClientFacade.getRequestForArray(
		Services.urlMap.get(new Integer(objectType)));
    }

    public static List<ComboBoxItem> listCurrentRecordRefernces(int objectType)
    {
	logger.debug("listCurrentRecordRefernces() | List Object Type: " + objectType);
	JSONArray items = RestfulClientFacade.getRequestForArray(
		Services.urlMap.get(new Integer(objectType)));
	ArrayList<ComboBoxItem> comboBoxItems = new ArrayList<>();
	for (Object item : items) {
	    JSONObject itemObject = (JSONObject) item;
	    String value = itemObject.get("code").toString();
	    String label = itemObject.get("code").toString();
	    if (objectType == TYPE_CUSTOMER) {
		label = itemObject.get("name").toString();
	    }
	    else if (objectType == TYPE_PRODUCT) {
		label = itemObject.get("description").toString();
	    }
	    logger.debug("listCurrentRecordRefernces() | Return object: " + item.toString());
	    comboBoxItems.add(new ComboBoxItem(value, label));
	}

	return comboBoxItems;
    }

    public static double getProductPrice(String productCode) {

	logger.debug("getProductPrice() | Product Code: " + productCode);
	JSONObject object = RestfulClientFacade.getRequest(
		Services.urlMap.get(1) + "/" + productCode);
	return Double.valueOf(object.get("price").toString());
    }
}
