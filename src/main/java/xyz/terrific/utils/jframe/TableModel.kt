package xyz.terrific.utils.jframe

import javax.swing.table.AbstractTableModel

/**
 * @author TerrificTable55
 */
class TableModel : AbstractTableModel() {
    override fun getRowCount(): Int {
        return 0
    }

    override fun getColumnCount(): Int {
        return 0
    }

    override fun isCellEditable(row: Int, column: Int): Boolean {
        return false
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any? {
        return null
    }
}
