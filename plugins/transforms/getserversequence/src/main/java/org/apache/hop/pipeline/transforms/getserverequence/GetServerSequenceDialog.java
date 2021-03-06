/*! ******************************************************************************
 *
 * Hop : The Hop Orchestration Platform
 *
 * http://www.project-hop.org
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.apache.hop.pipeline.transforms.getserverequence;

import org.apache.hop.core.Const;
import org.apache.hop.core.util.Utils;
import org.apache.hop.i18n.BaseMessages;
import org.apache.hop.pipeline.PipelineMeta;
import org.apache.hop.pipeline.transform.BaseTransformMeta;
import org.apache.hop.pipeline.transform.ITransformDialog;
import org.apache.hop.ui.core.widget.ComboVar;
import org.apache.hop.ui.core.widget.TextVar;
import org.apache.hop.ui.pipeline.transform.BaseTransformDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;

public class GetServerSequenceDialog extends BaseTransformDialog implements ITransformDialog {
  private static Class<?> PKG = GetServerSequenceMeta.class; // for i18n purposes, needed by Translator!!

  private Label wlValuename;
  private Text wValuename;

  private Label wlHopServer;
  private ComboVar wHopServer;

  private Label wlSeqname;
  private TextVar wSeqname;

  private Label wlIncrement;
  private TextVar wIncrement;

  private GetServerSequenceMeta input;

  public GetServerSequenceDialog( Shell parent, Object in, PipelineMeta pipelineMeta, String sname ) {
    super( parent, (BaseTransformMeta) in, pipelineMeta, sname );
    input = (GetServerSequenceMeta) in;
  }

  public String open() {
    Shell parent = getParent();
    Display display = parent.getDisplay();

    shell = new Shell( parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MAX | SWT.MIN );
    props.setLook( shell );
    setShellImage( shell, input );

    ModifyListener lsMod = new ModifyListener() {
      public void modifyText( ModifyEvent e ) {
        input.setChanged();
      }
    };
    changed = input.hasChanged();

    FormLayout formLayout = new FormLayout();
    formLayout.marginWidth = Const.FORM_MARGIN;
    formLayout.marginHeight = Const.FORM_MARGIN;

    shell.setLayout( formLayout );
    shell.setText( BaseMessages.getString( PKG, "GetSequenceDialog.Shell.Title" ) );

    int middle = props.getMiddlePct();
    int margin = props.getMargin();

    // TransformName line
    wlTransformName = new Label( shell, SWT.RIGHT );
    wlTransformName.setText( BaseMessages.getString( PKG, "GetSequenceDialog.TransformName.Label" ) );
    props.setLook( wlTransformName );
    fdlTransformName = new FormData();
    fdlTransformName.left = new FormAttachment( 0, 0 );
    fdlTransformName.right = new FormAttachment( middle, -margin );
    fdlTransformName.top = new FormAttachment( 0, margin );
    wlTransformName.setLayoutData( fdlTransformName );
    wTransformName = new Text( shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    wTransformName.setText( transformName );
    props.setLook( wTransformName );
    wTransformName.addModifyListener( lsMod );
    fdTransformName = new FormData();
    fdTransformName.left = new FormAttachment( middle, 0 );
    fdTransformName.top = new FormAttachment( 0, margin );
    fdTransformName.right = new FormAttachment( 100, 0 );
    wTransformName.setLayoutData( fdTransformName );

    // Valuename line
    wlValuename = new Label( shell, SWT.RIGHT );
    wlValuename.setText( BaseMessages.getString( PKG, "GetSequenceDialog.Valuename.Label" ) );
    props.setLook( wlValuename );
    FormData fdlValuename = new FormData();
    fdlValuename.left = new FormAttachment( 0, 0 );
    fdlValuename.top = new FormAttachment( wTransformName, margin );
    fdlValuename.right = new FormAttachment( middle, -margin );
    wlValuename.setLayoutData( fdlValuename );
    wValuename = new Text( shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    wValuename.setText( "" );
    props.setLook( wValuename );
    wValuename.addModifyListener( lsMod );
    FormData fdValuename = new FormData();
    fdValuename.left = new FormAttachment( middle, 0 );
    fdValuename.top = new FormAttachment( wTransformName, margin );
    fdValuename.right = new FormAttachment( 100, 0 );
    wValuename.setLayoutData( fdValuename );

    // Connection line
    //
    wlHopServer = new Label( shell, SWT.RIGHT );
    wlHopServer.setText( BaseMessages.getString( PKG, "GetSequenceDialog.HopServer.Label" ) );
    props.setLook( wlHopServer );
    FormData fdlHopServer = new FormData();
    fdlHopServer.left = new FormAttachment( 0, 0 );
    fdlHopServer.top = new FormAttachment( wValuename, margin );
    fdlHopServer.right = new FormAttachment( middle, -margin );
    wlHopServer.setLayoutData( fdlHopServer );
    wHopServer = new ComboVar( pipelineMeta, shell, SWT.LEFT | SWT.SINGLE | SWT.BORDER );
    wHopServer.setItems( pipelineMeta.getHopServerNames() );
    FormData fdHopServer = new FormData();
    fdHopServer.left = new FormAttachment( middle, 0 );
    fdHopServer.top = new FormAttachment( wValuename, margin );
    fdHopServer.right = new FormAttachment( 100, 0 );
    wHopServer.setLayoutData( fdHopServer );

    // Seqname line
    wlSeqname = new Label( shell, SWT.RIGHT );
    wlSeqname.setText( BaseMessages.getString( PKG, "GetSequenceDialog.Seqname.Label" ) );
    props.setLook( wlSeqname );
    FormData fdlSeqname = new FormData();
    fdlSeqname.left = new FormAttachment( 0, 0 );
    fdlSeqname.right = new FormAttachment( middle, -margin );
    fdlSeqname.top = new FormAttachment( wHopServer, margin );
    wlSeqname.setLayoutData( fdlSeqname );

    wSeqname = new TextVar( pipelineMeta, shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    wSeqname.setText( "" );
    props.setLook( wSeqname );
    wSeqname.addModifyListener( lsMod );
    FormData fdSeqname = new FormData();
    fdSeqname.left = new FormAttachment( middle, 0 );
    fdSeqname.top = new FormAttachment( wHopServer, margin );
    fdSeqname.right = new FormAttachment( 100, 0 );
    wSeqname.setLayoutData( fdSeqname );

    // Increment line
    wlIncrement = new Label( shell, SWT.RIGHT );
    wlIncrement.setText( BaseMessages.getString( PKG, "GetSequenceDialog.Increment.Label" ) );
    props.setLook( wlIncrement );
    FormData fdlIncrement = new FormData();
    fdlIncrement.left = new FormAttachment( 0, 0 );
    fdlIncrement.right = new FormAttachment( middle, -margin );
    fdlIncrement.top = new FormAttachment( wSeqname, margin );
    wlIncrement.setLayoutData( fdlIncrement );

    wIncrement = new TextVar( pipelineMeta, shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER );
    wIncrement.setText( "" );
    props.setLook( wIncrement );
    wIncrement.addModifyListener( lsMod );
    FormData fdIncrement = new FormData();
    fdIncrement.left = new FormAttachment( middle, 0 );
    fdIncrement.top = new FormAttachment( wSeqname, margin );
    fdIncrement.right = new FormAttachment( 100, 0 );
    wIncrement.setLayoutData( fdIncrement );

    // THE BUTTONS
    wOk = new Button( shell, SWT.PUSH );
    wOk.setText( BaseMessages.getString( PKG, "System.Button.OK" ) );
    wCancel = new Button( shell, SWT.PUSH );
    wCancel.setText( BaseMessages.getString( PKG, "System.Button.Cancel" ) );

    setButtonPositions( new Button[] { wOk, wCancel }, margin, wIncrement );

    // Add listeners
    lsOk = new Listener() {
      public void handleEvent( Event e ) {
        ok();
      }
    };
    lsCancel = new Listener() {
      public void handleEvent( Event e ) {
        cancel();
      }
    };

    wOk.addListener( SWT.Selection, lsOk );
    wCancel.addListener( SWT.Selection, lsCancel );

    lsDef = new SelectionAdapter() {
      public void widgetDefaultSelected( SelectionEvent e ) {
        ok();
      }
    };

    wTransformName.addSelectionListener( lsDef );
    wValuename.addSelectionListener( lsDef );
    wSeqname.addSelectionListener( lsDef );
    wIncrement.addSelectionListener( lsDef );

    // Detect X or ALT-F4 or something that kills this window...
    shell.addShellListener( new ShellAdapter() {
      public void shellClosed( ShellEvent e ) {
        cancel();
      }
    } );

    // Set the shell size, based upon previous time...
    setSize();

    getData();
    input.setChanged( changed );

    shell.open();
    while ( !shell.isDisposed() ) {
      if ( !display.readAndDispatch() ) {
        display.sleep();
      }
    }
    return transformName;
  }

  /**
   * Copy information from the meta-data input to the dialog fields.
   */
  public void getData() {
    logDebug( BaseMessages.getString( PKG, "GetSequenceDialog.Log.GettingKeyInfo" ) );

    wValuename.setText( Const.NVL( input.getValuename(), "" ) );
    wHopServer.setText( Const.NVL( input.getHopServerName(), "" ) );
    wSeqname.setText( Const.NVL( input.getSequenceName(), "" ) );
    wIncrement.setText( Const.NVL( input.getIncrement(), "" ) );

    wTransformName.selectAll();
    wTransformName.setFocus();
  }

  private void cancel() {
    transformName = null;
    input.setChanged( changed );
    dispose();
  }

  private void ok() {
    if ( Utils.isEmpty( wTransformName.getText() ) ) {
      return;
    }

    transformName = wTransformName.getText(); // return value

    input.setHopServerName( wHopServer.getText() );
    input.setValuename( wValuename.getText() );
    input.setSequenceName( wSeqname.getText() );
    input.setIncrement( wIncrement.getText() );

    dispose();
  }

}
