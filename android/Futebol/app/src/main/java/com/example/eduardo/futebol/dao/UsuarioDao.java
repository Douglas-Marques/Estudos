package com.example.eduardo.futebol.dao;

import android.content.Context;
import android.widget.Toast;

import com.example.eduardo.futebol.model.Usuario;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Eduardo on 21/08/2017.
 */

public class UsuarioDao {

    private Context context;

    public UsuarioDao(Context ctx){
        context = ctx;
    }

    public void  cadastrarUsuario(){
        Realm realm = Realm.getDefaultInstance();
        Usuario usuario = new Usuario("eduardo@eduardo", "eduardo", "Eduardo");
        realm.beginTransaction();
        RealmResults<Usuario> results = realm.where(Usuario.class).findAll();
        if(results.size() == 0){
            realm.copyToRealm(usuario);
            realm.commitTransaction();
        }
    }

    public boolean login(String email, String senha){
        Realm realm = Realm.getDefaultInstance();
        Usuario results = realm.where(Usuario.class).findFirst();
        return results.getEmail().equals(email) && results.getSenha().equals(senha);
    }

    public boolean verificaUsuario(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Usuario> results = realm.where(Usuario.class).findAll();
        return !results.isEmpty();
    }

    public Usuario getUsuario(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Usuario> results = realm.where(Usuario.class).findAll();
        if(results.size() > 0) {
            Usuario usuario = results.get(0);
            return usuario;
        }else{
            return null;
        }
    }
}
