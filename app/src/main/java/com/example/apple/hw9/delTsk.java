package com.example.apple.hw9;   
import android.annotation.SuppressLint; 
import android.database.Cursor; 
import android.content.Intent; 
import android.database.sqlite.SQLiteDatabase;  import android.os.Bundle; 
import android.support.v7.app.AppCompatActivity;  import android.view.View; 
import android.widget.EditText; 
import android.widget.TextView; 
import android.widget.Toast;   
public class delTsk extends AppCompatActivity { 
         
    private EditText etID; 
    private TextView naTxtView,phTxtView;  private MyDBHandler dbHandler;  SQLiteDatabase database; 
               
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);  setContentView(R.layout.activity_del_tsk); 
        etID = (EditText)findViewById(R.id.idInp); 
        naTxtView = (TextView)findViewById(R.id.naTxtView);  phTxtView = (TextView)findViewById(R.id.phTxtView);  dbHandler = new MyDBHandler(this); 
        dbHandler = new MyDBHandler(getApplicationContext());  database = dbHandler.getWritableDatabase(); 
    } 
    public void shwIDInfo(View V){ 
        String id = etID.getText().toString();  if(id.isEmpty()){ 
            Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show(); 
            return;  } 
        String sqltStmt = "Select * from " + dbHandler.TABLE_NAME + " where " + dbHandler.COLUMN_RECID + "
                = ?"; 
        Cursor c = database.rawQuery(sqltStmt, new String[]
                {id}); 
        if(!c.moveToFirst()){ 
            Toast.makeText(getApplicationContext(),"No ID has matched",Toast.LENGTH_LONG).show(); 
            return;  } 
        naTxtView.setText(c.getString(1)); phTxtView.setText(c.getString(2)); c.close(); 
    } 
    public void delTsk(View view){ 
        String id = etID.getText().toString();  if(id.isEmpty()){ 
            Toast.makeText(getApplicationContext(),"Please, fill-in missing data",Toast.LENGTH_LONG).show(); 
 
            return;  } 
        Cursor c= database.rawQuery("SELECT * FROM "+ dbHandler.TABLE_NAME + " where " + dbHandler.COLUMN_RECID + "
                = ?" , 
        database.execSQL(" delete from " + dbHandler.TABLE_NAME + " where " + dbHandler.COLUMN_RECID + " = ?" , new String[] {id}); 
        String tstMsg; 
        tstMsg = "Id: " + id + " is deleted"; 
        Toast.makeText(getApplicationContext(),tstMsg,Toast.LENGTH_LON
                G).show(); 
        etID.setText("");  } 
            else {  Toast.makeText(getApplicationContext(),"Invalid
            ID",Toast.LENGTH_LONG).show(); 
    }  } 
public void backTo(View view){ 
        Intent t = new Intent(this,MainActivity.class);  startActivity(t); 
        dbHandler.close(); 
        finish(); 
        }  }