package com.jylu.dao.mongo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.jylu.dao.mongo.BaseMongoDao;
import com.mongodb.WriteResult;

@Repository
public class BaseMongoDaoImpl<T> implements BaseMongoDao<T>{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public T save(T entity) {
		mongoTemplate.insert(entity);
		return entity;
	}

	@Override
	public T findById(String id) {
		return null;
	}

	@Override
	public T findById(String id, String collectionName) {
		return null;
	}

	@Override
	public List<T> findAll() {
		return null;
	}

	@Override
	public List<T> findAll(String collectionName) {
		return null;
	}

	@Override
	public List<T> find(Query query) {
		return null;
	}

	@Override
	public T findOne(Query query) {
		return null;
	}

	@Override
	public Page<T> findPage(Page<T> page, Query query) {
		return null;
	}

	@Override
	public long count(Query query) {
		return 0;
	}

	@Override
	public WriteResult update(Query query, Update update) {
		return null;
	}

	@Override
	public T updateOne(Query query, Update update) {
		return null;
	}

	@Override
	public WriteResult update(T entity) {
		return null;
	}

	@Override
	public void remove(Query query) {
		
	}

}
