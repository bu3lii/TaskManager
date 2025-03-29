import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TaskTableModel extends AbstractTableModel {
    private final String[] columns = { "Done", "Title", "Due Date" };
    private final List<Task> tasks;

    public TaskTableModel(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Task task = tasks.get(row);
        return switch (col) {
            case 0 -> task.isComplete();
            case 1 -> task.getName();
            case 2 -> task.getDuedate();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (col == 0) {
            tasks.get(row).setComplete((Boolean) value);
            fireTableCellUpdated(row, col);
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 0; // Only checkbox is editable
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return switch (column) {
            case 0 -> Boolean.class; // Checkbox
            case 1, 2 -> String.class;
            default -> Object.class;
        };
    }
}
