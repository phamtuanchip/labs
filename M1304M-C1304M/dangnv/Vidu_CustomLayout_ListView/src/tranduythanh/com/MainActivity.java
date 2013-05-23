package tranduythanh.com;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

	ArrayList<Employee>arrEmployee=new ArrayList<Employee>();
	//Sử dụng MyArrayAdapter thay thì ArrayAdapter
	MyArrayAdapter adapter=null;
	ListView lvNhanvien=null;
	
	Button btnNhap;
	ImageButton btnRemoveAll;
	EditText editMa,editTen;
	RadioGroup genderGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnNhap=(Button) findViewById(R.id.btnNhap);
		btnRemoveAll=(ImageButton) findViewById(R.id.btndelete);
		editMa=(EditText) findViewById(R.id.editMa);
		editTen=(EditText) findViewById(R.id.editTen);
		genderGroup=(RadioGroup) findViewById(R.id.radioGroup1);
		
		lvNhanvien=(ListView) findViewById(R.id.lvnhanvien);
		arrEmployee=new ArrayList<Employee>();
		//Khởi tạo đối tượng adapter và gán Data source
		adapter=new MyArrayAdapter(
				this,
				R.layout.my_item_layout,// lấy custom layout
				arrEmployee/*thiết lập data source*/);
		lvNhanvien.setAdapter(adapter);//gán Adapter vào Lisview
		
		btnNhap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				xulyNhap();
			}
		});
		btnRemoveAll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				xulyXoa();
			}
		});
	}
	//gọi hàm xử lý nhập thông tin nhân viên
	public void xulyNhap()
	{
		String ma=editMa.getText()+"";
		String ten=editTen.getText()+"";
		boolean gioitinh=false;//Nam =false
		if(genderGroup.getCheckedRadioButtonId()==R.id.radNu)
			gioitinh=true;
		//Tạo một employee
		Employee emp=new Employee();
		emp.setId(ma);
		emp.setName(ten);
		emp.setGender(gioitinh);
		//Đưa vào danh sách
		arrEmployee.add(emp);
		//gọi hàm cập nhật giao diện
		adapter.notifyDataSetChanged();
		//Sau khi update thì xóa trắng dữ liệu và cho editma focus
		editMa.setText("");
		editTen.setText("");
		editMa.requestFocus();
	}
	//hàm xử lý xóa
	public void xulyXoa()
	{
		//ta nên đi ngược danh sách, kiểm tra phần tử nào checked
		//thì xóa đúng vị trí đó ra khỏi arrEmployee
		for(int i=lvNhanvien.getChildCount()-1;i>=0;i--)
		{
			//lấy ra dòng thứ i trong ListView
			//Dòng thứ i sẽ có 3 phần tử: ImageView, TextView, Checkbox
			View v=lvNhanvien.getChildAt(i);
			//Ta chỉ lấy CheckBox ra kiểm tra
			CheckBox chk=(CheckBox) v.findViewById(R.id.chkitem);
			//Nếu nó Checked thì xóa ra khỏi arrEmployee
			if(chk.isChecked())
			{
				//xóa phần tử thứ i ra khỏi danh sách
				arrEmployee.remove(i);
			}
		}
		//Sau khi xóa xong thì gọi update giao diện
		adapter.notifyDataSetChanged();
	}
}
