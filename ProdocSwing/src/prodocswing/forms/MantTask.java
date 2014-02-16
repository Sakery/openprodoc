/*
 * OpenProdoc
 * 
 * See the help doc files distributed with
 * this work for additional information regarding copyright ownership.
 * Joaquin Hierro licenses this file to You under:
 * 
 * License GNU GPL v3 http://www.gnu.org/licenses/gpl.html
 * 
 * you may not use this file except in compliance with the License.  
 * Unless agreed to in writing, software is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * author: Joaquin Hierro      2011
 * 
 */

/*
 * MantUsers.java
 *
 * Created on 17-feb-2010, 21:16:33
 */

package prodocswing.forms;

import java.util.Date;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import prodoc.Attribute;
import prodoc.Cursor;
import prodoc.DriverGeneric;
import prodoc.PDException;
import prodoc.PDLog;
import prodoc.PDObjDefs;
import prodoc.PDTasksDef;
import prodoc.PDTasksExecEnded;
import prodoc.Record;

/**
 *
 * @author jhierrot
 */
public class MantTask extends javax.swing.JDialog
{
private Record CronTask;
private boolean Cancel;

/** Creates new form MantUsers
 * @param parent 
 * @param modal
 */
public MantTask(java.awt.Frame parent, boolean modal)
{
super(parent, modal);
initComponents();
}

/** This method is called from within the constructor to
 * initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is
 * always regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        LabelOperation = new javax.swing.JLabel();
        TaskNameLabel = new javax.swing.JLabel();
        TaskNameTextField = new javax.swing.JTextField();
        DescriptionLabel = new javax.swing.JLabel();
        DescriptionTextField = new javax.swing.JTextField();
        CategoryLabel = new javax.swing.JLabel();
        CategoryTextField = new javax.swing.JTextField();
        TypeLabel = new javax.swing.JLabel();
        TypeComboBox = new javax.swing.JComboBox();
        ObjTypeLabel = new javax.swing.JLabel();
        ObjTypeComboBox = new javax.swing.JComboBox();
        FilterLabel = new javax.swing.JLabel();
        FilterTextField = new javax.swing.JTextField();
        ParamLabel = new javax.swing.JLabel();
        ParamTextField = new javax.swing.JTextField();
        ParamLabel2 = new javax.swing.JLabel();
        ParamTextField2 = new javax.swing.JTextField();
        ParamLabel3 = new javax.swing.JLabel();
        ParamTextField3 = new javax.swing.JTextField();
        ParamLabel4 = new javax.swing.JLabel();
        ParamTextField4 = new javax.swing.JTextField();
        NextDateLabel = new javax.swing.JLabel();
        NextDateTextField = new javax.swing.JFormattedTextField();
        ActiveLabel = new javax.swing.JLabel();
        ActiveCB = new javax.swing.JCheckBox();
        TransactLabel = new javax.swing.JLabel();
        TransactCB = new javax.swing.JCheckBox();
        ButtonAcept = new javax.swing.JButton();
        StartDateLabel = new javax.swing.JLabel();
        StartDateTextField = new javax.swing.JFormattedTextField();
        EndDateLabel = new javax.swing.JLabel();
        EndDateTextField = new javax.swing.JFormattedTextField();
        ResultLabel = new javax.swing.JLabel();
        ResultCB = new javax.swing.JCheckBox();
        ResMesgLabel = new javax.swing.JLabel();
        ResMesgField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(MainWin.TT("Task_Results"));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });

        LabelOperation.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        LabelOperation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelOperation.setText("jLabel1");

        TaskNameLabel.setFont(MainWin.getFontDialog());
        TaskNameLabel.setText("jLabel1");

        TaskNameTextField.setFont(MainWin.getFontDialog());

        DescriptionLabel.setFont(MainWin.getFontDialog());
        DescriptionLabel.setText("jLabel1");

        DescriptionTextField.setFont(MainWin.getFontDialog());

        CategoryLabel.setFont(MainWin.getFontDialog());
        CategoryLabel.setText("jLabel1");

        CategoryTextField.setFont(MainWin.getFontDialog());

        TypeLabel.setFont(MainWin.getFontDialog());
        TypeLabel.setText("jLabel1");

        TypeComboBox.setFont(MainWin.getFontDialog());
        TypeComboBox.setModel(getListTypeTask());

        ObjTypeLabel.setFont(MainWin.getFontDialog());
        ObjTypeLabel.setText("jLabel1");

        ObjTypeComboBox.setFont(MainWin.getFontDialog());
        ObjTypeComboBox.setModel(getListObj());
        ObjTypeComboBox.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ObjTypeComboBoxActionPerformed(evt);
            }
        });

        FilterLabel.setFont(MainWin.getFontDialog());
        FilterLabel.setText("jLabel1");

        FilterTextField.setFont(MainWin.getFontDialog());

        ParamLabel.setFont(MainWin.getFontDialog());
        ParamLabel.setText("jLabel1");

        ParamTextField.setFont(MainWin.getFontDialog());

        ParamLabel2.setFont(MainWin.getFontDialog());
        ParamLabel2.setText("jLabel1");

        ParamTextField2.setFont(MainWin.getFontDialog());

        ParamLabel3.setFont(MainWin.getFontDialog());
        ParamLabel3.setText("jLabel1");

        ParamTextField3.setFont(MainWin.getFontDialog());

        ParamLabel4.setFont(MainWin.getFontDialog());
        ParamLabel4.setText("jLabel1");

        ParamTextField4.setFont(MainWin.getFontDialog());

        NextDateLabel.setFont(MainWin.getFontDialog());
        NextDateLabel.setText("jLabel1");

        NextDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/MM/yyyy"))));
        NextDateTextField.setFont(MainWin.getFontDialog());

        ActiveLabel.setFont(MainWin.getFontDialog());
        ActiveLabel.setText("jLabel1");

        ActiveCB.setBorder(null);

        TransactLabel.setFont(MainWin.getFontDialog());
        TransactLabel.setText("jLabel1");

        TransactCB.setBorder(null);

        ButtonAcept.setFont(MainWin.getFontDialog());
        ButtonAcept.setText(MainWin.TT("Ok"));
        ButtonAcept.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ButtonAceptActionPerformed(evt);
            }
        });

        StartDateLabel.setFont(MainWin.getFontDialog());
        StartDateLabel.setText("jLabel1");

        StartDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/MM/yyyy"))));
        StartDateTextField.setFont(MainWin.getFontDialog());

        EndDateLabel.setFont(MainWin.getFontDialog());
        EndDateLabel.setText("jLabel1");

        EndDateTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("d/MM/yyyy"))));
        EndDateTextField.setFont(MainWin.getFontDialog());

        ResultLabel.setFont(MainWin.getFontDialog());
        ResultLabel.setText("jLabel1");

        ResultCB.setBorder(null);

        ResMesgLabel.setFont(MainWin.getFontDialog());
        ResMesgLabel.setText("jLabel1");

        ResMesgField.setFont(MainWin.getFontDialog());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ParamLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ParamLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResMesgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ParamLabel)
                    .addComponent(ResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FilterLabel)
                    .addComponent(ObjTypeLabel)
                    .addComponent(ParamLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NextDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ActiveLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TransactLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TaskNameLabel)
                    .addComponent(StartDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CategoryLabel)
                    .addComponent(EndDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescriptionLabel)
                    .addComponent(TypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TransactCB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonAcept))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ParamTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ParamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResMesgField, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ObjTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ResultCB)
                            .addComponent(EndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ParamTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ParamTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NextDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ActiveCB)
                            .addComponent(StartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TaskNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CategoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(LabelOperation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DescriptionTextField, EndDateTextField, FilterTextField, NextDateTextField, ObjTypeComboBox, ParamTextField, ParamTextField2, ParamTextField3, ParamTextField4, ResMesgField, StartDateTextField, TypeComboBox});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ActiveLabel, CategoryLabel, DescriptionLabel, EndDateLabel, FilterLabel, NextDateLabel, ObjTypeLabel, ParamLabel, ParamLabel2, ParamLabel3, ParamLabel4, ResMesgLabel, ResultLabel, StartDateLabel, TaskNameLabel, TransactLabel, TypeLabel});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelOperation)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TaskNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TaskNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescriptionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CategoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CategoryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ObjTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ObjTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FilterLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ParamTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ParamLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ParamLabel2)
                    .addComponent(ParamTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ParamLabel3)
                    .addComponent(ParamTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ParamLabel4)
                    .addComponent(ParamTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextDateLabel)
                    .addComponent(NextDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartDateLabel)
                    .addComponent(StartDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EndDateLabel)
                    .addComponent(EndDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ResultLabel)
                    .addComponent(ResultCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ResMesgField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResMesgLabel))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ActiveCB)
                    .addComponent(ActiveLabel))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TransactLabel)
                    .addComponent(TransactCB)
                    .addComponent(ButtonAcept))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
Cancel=true;
    }//GEN-LAST:event_formWindowClosing

    private void ObjTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ObjTypeComboBoxActionPerformed
    {//GEN-HEADEREND:event_ObjTypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObjTypeComboBoxActionPerformed

    private void ButtonAceptActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ButtonAceptActionPerformed
    {//GEN-HEADEREND:event_ButtonAceptActionPerformed
Cancel=true;
this.dispose();
    }//GEN-LAST:event_ButtonAceptActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ActiveCB;
    private javax.swing.JLabel ActiveLabel;
    private javax.swing.JButton ButtonAcept;
    private javax.swing.JLabel CategoryLabel;
    private javax.swing.JTextField CategoryTextField;
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.JTextField DescriptionTextField;
    private javax.swing.JLabel EndDateLabel;
    private javax.swing.JFormattedTextField EndDateTextField;
    private javax.swing.JLabel FilterLabel;
    private javax.swing.JTextField FilterTextField;
    private javax.swing.JLabel LabelOperation;
    private javax.swing.JLabel NextDateLabel;
    private javax.swing.JFormattedTextField NextDateTextField;
    private javax.swing.JComboBox ObjTypeComboBox;
    private javax.swing.JLabel ObjTypeLabel;
    private javax.swing.JLabel ParamLabel;
    private javax.swing.JLabel ParamLabel2;
    private javax.swing.JLabel ParamLabel3;
    private javax.swing.JLabel ParamLabel4;
    private javax.swing.JTextField ParamTextField;
    private javax.swing.JTextField ParamTextField2;
    private javax.swing.JTextField ParamTextField3;
    private javax.swing.JTextField ParamTextField4;
    private javax.swing.JTextField ResMesgField;
    private javax.swing.JLabel ResMesgLabel;
    private javax.swing.JCheckBox ResultCB;
    private javax.swing.JLabel ResultLabel;
    private javax.swing.JLabel StartDateLabel;
    private javax.swing.JFormattedTextField StartDateTextField;
    private javax.swing.JLabel TaskNameLabel;
    private javax.swing.JTextField TaskNameTextField;
    private javax.swing.JCheckBox TransactCB;
    private javax.swing.JLabel TransactLabel;
    private javax.swing.JComboBox TypeComboBox;
    private javax.swing.JLabel TypeLabel;
    // End of variables declaration//GEN-END:variables

//----------------------------------------------------------------
/**
*
*/
public void EditMode()
{
LabelOperation.setText(MainWin.TT("Task_Results"));
TaskNameTextField.setEditable(false);
CategoryTextField.setEditable(false);
DescriptionTextField.setEditable(false);
ObjTypeComboBox.setEnabled(false);
TypeComboBox.setEnabled(false);
FilterTextField.setEditable(false);
ParamTextField.setEditable(false);
ParamTextField2.setEditable(false);
ParamTextField3.setEditable(false);
ParamTextField4.setEditable(false);
TypeComboBox.setEditable(false);
ObjTypeComboBox.setEditable(false);
NextDateTextField.setEditable(false);
StartDateTextField.setEditable(false);
EndDateTextField.setEditable(false);
ResMesgField.setEditable(false);
ResultCB.setEnabled(false);
ActiveCB.setEnabled(false);
TransactCB.setEnabled(false);
}
//----------------------------------------------------------------
/**
* @return the User
*/
public Record getRecord()
{
return CronTask;
}
//----------------------------------------------------------------
/**
 * @param pTask
*/
public void setRecord(Record pTask)
{
CronTask = pTask;
Attribute Attr=CronTask.getAttr(PDTasksExecEnded.fNAME); //-------------------------
TaskNameLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    TaskNameTextField.setText((String)Attr.getValue());
TaskNameTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fCATEGORY);//--------------------------
CategoryLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    CategoryTextField.setText((String)Attr.getValue());
CategoryTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fDESCRIPTION);//--------------------------
DescriptionLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    DescriptionTextField.setText((String)Attr.getValue());
DescriptionTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fFILTER); //--------------------------
FilterLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    FilterTextField.setText((String)Attr.getValue());
FilterTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fPARAM); //--------------------------
ParamLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    ParamTextField.setText((String)Attr.getValue());
ParamTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fPARAM2); //--------------------------
ParamLabel2.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    ParamTextField2.setText((String)Attr.getValue());
ParamTextField2.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fPARAM3); //--------------------------
ParamLabel3.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    ParamTextField3.setText((String)Attr.getValue());
ParamTextField3.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fPARAM4); //--------------------------
ParamLabel4.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    ParamTextField4.setText((String)Attr.getValue());
ParamTextField4.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fTYPE); //--------------------------
TypeLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    TypeComboBox.setSelectedIndex((Integer)Attr.getValue());
TypeComboBox.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fOBJTYPE); //--------------------------
ObjTypeLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    ObjTypeComboBox.setSelectedItem((String)Attr.getValue());
ObjTypeComboBox.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fNEXTDATE); //--------------------------
NextDateLabel.setText(MainWin.DrvTT(Attr.getUserName()));
try {
NextDateTextField.setFormatterFactory(MainWin.getFormFacTS());
} catch(Exception e)
    {
    PDLog.Error(e.getLocalizedMessage());
    }
