
package presentation.inventario;

import lombok.extern.log4j.Log4j2;
import negocio.inventario.LogicalCategory;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ivanl
 */
@Log4j2
public class CategoryFrom extends javax.swing.JPanel {
    private final LogicalCategory logicalCategory = new LogicalCategory();
    private entidad.entitys.inventario.Category accionCategory = entidad.entitys.inventario.Category.builder().build();
    /**
     * Creates new form Category
     */
    public CategoryFrom() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        textNameCategory = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDataCategory = new javax.swing.JTable();
        textDescriptionCategory = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("GESTIONAR CATEGORIA DE PRODUCTOS");

        jLabel1.setText("Nombre");

        tableDataCategory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Name", "Descripcion", "Creacion", "Moficacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDataCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataCategoryMouseClicked(evt);
            }
        });
        tableDataCategory.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableDataCategoryComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(tableDataCategory);

        textDescriptionCategory.setColumns(3);

        jLabel2.setText("Descripcion");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnActualizarActionPerformed(evt);
                } catch (ParseException e) {
                    log.error("Error: {}", e.getMessage());
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnEliminarActionPerformed(evt);
                } catch (ParseException e) {
                    log.error("Error: {}", e.getMessage());
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(textNameCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                            .addComponent(textDescriptionCategory))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addGap(29, 29, 29)
                        .addComponent(btnEliminar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNameCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textDescriptionCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableDataCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataCategoryMouseClicked
        DefaultTableModel defaultTableModel = (DefaultTableModel)tableDataCategory.getModel();
        int indexSelect = tableDataCategory.getSelectedRow();

        textNameCategory.setText(defaultTableModel.getValueAt(indexSelect, 1).toString());
        textDescriptionCategory.setText(defaultTableModel.getValueAt(indexSelect, 2).toString());
        accionCategory.setIdCategory(Integer.valueOf(defaultTableModel.getValueAt(indexSelect, 0).toString()));
        accionCategory.setName(defaultTableModel.getValueAt(indexSelect, 1).toString());
        accionCategory.setDescription(defaultTableModel.getValueAt(indexSelect, 2).toString());
    }//GEN-LAST:event_tableDataCategoryMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_btnActualizarActionPerformed
        log.info("Actualizando categoria con ID: {}", accionCategory.getIdCategory());
        String result = logicalCategory.updateCategory(accionCategory);
        formComponentShown();
        JOptionPane.showMessageDialog(null, "Actualizacion: " + result);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_btnEliminarActionPerformed
        log.info("Eliminando categoria con ID: {}", accionCategory.getIdCategory());
        String result = logicalCategory.deleteCategory(accionCategory);
        formComponentShown();
        JOptionPane.showMessageDialog(null, "Eliminacion: " + result);
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void formComponentShown() throws ParseException {
        List<entidad.entitys.inventario.Category> categoryList = logicalCategory.categoryList();
        DefaultTableModel defaultTableModel = (DefaultTableModel)tableDataCategory.getModel();
        defaultTableModel.setRowCount(0);

        for (entidad.entitys.inventario.Category category : categoryList) {
            defaultTableModel.addRow(new Object[] {
                category.getIdCategory(),
                category.getName(),
                category.getDescription(),
                category.getDateCreate(),
                category.getDateUpdate()
            });
        }
    }
    private void tableDataCategoryComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableDataCategoryComponentShown
        try {
            formComponentShown();
        } catch (ParseException ex) {
            log.error("Ups!! Ocurrio un error al cargar los datos: " + ex.getMessage());
        }
    }//GEN-LAST:event_tableDataCategoryComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDataCategory;
    private javax.swing.JTextField textDescriptionCategory;
    private javax.swing.JTextField textNameCategory;
    // End of variables declaration//GEN-END:variables
}
