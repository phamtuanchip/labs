package layout;

/*<applet code= MyGridBag width = 500 height = 500>
</applet>
*/
import java.awt.*;
import java.applet.Applet;
public class MyGridBag extends Applet
{
	TextArea ObjTa;
	TextField ObjTf;
	Button butta, buttf;
	CheckboxGroup cbg;
	Checkbox cbbold,cbitalic,cbplain,cbboth;
	GridBagLayout gb;
	GridBagConstraints gbc;
	public void init()
	{
		gb = new GridBagLayout();
		setLayout(gb);
		gbc = new GridBagConstraints();
		ObjTa = new TextArea("Textarea ",5,10);
		ObjTf = new TextField("enter your name");
		butta = new Button("TextArea");
		buttf = new Button("TextField"); 
		cbg = new CheckboxGroup();
		cbbold = new Checkbox("Bold",cbg,false);
		cbitalic = new Checkbox("Italic",cbg,false);
		cbplain = new Checkbox("Plain",cbg,false);
		cbboth = new Checkbox("Bold/Italic",cbg,true);
		gbc.fill = GridBagConstraints.BOTH;
		addComponent(ObjTa,0,0,4,1);	
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(butta,0,1,1,1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(buttf,0,2,1,1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(cbbold,2,1,1,1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(cbitalic,2,2,1,1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(cbplain,3,1,1,1); 
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(cbboth,3,2,1,1);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		addComponent(ObjTf,4,0,1,3);
	}
	public void addComponent(Component comp, int row, int col, int   nrow, int ncol)
	{
		gbc.gridx = col;
		gbc.gridy = row;
		
		gbc.gridwidth = ncol;
		gbc.gridheight = nrow;
		
		gb.setConstraints(comp,gbc);
		add(comp);
	}
}


