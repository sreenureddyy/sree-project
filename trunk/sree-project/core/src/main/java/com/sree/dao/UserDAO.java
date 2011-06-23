package com.sree.dao;

import org.springframework.stereotype.Repository;

@Repository("userDAO")
@SuppressWarnings("unchecked")
public class UserDAO extends GenericDAO implements IUserDAO {

}
