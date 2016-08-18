package facci.com.sqlitemd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityMD extends AppCompatActivity {
    DBHelper dbSQLite;
    EditText txtNombre,txtApellido,txtSemestre;
    Button btnInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_md);
        dbSQLite=new DBHelper(this);

    }
    public void insertarClick (View v){
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtApellido=(EditText)findViewById(R.id.txtApellido);
        txtSemestre= (EditText)findViewById(R.id.txtSemestre);

        boolean estaInsertado = dbSQLite.insertar(txtNombre.getText().toString(),txtApellido.getText().toString(),Integer.parseInt(txtSemestre.getText().toString()));
        if (estaInsertado)
            Toast.makeText(MainActivityMD.this,"Datos Ingresados",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivityMD.this,"Lo sentimos ocurrió un error ",Toast.LENGTH_SHORT).show();

    }
    public void verTodosclick(View v){
        Cursor res = dbSQLite.selectVerTodos();
        if (res.getCount()== 0){
            mostrarMensaje("Error","No se encontraron registros");
            return;
        }
        StringBuffer buffer= new StringBuffer( );
        while (res.moveToNext()){
            buffer.append("Id :"+res.getString(0)+"/n");
            buffer.append("Nombre :"+res.getString(1)+"/n");
            buffer.append("Apellido :"+res.getString(2)+"/n");
            buffer.append("Semestre :"+res.getInt(3)+"/n/n");

        }
        mostrarMensaje("Registro",buffer.toString());
    }
    public void mostrarMensaje(String titulo,String Mensaje){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(titulo);
        builder.setMessage(Mensaje);
        builder.show();
    }
}
