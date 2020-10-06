package com.what2mix.business;

import com.what2mix.domain.User;
import com.what2mix.exception.InputEmailException;
import com.what2mix.exception.InputNameException;
import com.what2mix.exception.InputPasswordException;
import com.what2mix.persistence.UserDAO;

public class UserBO {

    private UserDAO dao = new UserDAO();

    public void register(User user) {

        dao.writeNewUser(user);

    }

    // FIXME Pensar melhor na arquitetura (deixar para final)
//    public Boolean login(String email, String password) throws InputNameException, InputPasswordException {
//
//        return true;
//    }

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

        User user = new User(name, email, password);

        return user;
    }

    public User validateLogin(String email, String password) throws InputNameException, InputPasswordException {

        if (password.equals(null) || password.trim().isEmpty()) {
            throw new InputPasswordException("Senha não pode ser vazia!");
        }

        if (email.equals(null) || email.trim().isEmpty()) {
            throw new InputNameException("E-mail não pode ser vazio!");
        }

        User user = new User(email, password);
        return user;
    }

    public User getLoggedUser(String email){

        User user = dao.findByEmail(email);

        return user;
    }

}
