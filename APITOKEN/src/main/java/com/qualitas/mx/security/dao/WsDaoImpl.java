package com.qualitas.mx.security.dao;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.qualitas.mx.security.model.UsuarioModel;
import com.qualitas.mx.security.model.Usuarios;

@Repository("WsDao")
public class WsDaoImpl extends SqlSessionDaoSupport implements WsDao {

	@Override
	public Usuarios getUsuario(String username) {
		return (Usuarios)getSqlSession().selectOne("getUsuario",username);
	}
	
	@Override
	public void insertUser (UsuarioModel usuario) {
		getSqlSession().insert("insertUser", usuario);
	}
	
}
