package com.example..apple.hw9;   
import android.content.Intent; 
import android.database.sqlite.SQLiteDatabase;  import android.support.v7.app.AppCompatActivity;  import android.os.Bundle; 
import android.view.View; 
import android.widget.EditText; 
import android.widget.Toast; 
         import com.example.apple.hw9.MainActivity;
import com.example.apple.hw9.MyDBHandler;
import com.example.apple.hw9.R;

public class insTsk extends AppCompatActivity { 
    private EditText etName,etPhone;  private MyDBHandler dbHandler;  private SQLiteDatabase db;  @Override 
    protected void onCreate(Bundle savedInstanceState) {  super.onCreate(savedInstanceState);  setContentView(R.layout.activity_ins_tsk); 
        etName = (EditText)findViewById(R.id.edName); 
        etPhone = (EditText)findViewById(R.id.edPhon);  dbHandler = new MyDBHandler(getApplicationContext());  db = dbHandler.getWritableDatabase(); 
    } 
    public void addButtonClicked(View view){ 
        String naStr = etName.getText().toString();  String phStr = etPhone.getText().toString();  if(naStr.isEmpty() || phStr.isEmpty()){ 
            Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show(); 
   
            return;  } 
        db.execSQL("insert into "+ dbHandler.TABLE_NAME + "(" + dbHandler.COLUMN_NAME + ","+ dbHandler.COLUMN_PHONE +") " + 
                 
                "VALUES (?,?)", new String[] {naStr,phStr}); 
        String tstMsg; 
        tstMsg="Name:"+naStr+",Phone:"+phStr+ " is inserted";
        Toast.makeText(getApplicationContext(),tstMsg,Toast.LENGTH_LON G).show(); 
        etName.setText(""); 
        etPhone.setText("");  } 
    public void backTo(View view){ 
        Intent t = new Intent(this,MainActivity.class);  startActivity(t); 
        dbHandler.close(); 
        finish(); 
    }  }