if (Attr.getValue()!=null)
    NextDateTextField.setValue(Attr.getValue());
else
    NextDateTextField.setValue(new Date());
NextDateTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription())  +"("+MainWin.getFormatTS()+")");
Attr=CronTask.getAttr(PDTasksExecEnded.fSTARTDATE); //--------------------------
StartDateLabel.setText(MainWin.DrvTT(Attr.getUserName()));
try {
StartDateTextField.setFormatterFactory(MainWin.getFormFacTS());
} catch(Exception e)
    {
    PDLog.Error(e.getLocalizedMessage());
    }
if (Attr.getValue()!=null)
    StartDateTextField.setValue(Attr.getValue());
else
    StartDateTextField.setValue(new Date());
StartDateTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription())  +"("+MainWin.getFormatTS()+")");
Attr=CronTask.getAttr(PDTasksExecEnded.fENDDATE); //--------------------------
EndDateLabel.setText(MainWin.DrvTT(Attr.getUserName()));
try {
EndDateTextField.setFormatterFactory(MainWin.getFormFacTS());
} catch(Exception e)
    {
    PDLog.Error(e.getLocalizedMessage());
    }
if (Attr.getValue()!=null)
    EndDateTextField.setValue(Attr.getValue());
else
    EndDateTextField.setValue(new Date());
