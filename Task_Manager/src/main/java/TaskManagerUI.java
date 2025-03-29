import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Properties;
import org.jdatepicker.impl.*;

// without youtube and w3schools id be stuck on a console based app :prayge:, im still learning the ins and outs of UI
// tbh might make something that would roll back to console if the UI breaks or anything

public class TaskManagerUI extends JFrame {
    private  TaskManager manager;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTable table;
    private TaskTableModel tableModel;

    public TaskManagerUI(TaskManager manager) {
        this.manager = manager;

        setTitle("bu3li's Task Manager v0.0.1");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new TaskTableModel(manager.returnList());
        table = new JTable(tableModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    int row = table.getSelectedRow();
                    Task task = manager.returnList().get(row);
                    JOptionPane.showMessageDialog(
                            TaskManagerUI.this,task.getDescription(),"Description for: " + task.getName(),  JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Set As Complete");
        JButton removeButton = new JButton("Remove Task");

        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        /// getting the buttons to do stuff i guess 1st time doing UI so god help me

        addButton.addActionListener(e -> addTask());
        completeButton.addActionListener(e -> completeTask());
        removeButton.addActionListener(e -> removeTask());

        refreshTaskList();
    }

    private void addTask(){
        String title = JOptionPane.showInputDialog(this, "Enter Task Name: ");
        String description = JOptionPane.showInputDialog(this, "Enter Task Description: ");

        // commented line because skill issue and i dont want  it to be a string anymore
       // String dueDate = JOptionPane.showInputDialog(this, "Enter Due Date: ");

        // thank you youtube :prayge:
        UtilDateModel model = new UtilDateModel();
        Properties mordekaiser = new Properties();
        mordekaiser.put("text.today","Today");
        mordekaiser.put("text.month","Month");
        mordekaiser.put("text.year","Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model,mordekaiser);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());

        // actually applying the  youtube shit andf makiing  it work, ty youtube again
        int r = JOptionPane.showConfirmDialog(this, datePicker, "Pick Due Date", JOptionPane.OK_CANCEL_OPTION);
        if(r == JOptionPane.OK_OPTION){
            java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
            if(selectedDate!=null){
                String dueDate = new SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
                if(!title.isEmpty()) {
                    manager.addTask(new Task(title, description, dueDate, false));
                    refreshTaskList();
                }
            }
        }
    }

    private void removeTask(){
        int selectedRow = table.getSelectedRow();
        if(selectedRow>=0){
            manager.deleteTask(selectedRow);
            refreshTaskList();
        }
        else{
            JOptionPane.showMessageDialog(this,"Please Select a Task to Remove");
        }
    }

    private void completeTask() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            manager.markTastComplete(selectedRow);
            refreshTaskList();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task to mark as completed.");
        }
    }

    private void refreshTaskList() {
        tableModel.fireTableDataChanged();
    }


}
