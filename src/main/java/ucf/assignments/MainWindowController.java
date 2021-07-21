/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Jason Saini
 */
package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class MainWindowController {

    @FXML
    TextField serialNumberTextfield, nameTextfield, valueTextfield;

    @FXML
    ObservableList<InventoryItem> itemDataList = FXCollections.observableArrayList();

    @FXML
    TableView inventoryTable;

    @FXML
    TableColumn <InventoryItem, String> serialNumberColumn, nameColumn, valueColumn;

    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public void addItemButtonClicked(ActionEvent actionEvent) {
        addItem();
    }

    public void loadButtonClicked(ActionEvent actionEvent) {
        loadFile();
    }

    public void saveAsTSVButtonClicked(ActionEvent actionEvent) {
        saveTSV();
    }

    public void saveAsHTMLButtonClicked(ActionEvent actionEvent) { saveHTML(); }

    public void saveAsJSONButtonClicked(ActionEvent actionEvent) {
        saveJSON();
    }

    public void searchByNameClicked(ActionEvent actionEvent) {
        searchByName();
    }

    public void searchBySerialNumClicked(ActionEvent actionEvent) {
        searchBySerialNum();
    }

    public void removeItemButtonClicked(ActionEvent actionEvent) {
        removeItem();
    }

    public void editNameButtonClicked(ActionEvent actionEvent) {
        editName();
    }

    public void editValueButtonClicked(ActionEvent actionEvent) {
        editValue();
    }

    public void editSerialNumberButtonClicked(ActionEvent actionEvent) {
        editSerialNumber();
    }


    private void addItem()
    {
        // initialize scenemanager
        SceneManager sceneManager = new SceneManager();
        sceneManager.load();
        // create inventory item object
        InventoryItem newItem = new InventoryItem();

        // get item name from name textfield
        String curName = nameTextfield.getText();
            // if item name  is not between 2 and 256 characters
                if(curName.length() < 2 || curName.length() > 256)
                {
                    // show invalid format popup!
                    nameTextfield.clear();
                }
                else
                {
                    newItem.setName(curName);
                }

        // get value from value textfield
            String curValString = valueTextfield.getText();
            double curVal = 0;
            try {
                // try formatting string to double
                curVal = Double.parseDouble(curValString);
                newItem.setDollarVal("$" + decimalFormat.format(curVal));
            }
            catch(Exception e) {
                e.printStackTrace();
                // show invalid format popup!
            }
             // get serial number from SN textfield
            String serialString = serialNumberTextfield.getText();
            // if serial number is not exactly 10 digits
            if(serialString.length() != 10 || newItem.hasInvalidCharacters(serialString)) {
                // ERROR DIALOG for invalid serial number format!
                sceneManager.setupDialogStage("SN Format Error Dialog", "Invalid Format!", false);
                return;
            }
            else if(itemDataList.size() > 1 && serialNumberAlreadyExists(serialString)) {
                // SERIAL NUMBER ALREADY EXISTS ALERT
            }
            else {
                newItem.setSerialNum(serialString);

            }
        // set cell value factory
        nameColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem,String>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem,String>("dollarVal"));
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<InventoryItem,String>("serialNum"));
        // add new item to back list
        itemDataList.add(newItem);
        // update tabelview to new list
        inventoryTable.setItems(itemDataList);

        // clear textfields for next entry
        nameTextfield.clear();
        serialNumberTextfield.clear();
        valueTextfield.clear();
    }

    public boolean serialNumberAlreadyExists(String serialString)
    {
        // iterate over back list
        for(int i = 0; i < itemDataList.size(); i++)
        {
            // if any of the serial numbers match the passed in string
            if(itemDataList.get(i).getSerialNum().equals(serialString))
            {
                return true;
            }
        }
        return false;
    }

    private void removeItem()
    {
        // get the index of selected item in tableview
        int selectedIndex = inventoryTable.getSelectionModel().getSelectedIndex();
        // remove item at index from observable list
        InventoryItem toRemove = itemDataList.get(selectedIndex);
        itemDataList.remove(toRemove);
        inventoryTable.setItems(itemDataList);
        // update tableview

    }

    private void editName()
    {
        // store index of selected item
        // open popup window
        // collect new name from text field
        // set new name in observable list
        // update tableview
        // close popup
    }

    private void editValue()
    {
        // store index of selected item
        // open popup window
        // collect new value from text field
        // set new value in observable list
        // update tableview
        // close popup
    }

    private void editSerialNumber()
    {
        // store index of selected item
        // open popup window
        // collect new Serial number from text field
        // set new serial number in observable list
        // update tableview
        // close popup
    }


    private void loadFile()
    {
        // find file via file chooser

        // if file is TSV
            // call load TSV function
        // if file is HTML
            // call load HTML function
        // if file is JSON
            // call load JSON function
    }

    private void loadTSV()
    {
        
    }
    
    private void loadHTML()
    {

    }

    private void loadJSON()
    {

    }

    private void saveTSV()
    {

    }

    private void saveHTML()
    {

    }

    private void saveJSON()
    {

    }

    private void searchByName()
    {
        // create a temp list of inventory items
        // iterate through entire list
            // if search key is a substring of an item's name
                // add item to temp list
        // update search tableview
    }

    private void searchBySerialNum()
    {
        // create a temp list of inventory items
        // iterate through entire list
            // if search key matches item's serial number
                // add item to temp list
        // update search tableview with temp list
    }



}
