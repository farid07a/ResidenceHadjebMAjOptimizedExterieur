/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testTable;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author farid
 */
public class TheModelOfTable extends AbstractTableModel{
    
    private String [] Columns;
    private Object [][] Rows;

    public TheModelOfTable() {
    }

    public TheModelOfTable(String[] Columns, Object[][] Rows) {
        this.Columns = Columns;
        this.Rows = Rows;
    }
    
    
    @Override
    public int getRowCount() {
        return this.Rows.length;
    }

    @Override
    public int getColumnCount() {
        return this.Columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.Rows[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return super.getColumnName(column); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        
        if (columnIndex==2) {
            return Icon.class;
        }else {
        return  getValueAt(0, columnIndex).getClass();
        }
    }
    
    
    
    
}
