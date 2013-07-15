package com.example.cc_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadTelaPrincipal();
    }

    public void loadTelaPrincipal() {
    	
    	setContentView(R.layout.activity_main);
    	
    	Button btAdicionarNovo = (Button)findViewById(R.id.bt_adicionar_novo);
    	btAdicionarNovo.setOnClickListener(new View.OnClickListener(){
    		public void onClick(View arg0){
    			loadTelaAddFuncionario();
    		}
    	});
    }
    
    public void loadTelaAddFuncionario() {
    	setContentView(R.layout.novo);
    	
    	Button btCancelar = (Button) findViewById(R.id.bt_cancelar); 
    	btCancelar.setOnClickListener(new View.OnClickListener(){
    		public void onClick(View arg0){
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
    
    public void clickButton() {
    	
    }
    
}
