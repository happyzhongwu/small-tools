package core;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.custom.StyledText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CopyFile {

	protected Shell shell;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CopyFile window = new CopyFile();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(798, 632);
		shell.setText("\u6587\u4EF6\u590D\u5236");
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Composite composite = formToolkit.createComposite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		composite.setLayoutData(new RowData(775, 303));
		formToolkit.paintBordersFor(composite);
		
		TextViewer textViewer = new TextViewer(composite, SWT.H_SCROLL|SWT.V_SCROLL);
		StyledText styledText = textViewer.getTextWidget();
		formToolkit.paintBordersFor(styledText);
		
		Composite composite_1 = formToolkit.createComposite(shell, SWT.NONE);
		composite_1.setTouchEnabled(true);
		composite_1.setToolTipText("");
		composite_1.setLayout(new GridLayout(2, false));
		composite_1.setLayoutData(new RowData(773, 275));
		formToolkit.paintBordersFor(composite_1);
		
		Label label = new Label(composite_1, SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 11, SWT.NORMAL));
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.widthHint = 129;
		gd_label.heightHint = 42;
		label.setLayoutData(gd_label);
		formToolkit.adapt(label, true, true);
		label.setText("\u76EE\u6807\u8DEF\u5F84");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		formToolkit.adapt(text, true, true);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);
		
		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] sourcePaths = styledText.getText().split("\\n");
				String targetPath = text.getText().trim();
				if(!targetPath.endsWith("\\")) {
					targetPath = targetPath + "\\";
				}
				File targetPathDirs =  new File(targetPath);
				if(!targetPathDirs.exists()) {
					targetPathDirs.mkdirs();
				}
				for (String sourcePath : sourcePaths) {
					sourcePath = sourcePath.trim();
					if(sourcePath.length() > 0) {
						System.out.println(sourcePath);
						File source = new File(sourcePath);
						if(source.exists()) {
							try {
								copyFile(source,targetPath);
							} catch (IOException e1) {
								e1.printStackTrace();
							}  
						}
					}
				}
			}
		});
		GridData gd_btnNewButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_btnNewButton.widthHint = 116;
		btnNewButton.setLayoutData(gd_btnNewButton);
		formToolkit.adapt(btnNewButton, true, true);
		btnNewButton.setText("\u786E\u5B9A");

	}
	
	private void copyFile(File sourceFile,String targetPath) throws IOException {
		String[] splits = sourceFile.getPath().split("\\\\");
		LinkedList<String> asList = new LinkedList<String>(Arrays.asList(splits));
		asList.removeFirst();
		asList.removeLast();
		targetPath = targetPath + "\\" + String.join("\\", asList) + "\\";
		File targetPathDirs = new File(targetPath);
		if(!targetPathDirs.exists()) {
			targetPathDirs.mkdirs();
		}
		
		File targetFile = new File(targetPath+sourceFile.getName());
		// 新建文件输入流并对它进行缓冲   
        FileInputStream input = new FileInputStream(sourceFile);  
        BufferedInputStream inBuff=new BufferedInputStream(input);  
  
        // 新建文件输出流并对它进行缓冲   
        FileOutputStream output = new FileOutputStream(targetFile);  
        BufferedOutputStream outBuff=new BufferedOutputStream(output);  
          
        // 缓冲数组   
        byte[] b = new byte[1024 * 5];  
        int len;  
        while ((len =inBuff.read(b)) != -1) {  
            outBuff.write(b, 0, len);  
        }  
        // 刷新此缓冲的输出流   
        outBuff.flush();  
          
        //关闭流   
        inBuff.close();  
        outBuff.close();  
        output.close();  
        input.close();  
	}
}
