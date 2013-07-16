package com.example.cc_android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.cc_android.db.FuncionarioDB;
import com.example.cc_android.pojo.Funcionario;

public class MainActivity extends Activity {

	ListView listView;
	FuncionarioDB funcionarioDB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadTelaPrincipal();
	}

	@SuppressLint("NewApi")
	public void loadTelaPrincipal() {

		//
		setContentView(R.layout.activity_main);

		funcionarioDB = new FuncionarioDB(this);
		funcionarioDB.open();
		
		// Definir layout das linhas do listView
		String[] fromColumns = {"nome", "salario"};
		int[] toViews = {android.R.id.text1, android.R.id.text2};

		// recuperar o Listview da página
		listView = (ListView) findViewById(R.id.list);

		// montar o adapter, reponsável por alimentar o listView
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_2,
				funcionarioDB.getAll(),
				fromColumns, toViews, 0);
		
		// setar o adapter
		listView.setAdapter(adapter);

		// setar ação do click da linha do listView
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// abrir novo.xml
				setContentView(R.layout.novo);

				//buscar o funcionario selecionado
				Cursor cursorFunc = funcionarioDB.getById(id);
				
				//recuperar editText código
				EditText codigo = (EditText) findViewById(R.id.editTextCodigo);
				codigo.setText("" + Integer.toString(cursorFunc.getInt(0)),
						TextView.BufferType.EDITABLE);
				
				//recuperar editText nome
				EditText nome = (EditText) findViewById(R.id.editTextNome);
				nome.setText("" + cursorFunc.getString(1),
						TextView.BufferType.EDITABLE);

				//recuperar editText salário
				EditText salario = (EditText) findViewById(R.id.editTextSalario);
				salario.setText("" + cursorFunc.getDouble(2),
						TextView.BufferType.EDITABLE);

				//recuperar button salvar
				Button btSalvar = (Button) findViewById(R.id.bt_salvar);
				
				//setar evento de click do button.
				btSalvar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						Funcionario funcionario = new Funcionario();

						EditText codigo = (EditText) findViewById(R.id.editTextCodigo);
						funcionario.setId(Integer.parseInt(codigo.getText()
								.toString()));

						EditText nome = (EditText) findViewById(R.id.editTextNome);
						funcionario.setNome(nome.getText().toString());

						EditText salario = (EditText) findViewById(R.id.editTextSalario);
						funcionario.setSalario(Double.parseDouble(salario
								.getText().toString()));
						
						funcionarioDB.update(funcionario.getId(), funcionario.getNome(), funcionario.getSalario());

						loadTelaPrincipal();
					}
				});

				Button btCancelar = (Button) findViewById(R.id.bt_cancelar);
				btCancelar.setOnClickListener(new View.OnClickListener() {
					public void onClick(View arg0) {
						loadTelaPrincipal();
					}
				});
			}
		});

		Button btAdicionarNovo = (Button) findViewById(R.id.bt_adicionar_novo);
		btAdicionarNovo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				loadTelaAddFuncionario();
			}
		});
	}

	public void loadTelaAddFuncionario() {
		setContentView(R.layout.novo);

		Button btSalvar = (Button) findViewById(R.id.bt_salvar);
		btSalvar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Funcionario funcionario = new Funcionario();

				EditText codigo = (EditText) findViewById(R.id.editTextCodigo);
				funcionario.setId(Integer.parseInt(codigo.getText().toString()));

				EditText nome = (EditText) findViewById(R.id.editTextNome);
				funcionario.setNome(nome.getText().toString());

				EditText salario = (EditText) findViewById(R.id.editTextSalario);
				funcionario.setSalario(Double.parseDouble(salario.getText()
						.toString()));
				
				funcionarioDB.insert(funcionario);

				loadTelaPrincipal();
			}
		});

		Button btCancelar = (Button) findViewById(R.id.bt_cancelar);
		btCancelar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				loadTelaPrincipal();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
