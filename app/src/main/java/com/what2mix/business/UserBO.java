package com.what2mix.business;

import com.what2mix.domain.User;
import com.what2mix.exception.InputEmailException;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputPasswordException;

public class UserBO {



    public User register(String name, String email, String password) throws InputNameException, InputEmailException, InputPasswordException {
        validateRegister(name, email, password);
        User user = new User(name, email, password);
        return user;
    }

    public boolean login(String email, String password) throws InputNameException, InputPasswordException {
        validateLogin(email, password);
        return true;
    }

    private void validateRegister(String name, String email, String password) throws InputNameException, InputEmailException, InputPasswordException {
        if (name.equals(null) || name.trim().isEmpty()) {
            throw new InputNameException("Nome não pode ser vazio!");
        }
        if (email.equals(null) || email.trim().isEmpty()) {
            throw new InputEmailException("E-mail não pode ser vazio!");
        }

        if (password.equals(null) || password.trim().isEmpty()) {
            throw new InputPasswordException("Senha não pode ser vazia!");
        }
    }

    private void validateLogin(String email, String password) throws InputNameException, InputPasswordException {

        if (password.equals(null) || password.trim().isEmpty()) {
            throw new InputPasswordException("Senha não pode ser vazia!");
        }

        if (email.equals(null) || email.trim().isEmpty()) {
            throw new InputNameException("E-mail não pode ser vazio!");
        }

    }

}
