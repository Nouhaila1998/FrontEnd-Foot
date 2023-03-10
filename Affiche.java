/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import dao.IDao;
import entity.Machine;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class Affiche extends javax.swing.JInternalFrame {

    
    private IDao<Machine>  stub;
    public Affiche() throws NamingException, RemoteException{
        initComponents();
        
         final Hashtable<Object, Object> config = new Hashtable<Object, Object>();
			config.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
			config.put(Context.PROVIDER_URL, "rmi://localhost:1099");
			InitialContext context = new InitialContext(config);
		
		        stub = (IDao<Machine>) context.lookup("dao");
			
			System.out.println("Liste");
			System.out.println(stub.findAll());
                        
                         fill();
    }
    
    private void fill() throws RemoteException{
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        List<Machine> machines = stub.findAll();
        Object[] rowData = new Object[4];
        
        for(int i = 0; i < machines.size(); i++){
            rowData[0] = machines.get(i).getRef();
            rowData[1] = machines.get(i).getMarque();
            rowData[2] = machines.get(i).getPrix();
            rowData[3] = machines.get(i).getDateAchat();
            
            model.addRow(rowData);
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ref", "Marque", "Prix", "Date Achat"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
