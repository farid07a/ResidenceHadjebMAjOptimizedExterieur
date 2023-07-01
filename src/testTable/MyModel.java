/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testTable;

/**
 *
 * @author farid
 */
import ViewMyPrj.CLabel;
 
class MyModel extends javax.swing.table.DefaultTableModel{
 
    Object[][] row = {{new CLabel(), "Row 1 Col 2", "Row 1 Col3"},
                      {new CLabel(), "Row 2 Col 2", "Row 2 Col3"},
                      {new CLabel(), "Row 3 Col 2", "Row 3 Col3"},
                      {new CLabel(), "Row 4 Col 2", "Row 4 Col3"}};
 
    Object[] col = {"Column 1", "Column 2", "Column 3"};
 
    public MyModel (){
 
    //Adding columns
        for(Object c: col)
            this.addColumn(c);
 
    //Adding rows
        for(Object[] r: row)
            addRow(r);
 
    }
 
    @Override
    public Class getColumnClass(int columnIndex) {
        if(columnIndex == 0)return getValueAt(0, columnIndex).getClass();
 
        else return super.getColumnClass(columnIndex);
 
    }
 
}