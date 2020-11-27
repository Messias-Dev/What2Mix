package com.what2mix.business;

import com.what2mix.domain.User;
import com.what2mix.exception.InputEmailException;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputPasswordException;
import com.what2mix.persistence.UserDAO;

public class UserBO {

    // Referência a persistência
    private UserDAO dao = new UserDAO();

    // Registra usuário via persistência
    public void register(User user) {

        dao.writeNewUser(user);

    }

    // Valida parâmetros para criação do usuário
    public User validateRegister(String name, String email, String password) throws InputNameException, InputEmailException, InputPasswordException {
        if (name.equals(null) || name.trim().isEmpty()) {
            throw new InputNameException("Nome não pode ser vazio!");
        }
        if (email.equals(null) || email.trim().isEmpty()) {
            throw new InputEmailException("E-mail não pode ser vazio!");
        }

        if (password.equals(null) || password.trim().isEmpty()) {
            throw new InputPasswordException("Senha não pode ser vazia!");
        }

        // Capitaliza string anterior e atribui a string nova
        String nameCapitalized = capitalizeName(name);

        // Cria objeto
        User user = new User(nameCapitalized, email, password);

        return user;
    }

    // Capitaliza nome de usuário
    private String capitalizeName(String name) {
        String output = "";

        String[] textArray = name.toLowerCase().trim().split("\\s+");

        for (int i = 0; i < textArray.length; i++) {
            textArray[i] = textArray[i].substring(0, 1).toUpperCase() + textArray[i].substring(1);
        }

        for (int i = 0; i < textArray.length; i++) {
            output = output + textArray[i] + " ";
        }

        return output.trim();
    }

    // Valida parâmetros para login do usuário
    public User validateLogin(String email, String password) throws InputNameException, InputPasswordException {

        if (password.equals(null) || password.trim().isEmpty()) {
            throw new InputPasswordException("Senha não pode ser vazia!");
        }

        if (email.equals(null) || email.trim().isEmpty()) {
            throw new InputNameException("E-mail não pode ser vazio!");
        }

        // Cria objeto
        User user = new User(email, password);

        return user;
    }

    // Pega usuário por e-mail via persistência
    public User getUserByEmail(String email){

        User user = dao.findByEmail(email);

        return user;
    }

}
