package com.dev.frontend.panels.edit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.dev.frontend.services.Services;

public class EditProduct extends EditContentPanel
{
    private static final long serialVersionUID = -8971249970444644844L;
    static final Logger logger = Logger.getLogger(EditProduct.class);

    private final JTextField txtCode = new JTextField();
    private final JTextField txtDescription = new JTextField();
    private final JTextField txtQuantity = new JTextField();
    private final JTextField txtPrice = new JTextField();

    private JSONObject product = new JSONObject();

    public EditProduct()
    {
	GridBagLayout gridBagLayout = new GridBagLayout();
	setLayout(gridBagLayout);

	GridBagConstraints gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.gridx = 0;
	gbc.gridy = 0;
	add(new JLabel("Code"), gbc);

	gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.gridx = 1;
	gbc.gridy = 0;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	add(txtCode, gbc);
	gbc.anchor = GridBagConstraints.LAST_LINE_START;
	txtCode.setColumns(10);
	gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.gridx = 0;
	gbc.gridy = 1;
	add(new JLabel("Description"), gbc);

	gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.gridx = 1;
	gbc.gridy = 1;
	gbc.gridwidth = 3;
	gbc.anchor = GridBagConstraints.LAST_LINE_START;
	add(txtDescription, gbc);
	txtDescription.setColumns(28);
	gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.gridx = 0;
	gbc.gridy = 2;
	add(new JLabel("Price"), gbc);

	gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.gridx = 1;
	gbc.gridy = 2;
	gbc.anchor = GridBagConstraints.LAST_LINE_START;
	add(txtPrice, gbc);
	txtPrice.setColumns(10);

	gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 5);
	gbc.gridx = 2;
	gbc.gridy = 2;
	add(new JLabel("Quantity"), gbc);

	gbc = new GridBagConstraints();
	gbc.insets = new Insets(5, 5, 5, 15);
	gbc.gridx = 3;
	gbc.gridy = 2;
	gbc.anchor = GridBagConstraints.LAST_LINE_START;
	add(txtQuantity, gbc);
	txtQuantity.setColumns(10);
    }

    @Override
    public boolean bindToGUI(Object o)
    {
	logger.debug("bindToGUI() | Product Object: " + o.toString());
	product = (JSONObject) o;
	txtCode.setText(product.get("code").toString());
	txtDescription.setText(product.get("description").toString());
	txtPrice.setText(product.get("price").toString());
	txtQuantity.setText(product.get("quantity").toString());
	return false;
    }

    @Override
    public Object guiToObject()
    {
	logger.debug("guiToObject() | Product Object: " + product);
	product.put("code", txtCode.getText());
	product.put("description", txtDescription.getText());
	product.put("price", txtPrice.getText());
	product.put("quantity", txtQuantity.getText());
	return product;
    }

    @Override
    public int getObjectType()
    {
	return Services.TYPE_PRODUCT;
    }

    @Override
    public String getCurrentCode()
    {
	return txtCode.getText();
    }

    @Override
    public void clear()
    {
	txtCode.setText("");
	txtDescription.setText("");
	txtPrice.setText("");
	txtQuantity.setText("");
	if (product != null) {
	    product.clear();
	}

    }

    @Override
    public void onInit()
    {
	/*
	 * TEST CLIENT
	 * System.out.println("***************************EDITPRODUCT!!!!!!!");
	 * JSONObject
	 * getResult=RestfulClientFacade.getRequest("http://date.jsontest.com");
	 * System.out.println("RESULT: "+getResult.toString());
	 * 
	 * System.out.println("POST!!!!!!!!!!!!!!!!!!!"); JSONObject
	 * postResult=RestfulClientFacade
	 * .postRequest("https://posttestserver.com/post.php"
	 * ,"{\"Nombre\":\"Lagarto\"}");
	 * System.out.println("RESULT: "+postResult.toString());
	 */
    }
}
