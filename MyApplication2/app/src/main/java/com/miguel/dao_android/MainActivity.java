package com.miguel.dao_android;

import android.content.Context;
import android.support.v4.util.CircularArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.ContactoDAO;
import com.miguel.dao_android.Domain_Layer.Contacto;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText et_nombre;
    EditText et_correo;
    EditText et_salario;
    Button guardar;
    Button borrar;
    Button actualizar;
    Button adelante;
    Button atras;
    Button salarios;
    List<Contacto> contactos;
    Contacto current;
    int currentPos = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //definir los controles que estan en el main.xml

        et_nombre = (EditText) findViewById(R.id.et1);
        et_correo = (EditText) findViewById(R.id.et2);
        et_salario = (EditText) findViewById(R.id.et3);

        guardar = (Button) findViewById(R.id.bt_insert);
        borrar = (Button) findViewById(R.id.bt_delete);
        actualizar = (Button) findViewById(R.id.bt_update);

        adelante = (Button) findViewById(R.id.bt_adelante);
        atras = (Button) findViewById(R.id.bt_atras);
        salarios = (Button) findViewById(R.id.suma);

        cargarDatos();

        guardar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCurrent();
            }
        });

        borrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarCurrent();
            }
        });

        actualizar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos();
            }
        });

        adelante.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                avanzar();
            }
        });

        atras.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                atras();
            }
        });

        salarios.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sumatoriaSalarios();
            }
        });

    }

    private void sumatoriaSalarios() {
        float salario = 0;
        for (Contacto item : this.contactos) {
            salario += item.getSalario();
        }
        Toast.makeText(this, String.format("Salario total: %.2f", salario), Toast.LENGTH_LONG).show();
    }

    private void avanzar() {
        if (this.currentPos + 1 >= this.contactos.size()) {
            this.currentPos = 0;
        } else {
            this.currentPos++;
        }
        this.current = this.contactos.get(this.currentPos);
        displayContacto(this.current);
    }

    private void atras() {
        if (this.currentPos - 1 < 0) {
            this.currentPos = this.contactos.size() - 1;
        } else {
            this.currentPos--;
        }
        this.current = this.contactos.get(this.currentPos);
        displayContacto(this.current);
    }

    private void borrarCurrent() {
        if (this.current != null && this.current.getid() > 0) {
            ContactoDAO.getInstance().setContext(this).delete(this.current.getid());
            this.contactos.remove(this.contactos);
            this.currentPos--;
            this.current = this.contactos.get(currentPos);
            displayContacto(this.current);
        } else {
            Toast.makeText(this, "El Contacto actual no está guardado!", Toast.LENGTH_LONG).show();
        }
    }

    private void saveCurrent() {
        if (this.current != null && this.current.isValid()) {
            if (ContactoDAO.getInstance().setContext(this).create(this.current)) {
                this.contactos.add(current);
                this.currentPos = this.contactos.size() - 1;
                displayContacto(this.current);
            }
        } else {
            Toast.makeText(this, "Introduzca los datos correctamente!", Toast.LENGTH_LONG).show();
        }
    }

    private void displayContacto(Contacto current) {
        this.et_nombre.setText(current.getusername());
        this.et_salario.setText(String.valueOf(current.getSalario()));
        this.et_correo.setText(current.getemail());
    }

    private void cargarDatos() {
        this.contactos = ContactoDAO.getInstance().setContext(this).readALL(null);
        if (this.contactos.size() > 0) {
            this.current = this.contactos.get(this.contactos.size() - 1);
            this.currentPos = this.contactos.size() - 1;
            displayContacto(this.current);
        } else {
            this.current = new Contacto();
        }
    }
}
