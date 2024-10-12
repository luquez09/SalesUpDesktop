
package presentation.inventario;

import entidad.constantes.Constants;
import entidad.entitys.inventario.Category;
import lombok.extern.log4j.Log4j2;
import negocio.inventario.LogicalCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author ivanl
 */
@Log4j2
public class CategoryFrom extends javax.swing.JPanel {
    private final LogicalCategory logicalCategory = new LogicalCategory();
    private Category accionCategory = Category.builder().build();
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
        lblCountRegister = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                try {
                    formAncestorAdded(evt);
                } catch (ParseException e) {
                    log.error(Constants.ERROR_APP + e.getMessage());
                    JOptionPane.showMessageDialog(null, Constants.ERROR_APP + e.getMessage());
                }
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

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
        if (tableDataCategory.getColumnModel().getColumnCount() > 0) {
            tableDataCategory.getColumnModel().getColumn(0).setMinWidth(1);
            tableDataCategory.getColumnModel().getColumn(0).setMaxWidth(1);
            tableDataCategory.getColumnModel().getColumn(1).setResizable(false);
            tableDataCategory.getColumnModel().getColumn(2).setResizable(false);
        }

        textDescriptionCategory.setColumns(3);

        jLabel2.setText("Descripcion");

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/agregar.png"))); // NOI18N
        btnAceptar.setText("Agregar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnAceptarActionPerformed(evt);
                } catch (ParseException e) {
                    log.error(Constants.ERROR_APP + e.getMessage());
                    JOptionPane.showMessageDialog(null, Constants.ERROR_APP + e.getMessage());
                }
            }
        });

        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/actualizar-flecha.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnActualizarActionPerformed(evt);
                } catch (ParseException e) {
                    log.error(Constants.ERROR_APP + e.getMessage());
                    JOptionPane.showMessageDialog(null, Constants.ERROR_APP + e.getMessage());
                }
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/boton-menos.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    btnEliminarActionPerformed(evt);
                } catch (ParseException e) {
                    log.error(Constants.ERROR_APP + e.getMessage());
                    JOptionPane.showMessageDialog(null, Constants.ERROR_APP + e.getMessage());
                }
            }
        });

        lblCountRegister.setText("Total de registros: ");

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancelar.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(textNameCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                    .addComponent(textDescriptionCategory))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(lblCountRegister)
                        .addGap(129, 129, 129))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textNameCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textDescriptionCategory))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCountRegister))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableDataCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataCategoryMouseClicked
        DefaultTableModel defaultTableModel = (DefaultTableModel)tableDataCategory.getModel();
        int indexSelect = tableDataCategory.getSelectedRow();

        textNameCategory.setText(defaultTableModel.getValueAt(indexSelect, 1).toString());
        textDescriptionCategory.setText(defaultTableModel.getValueAt(indexSelect, 2).toString());
        accionCategory.setIdCategory(Integer.valueOf(defaultTableModel.getValueAt(indexSelect, 0).toString()));
        btnAceptar.setEnabled(false);
        btnCancelar.setVisible(true);
    }//GEN-LAST:event_tableDataCategoryMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_btnAceptarActionPerformed
        Category category = Category.builder()
                .name(textNameCategory.getText())
                .description(textDescriptionCategory.getText())
                .build();
        String result = logicalCategory.addCategory(category);
        formComponentShown();
        JOptionPane.showMessageDialog(null, result);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_btnActualizarActionPerformed
        accionCategory.setDescription(textDescriptionCategory.getText());
        accionCategory.setName(textNameCategory.getText());

        log.info("Actualizando categoria con ID: {}", accionCategory.getIdCategory());
        log.info("Cuerpo del DTO: {}", accionCategory);
        String result = logicalCategory.updateCategory(accionCategory);
        formComponentShown();
        cleanFrom();
        activeButton();
        JOptionPane.showMessageDialog(null, "Actualizacion: " + result);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_btnEliminarActionPerformed
        log.info("Eliminando categoria con ID: {}", accionCategory.getIdCategory());
        String result = logicalCategory.deleteCategory(accionCategory);
        formComponentShown();
        cleanFrom();
        activeButton();
        JOptionPane.showMessageDialog(null, "Eliminacion: " + result);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void formComponentShown() throws ParseException {
        SwingUtilities.invokeLater(() -> {
            try {
                String countRegister;
                List<Category> categoryList = logicalCategory.categoryList();
                log.info("Consultando los datos de categoria: {}", categoryList.toString());
                DefaultTableModel defaultTableModel = (DefaultTableModel) tableDataCategory.getModel();
                defaultTableModel.setRowCount(0);

                for (Category category : categoryList) {
                    defaultTableModel.addRow(new Object[]{
                            category.getIdCategory(),
                            category.getName(),
                            category.getDescription(),
                            category.getDateCreate(),
                            category.getDateUpdate()
                    });
                }

                countRegister = Constants.NUMBER_REGISTER + categoryList.size();
                lblCountRegister.setText(countRegister);

                defaultTableModel.fireTableDataChanged();
            } catch (ParseException e) {
                log.error("Ups!! Ocurrio un error al cargar los datos: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Ocurrio un error, cauda: " + e.getMessage());
            }
        });
    }

    private void tableDataCategoryComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableDataCategoryComponentShown
        try {
            formComponentShown();
        } catch (ParseException ex) {
            log.error("Ups!! Ocurrio un error al cargar los datos: " + ex.getMessage());
        }
    }//GEN-LAST:event_tableDataCategoryComponentShown

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) throws ParseException {//GEN-FIRST:event_formAncestorAdded
       formComponentShown();
        btnCancelar.setVisible(false);
    }//GEN-LAST:event_formAncestorAdded

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        activeButton();
        cleanFrom();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void activeButton() {
        btnAceptar.setEnabled(true);
        btnCancelar.setVisible(false);
    }

    private void cleanFrom() {
        textDescriptionCategory.setText(Constants.EMPTY);
        textNameCategory.setText(Constants.EMPTY);
        textNameCategory.requestFocus(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCountRegister;
    private javax.swing.JTable tableDataCategory;
    private javax.swing.JTextField textDescriptionCategory;
    private javax.swing.JTextField textNameCategory;
    // End of variables declaration//GEN-END:variables
}
