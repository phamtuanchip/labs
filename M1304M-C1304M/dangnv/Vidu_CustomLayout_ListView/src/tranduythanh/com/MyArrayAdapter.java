package tranduythanh.com;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapter extends
				ArrayAdapter<Employee>
{
	Activity context=null;
	ArrayList<Employee>myArray=null;
	int layoutId;
	/**
	 * Constructor này dùng để khởi tạo các giá trị 
	 * từ MainActivity truyền vào
	 * @param context : là Activity từ Main
	 * @param layoutId: Là layout custom do ta tạo (my_item_layout.xml)
	 * @param arr : Danh sách nhân viên truyền từ Main
	 */
	public MyArrayAdapter(Activity context,
			int layoutId,
			ArrayList<Employee>arr){
		super(context, layoutId, arr);
		this.context=context;
		this.layoutId=layoutId;
		this.myArray=arr;
	}
	/**
	 * hàm dùng để custom layout, ta phải override lại hàm này
	 * từ MainActivity truyền vào
	 * @param position : là vị trí của phần tử trong danh sách nhân viên
	 * @param convertView: convertView, dùng nó để xử lý Item
	 * @param parent : Danh sách nhân viên truyền từ Main
	 * @return View: trả về chính convertView
	 */
	public View getView(int position, View convertView, 
			ViewGroup parent) {
		/**
		 * bạn chú ý là ở đây Tôi không làm:
		 * if(convertView==null)
		 * {
		 * 	LayoutInflater inflater=
		 *			context.getLayoutInflater();
		 *	convertView=inflater.inflate(layoutId, null);
		 * }
		 * Lý do là ta phải xử lý xóa phần tử Checked, nếu dùng If thì 
		 * nó lại checked cho các phần tử khác sau khi xóa vì convertView
		 * lưu lại trạng thái trước đó
		 */
		LayoutInflater inflater=
					context.getLayoutInflater();
			convertView=inflater.inflate(layoutId, null);
			//chỉ là test thôi, bạn có thể bỏ If đi
		if(myArray.size()>0 && position>=0)
		{
			//dòng lệnh lấy TextView ra để hiển thị Mã và tên lên
			final TextView txtdisplay=(TextView) 
					convertView.findViewById(R.id.txtitem);
			//lấy ra nhân viên thứ position
			final Employee emp=myArray.get(position);
			//đưa thông tin lên TextView
			//emp.toString() sẽ trả về Id và Name 
			txtdisplay.setText(emp.toString());
			//lấy ImageView ra để thiết lập hình ảnh cho đúng
			final ImageView imgitem=(ImageView) 
					convertView.findViewById(R.id.imgitem);
			//nếu là Nữ thì lấy hình con gái
			if(emp.isGender())
				imgitem.setImageResource(R.drawable.girlicon);
			else//nếu là Nam thì lấy hình con trai
				imgitem.setImageResource(R.drawable.boyicon );
		}
		//Vì View là Object là dạng tham chiếu đối tượng, nên
		//mọi sự thay đổi của các object bên trong convertView 
		//thì nó cũng biết sự thay đổi đó
		return convertView;//trả về View này, tức là trả luôn 
							//về các thông số mới mà ta vừa thay đổi
	}
}