EndDateTextField.setToolTipText(MainWin.DrvTT(Attr.getDescription())  +"("+MainWin.getFormatTS()+")");
Attr=CronTask.getAttr(PDTasksExecEnded.fENDSOK); //--------------------------
ResultLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    ResultCB.setSelected((Boolean)Attr.getValue());
ResultCB.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fRESULT); //--------------------------
ResMesgLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    this.ResMesgField.setText((String)Attr.getValue());
ResMesgField.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fACTIVE); //--------------------------
ActiveLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    ActiveCB.setSelected((Boolean)Attr.getValue());
ActiveCB.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
Attr=CronTask.getAttr(PDTasksExecEnded.fTRANSACT); //--------------------------
TransactLabel.setText(MainWin.DrvTT(Attr.getUserName()));
if (Attr.getValue()!=null)
    TransactCB.setSelected((Boolean)Attr.getValue());
TransactCB.setToolTipText(MainWin.DrvTT(Attr.getDescription()));
}
//----------------------------------------------------------------
/**
* @return the Cancel
*/
public boolean isCancel()
{
return Cancel;
}
//----------------------------------------------------------------

private ComboBoxModel getListTypeTask()
{
return(new DefaultComboBoxModel(PDTasksDef.getListTypeTask()));
}
//----------------------------------------------------------------

private ComboBoxModel getListObj()
{
Vector VObjects=new Vector();
try {
DriverGeneric Session=MainWin.getSession();
PDObjDefs Obj = new PDObjDefs(Session);
Cursor CursorId = Obj.getListFold();
Record Res=Session.NextRec(CursorId);
while (Res!=null)
    {
    Attribute Attr=Res.getAttr(PDObjDefs.fNAME);
    VObjects.add(Attr.getValue());
    Res=Session.NextRec(CursorId);
    }
Session.CloseCursor(CursorId);
CursorId = Obj.getListDocs();
Res=Session.NextRec(CursorId);
while (Res!=null)
    {
    Attribute Attr=Res.getAttr(PDObjDefs.fNAME);
    VObjects.add(Attr.getValue());
    Res=Session.NextRec(CursorId);
    }
Session.CloseCursor(CursorId);
} catch (PDException ex)
    {
    MainWin.Message("Error"+ex.getLocalizedMessage());
    }
return(new DefaultComboBoxModel(VObjects));
}
//----------------------------------------------------------------
}
