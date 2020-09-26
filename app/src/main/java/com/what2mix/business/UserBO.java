package com.what2mix.business;

import android.content.Context;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuthException;
import com.what2mix.domain.User;
import com.what2mix.exception.DataInsufficientException;
import com.what2mix.persistence.UserDAO;

public class UserBO {



    public void register(String name, String email, String password) throws FirebaseAuthException, DataInsufficientException {
        System.out.println("==================================================================================");
        System.out.println("passou pelo register da business");
        System.out.println("==================================================================================");
        validateRegister(name, email, password);

        User user = new User(name, email, password);
        System.out.println("==================================================================================");
        System.out.println("Argumentos a ser passados: "+name+", " +email+", " +password);
        System.out.println("==================================================================================");

        UserDAO dao = new UserDAO();
        dao.signUp(email, password);
    }

    public User login(String email, String password) throws DataInsufficientException {
        validateLogin(email, password);
        return null;
    }

    private void validateRegister(String name, String email, String password) throws DataInsufficientException {
        if (name.equals(null) || name.trim().isEmpty()) {
            throw new DataInsufficientException("Nome não pode ser vazio !");
        }
        if (email.equals(null) || email.trim().isEmpty()) {
            throw new DataInsufficientException("E-mail não pode ser vazio !");
        }

        if (password.equals(null) || password.trim().isEmpty()) {
            throw new DataInsufficientException("Senha não pode ser vazia !");
        }
    }

    private void validateLogin(String email, String password) throws DataInsufficientException {

        if (password.equals(null) || password.trim().isEmpty()) {
            throw new DataInsufficientException("Senha não pode ser vazia !");
        }

        if (email.equals(null) || email.trim().isEmpty()) {
            throw new DataInsufficientException("E-mail não pode ser vazio !");
        }

    }

}
